package fr.kinderrkill.old.utils;

import java.io.UnsupportedEncodingException;

public class Lang {

    public static boolean isFrench;

    public enum list {

        SELECT_TEMPLATE("Select a template :", "Sélectionner un modèle :"),

        MODEL_NAME("Define the model name", "Define the model name"),
        MODEL_TEXTURE("Define the model texture", "Define the model texture"),
        ;

        private String engLang;
        private String frLang;


        list(String eng, String fr) {
            this.engLang = eng;
            this.frLang = fr;
        }

        public String getName() {
            try {
                return isFrench ? new String(frLang.getBytes(), "UTF-8") : engLang;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
