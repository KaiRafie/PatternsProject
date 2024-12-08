package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminUpdateBillController {

    @FXML
    private TextField billIdTextField;

    @FXML
    private Button createButton;

    @FXML
    private TextField dateTextField;

    @FXML
    private Button homeButton;

    @FXML
    private TextField insuranceDeductionTextField;

    @FXML
    private Button previousButton;

    @FXML
    private TextField subtotalTextField;

    @FXML
    private TextField timeTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private TextField visitIdTextField;

    @FXML
    void createButtonClick(ActionEvent event) {
        int billId = Integer.parseInt(billIdTextField.getText());
        int visitId = Integer.parseInt(visitIdTextField.getText());
        String date = dateTextField.getText();
        String time = timeTextField.getText();
        double subtotal = Double.parseDouble(subtotalTextField.getText());
        double total = Double.parseDouble(totalTextField.getText());
        double insuranceDeduction = Double.parseDouble(insuranceDeductionTextField.getText());
    }

    @FXML
    public void homeButtonClick(ActionEvent ae){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/MainPage.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) homeButton.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Dental Management System");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void previousButtonClick(ActionEvent ae) {
        NavigationManager.getInstance().navigateBack();
    }

}

