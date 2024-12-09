package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.visit.Visit;

public class PatientPageController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Button homeButton;

    @FXML
    private TextField billIdTextField;

    @FXML
    private TextField visitIdTextField;

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
    private TextField viewBillIdTextField;

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

    @FXML public void refreshButtonClick(ActionEvent ae){

    }

    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

    @FXML
    void submitButtonClick(ActionEvent event) {
        int billId = Integer.parseInt(billIdTextField.getText());
        double amountPaid = Double.parseDouble(amountPaidTextField.getText());
    }

    @FXML
    void viewBillButtonClick(ActionEvent event) {
        int billId = Integer.parseInt(viewBillIdTextField.getText());
        //query that returns list
        //loadVisitsToViewBox(list returned from query)
        //CALL METHOD TO LOAD LIST FROM QUERY TO BILLLISTVIEW
    }

    @FXML
    void viewVisitButtonClick(ActionEvent event) {
        int visitId = Integer.parseInt(visitIdTextField.getText());
        //query that returns list
        //loadVisitsToListView(list returned from query)
        //CALL METHOD TO LOAD LIST FROM QUERY TO VISITLISTVIEW
    }

}
