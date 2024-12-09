package patterns.dental.clinic.controller.pagescontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import patterns.dental.clinic.controller.ClinicSystemController;

public class DentistSignInController {
    ClinicSystemController controller = new ClinicSystemController();

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
        int id = Integer.parseInt(userIdTextField.getText());
        String password = passwordTextField.getText();
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
