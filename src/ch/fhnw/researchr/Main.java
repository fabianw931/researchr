package ch.fhnw.researchr;

import ch.fhnw.researchr.controller.ResearchrController;
import ch.fhnw.researchr.model.ResearchrModel;
import ch.fhnw.researchr.view.ResearchrView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    //private static String UI = "resources/view/UI.fxml";

    private ResearchrModel model;
    private ResearchrView view;
    private ResearchrController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* root = FXMLLoader.load(getClass().getResource(UI));
        Scene sexyScene = new Scene(root, 300,250);
        primaryStage.setScene(sexyScene);
        primaryStage.show();
         */

        model = new ResearchrModel();
        view = new ResearchrView(primaryStage, model);
        controller = new ResearchrController(view, model);
        view.start();

    }

    @Override
    public void stop(){
        if (view != null) view.stop();
    }

}