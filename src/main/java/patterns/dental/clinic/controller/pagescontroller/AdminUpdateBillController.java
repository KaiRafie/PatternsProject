package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.LanguageController;

public class AdminUpdateBillController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Label billIdLabel;

    @FXML
    private TextField billIdTextField;

    @FXML
    private Label dateLabel;

    @FXML
    private TextField dateTextField;

    @FXML
    private Button homeButton;

    @FXML
    private TextField insuranceDeductionTextField;

    @FXML
    private Label insuranceLabel;

    @FXML
    private Button previousButton;

    @FXML
    private Label subtotalLabel;

    @FXML
    private TextField subtotalTextField;

    @FXML
    private Label timeLabel;

    @FXML
    private TextField timeTextField;

    @FXML
    private Label totalLabel;

    @FXML
    private TextField totalTextField;

    @FXML
    private Button updateButton;

    @FXML
    private Label visitIdLabel;

    @FXML
    private TextField visitIdTextField;

    @FXML
    private void initialize() {
        billIdLabel.setText(LanguageController.getText("BillId"));
        billIdTextField.setPromptText(LanguageController.getText("BillId"));
        dateLabel.setText(LanguageController.getText("Date"));
        dateTextField.setPromptText(LanguageController.getText("Date"));
        homeButton.setText(LanguageController.getText("HomeBtn"));
        insuranceDeductionTextField.setPromptText(LanguageController.getText("InsuranceDeduction"));
        insuranceLabel.setText(LanguageController.getText("InsuranceDeduction"));
        previousButton.setText(LanguageController.getText("PreviousBtn"));
        subtotalLabel.setText(LanguageController.getText("Subtotal"));
        subtotalTextField.setPromptText(LanguageController.getText("Subtotal"));
        timeLabel.setText(LanguageController.getText("Time"));
        timeTextField.setPromptText(LanguageController.getText("Time"));
        totalLabel.setText(LanguageController.getText("Total"));
        totalTextField.setPromptText(LanguageController.getText("Total"));
        updateButton.setText(LanguageController.getText("UpdateBtn"));
        visitIdLabel.setText(LanguageController.getText("VisitId"));
        visitIdTextField.setPromptText(LanguageController.getText("VisitId"));
    }

    @FXML
    void updateButtonClick(ActionEvent event) {
        int billId = Integer.parseInt(billIdTextField.getText());
        int visitId = Integer.parseInt(visitIdTextField.getText());
        String date = dateTextField.getText();
        String time = timeTextField.getText();
        double subtotal = Double.parseDouble(subtotalTextField.getText());
        double total = Double.parseDouble(totalTextField.getText());
        double insuranceDeduction = Double.parseDouble(insuranceDeductionTextField.getText());

        boolean updateState  = controller.updateBill(billId, visitId, date, time, subtotal, total, insuranceDeduction);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Bill Update");

        if (updateState) {
            alert.setHeaderText("Bill Updated successfully");
            alert.setContentText("You have Updated a Bill successfully");
        } else {
            alert.setHeaderText("Bill NOT Updated successfully");
            alert.setContentText("The system was unable to Update the Bill, check the information and try again!");
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

