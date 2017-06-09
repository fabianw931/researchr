package ch.fhnw.researchr.model;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    private String fileLocation = "programminglanguages.json";

    public FileHandler() {

    }

    public void save(ObservableList<Language> languages){


        ObservableList<Language> langs = languages;
        JsonWriter writer = null;


        try {
            writer = new JsonWriter(new FileWriter(fileLocation));

            writer.beginObject();
            writer.name("programmingLanguages");
            writer.beginArray();

            for (Language l : langs){

                writer.beginObject();
                writer.name("Name").value(l.getName());
                writer.name("Erscheinungsjahr").value(l.getPublishedYear());
                writer.name("Entwickler").value(l.getDeveloper());
                writer.name("Typisierung").value(l.getTyping());
                writer.name("Paradigmen").value(l.getParadigms());
                writer.name("StackoverflowTags").value(l.getStackoverflowTags());
                writer.endObject();
            }

            writer.endArray();
            writer.endObject();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public JsonArray read() {

        try {

            JsonElement jElem = new JsonParser().parse(new FileReader(fileLocation));

            System.out.println(jElem);

            JsonObject jObj = jElem.getAsJsonObject();

            JsonArray jArr = jObj.getAsJsonArray("programmingLanguages");

            return jArr;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

}
