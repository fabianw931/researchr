package ch.fhnw.researchr.model;

public class ProgrammingLanguage {

    private String name;
    private short publishYear;
    private String developer;
    private String typing;
    private String paradigms;
    private int stackoverflowTags;

    public ProgrammingLanguage(String name, short publishYear, String developer,
                               String typing, String paradigms, int stackoverflowTags) {

        this.name = name;
        this.publishYear = publishYear;
        this.developer = developer;
        this.typing = typing;
        this.paradigms = paradigms;
        this.stackoverflowTags = stackoverflowTags;

    }

    public ProgrammingLanguage(String name, String developer){
        this.name = name;
        this.developer = developer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(short publishYear) {
        this.publishYear = publishYear;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getTyping() {
        return typing;
    }

    public void setTyping(String typing) {
        this.typing = typing;
    }

    public String getParadigms() {
        return paradigms;
    }

    public void setParadigms(String paradigms) {
        this.paradigms = paradigms;
    }

    public int getStackoverflowTags() {
        return stackoverflowTags;
    }

    public void setStackoverflowTags(int stackoverflowTags) {
        this.stackoverflowTags = stackoverflowTags;
    }
}
