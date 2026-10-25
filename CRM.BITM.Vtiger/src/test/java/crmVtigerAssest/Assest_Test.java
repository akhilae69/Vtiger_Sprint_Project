package crmVtigerAssest;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import genricUtility.ScreenshotUtility;
import objRepo.AssetPage;
import objRepo.HomePage;

public class Assest_Test extends BaseClass {
	
	// Verifies that the Create New Asset form opens successfully
	@Test(priority = 1, groups = {"SMK", "POS"})
	public void verifyCreateAssestFormOpens_Test() throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.verifycreatingassest();
	}
	
	// Verifies that an asset can be created with valid details
	@Test(priority = 2, groups = {"FUN", "POS"},dataProvider ="newAsset",dataProviderClass = DataProviderClass.class)
	public void verifyAssetStatusCanBeChangedAndSaved_Test(String no,String name) throws Exception {
	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.getserialno(no);
	    a.getassetstatus();
	    a.getassetname(name);
	    a.getassestProductIcon();
	    a.getassestCustomerIcon();
	    a.getsaveButton();
	    a.getverifyassest();
	    a.getsearchfield(name);
	    a.getsearchIn();
	    a.getsearchnowbtn();
	}
	
	// Verifies that an existing invoice can be associated with an asset
	@Test(priority = 3, groups = {"INT", "POS"},dataProvider ="newAsset",dataProviderClass = DataProviderClass.class)
	public void verifyExistingInvoiceCanBeAssociatedWithAsset_Test(String no,String name)
	        throws Exception {

	    login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.getserialno(no);
	    a.getassetstatus();
	    a.getassetname(name);
	    a.getassestProductIcon();
	    a.getassestCustomerIcon();
	    a.getInvoiceIcon();
	    a.getsaveButton();
	    a.getAssetlink();
	    a.verifyAssetPresent(name);
	    logout();
	}
	
	// Verifies asset details after re-login
	@Test(priority = 4, groups = {"SYS", "POS"},dataProvider ="newAsset",dataProviderClass = DataProviderClass.class)
	public void verifyAssetInformationPersistsAfterRelogin_Test(String no,String name) throws Exception {
		login();
	    HomePage home = new HomePage(driver);
	    home.getMorelink();
	    home.getAssetslink();
	    AssetPage a = new AssetPage(driver);
	    a.getcreateIcon();
	    a.getserialno(no);
	    a.getassetstatus();
	    a.getassetname(name);
	    a.getassestProductIcon();
	    a.getassestCustomerIcon();
	    a.getInvoiceIcon();
	    a.getsaveButton();
	    logout();
	    login();
	    HomePage h = new HomePage(driver);
	    h.getMorelink();
	    h.getAssetslink();
	    a.verifyAssetPresent(name);	
	}
	
	 // Verifies that cancelling asset changes does not save the updated name
	@Test(priority = 5,groups = {"REG", "POS"},dataProvider = "updatedAssetName",dataProviderClass = DataProviderClass.class)
		public void verifyCancelDoesNotSaveAssetChanges_Test(String existingName, String updatedName) throws Exception {
		    login();
		    HomePage home = new HomePage(driver);
		    home.getMorelink();
		    home.getAssetslink();
		    AssetPage a = new AssetPage(driver);
		    a.getsearchfield(existingName);
		    a.getsearchIn();
		    a.getsearchnowbtn();
		    a.openAsset(existingName);
		    a.editButton();
		    a.changeAssetName(updatedName);
		    a.getcancelButton();
		    Thread.sleep(2000);
		    a.getAssetlink();
		    a.getsearchfield(updatedName);
		    a.getsearchIn();
		    a.getsearchnowbtn();
		    a.verifyUpdatedAssetNotPresent(updatedName);
		    Thread.sleep(2000);
		    ScreenshotUtility.takeScreenshot(driver, "CancelDoesNotSaveAssetChanges");
		    logout();
	}
}
