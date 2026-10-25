package crmVtigerLogin;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import genricUtility.ScreenshotUtility;
import objRepo.LoginPage;

public class Login_Test extends BaseClass {

	// Verifies that login is successful with valid credentials
    @Test(priority = 1,groups = {"FUN", "POS"})
    public void validLoginTest() throws Exception {
    		
    		login();
        String title = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();
        Reporter.log("Home Page Title: " + title, true);
        Reporter.log("Current URL: " + currentUrl, true);
        Assert.assertTrue(title.toLowerCase().contains("vtiger"),"Home page title is incorrect");
        Assert.assertTrue(currentUrl.contains("module=Home"),"User is not navigated to the Home page");
        Reporter.log("Login successful", true);
    }
    
    // Verify that an error message is displayed for invalid login credentials
    @Test(priority = 2,groups = {"FUN","NEG"},dataProvider = "invalidLoginData",dataProviderClass = DataProviderClass.class)
    public void invalidLoginTest(String username, String password) throws Exception {
        LoginPage l = new LoginPage(driver);
        l.login(username, password);
        l.getErrorMessage();
        Reporter.log("Invalid login validation completed", true);
        ScreenshotUtility.takeScreenshot(driver, "InvalidLogin");
    }
}