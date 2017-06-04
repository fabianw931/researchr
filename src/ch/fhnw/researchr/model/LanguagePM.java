package ch.fhnw.researchr.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;

public class LanguagePM {
    private final StringProperty applicationTitle  = new SimpleStringProperty("Programmiersprachen");

    private final IntegerProperty selectedLanguageId = new SimpleIntegerProperty(-1);

    private ObservableList<Language> languages = FXCollections.observableArrayList();

    private final Language languageProxy = new Language();

    public LanguagePM() {
        this(getLanguages());
    }

    public LanguagePM(List<Language> languageList) {
        languages.addAll(languageList);

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
                    }

                    if (newSelection != null) {
                        languageProxy.idProperty().bindBidirectional(newSelection.idProperty());
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

}