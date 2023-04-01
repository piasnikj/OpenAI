package org.pias.openai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class OpenAI extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/OpenAIView.fxml"));
        Scene scene = new Scene(root);
        URL cssUrl = getClass().getResource("view/styles.css");
        scene.getStylesheets().add(cssUrl.toExternalForm());

        primaryStage.setTitle("OpenAI JavaFX Application");
        primaryStage.setHeight(720);
        primaryStage.setWidth(440);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
