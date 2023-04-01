package org.pias.openai.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.pias.openai.Config;
import org.pias.openai.service.ApiService;
import org.pias.openai.service.UnirestApiService;
import org.pias.openai.service.chat.payload.ChatCompletionRequest;
import org.pias.openai.service.chat.payload.Message;
import org.pias.openai.service.chat.response.ChatCompletionResponse;
import org.pias.openai.service.chat.response.Choice;
import org.pias.openai.service.chat.response.Usage;
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
    private Label lblTotalCost;
    @FXML
    private Label lblTotalTokens;
    private float runningCost = 0.0f;
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

        lblTotalCost.setText("$0.000");
        lblTotalTokens.setText("0");

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
                .maxTokens(Integer.parseInt(Config.getInstance().getProperty("maxTokens")))
                .temperature(Float.parseFloat(Config.getInstance().getProperty("temperature")))
                .messages(messages)
                .build();

        // Create and display the progress indicator
        ProgressIndicator progressIndicator = new ProgressIndicator();
        StackPane progressContainer = new StackPane(progressIndicator);
        responseContainer.getChildren().add(progressContainer);

        Task<ChatCompletionResponse> task = new Task<>() {
            @Override
            protected ChatCompletionResponse call() {
                try {
                    return apiService.chat(request);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        task.setOnSucceeded(event -> {
            responseContainer.getChildren().remove(progressContainer);

            Optional<String> optChatMessage = task.getValue().choices().stream()
//                    .filter(choice -> choice.finishReason().equals("stop"))
                    .map(Choice::message)
                    .map(Message::content)
                    .findFirst();

            if (optChatMessage.isEmpty()) {
                return;
            }

            String chatMessage = optChatMessage.get();
            Label openAiResponse = new Label("Assistant: " + chatMessage);

            addMessageToContext("assistant", chatMessage);
            updateTokenDataLbls(task.getValue().usage());

            // Scroll to the bottom of the response container
            openAiResponse.getStyleClass().add("openai-response");
            responseContainer.getChildren().add(openAiResponse);

            Platform.runLater(() -> {
                scrollPane.layout();
                scrollPane.setVvalue(1.0);
            });
        });

        // Handle any errors that may occur during task execution
        task.setOnFailed(event -> {
            responseContainer.getChildren().remove(progressContainer);
            Throwable exception = task.getException();
            if (exception != null) {
                exception.printStackTrace();
            }
        });

        // Start the task in a separate thread
        new Thread(task).start();
    }

    private void updateTokenDataLbls(Usage usage) {
        int runningTokens = usage.totalTokens();
        runningCost += runningTokens * (.002 / 1000);

        lblTotalTokens.setText(String.format("%d", runningTokens));
        lblTotalCost.setText(String.format("$%.3f", runningCost));
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
