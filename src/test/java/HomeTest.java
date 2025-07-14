

   import base.AppiumBase;
   import pages.HomePage;
   import org.slf4j.Logger;
   import org.slf4j.LoggerFactory;
   import org.testng.annotations.AfterClass;
   import org.testng.annotations.BeforeClass;
   import org.testng.annotations.Test;

   public class HomeTest extends AppiumBase {
       private static final Logger logger = LoggerFactory.getLogger(HomeTest.class);
       private HomePage homePage;

       @BeforeClass
       public void setUp() {
           setupDriver();
           homePage = new HomePage(driver);
       }

       @Test
       public void testAccessibilityClick() {
           logger.info("Starting accessibility click test");
           homePage.clickAccessibility();
           // Add assertions here based on the next screen behavior
       }

       @AfterClass
       public void tearDown() {
           quitDriver();
       }
   }