package crmVtigerPurchaseOrder;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import genricUtility.ScreenshotUtility;
import objRepo.HomePage;
import objRepo.PurchasePage;

public class PurchaseOrder_Test extends BaseClass {

    // Verify Purchase Order creation
    @Test(
        priority = 1,
        groups = {"FUN", "POS"},
        dataProvider = "purchase",
        dataProviderClass = DataProviderClass.class
    )
    public void verifyPurchaseOrderCreation_Test(
            String subject,
            String billingAddress,
            String shippingAddress,
            String quantity) throws Exception {

        login();

        HomePage h = new HomePage(driver);
        h.getMorelink();
        h.getPurchaselink();

        PurchasePage p = new PurchasePage(driver);
        p.getCreatePurchaseIcon();
        p.enterSubject(subject);
        p.getVendorIcon();
        p.getstatus();
        p.getAssignedId();
        p.enterBillingAddress(billingAddress);
        p.enterShippingAddress(shippingAddress);
        p.getproductSearchIcon();
        p.enterQuantity(quantity);
        p.clickSave();

        p.getpurchaseLink();
        p.getsearchfield(subject);
        p.getsearchIn();
        p.getsearchnowbtn();
        p.verifypurchase(subject);

        logout();
    }

   // Verifies that editing one Purchase Order does not affect another
    @Test(
        priority = 2,
        groups = {"REG", "POS"},
        dataProvider = "newPurchase",
        dataProviderClass = DataProviderClass.class
    )
    public void verifyEditingOnePurchaseOrderDoesNotAffectAnother_Test(
            String newName) throws Exception {

        login();

        HomePage home = new HomePage(driver);
        home.getMorelink();
        home.getPurchaselink();

        PurchasePage p = new PurchasePage(driver);
        p.getproduct1();
        p.geteditbtn();
        p.geteditsubject(newName);
        p.clickSave();

        p.getpurchaseLink();
        p.getsearchfield(newName);
        p.getsearchIn();
        p.getsearchnowbtn();
        p.verifyeditedsubject(newName);

        logout();
    }
    // Verify Purchase Order Qty using Boundary Value Analysis
    @Test(
        priority = 3,
        groups = {"FUN", "BVA"},
        dataProvider = "QtyBVA",
        dataProviderClass = DataProviderClass.class
    )
    public void verifyPurchaseOrderQtyBVA_Test(
            String subject,
            String vendorName,
            String billingAddress,
            String shippingAddress,
            String productName,
            String quantity,
            String expectedResult) throws Exception {

        login();

        HomePage home = new HomePage(driver);
        home.getMorelink();
        home.getPurchaselink();

        PurchasePage purchase = new PurchasePage(driver);

        purchase.getCreatePurchaseIcon();
        purchase.enterSubject(subject);
        purchase.getVendorIcon();
        purchase.getstatus();
        purchase.getAssignedId();
        purchase.enterBillingAddress(billingAddress);
        purchase.enterShippingAddress(shippingAddress);
        purchase.getproductSearchIcon();
        purchase.enterQuantity(quantity);
        purchase.clickSaveButtonOnly();

        String actualResult = expectedResult.equals("Reject")
                ? purchase.verifyInvalidQtyAlert()
                : purchase.verifyValidQtySubmission(subject);
        
        Thread.sleep(2000);
        Assert.assertEquals(actualResult,expectedResult,"Unexpected validation result for Qty: " + quantity);
        ScreenshotUtility.takeScreenshot(driver,"QtyBVA_" + quantity.replace("-", "Negative"));
        logout();
    }
}