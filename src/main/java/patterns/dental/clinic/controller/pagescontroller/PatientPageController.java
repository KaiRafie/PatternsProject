package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.DatabaseController;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.visit.Visit;

import javax.xml.crypto.Data;
import java.util.List;

public class PatientPageController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Button homeButton;

    @FXML
    private TextField billIdTextField;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private Button previousButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button viewBillButton;

    @FXML
    private Button viewVisitButton;

    @FXML
    private TextField amountPaidTextField;

    @FXML
    private ListView<Visit> visitListView;

    @FXML
    private ListView<Bill> billListView;

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

    @FXML
    public void refreshButtonClick(ActionEvent ae){
        int patientId = Integer.parseInt(patientIdTextField.getText());
        List<Visit> visits = DatabaseController.queryVisitsByPatientId(patientId);
        List<Bill> bills = DatabaseController.queryBillsByPatientId(patientId);
        loadBillsToListView(bills);
        loadVisitsToListView(visits);
    }

    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

    @FXML
    void submitButtonClick(ActionEvent event) {
        long billId = Long.parseLong(billIdTextField.getText());
        double amountPaid = Double.parseDouble(amountPaidTextField.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient Authentication");
        if (DatabaseController.updateBillTotalsWithPayment(billId, amountPaid)){
            alert.setHeaderText("Payment Confirmation");
            alert.setContentText("The amount of " + amountPaid + "has been paid to Bill " + billId);
            alert.showAndWait();
        } else {
            alert.setHeaderText("Payment Failed");
            alert.setContentText("The amount of " + amountPaid + "failed to be paid to Bill " + billId);
            alert.showAndWait();
        }
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
