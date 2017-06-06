package ch.fhnw.researchr.model;


public interface Command {
    void undo();
    void redo();
}
