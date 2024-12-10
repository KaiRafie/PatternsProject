package patterns.dental.clinic.controller.pagescontroller;

import javafx.collections.FXCollections;
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

import java.util.List;

public class DentistPageController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Button createVisitButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private Button previousButton;

    @FXML
    private TextField dateTextField;

    @FXML
    private TextField visitTypeTextField;

    @FXML
    private TextField timeTextField;

    @FXML
    private TextField dentistIdTextField;

    @FXML
    private TextField patientVisitIdTextField;

    @FXML
    private Button refreshButton;

    @FXML
    private Button viewButton;

    @FXML
    private TextField procedureInfoTextField;

    @FXML
    private ListView<Visit> visitListView;

    public void loadVisitsToViewBox(java.util.List<Visit> visits) {
        visitListView.getItems().clear();
        visitListView.getItems().addAll(visits);
    }

    @FXML
    public void refreshButtonClick(ActionEvent ae){
        if (patientIdTextField.getText() != null) {
            int patientId = Integer.parseInt(patientIdTextField.getText());
            List<Visit> visits = DatabaseController.queryVisitsByPatientId(patientId);
            loadVisitsToViewBox(visits);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Refresh Error");
        alert.setHeaderText("Patient Id is Null");
        alert.setContentText("Patient Id Cannot be Empty, Must Contain Data to Refresh");
        alert.showAndWait();
    }

    @FXML
    void createVisitButtonClick(ActionEvent event) {
        String procedureInfo = procedureInfoTextField.getText();
        String visitType = visitTypeTextField.getText();
        String date = dateTextField.getText();
        String time = timeTextField.getText();
        int patientId = Integer.parseInt(patientVisitIdTextField.getText());
        int dentistId = Integer.parseInt(dentistIdTextField.getText());

        boolean createdState = controller.createVisit(dentistId, patientId, visitType, date, time, procedureInfo);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Visit Creation");

        if (createdState) {
            alert.setHeaderText("Visit created successfully");
            alert.setContentText("You have created a Visit successfully");
        } else {
            alert.setHeaderText("Visit NOT created successfully");
            alert.setContentText("The system was unable to create the Visit, check the information and try again!");
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
    void viewButtonClick(ActionEvent event) {
        if (patientIdTextField.getText() != null) {
            int id = Integer.parseInt(patientIdTextField.getText());
            List<Visit> visit = DatabaseController.queryVisitsByPatientId(id);
            loadVisitsToViewBox(visit);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Error");
        alert.setHeaderText("Patient Id is Null");
        alert.setContentText("Patient Id Cannot be Empty, Must Contain Data to Refresh");
        alert.showAndWait();
    }
}
