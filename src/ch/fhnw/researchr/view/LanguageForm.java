package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.Language;
import ch.fhnw.researchr.model.LanguagePM;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.converter.NumberStringConverter;

public class LanguageForm extends GridPane implements ViewMixin {

    private final LanguagePM model;

    private Label     nameLabel;
    private TextField nameField;

    private Label     publishedYearLabel;
    private TextField publishedYearField;

    private Label     developerLabel;
    private TextField developerField;

    private Label     typingLabel;
    private TextField typingField;

    private Label     paradigmsLabel;
    private TextField paradigmsField;

    private Label     stackoverflowTagsLabel;
    private TextField stackoverflowTagsField;

    private PieChart pieChart;

    public LanguageForm(LanguagePM model) {
        this.model = model;
        getStyleClass().add("form");
        init();
    }

    @Override
    public void initializeControls() {
        nameLabel = new Label("Name");
        nameField = new TextField();

        publishedYearLabel = new Label("Erstellungsjahr");
        publishedYearField = new TextField();

        developerLabel = new Label("Entwickler");
        developerField = new TextField();

        typingLabel = new Label("Typ");
        typingField = new TextField();

        paradigmsLabel = new Label("Paradigmen");
        paradigmsField = new TextField();

        stackoverflowTagsLabel = new Label("Stackoverflow Tags");
        stackoverflowTagsField = new TextField();

        pieChart = new PieChart(model.getPieChartData());
    }

    @Override
    public void layoutControls() {
        ColumnConstraints grow = new ColumnConstraints();
        grow.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(new ColumnConstraints(), grow);

        addRow(1, nameLabel, nameField);
        addRow(2, publishedYearLabel, publishedYearField);
        addRow(3, developerLabel, developerField);
        addRow(4, typingLabel, typingField);
        addRow(5, paradigmsLabel, paradigmsField);
        addRow(6, stackoverflowTagsLabel, stackoverflowTagsField);
        addRow(7, pieChart);
    }

    @Override
    public void addBindings() {
        Language proxy = model.getLanguageProxy();

        nameField.textProperty().bindBidirectional(proxy.nameProperty());
        publishedYearField.textProperty().bindBidirectional(proxy.publishedYearProperty(), new NumberStringConverter());
        developerField.textProperty().bindBidirectional(proxy.developerProperty());
        typingField.textProperty().bindBidirectional(proxy.typingProperty());
        paradigmsField.textProperty().bindBidirectional(proxy.paradigmsProperty());
        stackoverflowTagsField.textProperty().bindBidirectional(proxy.stackoverflowTagsProperty(), new NumberStringConverter());

    }
}
