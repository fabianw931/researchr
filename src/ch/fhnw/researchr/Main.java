package ch.fhnw.researchr;

import ch.fhnw.researchr.model.LanguagePM;
import ch.fhnw.researchr.view.ApplicationUI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LanguagePM model = new LanguagePM();
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);
        Parent rootPanel = new ApplicationUI(model);

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