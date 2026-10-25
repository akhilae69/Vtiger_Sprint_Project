package crmVitgerVendors;

import org.testng.Reporter;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import genricUtility.ScreenshotUtility;
import objRepo.HomePage;
import objRepo.PurchasePage;
import objRepo.VendorsPage;

public class Vendor_Test extends BaseClass {
	
	// Verifies that the Create New Vendor form opens successfully
	@Test(priority = 1, groups = {"SMK", "POS"})
	public void verifyCreateVendorFormOpens_Test() throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.clickCreateVendor();
	    vendor.verifyCreateVendorForm();
	}
	
	//Verifies that a vendor is created successfully with valid details and the saved record displays the entered information
	@Test(priority = 2,groups = {"FUN", "POS"},dataProvider = "vendorData",dataProviderClass = DataProviderClass.class)
	public void createVendorWithValidDetails_Test(String vendorName,String email,String phone,String website,String category,String street,String poBox,String city,String state,String postalCode,String country,String description) throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.clickCreateVendor();
	    vendor.getVendorName(vendorName);
	    vendor.getEmail(email);
	    vendor.enterPhone(phone);
	    vendor.selectGLAccountByIndex();
	    vendor.enterWebsite(website);
	    vendor.enterCategory(category);
	    vendor.enterStreet(street);
	    vendor.enterPOBox(poBox);
	    vendor.enterCity(city);
	    vendor.enterState(state);
	    vendor.enterPostalCode(postalCode);
	    vendor.enterCountry(country);
	    vendor.enterDescription(description);
	    vendor.clickSave();
	}
	
	//Verify end-to-end Purchase Order creation from a vendor
	@Test(priority = 3,groups = {"SYS", "POS"},dataProvider = "purchaseOrderData",dataProviderClass = DataProviderClass.class)
	public void createPurchaseOrderFromVendor_Test(String subject,String vendorName,String billingAddress,String shippingAddress,String productName,String quantity) throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.openVendor(vendorName);
	    vendor.clickCreatePurchaseOrder();
	    PurchasePage purchase = new PurchasePage(driver);
	    purchase.enterSubject(subject);
	    purchase.selectStatus();
	    purchase.selectAssignedTo();
	    purchase.enterBillingAddress(billingAddress);
	    purchase.enterShippingAddress(shippingAddress);
	    purchase.clickProductSearchIcon();
	    purchase.enterQuantity(quantity);
	    purchase.clickSave();
	    vendor.clickMoreInformationTab();
	    vendor.clickPurchaseOrderSectionArrow();
	    vendor.verifypurchaseList();
	    Reporter.log("Purchase Order section displayed successfully.", true);
	    logout();
}
	
	// Verify vendor cannot be saved with blank Vendor Name
	@Test(priority = 4, groups = {"NEG"})
	public void verifyVendorNameMandatory_Test() throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getVendorslink();
	    VendorsPage vendor = new VendorsPage(driver);
	    vendor.clickCreateVendor();
	    vendor.verifyCreateVendorForm();
	    vendor.verifyVendorNameMandatory();
	    vendor.clickSaveAndVerifyVendorNameAlert();
	    ScreenshotUtility.takeScreenshot(driver, "VendorNameMandatoryValidation");
	    Reporter.log("Vendor creation was prevented because Vendor Name was blank.",true);
	    logout();
	}	
	
}