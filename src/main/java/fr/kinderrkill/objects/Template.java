package fr.kinderrkill.objects;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Template {

    private final String key;
    private final String blockstate;
    private String modelsBlocks;
    private final String modelItem;

    private JSONArray modelsBlocksArray;
    private boolean haveMultipleModelsBlocks;

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
            this.modelsBlocksArray = (JSONArray) modelsBlocks;
            this.haveMultipleModelsBlocks = true;
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

    public boolean isMultipleModelsBlocks() { return haveMultipleModelsBlocks; }

    public JSONArray getArrayofModelsBlocks() { return modelsBlocksArray; }

    public List<String> getModelsBlocksList() {
        if (haveMultipleModelsBlocks) {
            List<String> list = new ArrayList<>();
            modelsBlocksArray.forEach(model -> list.add(model.toString()));
            return list;
        } else {
            return Collections.singletonList(modelsBlocks);
        }
    }

    public String getModelItem() {
        return modelItem;
    }
}
