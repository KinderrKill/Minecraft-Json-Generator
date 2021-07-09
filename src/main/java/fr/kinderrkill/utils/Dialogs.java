package fr.kinderrkill.utils;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;

public class Dialogs {

    public static void showFXMLFileNotFound() {
        generateAlert(Alert.AlertType.ERROR,
                "Erreur lancement MinecraftJSONGenerator :",
                "-> Fichier FXML 'MainWindow' introuvable !").showAndWait();
    }

    public static void showPropertiesFileNotFound() {
        generateAlert(Alert.AlertType.ERROR,
                "Erreur lancement MinecraftJSONGenerator :",
                "-> Fichier Properties introuvable !").showAndWait();
    }

    public static void showTemplateNotCorrect() {
        generateAlert(Alert.AlertType.ERROR,
                "Erreur validation Template :",
                "-> Le template est incorrecte !").showAndWait();
    }

    public static void showExitWithoutSaving() {
        generateAlert(Alert.AlertType.INFORMATION,
        "Erreur fermeture MinecraftJSONGenerator :",
        "-> Sauvegarde du fichier Properties impossible, fichier introuvable !").showAndWait();
    }

    public static void showErrorWhenSaving() {
        generateAlert(Alert.AlertType.INFORMATION,
                "Erreur fermeture MinecraftJSONGenerator :",
                "-> Sauvegarde du fichier Properties impossible !").showAndWait();
    }

    public static void generateWithSuccess() {
        generateAlert(Alert.AlertType.CONFIRMATION,
                "MinecraftJSONGenerator :",
                "-> Fichiers générés avec succès !").show();
    }

    // Utils
    private static Alert generateAlert(Alert.AlertType type, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle("Minecraft JSON Generator");
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

}
