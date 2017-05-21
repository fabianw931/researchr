package ch.fhnw.researchr.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResearchrModel {
    private final StringProperty windowTitle = new SimpleStringProperty("Researchr");

    public StringProperty windowTitleProperty() {
        return windowTitle;
    }
}
