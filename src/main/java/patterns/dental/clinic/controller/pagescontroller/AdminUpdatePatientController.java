package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.LanguageController;

public class AdminUpdatePatientController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Label dobLabel;

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
    private Label passwordLabel;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label patientIdLabel;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private Button previousButton;

    @FXML
    private Button updateButton;

    @FXML
    private void initialize() {
        dobLabel.setText(LanguageController.getText("DateOfBirth"));
        firstNameLabel.setText(LanguageController.getText("FirstName"));
        homeButton.setText(LanguageController.getText("HomeBtn"));
        lastNameLabel.setText(LanguageController.getText("LastName"));
        passwordLabel.setText(LanguageController.getText("Password"));
        patientIdLabel.setText(LanguageController.getText("PatientId"));
        previousButton.setText(LanguageController.getText("PreviousBtn"));
        updateButton.setText(LanguageController.getText("UpdateBtn"));
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

    @FXML
    void updateButtonClick(ActionEvent event) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        int id = Integer.parseInt(patientIdTextField.getText());
        String password = passwordTextField.getText();
        String dob = dobTextField.getText();

        boolean updateState = controller.updatePatient(id, firstName, lastName, dob, password);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Patient Update");

        if (updateState) {
            alert.setHeaderText("Patient Updated successfully");
            alert.setContentText("You have Updated a Patient account successfully");
        } else {
            alert.setHeaderText("Patient NOT Updated successfully");
            alert.setContentText("The system was unable to Update the Patient account, check the information and try again!");
        }
    }
}

