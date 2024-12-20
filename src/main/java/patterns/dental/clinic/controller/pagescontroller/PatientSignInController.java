package patterns.dental.clinic.controller.pagescontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.LanguageController;
import patterns.dental.clinic.model.ClinicSystem;
import patterns.dental.clinic.model.user.User;

import java.io.IOException;


public class PatientSignInController {
    ClinicSystemController controller = new ClinicSystemController();
    ClinicSystem clinicSystem = ClinicSystem.getInstance();

    @FXML
    private Button homeButton;

    @FXML
    private Button logInButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button previousButton;

    @FXML
    private Label signInLabel;

    @FXML
    private TextField userIdField;

    @FXML
    private Label patientSignInLabel;

    /**
     * Method to initialize all component texts for internalization
     */
    @FXML
    private void initialize() {
        homeButton.setText(LanguageController.getText("HomeBtn"));
        logInButton.setText(LanguageController.getText("LoginBtn"));
        previousButton.setText(LanguageController.getText("PreviousBtn"));
        signInLabel.setText(LanguageController.getText("SignInLabel"));
        patientSignInLabel.setText(LanguageController.getText("PatientSginInLabel"));
    }

    /**
     * Method to take user to home page when clicking home button
     *
     * @param ae
     */
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

    /**
     * Method to take user to previous page
     *
     * @param ae
     */
    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

    /**
     * Method to authenticate user log in
     * takes user Id from userIdField
     * takes password from passwordField
     * @param ae
     */
    @FXML
    public void authenticateButton(ActionEvent ae) {
        long userId = Integer.parseInt(userIdField.getText());
        String password = passwordField.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Patient Authentication");

        for (int i = 0; i < clinicSystem.getPatientsList().size(); i++) {
            User patient = clinicSystem.getPatientsList().get(i);
            if (patient.getUserID() == userId) {
                if (password.equals(patient.getLoginPass())) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                "/fxml/PatientPage.fxml"));
                        AnchorPane root = loader.load();

                        Scene scene = new Scene(root);

                        NavigationManager.getInstance().navigateTo(scene);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    alert.setHeaderText("Authentication Failed");
                    alert.setContentText("Incorrect Password and User Id combination!");

                    alert.showAndWait();
                }
            }
        }
    }
}
