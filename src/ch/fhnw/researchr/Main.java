package ch.fhnw.researchr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private static String UI = "resources/view/UI.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(UI));
        Scene sexyScene = new Scene(root, 300,250);
        primaryStage.setScene(sexyScene);
        primaryStage.show();
    }


}