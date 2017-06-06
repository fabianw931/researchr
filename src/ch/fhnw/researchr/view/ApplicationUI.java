package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.LanguagePM;
import javafx.scene.layout.BorderPane;

public class ApplicationUI extends BorderPane implements ViewMixin {
    private final LanguagePM model;

    private Toolbar      toolbar;
    private LanguageForm languageForm;
    private LanguageListView languageListView;

    public ApplicationUI(LanguagePM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        languageForm = new LanguageForm(model);
        toolbar = new Toolbar(model);
        languageListView = new LanguageListView(model);
    }

    @Override
    public void layoutControls() {
        setTop(toolbar);
        setCenter(languageForm);
        setLeft(languageListView);
        //setCenter(new VBox(countryHeader, countryForm));
    }

}
