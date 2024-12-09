package patterns.dental.clinic.controller.pagescontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import patterns.dental.clinic.controller.ClinicSystemController;

public class AdminCreatePatientController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private Button createButton;

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
    private Button previousButton;

    /**
     * Method that creates patient from inputted information from GUI
     */
    @FXML
    void createButtonClick(ActionEvent event) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String password = passwordTextField.getText();
        String dob = dobTextField.getText();

        boolean createdState = controller.createPatient(firstName, lastName, dob, password);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Patient Creation");

        if (createdState) {
            alert.setHeaderText("Patient created successfully");
            alert.setContentText("You have created a patient successfully");
        } else {
            alert.setHeaderText("Patient NOT created successfully");
            alert.setContentText("The system was unable to create the patient, check the information and try again!");
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
