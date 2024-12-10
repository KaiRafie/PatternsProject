package patterns.dental.clinic.controller.pagescontroller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationManager {

    private static NavigationManager instance;
    private Scene previousScene;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private NavigationManager() {
    }

    public static NavigationManager getInstance() {
        if (instance == null) {
            synchronized (NavigationManager.class) {
                if (instance == null) {
                    instance = new NavigationManager();
                }
            }
        }
        return instance;
    }

    /**
     * Method to navigate to next scene while maintaining history
     * stores current scene as previous sense before switching scenes
     *
     * @param newScene
     */
    public void navigateTo(Scene newScene) {
        if (primaryStage.getScene() != null) {
            previousScene = primaryStage.getScene();
        }
        primaryStage.setScene(newScene);
    }

    /**
     * Method to go back to previous scene
     */
    public void navigateBack() {
        if (previousScene != null) {
            primaryStage.setScene(previousScene);
            previousScene = null; // Reset after navigating back
        } else {
            System.out.println("No previous page available!");
        }
    }
}
