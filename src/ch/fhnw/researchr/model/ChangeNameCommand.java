package ch.fhnw.researchr.model;

/**
 * Created by nadimsalloum on 06.06.17.
 */
public class ChangeNameCommand implements Command {

    private LanguagePM model;
    private Language language;

    private String oldValue;
    private String newValue;

    public ChangeNameCommand(LanguagePM model, Language language, String oldValue, String newValue) {
        this.model = model;
        this.language = language;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void undo() {
        model.changeName(language, oldValue);
    }

    @Override
    public void redo() {
        model.changeName(language, newValue);
    }
}
