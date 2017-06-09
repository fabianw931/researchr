package ch.fhnw.researchr.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Language {

    private static int counter = 1;

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty publishedYear = new SimpleIntegerProperty();
    private StringProperty developer = new SimpleStringProperty();
    private StringProperty typing = new SimpleStringProperty();
    private StringProperty paradigms = new SimpleStringProperty();
    private IntegerProperty stackoverflowTags = new SimpleIntegerProperty();

    public Language(String name, int publishedYear, String developer,
                    String typing, String paradigms, int stackoverflowTags) {

        setId(counter++);
        setName(name);
        setPublishedYear(publishedYear);
        setDeveloper(developer);
        setTyping(typing);
        setParadigms(paradigms);
        setStackoverflowTags(stackoverflowTags);

    }

    public Language(String name, String developer) {
        setId(counter++);
        setName(name);
        setDeveloper(developer);
    }

    public Language() {
        setId(counter++);
        setPublishedYear(LocalDate.now().getYear());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPublishedYear() {
        return publishedYear.get();
    }

    public IntegerProperty publishedYearProperty() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear.set(publishedYear);
    }

    public String getDeveloper() {
        return developer.get();
    }

    public StringProperty developerProperty() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer.set(developer);
    }

    public String getTyping() {
        return typing.get();
    }

    public StringProperty typingProperty() {
        return typing;
    }

    public void setTyping(String typing) {
        this.typing.set(typing);
    }

    public String getParadigms() {
        return paradigms.get();
    }

    public StringProperty paradigmsProperty() {
        return paradigms;
    }

    public void setParadigms(String paradigms) {
        this.paradigms.set(paradigms);
    }

    public int getStackoverflowTags() {
        return stackoverflowTags.get();
    }

    public IntegerProperty stackoverflowTagsProperty() {
        return stackoverflowTags;
    }

    public void setStackoverflowTags(int stackoverflowTags) {
        this.stackoverflowTags.set(stackoverflowTags);
    }
}
