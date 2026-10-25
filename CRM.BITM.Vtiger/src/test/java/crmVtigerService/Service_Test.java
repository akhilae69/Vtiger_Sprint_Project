package crmVtigerService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import objRepo.HomePage;
import objRepo.ServicePage;

public class Service_Test extends BaseClass {

    // STC01: Verify Create Service form opens
    @Test(priority = 1, groups = {"SMK", "POS"})
    public void verifyCreateServiceFormOpens_Test() throws Exception {

        login();

        HomePage home = new HomePage(driver);
        home.getMorelink();
        home.getServiceslink();

        ServicePage service = new ServicePage(driver);
        service.getcreateIcon();

        String actualTitle = service.getCreatingServiceTitle();

        Assert.assertEquals(actualTitle,"Creating New Service","FAIL: Create Service page title is incorrect");
        Reporter.log("PASS: Create Service page opened successfully",true);

        logout();
    }

    // STC02: Update Active status
    @Test(priority = 2, groups = {"SYS", "POS"})
    public void STC02() throws Exception {

        login();

        HomePage home = new HomePage(driver);
        home.getMorelink();
        home.getServiceslink();

        ServicePage service = new ServicePage(driver);

        // Open the Service record
        service.servicesname();

        // Capture the original status
        String originalStatus = service.getActiveStatus();

        Reporter.log("Original Service status: " + originalStatus,true);

        // Edit and toggle the checkbox
        service.getEditlink();
        service.getactivercheck();
        service.getsavebtn();

        // Read the status after saving
        String updatedStatus = service.getActiveStatus();
        Assert.assertNotEquals(updatedStatus, originalStatus,"FAIL: Service status did not change after saving");
        Reporter.log("PASS: Service status changed from "+ originalStatus + " to " + updatedStatus,true);

        logout();
    }

    // STC03: Negative test - Search for a nonexistent Service
    @Test(priority = 3, groups = {"NEG"})
    public void verifyInvalidServiceName_Test() throws Exception {

        login();

        HomePage home = new HomePage(driver);
        home.getMorelink();
        home.getServiceslink();

        ServicePage service = new ServicePage(driver);

        String invalidName = "Invalid_Service_99999";

        // Search for a Service that should not exist
        service.searchServiceName(invalidName);
        service.clickSearchButton();

        // Validate the search result
        boolean servicePresent = service.isServicePresent(invalidName);

        Assert.assertFalse(servicePresent, "FAIL: Nonexistent Service was found: " + invalidName);
        Reporter.log("PASS: Invalid Service name was not found: " + invalidName, true);

        logout();
    }
}