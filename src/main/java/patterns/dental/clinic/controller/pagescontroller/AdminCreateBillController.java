package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.dental.clinic.controller.ClinicSystemController;

public class AdminCreateBillController {

    ClinicSystemController controller = new ClinicSystemController();

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
        int visitId = Integer.parseInt(visitIdTextField.getText());
        String date = dateTextField.getText();
        String time = timeTextField.getText();
        double subtotal = Double.parseDouble(subtotalTextField.getText());
        double total = Double.parseDouble(totalTextField.getText());
        double insuranceDeduction = Double.parseDouble(insuranceDeductionTextField.getText());

        boolean createdState = controller.createBill(visitId, date, time, subtotal, total, insuranceDeduction);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Bill Creation");

        if (createdState) {
            alert.setHeaderText("Bill created successfully");
            alert.setContentText("You have created a bill successfully");
        } else {
            alert.setHeaderText("Bill NOT created successfully");
            alert.setContentText("The system was unable to create the bill, check the information and try again!");
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

}

