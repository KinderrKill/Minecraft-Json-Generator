package fr.kinderrkill;

import fr.kinderrkill.utils.Config;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private void handleLangSwitch(ActionEvent event) {
        if (event.getSource() == frenchLangButton) {
            englishLangButton.setSelected(false);
            frenchLangButton.setSelected(true);
        }

        if (event.getSource() == englishLangButton) {
            englishLangButton.setSelected(true);
            frenchLangButton.setSelected(false);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == closeButton) {
            Config.set("LANG", englishLangButton.isSelected() ? "english" : "french");

            Config.save();

            //Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            //stage.close();
        }
        else if (event.getSource() == modelButton) {
            System.out.println("Model button pressed !");
        }
        else if (event.getSource() == definitionButton) {
            System.out.println("Definition button pressed !");
        }
        else if (event.getSource() == generateButton) {
            System.out.println("Generate button pressed !");
        }
        else if (event.getSource() == hyperlink) {
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("https://github.com/KinderrKill/Minecraft-Json-Generator"));
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private RadioButton frenchLangButton;

    @FXML
    private RadioButton englishLangButton;

    @FXML
    private Button modelButton;

    @FXML
    private Button definitionButton;

    @FXML
    private Button generateButton;

    @FXML
    private Button closeButton;

}
