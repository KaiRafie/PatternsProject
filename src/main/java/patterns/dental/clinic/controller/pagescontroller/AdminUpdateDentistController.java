package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.model.user.Operations;

import java.util.Arrays;
import java.util.List;

public class AdminUpdateDentistController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private TextField dentistIdTextField;

    @FXML
    private TextField dobTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Button homeButton;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ComboBox<Operations> operationsComboBox;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button previousButton;

    @FXML
    private TextField specialityTextField;

    @FXML
    private VBox operationsListBox;

    private final ListView<CheckBox> operationsListView = new ListView<>();

    @FXML
    private Button updateButton;

    @FXML
    public void initialize() {
        for (Operations operation : Operations.values()) {
            CheckBox checkBox = new CheckBox(operation.name());
            operationsListView.getItems().add(checkBox);
        }
        operationsListBox.getChildren().add(operationsListView);
    }

    @FXML
    public void homeButtonClick(ActionEvent ae){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/MainPage.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) homeButton.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Dental Management System");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

    @FXML
    void updateButtonClick(ActionEvent event) {
        int id = Integer.parseInt(dentistIdTextField.getText());
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String password = passwordTextField.getText();
        String dateOfBirth = dobTextField.getText();
        StringBuilder selectedOperations = new StringBuilder();
        for (CheckBox checkBox : operationsListView.getItems()) {
            if (checkBox.isSelected()) {
                selectedOperations.append(checkBox.getText()).append(", ");
            }
        }
        List<String> allowedOperations = Arrays.stream(selectedOperations.toString()
                .split(", ")).toList();
        String specialty = specialityTextField.getText();

        boolean updateState = controller.updateDentist(id, firstName, lastName, dateOfBirth, allowedOperations,
                specialty, password);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Dentist Update");

        if (updateState) {
            alert.setHeaderText("Dentist Updated successfully");
            alert.setContentText("You have Updated a Dentist account successfully");
        } else {
            alert.setHeaderText("Dentist NOT Updated successfully");
            alert.setContentText("The system was unable to Update the Dentist account, check the information and try again!");
        }

        alert.showAndWait();
    }

}
