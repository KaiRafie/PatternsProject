package patterns.dental.clinic.controller.pagescontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.model.ClinicSystem;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.User;
import patterns.dental.clinic.model.visit.Visit;

public class AdminPageController {
    ClinicSystemController controller = new ClinicSystemController();
    ClinicSystem clinicSystem = ClinicSystem.getInstance();

    @FXML
    private Button createBillButton;

    @FXML
    private Button createDentistButton;

    @FXML
    private Button deleteVisitButton;

    @FXML
    private TextField dentistIdTextField;

    @FXML
    private Button homeButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button removeDentistButton;

    @FXML
    private Button removePatientButton;

    @FXML
    private Button updatePatientButton;

    @FXML
    private Button createPatientButton;

    @FXML
    private Button updateBillButton;

    @FXML
    private Button updateDentistButton;

    @FXML
    private Button removeBillButton;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private TextField billIdTextField;

    @FXML
    private TextField deleteVisitButtonIdTextField;

    @FXML
    void createBillButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AdminCreateBill.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createDentistButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AdminCreateDentist.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteVisitButtonClick(ActionEvent event) {
        int id = Integer.parseInt(deleteVisitButtonIdTextField.getText());

         Visit visit = controller.removeVisit(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Visit Deletion");

        if (!clinicSystem.getVisitsList().contains(visit)) {
            alert.setHeaderText("Visit deleted successfully");
            alert.setContentText("You have deleted a visit successfully");
        } else {
            alert.setHeaderText("Visit NOT deleted successfully");
            alert.setContentText("The system was unable to deleted the Visit, check the Visit ID and try again!");
        }
        alert.showAndWait();
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
    void removeDentistButtonClick(ActionEvent event) {
        int id = Integer.parseInt(dentistIdTextField.getText());

        Dentist dentist = controller.removeDentist(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Dentist Deletion");

        if (!clinicSystem.getDentistsList().contains(dentist)) {
            alert.setHeaderText("Dentist account deleted successfully");
            alert.setContentText("You have deleted a dentist account successfully");
        } else {
            alert.setHeaderText("Dentist NOT deleted successfully");
            alert.setContentText("The system was unable to deleted the dentist account, check the dentist ID and try again!");
        }
        alert.showAndWait();
    }

    @FXML
    void updatePatientButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AdminUpdatePatient.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removePatientButtonClick(ActionEvent event) {
        int id = Integer.parseInt(patientIdTextField.getText());

        User patient = controller.removePatient(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Patient Deletion");

        if (!clinicSystem.getPatientsList().contains(patient)) {
            alert.setHeaderText("Patient account deleted successfully");
            alert.setContentText("You have deleted a patient account successfully");
        } else {
            alert.setHeaderText("Patient NOT deleted successfully");
            alert.setContentText("The system was unable to deleted the patient account, check the patient ID and try again!");
        }
        alert.showAndWait();
    }

    @FXML
    void removeBillButtonClick(ActionEvent ae){
        int id = Integer.parseInt(billIdTextField.getText());

        Bill bill = controller.removeBill(id);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Bill Deletion");

        if (!clinicSystem.getBillsList().contains(bill)) {
            alert.setHeaderText("Patient deleted successfully");
            alert.setContentText("You have deleted a patient account successfully");
        } else {
            alert.setHeaderText("Dentist NOT deleted successfully");
            alert.setContentText("The system was unable to deleted the patient account, check the patient ID and try again!");
        }
        alert.showAndWait();
    }

    @FXML
    void createPatientButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AdminCreatePatient.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateBillButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AdminUpdateBill.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateDentistButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/AdminUpdateDentist.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
