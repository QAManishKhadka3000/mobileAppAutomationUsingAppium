package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import config.ConfigManager;

import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static AndroidDriver driver;
    
    private DriverManager() {
        // Private constructor to prevent instantiation
    }
    
    public static AndroidDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    
    public static void initializeDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            
            // Basic capabilities
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 
                ConfigManager.getProperty("android.platform.name"));
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 
                ConfigManager.getProperty("android.device.name"));
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, 
                ConfigManager.getProperty("android.automation.name"));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 
                ConfigManager.getIntProperty("android.new.command.timeout"));
            
            // App capabilities
            capabilities.setCapability(MobileCapabilityType.APP, 
                System.getProperty("user.dir") + "/" + ConfigManager.getProperty("android.app.path"));
            capabilities.setCapability("appPackage", ConfigManager.getProperty("android.app.package"));
            capabilities.setCapability("appActivity", ConfigManager.getProperty("android.app.activity"));
            
            // Additional options
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoAcceptAlerts", true);
            
            URL appiumServerUrl = new URL(ConfigManager.getProperty("appium.server.url"));
            driver = new AndroidDriver(appiumServerUrl, capabilities);
            
            // Configure timeouts
            driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigManager.getIntProperty("implicit.wait")));
            
            logger.info("Android Driver initialized successfully");
            
        } catch (Exception e) {
            logger.error("Failed to initialize Android Driver: {}", e.getMessage());
            throw new RuntimeException("Driver initialization failed", e);
        }
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Android Driver closed successfully");
        }
    }
    
    public static void resetApp() {
        if (driver != null) {
            // Use terminateApp and activateApp instead of resetApp
            String appPackage = ConfigManager.getProperty("android.app.package");
            driver.terminateApp(appPackage);
            driver.activateApp(appPackage);
            logger.info("App reset successfully");
        }
    }
}