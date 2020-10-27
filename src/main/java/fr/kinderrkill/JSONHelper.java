package fr.kinderrkill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JSONHelper {

    private final JSONParser parser;

    private File configFile;
    private String templateName;

    public JSONHelper(File file) {
        parser = new JSONParser();
        configFile = file;
    }

    public void initTemplateObject(String key) {
        templateName = key;
    }

    public JSONObject getRenamedObject(File base, String key, String value) {
        try {
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(base));
            String json = jsonFile.toJSONString();
            System.out.println("Actuel Object : " + json);
            json = json.replace("\\/", "/").replace(key, value);
            JSONObject jsonObject = (JSONObject) parser.parse(json);
            System.out.println("Renamed Object : " + jsonObject);
            return jsonObject;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getTemplateFromConfig(String key) {
        try {
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(this.configFile));
            return (JSONObject) jsonFile.get(key);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getStringFromTemplate(String key) {
        JSONObject jsonFile = getTemplateFromConfig(templateName);
        return (String) jsonFile.get(key);
    }

    public List<JSONObject> getAvailableTemplates() {
        try {
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(this.configFile));
            return new ArrayList<JSONObject>(jsonFile.keySet());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean haveMultipleModels() {
        return getTemplateFromConfig(templateName).get("model_blocks") instanceof JSONArray;
    }

    public List<String> getBlocksModels() {
        if (haveMultipleModels()) {
            JSONArray jsonFile = (JSONArray) getTemplateFromConfig(templateName).get("model_blocks");
            List<String> list = new ArrayList<>();
            jsonFile.forEach(model -> list.add(model.toString()));
            return list;
        }
        return Arrays.asList(getTemplateFromConfig(templateName).get("model_blocks").toString());
    }

    public String getTemplateName() {
        return templateName;
    }

}
