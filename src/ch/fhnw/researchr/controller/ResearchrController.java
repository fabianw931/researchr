package ch.fhnw.researchr.controller;

import ch.fhnw.researchr.model.LanguagePM;
import ch.fhnw.researchr.view.ResearchrView;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ResearchrController implements EventHandler {

    private final ResearchrView view = new ResearchrView(new LanguagePM());

    public ResearchrController() {

    }

    private void addEventHandlers(){

    }

    @Override
    public void handle(Event event) {
        final Object source = event.getSource();

        if (source.equals(view.getNewBtn())){

        }else if (source.equals(view.getRedoBtn())){

        }else if (source.equals(view.getSaveBtn())){

        }else if (source.equals(view.getRemoveBtn())){

        }else if (source.equals(view.getUndoBtn())){

        }else if (source.equals(view.getRedoBtn())){

        }
    }

    public ResearchrView getView() {
        return view;
    }
}
