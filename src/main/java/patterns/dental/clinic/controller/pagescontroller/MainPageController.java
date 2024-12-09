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

public class MainPageController {
    ClinicSystemController controller = new ClinicSystemController();

    @FXML
    private TextField adminKeyTextField;

    @FXML
    private Button dentistLogInButton;

    @FXML
    private Button patientLogInButton;

    @FXML
    public void patientLogInButtonClick(ActionEvent ae){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PatientSignIn.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dentistLogInButtonClick(ActionEvent ae){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/fxml/DentistSignIn.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);

            NavigationManager.getInstance().navigateTo(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void adminPageButtonClick(ActionEvent event) {
        if (adminKeyTextField.getText().equals("12345678")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/fxml/AdminPage.fxml"));
                AnchorPane root = loader.load();

                Scene scene = new Scene(root);

                NavigationManager.getInstance().navigateTo(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Admin Authentication");

                alert.setHeaderText("Authentication Failed");
                alert.setContentText("You have entered a wrong admin key, please try again!");


            alert.showAndWait();
        }

    }

}