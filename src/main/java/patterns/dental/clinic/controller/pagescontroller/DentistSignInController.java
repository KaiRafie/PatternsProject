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
import patterns.dental.clinic.model.ClinicSystem;
import patterns.dental.clinic.model.user.Dentist;

public class DentistSignInController {
    ClinicSystemController controller = new ClinicSystemController();
    ClinicSystem clinicSystem = ClinicSystem.getInstance();

    @FXML
    private Button homeButton;

    @FXML
    private Button logInButton;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button previousButton;

    @FXML
    private TextField userIdTextField;

    @FXML
    void logInButtonClick(ActionEvent event) {
        long userId = Integer.parseInt(userIdTextField.getText());
        String password = passwordTextField.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Patient Authentication");

        for (int i = 0; i < clinicSystem.getDentistsList().size(); i++) {
            Dentist dentist = clinicSystem.getDentistsList().get(i);
            if (dentist.getUserID() == userId) {
                if (password.equals(dentist.getLoginPass())) {
                    alert.setHeaderText("Authentication successful");
                    alert.setContentText("You have signed in successfully");
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                "/fxml/MainPage.fxml"));
                        AnchorPane root = loader.load();

                        Scene scene = new Scene(root);

                        NavigationManager.getInstance().navigateTo(scene);

                        alert.showAndWait();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    alert.setHeaderText("Authentication Failed");
                    alert.setContentText("Incorrect password, please try again!");

                    alert.showAndWait();
                }
            } else {
                alert.setHeaderText("Authentication Failed");
                alert.setContentText("Id not found, please try again!");

                alert.showAndWait();
            }
        }
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
