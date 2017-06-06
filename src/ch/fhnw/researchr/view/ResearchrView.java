package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.FileHandler;
import ch.fhnw.researchr.model.Language;
import ch.fhnw.researchr.model.LanguagePM;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ResearchrView extends BorderPane{

    private final LanguagePM model;

    private SplitPane splitPane;
    private ToolBar toolBar;

    private TableView<Language> tableView;

    private Button[] buttons;

    private Button newBtn;
    private Button saveBtn;
    private Button removeBtn;
    private Button undoBtn;
    private Button redoBtn;

    private Label searchLbl;
    private TextField searchField;

    private JsonArray jArr;

    public ResearchrView(LanguagePM model) {
        this.model = model;

        FileHandler fh = new FileHandler();
        jArr = fh.read();

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

        //todo change stuff here no tableview
        setLeft(tableView);

        setCenter(splitPane);
    }

    private void initializeToolBar(){
        initializeButtons();
        addButtonsToArray();
       // addEventHandlerToBtns();
        initializeSearchField();
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

    /*private void addEventHandlerToBtns() {
        for(Button b : buttons){
            b.setOnAction(controller);
        }
    }*/

    private void initializeSearchField() {
        searchLbl = new Label("Suche");
        searchField = new TextField();
    }

    private void initializeSidePanel() {
        initializeTableView();
        layoutSidePanel();
        fillTableView();

        addTableViewListeners();
    }

    private void initializeTableView() {


        //TODO splitpane stuff
    }

    private void fillTableView() {
        int i = 0;
        JsonObject jObj;
        String navFolder = "../resources/img/icons/";
        TableColumn<Language, String> langPicColumn = new TableColumn("Name");
        langPicColumn.setMinWidth(200);
        langPicColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Language, String> langInfoColumn = new TableColumn("Entwickler");
        langInfoColumn.setMinWidth(200);
        langInfoColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));

        ObservableList<Language> itemList = FXCollections.observableArrayList();

        while(i < jArr.size()) {
            jObj = jArr.get(i).getAsJsonObject();
            i++;
            itemList.add(
                    new Language(i,
                            jObj.get("Name").getAsString(),
                            jObj.get("Entwickler").getAsString()
                        )
                    );
        }

        tableView = new TableView<Language>();
        tableView.setItems(itemList);
        tableView.getColumns().addAll(langPicColumn, langInfoColumn);

    }

    private void addTableViewListeners() {
      //  tableView.

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

    public Button getNewBtn() {
        return newBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getRemoveBtn() {
        return removeBtn;
    }

    public Button getUndoBtn() {
        return undoBtn;
    }

    public Button getRedoBtn() {
        return redoBtn;
    }
}
