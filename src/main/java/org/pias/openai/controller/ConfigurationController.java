package org.pias.openai.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.pias.openai.Config;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {
    @FXML
    private TextField apiKeyInput;

    private String initialApiKey;

    @FXML
    private void saveConfiguration() {
        String apiKey = apiKeyInput.getText().trim();
        if (!apiKey.isEmpty() && !initialApiKey.equals(apiKeyInput.getText())) {
            // Save the API key to a configuration file or use any desired storage method
            Config.getInstance().updateProperty("api_key", apiKeyInput.getText());
        }

        // Close the window
        Stage stage = (Stage) apiKeyInput.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apiKeyInput.setText(Config.getInstance().getProperty("api_key"));
        initialApiKey = apiKeyInput.getText();
    }
}
