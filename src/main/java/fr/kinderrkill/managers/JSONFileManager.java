package fr.kinderrkill.managers;

import fr.kinderrkill.JSONGenerator;
import fr.kinderrkill.objects.Template;
import fr.kinderrkill.utils.Dialogs;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFileManager {

    private final JSONGenerator jsonGenerator = JSONGenerator.getInstance();

    private final File blockStateParentDir = new File(jsonGenerator.getBaseFile() + "/assets/blockstate/parents/");
    private final File modelBlockParentDir = new File(jsonGenerator.getBaseFile() + "/assets/model_blocks/parents/");
    private final File modelItemParentDir = new File(jsonGenerator.getBaseFile() + "/assets/model_items/parents/");

    private final File blockStateDir = new File(jsonGenerator.getBaseFile() + "/assets/blockstate/");
    private final File modelBlockDir = new File(jsonGenerator.getBaseFile() + "/assets/model_blocks/");
    private final File modelItemDir = new File(jsonGenerator.getBaseFile() + "/assets/model_items/");

    public void generateJSON(Template template, String blockstate, String texture) {
        File blockStateParentFile = new File(blockStateParentDir + "/" + template.getBlockState() + ".json");
        List<File> modelBlocksParentList = new ArrayList<>();
        if (template.isMultipleModelsBlocks()) {
            int index = 0;
            for (String str : template.getModelsBlocksList()) {
                modelBlocksParentList.add(new File(modelBlockParentDir + "/" + str + ".json"));
            }
        }
        File modelItemParentFile = new File(modelItemParentDir + "/" + template.getModelItem() + ".json");

        // Final file
        File newBlockstate = new File(blockStateDir + "/" + blockstate + ".json");

        Map<File, File> modelBlockParentChild = new HashMap<>();
        for (File file : modelBlocksParentList) {
            if (modelBlocksParentList.size() > 1) {
                modelBlockParentChild.put(file, new File(modelBlockDir + "/" + file.getName().replaceFirst("[.][^.]+$", "") + "_" + blockstate + ".json"));
            } else {
                modelBlockParentChild.put(file, new File(modelBlockDir + "/" + blockstate + ".json"));
            }
        }

        File newModelItem = new File(modelItemDir + "/" + blockstate + ".json");

        //Generate new JSON file
        createJson(newBlockstate, jsonGenerator.templateManager.getRenamedTemplate(blockStateParentFile, "$BLOCK", blockstate));
        for (File file : modelBlockParentChild.keySet()) {
            File destination = modelBlockParentChild.get(file);
            createJson(destination, jsonGenerator.templateManager.getRenamedTemplate(file, "$TEXTURE", texture));
        }
        createJson(newModelItem, jsonGenerator.templateManager.getRenamedTemplate(modelItemParentFile, "$ITEM", blockstate));

        Dialogs.generateWithSuccess();
    }

    private void createJson(File destination, JSONObject jsonObject) {
        try (FileWriter writer = new FileWriter(destination)) {
            writer.write(jsonObject.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
