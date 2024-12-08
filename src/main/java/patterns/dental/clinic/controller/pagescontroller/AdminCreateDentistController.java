package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.dental.clinic.model.user.Operations;

public class AdminCreateDentistController {

    @FXML
    private Button createButton;

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
    public void initialize() {
        // Load enum values into the ComboBox
        operationsComboBox.getItems().setAll(Operations.values());

        // Optionally, set a default value
        operationsComboBox.setValue(Operations.ALL_OPERATIONS); // Replace with a valid default if needed
    }

    @FXML
    void createButtonClick(ActionEvent event) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String password = passwordTextField.getText();
        String dateOfBirth = dobTextField.getText();
        Operations operation = operationsComboBox.getValue();
        String specialty = specialityTextField.getText();
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

}
