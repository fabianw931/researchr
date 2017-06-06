package ch.fhnw.researchr.model;

import javafx.beans.property.Property;

public class ValueChangeCommand<T> implements Command {

    private LanguagePM model;
    private Language language;
    private final Property<T> property;
    private final T           oldValue;
    private final T           newValue;

    public ValueChangeCommand(LanguagePM model, Property<T> property, T oldValue, T newValue) {
        this.model = model;
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public void undo() {
        model.setPropertyValueWithoutUndoSupport(property, oldValue);
    }

    public void redo() {
        model.setPropertyValueWithoutUndoSupport(property, newValue);
    }
}
