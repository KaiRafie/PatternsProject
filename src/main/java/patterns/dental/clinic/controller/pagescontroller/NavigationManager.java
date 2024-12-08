package patterns.dental.clinic.controller.pagescontroller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

public class NavigationManager {

    private static NavigationManager instance;
    private Scene previousScene;
    @Setter
    private Stage primaryStage;

    private NavigationManager() {}

    public static NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void navigateTo(Scene newScene) {
        if (primaryStage.getScene() != null) {
            previousScene = primaryStage.getScene();
        }
        primaryStage.setScene(newScene);
    }

    public void navigateBack() {
        if (previousScene != null) {
            primaryStage.setScene(previousScene);
            previousScene = null; // Reset after navigating back
        } else {
            System.out.println("No previous page available!");
        }
    }
}
