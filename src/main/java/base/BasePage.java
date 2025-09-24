package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected AndroidDriver driver;
    
    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 
            Duration.ofSeconds(10)), this);
    }
    
    protected void logInfo(String message) {
        logger.info(message);
    }
    
    protected void logError(String message) {
        logger.error(message);
    }
    
    // Add getter for driver if needed in child classes
    protected AndroidDriver getDriver() {
        return driver;
    }
}