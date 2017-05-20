package ch.fhnw.researchr.view;

import ch.fhnw.researchr.model.ResearchrModel;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class ResearchrView extends BorderLayout{

    final private Stage stage;
    final private ResearchrModel model;

    public ResearchrView(Stage stage, ResearchrModel model){
        this.stage = stage;
        this.model = model;
    }

    public void start() {
        stage.show();
    }

    public void stop() {
        stage.hide();
    }
}
