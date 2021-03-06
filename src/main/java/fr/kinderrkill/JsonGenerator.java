package fr.kinderrkill;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class JsonGenerator {

    private final String PREFIX = "[JsonGenerator] ";

    private JSONHelper jsonHelper;

    private File blockStateDir;
    private File modelBlocksDir;
    private File modelItemsDir;

    public void launch() {
        try {
            File baseFile = new File(JsonGenerator.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            File templatesConfig = new File(baseFile + "/assets/templates-config.json");
            jsonHelper = new JSONHelper(templatesConfig);

            blockStateDir = new File(baseFile + "/assets/blockstate/");
            modelBlocksDir = new File(baseFile + "/assets/model_blocks/");
            modelItemsDir = new File(baseFile + "/assets/model_items/");

            sendMessage("Started successfully at the location : " + baseFile.getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void generateJson(String modelName, String modelTexture) {
        Map<File, File> modelBlock = new HashMap<>();

        //Parents Files
        File blockStateParent = new File(blockStateDir + "/parents/" + jsonHelper.getStringFromTemplate("blockstate") + ".json");
        File modelItemParent = new File(modelItemsDir + "/parents/" + jsonHelper.getStringFromTemplate("model_items") + ".json");

        List<File> modelBlocksParent = new ArrayList<>();
        jsonHelper.getBlocksModels().forEach(model -> modelBlocksParent.add(new File(modelBlocksDir + "/parents/" + model + ".json")));

        //News Files
        File newBlockState = new File(blockStateDir + "/" + jsonHelper.getTemplateName() + "_" + modelName + ".json");
        File newModelItems = new File(modelItemsDir + "/" + jsonHelper.getTemplateName() + "_" + modelName + ".json");

        //Define models blocks
        for (File f : modelBlocksParent) {
            if(modelBlocksParent.size() > 1) {
                modelBlock.put(f, new File(modelBlocksDir + "/" + f.getName().replaceFirst("[.][^.]+$", "") + "_" + modelName + ".json"));
            } else {
                modelBlock.put(f, new File(modelBlocksDir + "/" + modelName + ".json"));
            }
        }

        //Create renamed JSON
        createJson(newBlockState, jsonHelper.getRenamedObject(blockStateParent, "$BLOCK", modelName));
        createJson(newModelItems, jsonHelper.getRenamedObject(modelItemParent, "$ITEM", modelName));

        for (File f : modelBlock.keySet()) {
            File destination = modelBlock.get(f);
            createJson(destination, jsonHelper.getRenamedObject(f, "$TEXTURE", modelTexture));
        }
    }

    private void createJson(File destination, JSONObject jsonObject) {
        try (FileWriter writer = new FileWriter(destination)) {
            writer.write(jsonObject.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONHelper getJsonHelper() {
        return jsonHelper;
    }

    public void sendMessage(String message) {
        Main.sendMessage(PREFIX + message);
    }

}
