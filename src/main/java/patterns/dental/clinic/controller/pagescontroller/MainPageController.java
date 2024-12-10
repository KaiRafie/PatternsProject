package patterns.dental.clinic.controller.pagescontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.LanguageController;
import patterns.dental.clinic.memento.ClinicSystemMemento;
import patterns.dental.clinic.model.ClinicSystem;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainPageController {
    ClinicSystemController controller = new ClinicSystemController();
    ClinicSystem clinicSystem = ClinicSystem.getInstance();

    @FXML
    private TextField adminKeyTextField;

    @FXML
    private Button englishBtn;

    @FXML
    private Button frenchBtn;

    @FXML
    private Button dentistLogInButton;

    @FXML
    private Button patientLogInButton;

    @FXML
    private Button adminPageButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button restoreButton;

    @FXML
    private Label signInLabel;

    @FXML
    private Label adminKeyLabel;

    /**
     * Method to change locale to english
     *
     * @param event
     */
    @FXML
    void changeToEnglish(ActionEvent event) {
        LanguageController.setLocale("en", "CA");
        updateUI();
    }

    /**
     * Method to change locale to french
     *
     * @param event
     */
    @FXML
    void changeToFrench(ActionEvent event) {
        LanguageController.setLocale("fr", "CA");
        updateUI();
    }

    /**
     * Method to update user interface elements depending on locale language
     */
    private void updateUI() {
        dentistLogInButton.setText(LanguageController.getText("AsDentist"));
        patientLogInButton.setText(LanguageController.getText("AsPatientBtn"));
        adminPageButton.setText(LanguageController.getText("AdminPageBtn"));
        signInLabel.setText(LanguageController.getText("SignInLabel"));
        saveButton.setText(LanguageController.getText("SaveBtn"));
        restoreButton.setText(LanguageController.getText("RestoreBtn"));
        adminKeyLabel.setText(LanguageController.getText("AdminKeyLabel"));
    }

    /**
     * Method to take user to patient log in page
     *
     * @param ae
     */
    @FXML
    public void patientLogInButtonClick(ActionEvent ae) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PatientSignIn.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to take user to dentist log in page
     *
     * @param ae
     */
    @FXML
    public void dentistLogInButtonClick(ActionEvent ae) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/DentistSignIn.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that checks admin key and if correct sends user to admin page
     * takes admin key from adminKeyTextField
     *
     * @param event
     */
    @FXML
    public void adminPageButtonClick(ActionEvent event) {
        if (adminKeyTextField.getText().equals("12345678")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/fxml/AdminPage.fxml"));
                AnchorPane root = loader.load();

                Scene scene = new Scene(root);

                NavigationManager.getInstance().navigateTo(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Admin Authentication");

            alert.setHeaderText("Authentication Failed");
            alert.setContentText("You have entered a wrong admin key, please try again!");

            alert.showAndWait();
        }
    }

    /**
     * Method to restore system to previous version
     * Calls ClinicSystemController restore system method
     *
     * @param ae
     */
    @FXML
    public void restoreSystem(ActionEvent ae) {
        ClinicSystemController controller = new ClinicSystemController();
        try {
            controller.restoreSystemHistory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to save system from current version
     * Calls ClinicSystemController save system method
     *
     * @param ae
     */
    @FXML
    public void saveSystem(ActionEvent ae) {
        ClinicSystemController controller = new ClinicSystemController();
        controller.saveSystemHistory();
    }
}