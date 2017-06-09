package ch.fhnw.researchr.view;


import ch.fhnw.researchr.model.LanguagePM;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        saveBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "save.png"))));
        newBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "add.png"))));
        removeBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "remove.png"))));
        undoBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "undo.png"))));
        redoBtn = new Button("", new ImageView(new Image(getClass().getResourceAsStream(navFolder + "redo.png"))));
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
        getChildren().addAll(saveBtn, newBtn, removeBtn, undoBtn, redoBtn, space, searchLabel, searchField);
    }

    @Override
    public void addBindings() {
        undoBtn.disableProperty().bind(model.disabledUndoProperty());
        redoBtn.disableProperty().bind(model.disabledRedoProperty());
        removeBtn.disableProperty().bind(model.disabledRemoveProperty());
        saveBtn.disableProperty().bind(model.disabledSaveProperty());
       // searchField.textProperty().bindBidirectional(model.searchTextProperty());
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
        searchField.textProperty().addListener((event, oldValue, newValue) -> {
            model.setSearchText(newValue);
            model.filtered();
        });
    }

    public TextField getSearchField(){
        return searchField;
    }
}
