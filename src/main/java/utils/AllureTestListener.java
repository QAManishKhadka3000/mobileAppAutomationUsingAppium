package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.DriverManager;

import java.io.ByteArrayInputStream;

public class AllureTestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        Allure.step("Starting test: " + result.getName());
        Allure.description("Test method: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed: " + result.getName());
        captureScreenshot();
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        Allure.step("Test failed: " + result.getName());
        captureScreenshot();
        
        // Add exception details
        if (result.getThrowable() != null) {
            Allure.addAttachment("Failure Reason", "text/plain", 
                result.getThrowable().getMessage());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.step("Test skipped: " + result.getName());
    }
    
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] captureScreenshot() {
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }
    
    // Alternative method with InputStream attachment
    public void captureScreenshotStream() {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", "image/png", 
                new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            // Ignore if screenshot fails
        }
    }
}