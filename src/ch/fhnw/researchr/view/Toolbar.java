package ch.fhnw.researchr.view;


import ch.fhnw.researchr.model.LanguagePM;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * @author Dieter Holz
 */
public class Toolbar extends HBox implements ViewMixin {

    private final LanguagePM model;

    private Button[] buttons;

    private Button newBtn;
    private Button saveBtn;
    private Button removeBtn;
    private Button undoBtn;
    private Button redoBtn;

    private Label searchLabel;
    private TextField searchField;

    public Toolbar(LanguagePM model) {
        this.model = model;
        getStyleClass().add("toolbar");
        init();
    }

    @Override
    public void initializeControls() {
        initializeSearchField();
        addButtonsToArray();
        initializeButtons();
    }

    private void initializeButtons() {
        String navFolder = "../resources/img/icons/";
        int size = 30;
        saveBtn = new Button("", new ImageView(new Image(getClass()
                .getResourceAsStream(navFolder + "save.png"),
                size,size,false, false)));
        newBtn = new Button("", new ImageView(new Image(getClass()
                .getResourceAsStream(navFolder + "add.png"),
                size,size,false, false)));
        removeBtn = new Button("", new ImageView(new Image(getClass()
                .getResourceAsStream(navFolder + "remove.png"),
                size,size,false, false)));
        undoBtn = new Button("", new ImageView(new Image(getClass()
                .getResourceAsStream(navFolder + "undo.png"),
                size,size,false, false)));
        redoBtn = new Button("", new ImageView(new Image(getClass()
                .getResourceAsStream(navFolder + "redo.png"),
                size,size,false, false)));

    }

    private void addButtonsToArray() {
        buttons = new Button[]{
                saveBtn,
                newBtn,
                removeBtn,
                undoBtn,
                redoBtn,
        };
    }

    private void initializeSearchField() {
        searchLabel = new Label("Suche");
        searchField = new TextField();
    }


    @Override
    public void layoutControls() {
        final Pane space = new Pane();
        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        setPadding(new Insets(0, 5, 0, 0));
        setMargin(searchLabel, new Insets(0,5,0,0));
        setAlignment(Pos.CENTER);

        getChildren().addAll(saveBtn, newBtn, removeBtn, undoBtn, redoBtn, space, searchLabel, searchField);
    }

    @Override
    public void addBindings() {
        undoBtn.disableProperty().bind(model.disabledUndoProperty());
        redoBtn.disableProperty().bind(model.disabledRedoProperty());
        removeBtn.disableProperty().bind(model.disabledRemoveProperty());
        saveBtn.disableProperty().bind(model.disabledSaveProperty());
    }

    @Override
    public void addEventHandlers() {
        saveBtn.setOnAction(event -> model.save());
        newBtn.setOnAction(event -> model.addNew());
        removeBtn.setOnAction(event -> model.remove());
        undoBtn.setOnAction(event -> model.undo());
        redoBtn.setOnAction(event -> model.redo());
    }

    @Override
    public void addValueChangedListeners() {
    }

    public TextField getSearchField(){
        return searchField;
    }
}
