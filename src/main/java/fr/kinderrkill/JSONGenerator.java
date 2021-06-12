package fr.kinderrkill;


import fr.kinderrkill.managers.TemplateManager;
import fr.kinderrkill.utils.Config;
import fr.kinderrkill.utils.properties.GeneratorProperties;
import fr.kinderrkill.utils.properties.GeneratorPropertiesFileException;

import java.io.File;
import java.net.URISyntaxException;

public class JSONGenerator {

    private static JSONGenerator instance;
    private static GeneratorProperties properties;

    public TemplateManager templateManager;

    public JSONGenerator() {
        instance = this;
        properties = new GeneratorProperties();

        Config.actualTab = Config.ScreenTab.MODEL;

        this.templateManager = new TemplateManager(this);

        // Init
        this.templateManager.loadTemplates();
    }

    public File getBaseFile() {
        try {
            return new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void closeGenerator() {
        properties.setProperty("LANG", Config.isEnglish ? "english" : "french");

        try {
            properties.save();
        } catch (GeneratorPropertiesFileException e) {
            e.printStackTrace();
        }
    }

    // Utils
    public static JSONGenerator getInstance() {
        return instance;
    }

    public static GeneratorProperties getProperties() {
        return properties;
    }
}
