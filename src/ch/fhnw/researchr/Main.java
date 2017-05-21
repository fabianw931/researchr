package ch.fhnw.researchr;

import ch.fhnw.researchr.model.ResearchrModel;
import ch.fhnw.researchr.view.ResearchrView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    //private static String UI = "resources/view/UI.fxml";

    /*
    private ResearchrModel model;
    private ResearchrView view;
    private ResearchrController controller;
    */

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

        /*
        model = new ResearchrModel();
        view = new ResearchrView(primaryStage, model);
        controller = new ResearchrController(view, model);
        view.start();
        */
        try{

            ResearchrModel model = new ResearchrModel();

            BorderPane rootPanel = new ResearchrView(model);

            Scene scene = new Scene(rootPanel, 300, 300);

            String stylesheet = getClass().getResource("resources/css/style.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);

            primaryStage.titleProperty().bind(model.windowTitleProperty());
            primaryStage.setScene(scene);

            primaryStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getCause());
        }
    }

    @Override
    public void stop(){
        System.exit(0);
        //if (view != null) view.stop();
    }

}