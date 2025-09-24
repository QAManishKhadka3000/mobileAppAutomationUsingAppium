package pages;

import base.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
    
    // Screen Title
    @AndroidFindBy(id = "android:id/text1")
    private List<WebElement> menuItems;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='API Demos']")
    private WebElement screenTitle;
    
    // Menu Items - Using content-desc as it's more reliable
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Access'ibility\"]")
    private WebElement accessibilityOption1;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Accessibility']")
    private WebElement accessibilityOption2;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Animation']")
    private WebElement animationOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='App']")
    private WebElement appOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Content']")
    private WebElement contentOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Graphics']")
    private WebElement graphicsOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Media']")
    private WebElement mediaOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='NFC']")
    private WebElement nfcOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='OS']")
    private WebElement osOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Preference']")
    private WebElement preferenceOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Text']")
    private WebElement textOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Views']")
    private WebElement viewsOption;

    @Step("Verify home screen is loaded successfully")
    public void verifyHomeScreenLoaded() {
        logInfo("Verifying home screen is loaded");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait for screen title
        wait.until(ExpectedConditions.visibilityOf(screenTitle));
        
        // Wait for at least one menu item to be visible
        wait.until(ExpectedConditions.visibilityOf(accessibilityOption1));
        
        logInfo("Home screen loaded successfully");
        logInfo("Screen title: " + getScreenTitle());
        logInfo("Number of menu items: " + getMenuItemsCount());
    }

    @Step("Click on Accessibility option")
    public void clickAccessibility() {
        logInfo("Clicking on Accessibility option");
        accessibilityOption2.click();
    }

    @Step("Click on Access'ibility option (with apostrophe)")
    public void clickAccessibilityWithApostrophe() {
        logInfo("Clicking on Access'ibility option");
        accessibilityOption1.click();
    }

    @Step("Click on Animation option")
    public void clickAnimation() {
        logInfo("Clicking on Animation option");
        animationOption.click();
    }

    @Step("Click on App option")
    public void clickApp() {
        logInfo("Clicking on App option");
        appOption.click();
    }

    @Step("Click on Content option")
    public void clickContent() {
        logInfo("Clicking on Content option");
        contentOption.click();
    }

    @Step("Click on Graphics option")
    public void clickGraphics() {
        logInfo("Clicking on Graphics option");
        graphicsOption.click();
    }

    @Step("Click on Media option")
    public void clickMedia() {
        logInfo("Clicking on Media option");
        mediaOption.click();
    }

    @Step("Click on NFC option")
    public void clickNFC() {
        logInfo("Clicking on NFC option");
        nfcOption.click();
    }

    @Step("Click on OS option")
    public void clickOS() {
        logInfo("Clicking on OS option");
        osOption.click();
    }

    @Step("Click on Preference option")
    public void clickPreference() {
        logInfo("Clicking on Preference option");
        preferenceOption.click();
    }

    @Step("Click on Text option")
    public void clickText() {
        logInfo("Clicking on Text option");
        textOption.click();
    }

    @Step("Click on Views option")
    public void clickViews() {
        logInfo("Clicking on Views option");
        viewsOption.click();
    }

    @Step("Get screen title text")
    public String getScreenTitle() {
        String title = screenTitle.getText();
        logInfo("Screen title: " + title);
        return title;
    }

    @Step("Get number of menu items")
    public int getMenuItemsCount() {
        int count = menuItems.size();
        logInfo("Number of menu items: " + count);
        return count;
    }

    @Step("Check if Accessibility option is displayed")
    public boolean isAccessibilityOptionDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(accessibilityOption2));
            boolean isDisplayed = accessibilityOption2.isDisplayed();
            logInfo("Accessibility option displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logError("Accessibility option not displayed: " + e.getMessage());
            return false;
        }
    }

    @Step("Print all available menu items")
    public void printAllMenuItems() {
        logInfo("=== Available Menu Items ===");
        for (WebElement menuItem : menuItems) {
            if (menuItem.isDisplayed()) {
                String text = menuItem.getText();
                String contentDesc = menuItem.getAttribute("content-desc");
                logInfo("- " + text + " (content-desc: " + contentDesc + ")");
            }
        }
    }

    @Step("Check if menu item '{menuText}' is present")
    public boolean isMenuItemPresent(String menuText) {
        try {
            WebElement menuItem = driver.findElement(By.xpath(
                "//android.widget.TextView[@text='" + menuText + "' or @content-desc='" + menuText + "']"));
            boolean isPresent = menuItem.isDisplayed();
            logInfo("Menu item '" + menuText + "' present: " + isPresent);
            return isPresent;
        } catch (Exception e) {
            logInfo("Menu item '" + menuText + "' not found");
            return false;
        }
    }

    @Step("Click on menu item by name: {menuItemName}")
    public void clickMenuItemByName(String menuItemName) {
        logInfo("Clicking on menu item: " + menuItemName);
        try {
            WebElement menuItem = driver.findElement(By.xpath(
                "//android.widget.TextView[@text='" + menuItemName + "' or @content-desc='" + menuItemName + "']"));
            menuItem.click();
            logInfo("Successfully clicked on: " + menuItemName);
        } catch (Exception e) {
            logError("Failed to click on menu item: " + menuItemName + " - " + e.getMessage());
            throw new RuntimeException("Menu item not found: " + menuItemName, e);
        }
    }

    @Step("Get all menu item texts")
    public List<String> getAllMenuItemTexts() {
        return menuItems.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .toList();
    }

    @Step("Verify specific menu items are present")
    public void verifyMenuItemsPresent(String... expectedItems) {
        for (String expectedItem : expectedItems) {
            if (!isMenuItemPresent(expectedItem)) {
                throw new AssertionError("Menu item not found: " + expectedItem);
            }
        }
        logInfo("All expected menu items are present");
    }

    @Step("Scroll to menu item: {menuItemName}")
    public void scrollToMenuItem(String menuItemName) {
        logInfo("Scrolling to menu item: " + menuItemName);
        // Simple scroll implementation - you might want to enhance this
        driver.findElementByAndroidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + menuItemName + "\"))");
    }
}