package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;
import java.time.Duration;

public class AppiumBase {
    private static final Logger logger = LoggerFactory.getLogger(AppiumBase.class);
    protected static AndroidDriver driver;

    public static void setupDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:platformName", "Android");
            caps.setCapability("appium:deviceName", "emulator-5554");
            caps.setCapability("appium:app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
            caps.setCapability("appium:appPackage", "io.appium.android.apis");
            caps.setCapability("appium:appActivity", ".ApiDemos");
            caps.setCapability("appium:automationName", "UiAutomator2");
            caps.setCapability("appium:newCommandTimeout", 300);

            driver = new AndroidDriver(new URL("http://10.7.1.82:4723/"), caps); // Updated URL
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            logger.info("Appium Driver initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize Appium Driver: {}", e.getMessage());
            throw new RuntimeException("Driver setup failed", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Appium Driver closed");
        }
    }
}