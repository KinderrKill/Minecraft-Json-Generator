package fr.kinderrkill;

import fr.kinderrkill.gui.GuiMain;

public class Main {

    public static void main(String[] args) {
        /*JsonGenerator generator = new JsonGenerator();
        try {
            generator.start();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
        GuiMain guiMain = new GuiMain();
        guiMain.launch();
    }

    public static void sendMessage(String message) {
        System.out.println(message);
    }

}
