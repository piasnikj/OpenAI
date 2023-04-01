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
import org.pias.openai.service.ApiService;
import org.pias.openai.service.UnirestApiService;
import org.pias.openai.service.chat.payload.ChatCompletionRequest;
import org.pias.openai.service.chat.payload.Message;
import org.pias.openai.service.chat.response.ChatCompletionResponse;
import org.pias.openai.service.chat.response.Choice;
import org.pias.openai.util.UtilityHelper;
import org.pias.openai.util.enums.ModelEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private ApiService apiService;

    private List<Message> messages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messages = new ArrayList<>();
        apiService = new UnirestApiService();

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
            URL url = UtilityHelper.getResourceFile("ConfigurationView.fxml")
                    .orElseThrow(() -> new RuntimeException("Resource not found"));
            Parent configurationRoot = FXMLLoader.load(url);
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

        addMessageToContext("user", prompt);

        ChatCompletionRequest request = new ChatCompletionRequest.Builder()
                .model(ModelEnum.GPT_3_5_TURBO.getModel())
                .messages(messages)
                .build();

        try {
            ChatCompletionResponse response = apiService.chat(request);

            // Display API response
            // Replace this with actual API response
            final Optional<String> optChatMessage = response.choices().stream()
                    .filter(choice -> choice.finishReason().equals("stop"))
                    .map(Choice::message)
                    .map(Message::content)
                    .findFirst();

            if (optChatMessage.isEmpty()) {
                return;
            }

            String chatMessage = optChatMessage.get();
            Label openAiResponse = new Label(chatMessage);

            addMessageToContext("assistant", chatMessage);

            openAiResponse.getStyleClass().add("openai-response");
            responseContainer.getChildren().add(openAiResponse);

            // Scroll to the bottom of the response container
            scrollPane.layout();
            scrollPane.setVvalue(1.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMessageToContext(String role,
                                     String strMessage) {
        Message message = new Message.Builder()
                .role(role)
                .content(strMessage)
                .build();

        messages.add(message);
    }
}
