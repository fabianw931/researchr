package ch.fhnw.researchr.controller;

import ch.fhnw.researchr.model.ResearchrModel;
import ch.fhnw.researchr.view.ResearchrView;

public class ResearchrController {

    final private ResearchrView view;
    final private ResearchrModel model;

    public ResearchrController(ResearchrView view, ResearchrModel model) {
        this.view = view;
        this.model = model;
    }
}
