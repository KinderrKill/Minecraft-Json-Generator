package fr.kinderrkill.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class Lang {

    private enum FRENCH {
       MODEL("#Model", "Modèle"),
        DEFINE("#Define", "Définition"),
        GENERATE("#Generate", "Générer"),
        QUIT("#QUIT", "Quitter l'application");
       ;

       private final String key;
       private final String translation;

        FRENCH(String key, String translation) {
            this.key = key;
            this.translation = translation;
        }

        public String getKey() {
            return this.key;
        }

        public String getTranslation() {
            return this.translation;
        }
    }

    private enum ENGLISH {
        MODEL("#Model", "Model"),
        DEFINE("#Define", "Define"),
        GENERATE("#Generate", "Generate"),
        QUIT("#QUIT", "Quit the application");
        ;

        private final String key;
        private final String translation;

        ENGLISH(String key, String translation) {
            this.key = key;
            this.translation = translation;
        }

        public String getKey() {
            return this.key;
        }

        public String getTranslation() {
            return this.translation;
        }
    }

    public static String getTranslation(String key) {
        if (Config.isEnglish) {
           Optional<ENGLISH> val = Stream.of(ENGLISH.values()).filter(value -> value.getKey().equalsIgnoreCase(key)).findFirst();
           if (val.isPresent()) return val.get().getTranslation();
           else return "#NULL";
        } else {
            Optional<FRENCH> val = Stream.of(FRENCH.values()).filter(value -> value.getKey().equalsIgnoreCase(key)).findFirst();
            if (val.isPresent()) return val.get().getTranslation();
            else return "#NULL";
        }
    }


}
