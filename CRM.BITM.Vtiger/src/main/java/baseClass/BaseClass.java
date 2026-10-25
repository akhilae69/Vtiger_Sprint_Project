package baseClass;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genricUtility.PropertyFileUtility;
import genricUtility.WebDriverUtility;
import objRepo.LoginPage;
import objRepo.LogoutPage;

public class BaseClass {

	public WebDriver driver;    
    @BeforeSuite(alwaysRun = true)
    public void bs() {
        Reporter.log("Open Database", true);
    }

    @AfterSuite(alwaysRun = true)
    public void as() {
        Reporter.log("Close Database connectivity", true);
    }

    @BeforeTest(alwaysRun = true)
    public void bt() {
        Reporter.log("PreCondition", true);
    }

    @AfterTest(alwaysRun = true)
    public void at() {
        Reporter.log("PostCondition", true);
    }

    // Launch the browser and open the application before each test
    @BeforeMethod (alwaysRun = true)
    @Parameters("browser")
    public void bc(@Optional("chrome") String browser) throws IOException, InterruptedException {

        Reporter.log("Opening browser", true);
        System.out.println("Browser received: " + browser);
        driver = WebDriverUtility.launchbrowser(browser);
        driver.get(PropertyFileUtility.getDataFromProperty("url"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user_name")));
        Reporter.log("Application launched", true);
    }
    
    @AfterMethod(alwaysRun = true)
    public void ac() {
        if (driver != null) {
            driver.quit();
            driver = null;
            Reporter.log("Browser closed successfully.", true);
        }
    }
    
    public void login() throws Exception {
    	LoginPage l = new LoginPage(driver);
    	l.getUn(PropertyFileUtility.getDataFromProperty("UN"));
    	l.getPw(PropertyFileUtility.getDataFromProperty("PW"));
    	l.getLoginBtn();
    }
    
    public void logout() {
    	LogoutPage l = new LogoutPage(driver);
    	l.getBtn();
    	l.getSignoutbtn();
    }
}