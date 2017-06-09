package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.Language;
import ch.fhnw.researchr.model.LanguagePM;
import javafx.geometry.Insets;
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
        pieChart.setTitle("Stackoverflow Popularität");
        pieChart.setLegendVisible(false);
    }

    @Override
    public void layoutControls() {
        ColumnConstraints grow = new ColumnConstraints();
        grow.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(new ColumnConstraints(), grow);

        gridLinesVisibleProperty().setValue(false);

        setPadding(new Insets(25, 25, 25, 25));
        setHgap(10);
        setVgap(10);

        add(nameLabel, 0, 0);
        add(nameField, 1, 0);

        add(publishedYearLabel, 0, 1);
        add(publishedYearField, 1, 1);

        add(developerLabel, 0, 2);
        add(developerField, 1, 2);

        add(typingLabel, 0, 3);
        add(typingField, 1, 3);

        add(paradigmsLabel, 0, 4);
        add(paradigmsField, 1, 4);

        add(stackoverflowTagsLabel, 0, 5);
        add(stackoverflowTagsField, 1, 5);

        add(pieChart,0,6,2 ,2);
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
