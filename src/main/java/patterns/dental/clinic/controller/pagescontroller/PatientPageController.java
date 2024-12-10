package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

    public void loadVisitsToListView(java.util.List<Visit> visits) {
        visitListView.getItems().clear();
        visitListView.getItems().addAll(visits);
    }

    public void loadBillsToListView(java.util.List<Bill> bills) {
        billListView.getItems().clear();
        billListView.getItems().addAll(bills);
    }

    @FXML public void refreshButtonClick(ActionEvent ae){

    }

    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

    @FXML
    void submitButtonClick(ActionEvent event) {
        int billId = Integer.parseInt(billTextField.getText());
        double amountPaid = Double.parseDouble(amountPaidTextField.getText());
    }

    @FXML
    void viewBillButtonClick(ActionEvent event) {
        int patientId = Integer.parseInt(patientIdTextField.getText());

        List<Bill> bills = DatabaseController.queryBillsByPatientId(patientId);
        loadBillsToListView(bills);
    }

    @FXML
    void viewVisitButtonClick(ActionEvent event) {
        int patientId = Integer.parseInt(patientIdTextField.getText());

        List<Visit> visits = DatabaseController.queryVisitsByPatientId(patientId);
        loadVisitsToListView(visits);
    }

}
