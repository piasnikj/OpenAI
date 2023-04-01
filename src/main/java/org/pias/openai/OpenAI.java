package org.pias.openai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pias.openai.util.UtilityHelper;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class OpenAI extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Optional<URL> optUrl = UtilityHelper.getResourceFile("OpenAIView.fxml");

        if (optUrl.isEmpty()) {
            throw new RuntimeException("Unable to find view file: OpenAIView.fxml");
        }

        Parent root = FXMLLoader.load(optUrl.get());
        Scene scene = new Scene(root);
        Optional<URL> optCssUrl = UtilityHelper.getResourceFile("styles.css");
        optCssUrl.ifPresent(url -> scene.getStylesheets().add(url.toExternalForm()));

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
