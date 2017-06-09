package ch.fhnw.researchr.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class LanguagePM {
    private final StringProperty applicationTitle  = new SimpleStringProperty("Programmiersprachen");

    private final IntegerProperty selectedLanguageId = new SimpleIntegerProperty(-1);

    public ObservableList<Language> languages = FXCollections.observableArrayList();

    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private FilteredList<Language> filteredData;

    private final Language languageProxy = new Language();

    private final ObservableList<Command> undoStack = FXCollections.observableArrayList();
    private final ObservableList<Command> redoStack = FXCollections.observableArrayList();

    private BooleanProperty disabledUndo   = new SimpleBooleanProperty();
    private BooleanProperty disabledRedo   = new SimpleBooleanProperty();
    private BooleanProperty disabledRemove = new SimpleBooleanProperty();
    private BooleanProperty disabledSave   = new SimpleBooleanProperty();

    private StringProperty searchText   = new SimpleStringProperty();
    private FileHandler fileHandler;

    private final ChangeListener propertyChangeListenerForUndoSupport = (observable, oldValue, newValue) -> {
        redoStack.clear();
        undoStack.add(0, new ValueChangeCommand(this, (Property) observable, oldValue, newValue));
    };

    public LanguagePM() {
        this(getLanguages());
    }

    public LanguagePM(List<Language> languageList) {

        languages.addAll(languageList);

        disabledUndo.bind(Bindings.isEmpty(undoStack));
        disabledRedo.bind(Bindings.isEmpty(redoStack));
        disabledRemove.bind(Bindings.isEmpty(languages));
        disabledSave.bind(Bindings.isEmpty(undoStack));

        selectedLanguageIdProperty().addListener((observable, oldValue, newValue) -> {
                    Language oldSelection = getLanguage(oldValue.intValue());
                    Language newSelection = getLanguage(newValue.intValue());

                    if (oldSelection != null) {
                        unbindFromProxy(oldSelection);
                        disableUndoSupport(oldSelection);
                    }

                    if (newSelection != null) {
                        bindToProxy(newSelection);
                        enableUndoSupport(newSelection);
                    }
                }
        );


        setSelectedLanguageId(1);

        selectedLanguageIdProperty().addListener(propertyChangeListenerForUndoSupport);
    }

    public final Language getLanguageProxy() {
        return languageProxy;
    }

    public Language getLanguage(int id) {
        return languages.stream()
                .filter(Language -> Language.getId() == id)
                .findAny()
                .orElse(null);
    }

    private static List<Language> getLanguages() {
        FileHandler fileHandler = new FileHandler();
        JsonArray jArr = fileHandler.read();

        JsonObject jObj;
        String navFolder = "/src/ch/fhnw/researchr/resources/img/languages/";
        String blankImg = "/src/ch/fhnw/researchr/resources/img/languages/blank.png";
        int i = 0;

        List<Language> list = new ArrayList();

        while(i < jArr.size()) {
            jObj = jArr.get(i).getAsJsonObject();
            i++;

            String imgPath = navFolder + jObj.get("Name").getAsString().toLowerCase() + ".png";

            String filePath = new File("").getAbsolutePath().concat(imgPath);

            if (!new File(filePath).exists()){
                filePath = new File("").getAbsolutePath().concat(blankImg);
            }

            try {
                filePath = URLEncoder.encode(filePath, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            Language lang = new Language(
                    jObj.get("Name").getAsString(),
                    jObj.get("Erscheinungsjahr").getAsInt(),
                    jObj.get("Entwickler").getAsString(),
                    jObj.get("Typisierung").getAsString(),
                    jObj.get("Paradigmen").getAsString(),
                    jObj.get("StackoverflowTags").getAsInt(),
                    new ImageView(new Image("file:" + filePath, 50, 50, false, false))
            );

            list.add(lang);
        }

        return list;
    }

    public ObservableList<Language> languages() {
       return languages;
    }

    public void setLanguages(FilteredList<Language> lang) {
         this.languages = lang;
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

    public <T> void setPropertyValueWithoutUndoSupport(Property<T> property, T newValue){
        property.removeListener(propertyChangeListenerForUndoSupport);
        property.setValue(newValue);
        property.addListener(propertyChangeListenerForUndoSupport);
    }

    public void save() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.save(languages());
    }

    public void addNew() {
        Language lang = new Language();
        System.out.println(lang.getId());
        languages.add(lang);
        setSelectedLanguageId(lang.getId());
    }

    public void remove() {
        Language lang = this.getLanguage(this.getSelectedLanguageId());
        int index = languages.indexOf(lang);
        languages.remove(lang);
        if (index > 0) {
            index--;
        }

        // if all languages have been deleted
        if (languages.size() == 0) {
            addNew();
            setSelectedLanguageId(1);
            return;
        }

        // point to the previous language
        if (index < languages.size()) {
            Language newPointer = languages.get(index);
            setSelectedLanguageId(newPointer.getId());
            return;
        }

        // point to the next language
        Language newPointer = languages.get(languages.size() - 1);
        setSelectedLanguageId(newPointer.getId());
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

    public BooleanProperty disabledUndoProperty() {
        return disabledUndo;
    }

    public BooleanProperty disabledRedoProperty() {
        return disabledRedo;
    }

    public BooleanProperty disabledRemoveProperty() {
        return disabledRemove;
    }

    public BooleanProperty disabledSaveProperty() {
        return disabledSave;
    }

    public String getSearchText() {
        return searchText.get();
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText.set(searchText);
    }

    public FilteredList<Language> filtered() {
        filteredData = new FilteredList<>(languages, s -> true);
        filteredData.setPredicate(language -> {
            // If filter text is empty, display all persons.
            if (getSearchText() == null || getSearchText().isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = getSearchText().toLowerCase();

            if (language.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            } else if (language.getDeveloper().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            } else if (language.getParadigms().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            } else if (language.getTyping().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            }
            return false; // Does not match.
        });

        return filteredData;
    }

    private void disableUndoSupport(Language language) {
        language.idProperty().removeListener(propertyChangeListenerForUndoSupport);
        language.nameProperty().removeListener(propertyChangeListenerForUndoSupport);
        language.publishedYearProperty().removeListener(propertyChangeListenerForUndoSupport);
        language.developerProperty().removeListener(propertyChangeListenerForUndoSupport);
        language.typingProperty().removeListener(propertyChangeListenerForUndoSupport);
        language.paradigmsProperty().removeListener(propertyChangeListenerForUndoSupport);
        language.stackoverflowTagsProperty().removeListener(propertyChangeListenerForUndoSupport);
    }

    private void enableUndoSupport(Language language) {
        language.idProperty().addListener(propertyChangeListenerForUndoSupport);
        language.nameProperty().addListener(propertyChangeListenerForUndoSupport);
        language.publishedYearProperty().addListener(propertyChangeListenerForUndoSupport);
        language.developerProperty().addListener(propertyChangeListenerForUndoSupport);
        language.typingProperty().addListener(propertyChangeListenerForUndoSupport);
        language.paradigmsProperty().addListener(propertyChangeListenerForUndoSupport);
        language.stackoverflowTagsProperty().addListener(propertyChangeListenerForUndoSupport);
    }

    private void unbindFromProxy(Language language) {
        languageProxy.idProperty().unbindBidirectional(language.idProperty());
        languageProxy.nameProperty().unbindBidirectional(language.nameProperty());
        languageProxy.publishedYearProperty().unbindBidirectional(language.publishedYearProperty());
        languageProxy.developerProperty().unbindBidirectional(language.developerProperty());
        languageProxy.typingProperty().unbindBidirectional(language.typingProperty());
        languageProxy.paradigmsProperty().unbindBidirectional(language.paradigmsProperty());
        languageProxy.stackoverflowTagsProperty().unbindBidirectional(language.stackoverflowTagsProperty());
    }

    private void bindToProxy(Language language) {
        languageProxy.idProperty().bindBidirectional(language.idProperty());
        languageProxy.nameProperty().bindBidirectional(language.nameProperty());
        languageProxy.publishedYearProperty().bindBidirectional(language.publishedYearProperty());
        languageProxy.developerProperty().bindBidirectional(language.developerProperty());
        languageProxy.typingProperty().bindBidirectional(language.typingProperty());
        languageProxy.paradigmsProperty().bindBidirectional(language.paradigmsProperty());
        languageProxy.stackoverflowTagsProperty().bindBidirectional(language.stackoverflowTagsProperty());
    } 

    public ObservableList<PieChart.Data> getPieChartData() {

        for (Language l : languages()){
            pieChartData.add(new PieChart.Data(l.getName(), l.getStackoverflowTags()));
        }

        return pieChartData;
    }

    private void naiveAddData(String name, double value){
        pieChartData.add(new PieChart.Data(name, value));
    }

    public void updatePieChart(){
        String name = languages().get(getSelectedLanguageId()).getName();
        int value = languages().get(getSelectedLanguageId()).getStackoverflowTags();
        for(PieChart.Data d : pieChartData){
            if(d.getName().equals(name)){
                d.setPieValue(value);
                return;
            }
        }
        naiveAddData(name, value);
    }



}