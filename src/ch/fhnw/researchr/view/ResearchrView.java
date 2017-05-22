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

    private ListView<Color> listView;

    private Button[] buttons;
    
    private Button newBtn;
    private Button saveBtn;
    private Button removeBtn;
    private Button undoBtn;
    private Button redoBtn;
    private Button editBtn;

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

        int imgSize = 30;

        Image saveImg = new Image(getClass().getResourceAsStream("../resources/img/save.png"),
                imgSize, imgSize, true,false);
        saveBtn = new Button("", new ImageView(saveImg));

        Image newImg = new Image(getClass().getResourceAsStream("../resources/img/add.png"),
                imgSize, imgSize, true,false);
        newBtn = new Button("", new ImageView(newImg));

        Image removeImg = new Image(getClass().getResourceAsStream("../resources/img/remove.png"),
                imgSize, imgSize, true,false);
        removeBtn = new Button("", new ImageView(removeImg));

        Image undoImg = new Image(getClass().getResourceAsStream("../resources/img/undo.png"),
                imgSize, imgSize, true,false);
        undoBtn = new Button("", new ImageView(undoImg));

        Image redoImg = new Image(getClass().getResourceAsStream("../resources/img/redo.png"),
                imgSize, imgSize, true,false);
        redoBtn = new Button("", new ImageView(redoImg));

        Image editImg = new Image(getClass().getResourceAsStream("../resources/img/web.png"),
                imgSize, imgSize, true,false);
        editBtn = new Button("", new ImageView(editImg));

        buttons = new Button[]{
                saveBtn,
                newBtn,
                removeBtn,
                undoBtn,
                redoBtn,
                editBtn
        };

        for(Button b : buttons){
            b.setStyle("-fx-background-radius: 0");

        }

    }

    private void initializeSidePanel() {
    }

    private void initializePropertiesView() {
    }

    private void layoutControls() {

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(buttons);

        setTop(toolbar);
    }
}
