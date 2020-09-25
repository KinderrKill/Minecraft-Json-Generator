package fr.kinderrkill;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        JsonGenerator generator = new JsonGenerator();
        try {
            generator.start();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message) {
        System.out.println(message);
    }

}
