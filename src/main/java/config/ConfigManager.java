package config;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static final Properties properties = new Properties();
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        try {
            InputStream input = ConfigManager.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            
            if (input != null) {
                properties.load(input);
                logger.info("Configuration properties loaded successfully");
            } else {
                logger.warn("config.properties not found, using default values");
                setDefaultProperties();
            }
        } catch (Exception e) {
            logger.warn("Error loading config.properties, using default values: {}", e.getMessage());
            setDefaultProperties();
        }
    }
    
    private static void setDefaultProperties() {
        // Set default values
        properties.setProperty("appium.server.url", "http://127.0.0.1:4723");
        properties.setProperty("android.platform.name", "Android");
        properties.setProperty("android.device.name", "emulator-5554");
        properties.setProperty("android.app.path", "apps/ApiDemos-debug.apk");
        properties.setProperty("android.app.package", "io.appium.android.apis");
        properties.setProperty("android.app.activity", ".ApiDemos");
        properties.setProperty("android.automation.name", "UiAutomator2");
        properties.setProperty("android.new.command.timeout", "300");
        properties.setProperty("implicit.wait", "10");
        properties.setProperty("explicit.wait", "20");
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public static int getIntProperty(String key) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (NumberFormatException e) {
            logger.warn("Invalid integer value for property '{}', using default", key);
            return 10; // default value
        }
    }
    
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}