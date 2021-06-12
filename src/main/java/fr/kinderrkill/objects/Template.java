package fr.kinderrkill.objects;

import org.json.simple.JSONArray;

public class Template {

    private final String key;
    private final String blockstate;
    private final String modelsBlocks;
    private final String modelItem;

    public Template(String key, String blockstate, String modelsBlocks, String modelItem) {
        this.key = key;
        this.blockstate = blockstate;
        this.modelsBlocks = modelsBlocks;
        this.modelItem = modelItem;
    }

    public Template(Object key, Object blockstate, Object modelsBlocks, Object modelItem) {
        this.key = (String) key;
        this.blockstate = (String) blockstate;
        if (modelsBlocks instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) modelsBlocks;
            this.modelsBlocks = jsonArray.toJSONString();
        } else {
            this.modelsBlocks = (String) modelsBlocks;
        }
        this.modelItem = (String) modelItem;
    }

    public String getKey() {
        return key;
    }

    public String getBlockState() {
        return blockstate;
    }

    public String getModelsBlocks() {
        return modelsBlocks;
    }

    public String getModelItem() {
        return modelItem;
    }
}
