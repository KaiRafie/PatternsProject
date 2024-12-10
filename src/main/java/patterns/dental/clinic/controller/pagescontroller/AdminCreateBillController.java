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

public class AdminCreateBillController {

    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Button createButton;

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
    private Label subTotalLabel;

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
    private Label visitIdLabel;

    @FXML
    private TextField visitIdTextField;

    /**
     * Method to initialize all component texts for internalization
     */
    void initialize() {
        createButton.setText(LanguageController.getText("CreateBtn"));
        homeButton.setText(LanguageController.getText("HomeBtn"));
        previousButton.setText(LanguageController.getText("PreviousBtn"));

        dateLabel.setText(LanguageController.getText("Date"));
        insuranceLabel.setText(LanguageController.getText("InsuranceDeduction"));
        subTotalLabel.setText(LanguageController.getText("Subtotal"));
        timeLabel.setText(LanguageController.getText("Time"));
        totalLabel.setText(LanguageController.getText("Total"));
        visitIdLabel.setText(LanguageController.getText("VisitId"));
    }

    /**
     * Method that creates Bill upon clicking createButton
     * Takes visit id, date, time, subtotal, total, and insurance deduction from respective text fields
     *
     * @param event
     */
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

}

