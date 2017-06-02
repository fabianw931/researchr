package ch.fhnw.researchr.model;

import org.json.*;

public class FileHandler {

    private String saveFileLocation;

    public FileHandler() {

    }

    public void importFromJSON() {

        JSONObject obj = new JSONObject("../../../programminglanguages.json");
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++){
            String post_id = arr.getJSONObject(i).getString("post_id");
        }

    }

    public void exportToJSON() {

    }

    public void save() {

    }

    public void read() {

    }

    public String getSaveFileLocation() {
        return saveFileLocation;
    }

    public void setSaveFileLocation(String saveFileLocation) {
        this.saveFileLocation = saveFileLocation;
    }

}
