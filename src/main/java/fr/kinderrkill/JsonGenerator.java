package fr.kinderrkill;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class JsonGenerator {

    private final String s = "[JsonGenerator] ";
    private final Scanner keyboard = new Scanner(System.in);

    private String templateName = "";

    private File baseFile;
    private File templatesConfig;
    private File blockstateDir;
    private File modelBlocksDir;
    private File modelItemsDir;

    private String modelName = "";
    private String modelTexture = "";

    public void start() throws URISyntaxException {
        baseFile = new File(JsonGenerator.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        templatesConfig = new File(baseFile + "/assets/templates-config.json");
        blockstateDir = new File(baseFile + "/assets/blockstate/");
        modelBlocksDir = new File(baseFile + "/assets/model-blocks/");
        modelItemsDir = new File(baseFile + "/assets/model-items/");

        sendMessage("Started successfully at the location : " + baseFile.getAbsolutePath());
        askTemplate();
    }

    public void askTemplate() {
        TemplateReader templateReader = new TemplateReader(templatesConfig);
        sendMessage("Available template : " + templateReader.getAvailableTemplate());
        sendMessage("What JSON Template do you want to use ?");
        templateName = keyboard.nextLine();

        sendMessage("> Template '" + templateName + "' selected - State = " + (templateReader.templateExist(templateName) ? "found !" : "not found !"));
        if (!templateReader.templateExist(templateName)) {
            askTemplate();
        } else {
            templateReader.initTemplate(templateName);
            askForName();
        }
    }

    private void askForName() {
        sendMessage("What is the name of your Block ?");
        modelName = keyboard.nextLine();
        askForTexture();
    }

    private void askForTexture() {
        sendMessage("What is the name of your texture ?");
        modelTexture = keyboard.nextLine();
        initDirectoryAndCopy();
    }

    private void initDirectoryAndCopy() {
        TemplateReader reader = new TemplateReader(templatesConfig);
        File newBlockstateDir = new File(blockstateDir + "/parents/" + reader.getJsonArrayFromConfig("blockstate") + ".json");
        File newModelBlocksDir = new File(modelBlocksDir + "/parents/" + reader.getJsonArrayFromConfig("model-blocs") + ".json");
        File newModelItemsDir = new File(modelItemsDir + "/parents/" + reader.getJsonArrayFromConfig("model-items") + ".json");

        File newBlockstate = new File(blockstateDir + "/" + modelName + ".json");
        File newModelBlocks = new File(modelBlocksDir + "/" + modelName + ".json");
        File newModelItems = new File(modelItemsDir + "/" + modelName + ".json");

        System.out.println("EXIST : " + newBlockstateDir.exists());
        TemplateReader r1 = new TemplateReader(newBlockstateDir);
        System.out.println("TEST 2 : " + r1.getJsonObject());
        try {
            copyFileUsingStream(newBlockstateDir, newBlockstate);
            renamedJson(newBlockstate, "texture");

            copyFileUsingStream(newModelBlocksDir, newModelBlocks);
            renamedJson(newModelBlocks, "texture");

            copyFileUsingStream(newModelItemsDir, newModelItems);
            renamedJson(newModelItems, "texture");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void renamedJson(File file, String key) {
        TemplateReader reader = new TemplateReader(file);
        JSONObject obj = reader.renamedJSONObject(key, modelName);
        System.out.println("DEBUG RENAMED : " + obj);

        //TODO REPLACE ACTUAL JSON WITH THE NEW ONE WIHT RENAMED FIELD
    }

    public void sendMessage(String message) {
        Main.sendMessage(s + message);
    }

    private void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = new FileInputStream(source);
        OutputStream os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();
    }

}
