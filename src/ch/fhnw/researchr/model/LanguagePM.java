package ch.fhnw.researchr.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;

public class LanguagePM {
    private final StringProperty applicationTitle  = new SimpleStringProperty("Programmiersprachen");

    private final IntegerProperty selectedLanguageId = new SimpleIntegerProperty(-1);

    private ObservableList<Language> languages = FXCollections.observableArrayList();

    private final Language languageProxy = new Language();

    private final ObservableList<Command> undoStack = FXCollections.observableArrayList();
    private final ObservableList<Command> redoStack = FXCollections.observableArrayList();

    private ChangeListener<String> changeNameListener;

    private BooleanProperty disabledUndo = new SimpleBooleanProperty();
    private BooleanProperty disabledRedo = new SimpleBooleanProperty();

    public LanguagePM() {
        this(getLanguages());
    }

    public LanguagePM(List<Language> languageList) {
        languages.addAll(languageList);

        disabledUndo.bind(Bindings.isEmpty(undoStack));
        disabledUndo.bind(Bindings.isEmpty(redoStack));

        /*
        undoStack.addListener((l, o, n) -> {
            disabledUndo.set(undoStack.isEmpty())
        });
        */

        selectedLanguageIdProperty().addListener((observable, oldValue, newValue) -> {
                    Language oldSelection = getLanguage(oldValue.intValue());
                    Language newSelection = getLanguage(newValue.intValue());

                    if (oldSelection != null) {
                        languageProxy.idProperty().unbindBidirectional(oldSelection.idProperty());
                        languageProxy.nameProperty().unbindBidirectional(oldSelection.nameProperty());
                        languageProxy.publishedYearProperty().unbindBidirectional(oldSelection.publishedYearProperty());
                        languageProxy.developerProperty().unbindBidirectional(oldSelection.developerProperty());
                        languageProxy.typingProperty().unbindBidirectional(oldSelection.typingProperty());
                        languageProxy.paradigmsProperty().unbindBidirectional(oldSelection.paradigmsProperty());
                        languageProxy.stackoverflowTagsProperty().unbindBidirectional(oldSelection.stackoverflowTagsProperty());

                        oldSelection.nameProperty().removeListener(changeNameListener);
                    }

                    if (newSelection != null) {
                        languageProxy.idProperty().bindBidirectional(newSelection.idProperty());


                        changeNameListener = (obj, oldVal, newVal) -> {
                            Command cmd = new ChangeNameCommand(this, newSelection, oldVal, newVal);

                            undoStack.add(0, cmd);
                            redoStack.clear();
                        };
                        newSelection.nameProperty().addListener(changeNameListener);
                        languageProxy.nameProperty().bindBidirectional(newSelection.nameProperty());

                        languageProxy.publishedYearProperty().bindBidirectional(newSelection.publishedYearProperty());
                        languageProxy.developerProperty().bindBidirectional(newSelection.developerProperty());
                        languageProxy.typingProperty().bindBidirectional(newSelection.typingProperty());
                        languageProxy.paradigmsProperty().bindBidirectional(newSelection.paradigmsProperty());
                        languageProxy.stackoverflowTagsProperty().bindBidirectional(newSelection.stackoverflowTagsProperty());
                    }
                }
        );
    }

    public final Language getLanguageProxy() {
        return languageProxy;
    }

    private Language getLanguage(int id) {
        return languages.stream()
                .filter(Language -> Language.getId() == id)
                .findAny()
                .orElse(null);
    }

    private static List<Language> getLanguages() {
        return Arrays.asList();
    }

    public ObservableList<Language> languages() {
        return languages;
    }

    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public int getSelectedLanguageId() {
        return selectedLanguageId.get();
    }

    public IntegerProperty selectedLanguageIdProperty() {
        return selectedLanguageId;
    }

    public void setSelectedLanguageId(int selectedLanguageId) {
        this.selectedLanguageId.set(selectedLanguageId);
    }

    public void save() {
        Language lang = this.getLanguage(this.getSelectedLanguageId());

        if (lang == null) return;

        System.out.println("Language: " + lang.getName());
    }

    public void addNew() {

    }

    public void remove() {

    }

    public void undo() {
        if (undoStack.isEmpty()) return;

        Command undoCommand = undoStack.remove(0);
        redoStack.add(0, undoCommand);
        undoCommand.undo();
    }

    public void redo() {
        if (redoStack.isEmpty()) return;

        Command redoCommand = redoStack.remove(0);
        undoStack.add(0, redoCommand);
        redoCommand.redo();
    }

    public void changeName(Language language, String value) {
        language.nameProperty().removeListener(changeNameListener);
        language.setName(value);
        language.nameProperty().addListener(changeNameListener);
    }

    public BooleanProperty disabledUndoProperty() {
        return disabledUndo;
    }

    public BooleanProperty disabledRedoProperty() {
        return disabledRedo;
    }
}