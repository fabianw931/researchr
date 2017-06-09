package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.Language;
import ch.fhnw.researchr.model.LanguagePM;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.html.ImageView;

public class LanguageListView extends TableView<Language> implements ViewMixin {

    private LanguagePM model;

    public LanguageListView(LanguagePM model) {
        this.model = model;

        getStyleClass().add("form");
        init();
    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void layoutControls() {

        TableColumn<Language, ImageView> langPicColumn = new TableColumn("Logo");
        langPicColumn.setCellValueFactory(new PropertyValueFactory<>("imageView"));

        TableColumn<Language, String> langNameColumn = new TableColumn("Name");
        langNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Language, String> langDevColumn = new TableColumn("Entwickler");
        langDevColumn.setMinWidth(200);
        langDevColumn.setCellValueFactory(new PropertyValueFactory<>("developer"));

        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        this.setItems(model.languages());
        this.getColumns().addAll(langPicColumn, langNameColumn, langDevColumn);

    }



    @Override
    public void addBindings() {



    }
}
