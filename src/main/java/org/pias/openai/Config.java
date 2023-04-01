package org.pias.openai;

import org.checkerframework.checker.units.qual.C;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Config {
    private static final String CONFIG_FILE = "config.yaml";

    private static Config INSTANCE = null;
    private Map<String, Object> configMap;

    public static Config getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }

        return INSTANCE;
    }

    private Config() {
        try {
            InputStream inputStream = new FileInputStream(CONFIG_FILE);
            Yaml yaml = new Yaml();
            configMap = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(final String property) {
        if (configMap.containsKey(property)) {
            return (String) configMap.get(property);
        }

        System.err.println("Property: " + property + " not found");
        return "";
    }

    public void updateProperty(final String key, final String value) {
        if (!configMap.containsKey(key)) {
            System.err.println("Property: " + key + " does NOT exist");
            return;
        }

        System.out.println("Property " + key + " current value: " + configMap.get(key) + " ---> New Value: " + value);
        configMap.put(key, value);

        final boolean propertyUpdated = updateYamlFile(getDefaultOptions());

        System.out.println(propertyUpdated ? "Successfully Updated Yaml File" : "Failed to update Yaml file");
    }


    private boolean updateYamlFile(final DumperOptions options) {
        Yaml yaml = new Yaml(options);
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            yaml.dump(configMap, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private DumperOptions getDefaultOptions () {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        return options;
    }

    // Add other configuration properties if needed
}
