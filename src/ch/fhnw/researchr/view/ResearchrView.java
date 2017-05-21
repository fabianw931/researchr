package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.ResearchrModel;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ResearchrView extends BorderPane{


    /*
    final private Stage stage;

    public ResearchrView(Stage stage, ResearchrModel model){
        this.stage = stage;
        this.model = model;

    }
    public void start() {
        stage.show();
    }

    public void stop() {
        stage.hide();
    }

    */
    final private ResearchrModel model;

    private Button          button;
    private ListView<Color> listView;

    public ResearchrView(ResearchrModel model) {
        this.model = model;

        try {

            initializeControls();
            layoutControls();

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void initializeControls() {
        initializeMenuBar();
        initializeSidePanel();
        initializePropertiesView();
    }

    private void initializeMenuBar(){
        Image newBtn = new Image(getClass().getResourceAsStream("resources/img/add.png"));
        button = new Button("This is cool", new ImageView(newBtn));
    }

    private void initializeSidePanel() {
    }

    private void initializePropertiesView() {
    }

    private void layoutControls() {

        ToolBar toolbar = new ToolBar(button);
        setTop(toolbar);
    }
}
