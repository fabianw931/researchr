package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.FileHandler;
import ch.fhnw.researchr.model.Language;
import ch.fhnw.researchr.model.LanguagePM;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LanguageListView extends TableView<Language> implements ViewMixin {

    private LanguagePM model;
    private JsonArray jArr;

    public LanguageListView(LanguagePM model) {
        this.model = model;

        FileHandler fh = new FileHandler();
        jArr = fh.read();

        getStyleClass().add("form");
        init();
    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void layoutControls() {
        JsonObject jObj;
        String navFolder = "../resources/img/icons/";

        TableColumn<Language, String> langPicColumn = new TableColumn("Name");
        langPicColumn.setMinWidth(200);
        langPicColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Language, String> langInfoColumn = new TableColumn("Entwickler");
        langInfoColumn.setMinWidth(200);
        langInfoColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));

        ObservableList<Language> itemList = FXCollections.observableArrayList();

        int i = 0;

        while(i < jArr.size()) {
            jObj = jArr.get(i).getAsJsonObject();
            i++;
            Language lang = new Language(
                    jObj.get("Name").getAsString(),
                    jObj.get("Entwickler").getAsString());

            itemList.add(lang);
        }


        this.setItems(itemList);
        this.getColumns().addAll(langPicColumn, langInfoColumn);


    }



    @Override
    public void addBindings() {

    }
}
