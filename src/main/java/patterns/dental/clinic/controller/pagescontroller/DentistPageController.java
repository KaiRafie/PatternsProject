package patterns.dental.clinic.controller.pagescontroller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.DatabaseController;
import patterns.dental.clinic.controller.LanguageController;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.visit.Visit;

import java.util.List;

public class DentistPageController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Button createVisitButton;

    @FXML
    private Label createVisitLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField dateTextField;

    @FXML
    private Label dentistIdLabel;

    @FXML
    private TextField dentistIdTextField;

    @FXML
    private Button homeButton;

    @FXML
    private Label patientIdLabel;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private TextField patientVisitIdTextField;

    @FXML
    private Button previousButton;

    @FXML
    private Label procedureInfoLabel;

    @FXML
    private Button refreshButton;

    @FXML
    private Button viewButton;

    @FXML
    private TextField procedureInfoTextField;

    @FXML
    private Label timeLabel;

    @FXML
    private TextField timeTextField;

    @FXML
    private Label viewPatientVisitsLabel;

    @FXML
    private ListView<Visit> visitListView;

    @FXML
    private Label visitTypeLabel;

    @FXML
    private TextField visitTypeTextField;

    /**
     * Method to initialize all component texts for internalization
     */
    @FXML
    private void initialize() {
        createVisitButton.setText(LanguageController.getText("CreateVisitlabel"));
        createVisitLabel.setText(LanguageController.getText("CreateVisitlabel"));
        dateLabel.setText(LanguageController.getText("Date"));
        dentistIdLabel.setText(LanguageController.getText("DentistId"));
        homeButton.setText(LanguageController.getText("HomeBtn"));
        patientIdLabel.setText(LanguageController.getText("PatientId"));
        previousButton.setText(LanguageController.getText("PreviousBtn"));
        procedureInfoLabel.setText(LanguageController.getText("ProcedureInfoLabel"));
        timeLabel.setText(LanguageController.getText("Time"));
        viewButton.setText(LanguageController.getText("ViewBtn"));
        viewPatientVisitsLabel.setText(LanguageController.getText("ViewPatientVisitLabel"));
        visitTypeLabel.setText(LanguageController.getText("VisitTypeLabel"));
    }

    /**
     * Method to load visit list into viewListView
     *
     * @param visits to be loaded in listView
     */
    public void loadVisitsToViewBox(java.util.List<Visit> visits) {
        visitListView.getItems().clear();
        visitListView.getItems().addAll(visits);
    }

    /**
     * Method to refresh all list views and reload its information
     *
     * @param ae
     */
    @FXML
    public void refreshButtonClick(ActionEvent ae) {
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

    /**
     * Method to create visit if create button is clicked
     * takes procedure info from procedureInfoTextField
     * takes visitType from visitTypeTextField
     * takes date from dateTextField
     * takes time from timeTextField
     * takes patient id from patientVisit
     *
     * @param event
     */
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

    /**
     * button to view patient visit history based on patient id
     * takes patient id from patientIdTextField
     *
     * @param event
     */
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
