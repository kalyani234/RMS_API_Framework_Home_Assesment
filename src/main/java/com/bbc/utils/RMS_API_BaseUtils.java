package com.bbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RMS_API_BaseUtils {

    private static Properties props = new Properties();

    // Load config.properties when the class is initialized
    static {
        try (InputStream input = RMS_API_BaseUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties file not found in classpath");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Get a property value by key from the config
    public static String get(String key) {
        return props.getProperty(key);
    }

    // Return the standard path separator used in URLs
    public static String getPathSeparator() {
        return "/";
    }

    // Helper method to return combined RMS base + endpoint URL
    public static String getRmsApiEndpointUrl() {
        return get("baseApiUrl") + get("ibltestEndpoint");
    }
}
