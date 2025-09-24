package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    private static AndroidDriver driver;
    
    public static void setDriver(AndroidDriver driver) {
        WaitHelper.driver = driver;
    }
    
    public static WebElement waitForElementVisible(WebElement element, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }
    
    public static boolean waitForElementInvisible(WebElement element, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.invisibilityOf(element));
    }
    
    public static WebElement waitForElementClickable(WebElement element, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}