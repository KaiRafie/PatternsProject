package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.DatabaseController;
import patterns.dental.clinic.controller.LanguageController;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.visit.Visit;

import javax.xml.crypto.Data;
import java.util.List;

public class PatientPageController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private TextField amountPaidTextField;

    @FXML
    private ListView<Bill> billListView;

    @FXML
    private TextField billTextField;

    @FXML
    private Button homeButton;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private Label payYourBillLabel;

    @FXML
    private Button previousButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button viewBillButton;

    @FXML
    private Button viewVisitButton;

    @FXML
    private Label viewYourBillLabel;

    @FXML
    private Label viewYourVisitsLabel;

    @FXML
    private ListView<Visit> visitListView;

    /**
     * Method to initialize all component texts for internalization
     */
    @FXML
    private void initialize() {
        homeButton.setText(LanguageController.getText("HomeBtn"));
        payYourBillLabel.setText(LanguageController.getText("PayYourBillLabel"));
        previousButton.setText(LanguageController.getText("PreviousBtn"));
        refreshButton.setText(LanguageController.getText("RefreshBtn"));
        submitButton.setText(LanguageController.getText("SubmitBtn"));
        viewBillButton.setText(LanguageController.getText("ViewBillBtn"));
        viewVisitButton.setText(LanguageController.getText("ViewVisitBtn"));
        viewYourBillLabel.setText(LanguageController.getText("ViewYourBillLabel"));
        viewYourVisitsLabel.setText(LanguageController.getText("ViewYourVisitsLabel"));
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
     * Method to load visit list into viewListView
     *
     * @param visits to be loaded in viewListView
     */
    public void loadVisitsToListView(java.util.List<Visit> visits) {
        visitListView.getItems().clear();
        visitListView.getItems().addAll(visits);
    }

    /**
     * Method to load bills list into billListView
     *
     * @param bills to be loaded in billListView
     */
    public void loadBillsToListView(java.util.List<Bill> bills) {
        billListView.getItems().clear();
        billListView.getItems().addAll(bills);
    }

    /**
     * Method to refresh visit and bill lists according to patient id
     * takes patient id from patientIdTextField
     *
     * @param ae
     */
    @FXML
    public void refreshButtonClick(ActionEvent ae) {
        if (patientIdTextField.getText() != null) {
            int patientId = Integer.parseInt(patientIdTextField.getText());
            List<Visit> visits = DatabaseController.queryVisitsByPatientId(patientId);
            List<Bill> bills = DatabaseController.queryBillsByPatientId(patientId);
            loadBillsToListView(bills);
            loadVisitsToListView(visits);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refresh Error");
        alert.setHeaderText("Cannot Load Patient Id");
        alert.setContentText("Patient Id and Dentist Id Cannot be Empty, Must Contain Data to Submit");
        alert.showAndWait();
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
     * Method that submits payment
     * takes bill id from billIdTextField
     * takes amount paid from amountPaidTextField
     *
     * @param event
     */
    @FXML
    void submitButtonClick(ActionEvent event) {
        if (billTextField.getText() != null && patientIdTextField.getText() != null) {
            long billId = Long.parseLong(billTextField.getText());
            double amountPaid = Double.parseDouble(amountPaidTextField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient Authentication");
            if (DatabaseController.updateBillTotalsWithPayment(billId, amountPaid)) {
                alert.setHeaderText("Payment Confirmation");
                alert.setContentText("The amount of " + amountPaid + " has been paid to Bill " + billId);
                alert.showAndWait();
            } else {
                alert.setHeaderText("Payment Failed");
                alert.setContentText("The amount of " + amountPaid + " failed to be paid to Bill " + billId);
                alert.showAndWait();
            }
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submission Error");
        alert.setHeaderText("Cannot Load Patient Id and Bill Id");
        alert.setContentText("Patient Id and Dentist Id Cannot be Empty, Must Contain Data to Submit");
        alert.showAndWait();
    }

    /**
     * Method to view bills of patient according to patient id
     * takes patient id from patientIdTextField
     *
     * @param event
     */
    @FXML
    void viewBillButtonClick(ActionEvent event) {
        if (patientIdTextField.getText() != null) {
            int patientId = Integer.parseInt(patientIdTextField.getText());
            List<Bill> bills = DatabaseController.queryBillsByPatientId(patientId);
            loadBillsToListView(bills);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Bill Error");
        alert.setHeaderText("Cannot Load Patient Id");
        alert.setContentText("Patient Id Cannot be Empty, Must Contain Data to View Bill(s)");
        alert.showAndWait();
    }

    /**
     * Method to view visits according to patient id
     * takes patient id from patientIdTextField
     *
     * @param event
     */
    @FXML
    void viewVisitButtonClick(ActionEvent event) {
        if (patientIdTextField.getText() != null) {
            int patientId = Integer.parseInt(patientIdTextField.getText());
            List<Visit> visits = DatabaseController.queryVisitsByPatientId(patientId);
            loadVisitsToListView(visits);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Bill Error");
        alert.setHeaderText("Cannot Load Patient Id");
        alert.setContentText("Patient Id Cannot be Empty, Must Contain Data to View Bill(s)");
        alert.showAndWait();
    }
}
