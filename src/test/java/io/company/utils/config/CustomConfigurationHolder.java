package io.company.utils.config;

import io.company.utils.logging.Loggable;

public class CustomConfigurationHolder implements Loggable {
    private final String customConfiguration = System.getProperty("custom.configuration");

    public String exampleConfiguration() {
        logger().info("Custom config: " + customConfiguration);
        return customConfiguration;
    }
}
