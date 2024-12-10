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

import patterns.dental.clinic.controller.LanguageController;
import patterns.dental.clinic.model.user.Operations;

import java.util.Arrays;
import java.util.List;

public class AdminCreateDentistController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Label PaswordLabel;

    @FXML
    private Label bdLabel;

    @FXML
    private Button createButton;

    @FXML
    private TextField dobTextField;

    @FXML
    private Label firstNameLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Button homeButton;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Label operationsLAbel;

    @FXML
    private VBox operationsListBox;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button previousButton;

    @FXML
    private TextField specialityTextField;

    @FXML
    private Label specialtyLabel;


    private final ListView<CheckBox> operationsListView = new ListView<>();

    /**
     * Method to initialize all component texts for internalization
     */
    @FXML
    public void initialize() {
        PaswordLabel.setText(LanguageController.getText("Password"));
        bdLabel.setText(LanguageController.getText("DateOfBirth"));
        firstNameLabel.setText(LanguageController.getText("FirstName"));
        lastNameLabel.setText(LanguageController.getText("LastName"));
        operationsLAbel.setText(LanguageController.getText("Operations"));
        specialtyLabel.setText(LanguageController.getText("Specialty"));

        createButton.setText(LanguageController.getText("CreateBtn"));
        homeButton.setText(LanguageController.getText("HomeBtn"));
        previousButton.setText(LanguageController.getText("PreviousBtn"));

        for (Operations operation : Operations.values()) {
            CheckBox checkBox = new CheckBox(operation.name());
            operationsListView.getItems().add(checkBox);
        }
        operationsListBox.getChildren().add(operationsListView);
    }

    /**
     * Method that creates Bill upon clicking createButton
     * Takes first name, last name, password, date of birth, and selected operations from respective text fields and choicebox
     *
     * @param event
     */
    @FXML
    void createButtonClick(ActionEvent event) {
        if (firstNameTextField.getText() != null && lastNameTextField.getText() != null && passwordTextField.getText() != null
                && dobTextField.getText() != null) {
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

            boolean createdState = controller.createDentist(firstName, lastName, password, dateOfBirth, allowedOperations, specialty);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Dentist Creation");

            if (createdState) {
                alert.setHeaderText("Dentist created successfully");
                alert.setContentText("You have created a dentist successfully");
            } else {
                alert.setHeaderText("Dentist NOT created successfully");
                alert.setContentText("The system was unable to create the dentist, check the information and try again!");
            }
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dentist Creating Error");
            alert.setHeaderText("Cannot Load Patient Id");
            alert.setContentText("Patient Id Cannot be Empty, Must Contain Data to View Bill(s)");
            alert.showAndWait();
        }
    }

    /**
     * Method to take user to home page when clicking home button
     *
     * @param ae
     */
    @FXML
    public void homeButtonClick(ActionEvent ae) {
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

    /**
     * Method to take user to previous page when clicking previous button
     *
     * @param ae
     */
    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

}
