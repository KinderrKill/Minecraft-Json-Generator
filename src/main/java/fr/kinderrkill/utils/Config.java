package fr.kinderrkill.utils;

public class Config {

    public static String WINDOW_TITLE = "Minecraft JSON Generator | Made by KinderrKill";

    // Properties values
    public static boolean isEnglish;
    public static ScreenTab actualTab;

    public enum ScreenTab {
        MODEL(),
        DEFINE(),
        ;

        ScreenTab() {}
    }
}
