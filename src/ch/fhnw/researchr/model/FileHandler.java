package ch.fhnw.researchr.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileHandler {

    private String saveFileLocation;

    public FileHandler() {

    }

    public void save() {

    }

    public void read() {
        try {

            JsonElement jElem = new JsonParser().parse(new FileReader("programminglanguages.json"));

            JsonObject jObj = jElem.getAsJsonObject();
            //jObj = jObj.getAsJsonObject("programmingLanguages");

            JsonArray jArr = jObj.getAsJsonArray("programmingLanguages");

            int i = 0;
            while(i < jArr.size()){
                jObj = jArr.get(i).getAsJsonObject();
                System.out.println(jObj.get("Name"));
                System.out.println(jObj.get("Erscheinungsjahr"));
                System.out.println(jObj.get("Entwickler"));
                System.out.println(jObj.get("Typisierung"));
                System.out.println(jObj.get("Paradigmen"));
                System.out.println(jObj.get("StackoverflowTags"));

                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getSaveFileLocation() {
        return saveFileLocation;
    }

    public void setSaveFileLocation(String saveFileLocation) {
        this.saveFileLocation = saveFileLocation;
    }

}
