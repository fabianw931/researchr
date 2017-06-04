package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.LanguagePM;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;

public class ListView<Language> extends TableView implements ViewMixin {

    LanguagePM model;

    public ListView(LanguagePM model) {
        this.model = model;
        getStyleClass().add("form");
        init();
    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void layoutControls() {

    }

    @Override
    public void addBindings() {

    }
}
