package tests;

import base.BaseTest;
import pages.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Epic("Mobile Automation")
@Feature("Home Screen Tests")
@Owner("QA Team")
public class HomeScreenTest extends BaseTest {
    
    private HomePage homePage;
    
    @Test(priority = 1)
    @Story("User can view home screen successfully")
    @Description("Verify that the home screen loads with all menu options")
    @Severity(SeverityLevel.BLOCKER)
    public void testHomeScreenLoadsSuccessfully() {
        homePage = new HomePage();
        
        homePage.verifyHomeScreenLoaded();
        
        assertTrue(homePage.isAccessibilityOptionDisplayed(), 
            "Accessibility option should be displayed");
        
        homePage.printAllMenuItems();
        
        assertTrue(homePage.getMenuItemsCount() >= 12, 
            "Should have at least 12 menu items");
        
        // Verify specific menu items
        homePage.verifyMenuItemsPresent("Accessibility", "Animation", "App", "Views");
    }
    
    @Test(priority = 2)
    @Story("User can navigate to different sections")
    @Description("Test basic navigation functionality using generic method")
    @Severity(SeverityLevel.CRITICAL)
    public void testNavigationUsingGenericMethod() {
        homePage = new HomePage();
        
        // Test navigation using generic method
        String[] sectionsToTest = {"Animation", "App", "Content"};
        
        for (String section : sectionsToTest) {
            homePage.clickMenuItemByName(section);
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            getDriver().navigate().back();
            
            assertTrue(homePage.isAccessibilityOptionDisplayed(), 
                "Should be back on home screen after navigating to " + section);
        }
    }
    
    @Test(priority = 3)
    @Story("Test all navigation options")
    @Description("Navigate through all available menu options")
    @Severity(SeverityLevel.NORMAL)
    public void testAllNavigationOptions() {
        homePage = new HomePage();
        
        // Get all menu item texts and test navigation for each
        var menuTexts = homePage.getAllMenuItemTexts();
        Allure.addAttachment("Available Menu Items", "text/plain", 
            String.join("\n", menuTexts));
        
        // Test first 3 items to avoid long execution
        for (int i = 0; i < Math.min(3, menuTexts.size()); i++) {
            String menuText = menuTexts.get(i);
            if (!menuText.isEmpty()) {
                homePage.clickMenuItemByName(menuText);
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                getDriver().navigate().back();
                
                assertTrue(homePage.isAccessibilityOptionDisplayed(), 
                    "Should be back on home screen after navigating to " + menuText);
            }
        }
    }
    
    @Test(priority = 4)
    @Story("Special characters handling")
    @Description("Test navigation with special character menu items")
    @Severity(SeverityLevel.MINOR)
    public void testSpecialCharacterMenuItem() {
        homePage = new HomePage();
        
        // Test the menu item with apostrophe
        homePage.clickAccessibilityWithApostrophe();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        getDriver().navigate().back();
        
        assertTrue(homePage.isAccessibilityOptionDisplayed(), 
            "Should be back on home screen after navigating to Access'ibility");
    }
}