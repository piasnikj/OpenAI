package org.pias.openai.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.pias.openai.Config;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {
    @FXML
    private TextField apiKeyInput;
    @FXML
    private TextField maxTokenInput;
    @FXML
    private TextField tempInput;
    private String initialApiKey;
    private final Map<TextField, String> m_tfInputValues = new HashMap<>();;
    private Map<TextField, String> m_confKeys;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        m_confKeys = Map.of(apiKeyInput, "api_key",
                maxTokenInput, "maxTokens",
                tempInput,  "temperature");

        m_tfInputValues.put(apiKeyInput, Config.getInstance().getProperty("api_key"));
        m_tfInputValues.put(maxTokenInput, Config.getInstance().getProperty("maxTokens"));
        m_tfInputValues.put(tempInput, Config.getInstance().getProperty("temperature"));

        for (final Map.Entry<TextField, String> entry : m_tfInputValues.entrySet()) {
            entry.getKey().setText(entry.getValue());
        }
    }

    @FXML
    private void saveConfiguration() {
        for (final Map.Entry<TextField, String> tfEntry : m_tfInputValues.entrySet()) {
            if (!tfEntry.getValue().equals(tfEntry.getKey().getText())) {
                Config.getInstance().updateProperty(m_confKeys.get(tfEntry.getKey()), tfEntry.getKey().getText());
            }
        }

        // Close the window
        Stage stage = (Stage) apiKeyInput.getScene().getWindow();
        stage.close();
    }
}
