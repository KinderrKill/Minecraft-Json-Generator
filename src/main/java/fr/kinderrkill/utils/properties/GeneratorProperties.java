package fr.kinderrkill.utils.properties;

import fr.kinderrkill.JSONGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GeneratorProperties {

    private static final String FILE_NAME = "generator.properties";

    private final Properties properties;

    public GeneratorProperties() {
        properties = new Properties();

        try {
            load();
        } catch (GeneratorPropertiesFileException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return new File(JSONGenerator.getInstance().getBaseFile(), FILE_NAME);
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public void load() throws GeneratorPropertiesFileException {
        File file = this.getFile();

        if (file.exists()) {
            try (FileInputStream stream = new FileInputStream(file)) {
                this.properties.load(stream);
            } catch (IOException e) {
                throw new GeneratorPropertiesFileException("cannot load MinecraftJSONGenerator properties file", e);
            }
        }
    }

    public void save() throws GeneratorPropertiesFileException {
        File generatorDirectory = JSONGenerator.getInstance().getBaseFile();

        if (!generatorDirectory.exists())
            throw new GeneratorPropertiesFileException("cannot find MinecraftJSONGenerator properties folder", null);

        try (FileOutputStream stream = new FileOutputStream(this.getFile())) {
            this.properties.store(stream, null);
        } catch (IOException e) {
            throw new GeneratorPropertiesFileException("cannot save MinecraftJSONGenerator properties file", e);
        }
    }


}
