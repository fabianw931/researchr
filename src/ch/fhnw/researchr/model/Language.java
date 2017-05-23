package ch.fhnw.researchr.model;

import java.util.List;

public class Language {

    /* name of the programming language */
    private String name;

    /* the year the programming language was created */
    private short year;

    /* developer of the programming language */
    private String developer;

    /* type/style of the programming language
    * a list of strings
    * */
    private List<String> typification;

    /* paradigms of the programming language
    * a list of Strings
    * */
    private List<String> paradigms;

    /* number of stackoverflow tags */
    private int numberOfTags;

    public Language(String name, short year, String developer, List<String> typification, List<String> paradigms, int numberOfTags) {
        this.name = name;
        this.year = year;
        this.developer = developer;
        this.typification = typification;
        this.paradigms = paradigms;
        this.numberOfTags = numberOfTags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public List<String> getTypification() {
        return typification;
    }

    public void setTypification(List<String> typification) {
        this.typification = typification;
    }

    public List<String> getParadigms() {
        return paradigms;
    }

    public void setParadigms(List<String> paradigms) {
        this.paradigms = paradigms;
    }

    public int getNumberOfTags() {
        return numberOfTags;
    }

    public void setNumberOfTags(int numberOfTags) {
        this.numberOfTags = numberOfTags;
    }
}
