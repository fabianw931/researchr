package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.ResearchrModel;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class ResearchrView extends BorderPane{

    final private ResearchrModel model;

    private SplitPane splitPane;
    private ToolBar toolBar;

    private ListView<Color> listView;

    private Button[] buttons;

    private Button newBtn;
    private Button saveBtn;
    private Button removeBtn;
    private Button undoBtn;
    private Button redoBtn;
    private Button exportBtn;
    private Button importBtn;

    private Label searchLbl;
    private TextField searchField;

    public ResearchrView(ResearchrModel model) {
        this.model = model;
        splitPane = new SplitPane();

        try {

            initializeControls();
            layoutControls();

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void initializeControls() {
        initializeToolBar();
        initializeSidePanel();
        initializePropertiesView();
    }

    private void layoutControls() {
        layoutToolBar();
        layoutSidePanel();
        layoutPropertiesView();

        setTop(toolBar);
        setCenter(splitPane);
    }

    private void initializeToolBar(){

        String navFolder = "../resources/img/icons/";

        saveBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "save.png"))));
        newBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "add.png"))));
        removeBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "remove.png"))));
        undoBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "undo.png"))));
        redoBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "redo.png"))));
        exportBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "upload.png"))));
        importBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "download.png"))));

        buttons = new Button[]{
                saveBtn,
                newBtn,
                removeBtn,
                undoBtn,
                redoBtn,
                exportBtn,
                importBtn
        };

        searchLbl = new Label("Suche");
        searchField = new TextField();

    }

    private void initializeSidePanel() {

    }

    private void initializePropertiesView() {
    }

    private void layoutToolBar() {
        final Pane space = new Pane();
        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        toolBar = new ToolBar();
        toolBar.getItems().addAll(buttons);
        toolBar.getItems().add(space);
        toolBar.getItems().add(searchLbl);
        toolBar.getItems().add(searchField);

    }

    private void layoutSidePanel() {

    }

    private void layoutPropertiesView() {

    }



}
