package patterns.dental.clinic.controller.pagescontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class PatientSignInController {

    @FXML
    private Button homeButton;

    @FXML
    private Button logInButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button previousButton;

    @FXML
    private TextField userIdField;

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
