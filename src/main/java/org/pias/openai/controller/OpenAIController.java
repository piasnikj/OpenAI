package org.pias.openai.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenAIController implements Initializable {

    @FXML
    private VBox responseContainer;
    @FXML
    private TextField promptInput;
    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.setVvalue(1.0);
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
