package org.pias.openai.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpenAIController implements Initializable {

    @FXML
    private VBox responseContainer;
    @FXML
    private TextField promptInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        promptInput.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendButton.fire();
                event.consume();
            }
        });
    }

    @FXML
    private void sendPrompt() {
        String prompt = promptInput.getText().trim();
        if (!prompt.isEmpty()) {
            // Call OpenAI API and display response
            displayResponse(prompt);
            promptInput.clear();
        }
    }

    @FXML
    private void showConfiguration() {
        try {
            Stage configurationStage = new Stage();
            Parent configurationRoot = FXMLLoader.load(getClass().getResource("/org/pias/openai/view/ConfigurationView.fxml"));
            Scene configurationScene = new Scene(configurationRoot);
            configurationStage.setTitle("Configuration");
            configurationStage.setScene(configurationScene);
            configurationStage.setWidth(540);
            configurationStage.setHeight(300);
            configurationStage.initModality(Modality.APPLICATION_MODAL);
            configurationStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayResponse(String prompt) {
        // Display user prompt
        Label userPrompt = new Label("You: " + prompt);
        userPrompt.getStyleClass().add("user-prompt");
        responseContainer.getChildren().add(userPrompt);

        // Display API response
        // Replace this with actual API response
        String apiResponse = "OpenAI: This is a sample response.";
        Label openAiResponse = new Label(apiResponse);
        openAiResponse.getStyleClass().add("openai-response");
        responseContainer.getChildren().add(openAiResponse);

        // Scroll to the bottom of the response container
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }
}
