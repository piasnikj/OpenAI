package org.pias.openai.util;

import java.net.URL;
import java.util.Optional;

public class UtilityHelper {
    private static final String ROOT_RESOURCE_VIEW = "/org/pias/openai/view/";
    public static Optional<URL> getResourceFile(String filePath) {
        return Optional.ofNullable(UtilityHelper.class.getResource(ROOT_RESOURCE_VIEW + filePath));
    }
}
