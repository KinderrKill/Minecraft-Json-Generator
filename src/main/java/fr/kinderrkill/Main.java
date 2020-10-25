package fr.kinderrkill;

import fr.kinderrkill.gui.GuiMain;
import fr.kinderrkill.utils.Lang;

public class Main {

    public static void main(String[] args) {
        //Lang.isFrench = System.getProperty("user.language").equalsIgnoreCase("fr");
        GuiMain guiMain = new GuiMain();
        guiMain.launch();
    }

    public static void sendMessage(String message) {
        System.out.println(message);
    }

}
