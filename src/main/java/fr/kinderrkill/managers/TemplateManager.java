package fr.kinderrkill.managers;

import fr.kinderrkill.JSONGenerator;
import fr.kinderrkill.objects.Template;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TemplateManager {

    private final JSONGenerator generator;

    private final String fileName = "templates-config.json";
    private final File templateFile;

    private List<Template> templates;

    private Template selectedTemplate;

    // JSON Utils
    private final JSONParser parser = new JSONParser();

    public TemplateManager(JSONGenerator generator) {
        this.generator = generator;

        this.templateFile = new File(this.generator.getBaseFile() + "/assets/" + this.fileName);
        if (!this.templateFile.exists()) return;

        this.templates = new ArrayList<>();
    }

    public void loadTemplates() {
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(this.templateFile));
            object.forEach((key, obj) -> {
                JSONObject value = (JSONObject) obj;
                this.templates.add(new Template(key, value.get("blockstate"), value.get("model_blocks"), value.get("model_items")));
            });
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
    }

    public List<Template> getTemplates() {
        return this.templates;
    }

    public Optional<Template> getTemplate(String key) {
        return this.templates.stream().filter(value -> value.getKey().equalsIgnoreCase(key)).findFirst();
    }

    public void defineSelectedTemplate(Template template) {
        this.selectedTemplate = template;
    }

    public Template getSelectedTemplate() {
        return this.selectedTemplate;
    }

    // Utils
    public List<String> getTemplatesForList() {
        List<String> list = new ArrayList<>();
        this.templates.forEach(value -> {
            String modelBlocksValue = value.getModelsBlocks() != null ? value.getModelsBlocks() : value.getArrayofModelsBlocks() != null ? value.getArrayofModelsBlocks().toString() : "ERROR 404";
            list.add(new String(value.getKey() + " | BlockState : " + value.getBlockState() + " | ModelBlocs : " + modelBlocksValue + " | ModelItem : " + value.getModelItem()));
        });
        return list;
    }

    public JSONObject getRenamedTemplate(File base, String key, String value) {
        try {
            System.out.println("File : " + base + " for KEY : " + key + " with value " + value);
            JSONObject jsonFile = (JSONObject) parser.parse(new FileReader(base));
            String json = jsonFile.toJSONString();
            json = json.replace("\\/", "/").replace(key, value);
            JSONObject jsonObject = (JSONObject) parser.parse(json);
            return jsonObject;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
