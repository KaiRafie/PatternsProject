package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.model.visit.Visit;

public class DentistPageController {

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
    private TextField dentisttIdTextField;

    @FXML
    private TextField patientVisitIdTextField;

    @FXML
    private Button viewButton;

    @FXML
    private TextField procedureInfoTextField;

    @FXML
    private ListView<Visit> visitListView;

    public void loadVisitsToViewBox(java.util.List<Visit> visits) {
        // Clear existing items
        visitListView.getItems().clear();

        // Add all visits to the ListView
        visitListView.getItems().addAll(visits);
    }

    @FXML
    void createVisitButtonClick(ActionEvent event) {
        String procedureInfo = procedureInfoTextField.getText();
        String visitType = visitTypeTextField.getText();
        String date = dateTextField.getText();
        String time = timeTextField.getText();
        int patientId = Integer.parseInt(patientVisitIdTextField.getText());
        int dentistId = Integer.parseInt(dentisttIdTextField.getText());
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
        int id = Integer.parseInt(patientIdTextField.getText());
        //query that returns list
        //loadVisitsToViewBox(list returned from query)
        //CALL METHOD TO LOAD LIST FROM QUERY TO LISTVIEW
    }

}
