package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccessibilityPage extends BasePage {
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Accessibility Node Provider']")
    private WebElement nodeProviderOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Accessibility Service']")
    private WebElement serviceOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Custom View']")
    private WebElement customViewOption;
    
    public void clickNodeProvider() {
        logInfo("Clicking on Accessibility Node Provider");
        nodeProviderOption.click();
    }
    
    public void clickService() {
        logInfo("Clicking on Accessibility Service");
        serviceOption.click();
    }
    
    public void clickCustomView() {
        logInfo("Clicking on Custom View");
        customViewOption.click();
    }
    
    public boolean isNodeProviderDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(nodeProviderOption));
            return nodeProviderOption.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}