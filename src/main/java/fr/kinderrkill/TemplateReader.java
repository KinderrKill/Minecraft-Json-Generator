package fr.kinderrkill;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TemplateReader {

    private JSONParser parser = new JSONParser();

    private File jsonFile;
    private String selectedTemplate = "";

    public TemplateReader(File inputFile) {
        this.jsonFile = inputFile;
    }

    public void initTemplate(String templateName) {
        this.selectedTemplate = templateName;
    }

    public List<JSONObject> getAvailableTemplate() {
        try {
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(this.jsonFile));
            return new ArrayList<JSONObject>(jsonFile.keySet());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJsonObject() {
        try {
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(this.jsonFile));
            return jsonFile;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getJsonArray(String key) {
        JSONObject obj = getJsonObject();
        return obj.get(key) != null ? (String)obj.get(key) : null;
    }

    public JSONObject getJsonObjectFromConfig() {
        try {
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(this.jsonFile));
            Iterator<String> keys = jsonFile.keySet().iterator();
            while(keys.hasNext()) {
                String key = keys.next();
                JSONObject jsonObject = (JSONObject) jsonFile.get(key);
                if(jsonObject != null) {
                    return jsonObject;
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getJsonArrayFromConfig(String key) {
        JSONObject jsonObj = getJsonObjectFromConfig();
        return jsonObj.get(key) != null ? (String)jsonObj.get(key) : null;
    }

    public JSONObject renamedJSONObject(String key, String value) {
        System.out.println("DEBUG OBJ START");
        JSONObject obj = getJsonObject();
        System.out.println("DEBUG OBJ START");
        obj.replace(key, value);
        return obj;
    }

    public boolean templateExist(String key) {
        return getAvailableTemplate().toString().contains(key);
    }

}
