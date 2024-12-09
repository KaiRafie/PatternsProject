package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import patterns.dental.clinic.model.user.Operations;
import java.util.Arrays;
import java.util.List;

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
    private TextField passwordTextField;

    @FXML
    private Button previousButton;

    @FXML
    private VBox operationsListBox;

    @FXML
    private TextField specialityTextField;


    private final ListView<CheckBox> operationsListView = new ListView<>();

    @FXML
    public void initialize() {
        for (Operations operation : Operations.values()) {
            CheckBox checkBox = new CheckBox(operation.name());
            operationsListView.getItems().add(checkBox);
        }
        operationsListBox.getChildren().add(operationsListView);
    }

    @FXML
    void createButtonClick(ActionEvent event) {
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
        System.out.println(allowedOperations);;
        String specialty = specialityTextField.getText();
    }

    @FXML
    public void homeButtonClick(ActionEvent ae){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/MainPage.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

}
