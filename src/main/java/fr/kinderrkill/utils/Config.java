package fr.kinderrkill.utils;

import java.io.*;
import java.util.Properties;

public class Config {

    private static Properties properties = null;

    public static void load() {
        if (properties == null) {
            properties = new Properties();
            try {
                InputStream is = Config.class.getResourceAsStream("/Config.properties");
                properties.load(is);
                // TODO: If 'is' is null -> Display error dialog and quit
                is.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Lang used by default ? " + get("LANG"));
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);
    }

    public static void save() {
        try {
            File file = new File("Config.properties");
            System.out.println("File " + file.exists());
            if (!file.exists()) return; // TODO: Do dialog error "close without saving properties"

            FileOutputStream out = new FileOutputStream(file);
            properties.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
