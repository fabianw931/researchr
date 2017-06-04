package ch.fhnw.researchr.view;

public interface ViewMixin {
    default void init() {
        initializeControls();
        layoutControls();
        addEventHandlers();
        addValueChangedListeners();
        addBindings();
    }

    void initializeControls();

    void layoutControls();

    default void addEventHandlers() {
    }

    default void addValueChangedListeners() {
    }

    default void addBindings() {
    }
}
