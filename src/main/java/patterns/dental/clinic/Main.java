package patterns.dental.clinic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import patterns.dental.clinic.controller.DatabaseController;
import patterns.dental.clinic.controller.pagescontroller.NavigationManager;
import patterns.dental.clinic.model.bill.*;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.RegularDentist;
import patterns.dental.clinic.model.user.User;
import patterns.dental.clinic.model.visit.Visit;
import patterns.dental.clinic.model.visit.VisitFactory;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Main extends Application {

    public static void main(String[] args) {
        DatabaseController.initializeDatabase();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            NavigationManager.getInstance().setPrimaryStage(stage);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainPage.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);


            stage.setScene(scene);
            stage.setTitle("Dental Management System");

            stage.show();

            NavigationManager.getInstance().setPrimaryStage(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
