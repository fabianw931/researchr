package ch.fhnw.researchr;

import ch.fhnw.researchr.controller.ResearchrController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setMinWidth(1000);
            primaryStage.setMinHeight(600);

            ResearchrController controller = new ResearchrController();
            BorderPane rootPanel = controller.getView();

            Scene scene = new Scene(rootPanel);

            String stylesheet = getClass().getResource("resources/css/style.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);

            primaryStage.titleProperty();
            primaryStage.setScene(scene);

            primaryStage.show();

    }

    @Override
    public void stop(){
        System.exit(0);
    }

}