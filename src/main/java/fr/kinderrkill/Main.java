package fr.kinderrkill;

import fr.kinderrkill.utils.Config;

import fr.kinderrkill.utils.Dialogs;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.net.URL;


public class Main extends Application {

    public static void main(String[] args) {
        JSONGenerator jsonGenerator = new JSONGenerator();

        launch(args);
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("/fxml/MainWindow.fxml");
        if (url == null) {
            Dialogs.showFXMLFileNotFound();
            return;
        }
        Parent root = FXMLLoader.load(url);

        InputStream icon = Main.class.getResourceAsStream("/default_icon.png");
        if (icon != null)
            primaryStage.getIcons().add(new Image(icon));

        primaryStage.setTitle(Config.WINDOW_TITLE);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
