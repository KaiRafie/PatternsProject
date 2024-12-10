package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.controller.ClinicSystemController;

public class AdminUpdatePatientController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private TextField dobTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Button homeButton;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField patientIdTextField;

    @FXML
    private Button previousButton;

    @FXML
    private Button updateButton;

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
    void updateButtonClick(ActionEvent event) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        int id = Integer.parseInt(patientIdTextField.getText());
        String password = passwordTextField.getText();
        String dob = dobTextField.getText();

        boolean updateState = controller.updatePatient(id, firstName, lastName, dob, password);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Patient Update");

        if (updateState) {
            alert.setHeaderText("Patient Updated successfully");
            alert.setContentText("You have Updated a Patient account successfully");
        } else {
            alert.setHeaderText("Patient NOT Updated successfully");
            alert.setContentText("The system was unable to Update the Patient account, check the information and try again!");
        }
    }
}

