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
import patterns.dental.clinic.model.user.User;

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

        alert.setTitle("Dentist Authentication");

        for (int i = 0; i < clinicSystem.getDentistsList().size(); i++) {
            User dentist = clinicSystem.getDentistsList().get(i);
            if (dentist.getUserID() == userId) {
                if (password.equals(dentist.getLoginPass())) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                "/fxml/DentistPage.fxml"));
                        AnchorPane root = loader.load();

                        Scene scene = new Scene(root);

                        NavigationManager.getInstance().navigateTo(scene);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    alert.setHeaderText("Authentication Failed");
                    alert.setContentText("Incorrect Password and User Id combination!");

                    alert.showAndWait();
                }
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
