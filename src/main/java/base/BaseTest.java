package base;

import io.appium.java_client.android.AndroidDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static AndroidDriver driver;
    
    @BeforeClass
    public void globalSetup() {
        logger.info("=== Starting Test Class ===");
        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();
    }
    
    @BeforeMethod
    public void testSetup() {
        logger.info("--- Starting Test Method ---");
    }
    
    @AfterMethod
    public void testTeardown() {
        logger.info("--- Ending Test Method ---");
    }
    
    @AfterClass
    public void globalTeardown() {
        DriverManager.quitDriver();
        logger.info("=== Ending Test Class ===");
    }
    
    // Add getter for driver
    protected AndroidDriver getDriver() {
        return driver;
    }
}