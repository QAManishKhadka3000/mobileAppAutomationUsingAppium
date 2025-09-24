package tests;

import base.BaseTest;
import pages.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Epic("Mobile Automation")
@Feature("Complete Navigation Tests")
@Owner("QA Team")
public class CompleteNavigationTest extends BaseTest {
    
    @Test
    @Story("Complete app navigation test")
    @Description("Test navigation through all main app sections")
    @Severity(SeverityLevel.CRITICAL)
    public void testCompleteAppNavigation() {
        HomePage homePage = new HomePage();
        homePage.verifyHomeScreenLoaded();
        
        String[] categories = {
            "Accessibility", "Animation", "App", "Content", 
            "Graphics", "Media", "NFC", "OS", "Preference", "Text", "Views"
        };
        
        for (String category : categories) {
            Allure.step("Testing navigation to: " + category);
            homePage.verifyHomeScreenLoaded();
            
            switch (category) {
                case "Accessibility":
                    homePage.clickAccessibility();
                    break;
                case "Animation":
                    homePage.clickAnimation();
                    break;
                case "App":
                    homePage.clickApp();
                    break;
                case "Content":
                    homePage.clickContent();
                    break;
                case "Graphics":
                    homePage.clickGraphics();
                    break;
                case "Media":
                    homePage.clickMedia();
                    break;
                case "NFC":
                    homePage.clickNFC();
                    break;
                case "OS":
                    homePage.clickOS();
                    break;
                case "Preference":
                    homePage.clickPreference();
                    break;
                case "Text":
                    homePage.clickText();
                    break;
                case "Views":
                    homePage.clickViews();
                    break;
            }
            
            Allure.step("Wait for navigation to " + category);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            Allure.step("Navigate back from " + category);
            getDriver().navigate().back();
            
            assertTrue(homePage.isAccessibilityOptionDisplayed(), 
                "Should be back on home screen after navigating to " + category);
        }
    }
}