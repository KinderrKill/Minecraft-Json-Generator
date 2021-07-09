package fr.kinderrkill.listeners;

import fr.kinderrkill.JSONGenerator;
import fr.kinderrkill.managers.JSONFileManager;
import fr.kinderrkill.objects.Template;
import fr.kinderrkill.utils.Config;
import fr.kinderrkill.utils.Dialogs;
import fr.kinderrkill.utils.Lang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final JSONGenerator jsonGenerator = new JSONGenerator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        englishLangButton.setSelected(Config.isEnglish);
        frenchLangButton.setSelected(!Config.isEnglish);

        updateLanguage();

        definePane.setVisible(false);
        this.paneLabelName.setText("I - " + Lang.getTranslation("#Model"));
        modelMainList.getItems().addAll(jsonGenerator.templateManager.getTemplatesForList());
    }

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

        updateLanguage();
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == closeButton) {
            JSONGenerator.getInstance().closeGenerator();

            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else if (event.getSource() == modelButton) {
            if (Config.actualTab != Config.ScreenTab.MODEL) {
                Config.actualTab = Config.ScreenTab.MODEL;
                updateModelTab();
            }
        }
        else if (event.getSource() == definitionButton) {
            if (Config.actualTab != Config.ScreenTab.DEFINE) {
                Config.actualTab = Config.ScreenTab.DEFINE;
                updateDefineTab();
            }
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
    private void handleValidateModel(ActionEvent event) {
        if (event.getSource() == modelValidateButton) {
            System.out.println("Validate model button pushed ! ");
            String selectedString = modelMainList.getSelectionModel().getSelectedItem();
            selectedString = selectedString.substring(0, selectedString.indexOf("|") - 1);
            if (!jsonGenerator.templateManager.getTemplate(selectedString).isPresent()) {
                Dialogs.showTemplateNotCorrect();
            } else {
                jsonGenerator.templateManager.defineSelectedTemplate(jsonGenerator.templateManager.getTemplate(selectedString).get());
                Config.actualTab = Config.ScreenTab.DEFINE;
                updateDefineTab();
            }
        }
    }

    @FXML
    private void handleGenerateButton(ActionEvent event) {
        if (Config.actualTab != Config.ScreenTab.DEFINE) return;

        JSONFileManager manager = new JSONFileManager();
        manager.generateJSON(jsonGenerator.templateManager.getSelectedTemplate(), blockstateField.getText(), modelItem.getText());
    }

    private void updateLanguage() {
        Config.isEnglish = englishLangButton.isSelected();

        modelButton.setText(Lang.getTranslation("#Model"));
        definitionButton.setText(Lang.getTranslation( "#Define"));
        generateButton.setText(Lang.getTranslation( "#Generate"));
        closeButton.setText(Lang.getTranslation("#Quit"));

        if (Config.actualTab == Config.ScreenTab.MODEL) {
            this.paneLabelName.setText("I - " + Lang.getTranslation("#Model"));
        } else if (Config.actualTab == Config.ScreenTab.DEFINE) {
            this.paneLabelName.setText("II - " + Lang.getTranslation("#Define"));
        }
    }

    private void updateModelTab() {
        if (Config.actualTab != Config.ScreenTab.MODEL) return;

        paneLabelName.setText("I - " + Lang.getTranslation("#Model"));
        definePane.setVisible(false);
        mainPane.setVisible(true);
        modelMainList.getItems().clear();
        modelMainList.getItems().addAll(jsonGenerator.templateManager.getTemplatesForList());
    }

    private void updateDefineTab() {
        if (Config.actualTab != Config.ScreenTab.DEFINE) return;

        paneLabelName.setText("II - " + Lang.getTranslation("#Define"));
        mainPane.setVisible(false);

        definePane.setVisible(true);
    }

    @FXML
    private void handleTextField(ActionEvent event) {
        System.out.println("Event TextField : " + event.getSource());
    }

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private Label paneLabelName;

    @FXML
    private Label paneLabelDirectory;

    // Main
    @FXML
    private Pane mainPane;

    @FXML
    private ListView<String> modelMainList;

    // Buttons

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

    @FXML
    private Button modelValidateButton;

    // Define

    @FXML
    private Pane definePane;

    @FXML
    private Label defineLabelBlockState;

    @FXML
    private TextField blockstateField;

    @FXML
    private Label defineLabelModelItem;

    @FXML
    private TextField modelItem;
}
