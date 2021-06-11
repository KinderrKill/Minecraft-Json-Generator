package fr.kinderrkill.utils;

import fr.kinderrkill.old.JsonGenerator;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class Config {

    private static Properties properties = null;
    public static boolean isEnglish;

    public static void load() {
        if (properties == null) {
            properties = new Properties();
            try {
                File baseFile = new File(JsonGenerator.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();

                File file = new File(baseFile + "/Config.properties");
                InputStream in = file.toURL().openStream();

                properties.load(in);
                isEnglish = get("LANG").equalsIgnoreCase("english");

                System.out.println("Lang used by default ? " + get("LANG") + " || IsEnglish : " + isEnglish);
            } catch (IOException | NullPointerException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);
    }

    public static void save() {
        try {
            File baseFile = new File(JsonGenerator.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();

            File file = new File(baseFile + "/Config.properties");
            if (!file.exists()) return; // TODO: Do dialog error "close without saving properties"

            System.out.println("File path SAVE : " + file.getAbsolutePath());

            FileOutputStream out = new FileOutputStream(file);
            properties.store(out, null);
            out.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
