package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Accessibility']")
    private WebElement accessibilityOption;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickAccessibility() {
        accessibilityOption.click();
    }
}