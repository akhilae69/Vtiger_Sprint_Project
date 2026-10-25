package crmVtigerPriceBooks;

import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genricUtility.DataProviderClass;
import objRepo.HomePage;
import objRepo.PriceBooksPage;

public class PriceBooks_Test extends BaseClass {

	// Verifies that an Price Book currency can be updated successfully
	@Test(priority = 1,groups = {"FUN", "POS"},dataProvider = "priceBookEditData",dataProviderClass = DataProviderClass.class)
		public void verifyPriceBookCanBeEdited_Test(String currency) throws Exception {
		    login();
		    HomePage h= new HomePage(driver);
		    h.getMorelink();
		    h.getPricelink();
		    PriceBooksPage p = new PriceBooksPage(driver);
		    p.getPricebookname();
		    p.getEditbtn();
		    p.getCurrency();
		    p.getSave();
		    p.getpricebooklink();
		    p.getsearchfield(currency);
		    p.getsearchIn();
		    p.getsearchnowbtn();
		    p.verifycurrency();
	}    
	
	// Verifies that the selected currency is saved correctly when creating a Price Book
	@Test(priority = 2,groups = {"INT", "POS"},dataProvider = "priceBookIntegrationData",dataProviderClass = DataProviderClass.class)
		public void verifyCurrencyRetainedAfterCreation_Test(String bookName, String currencyName) throws Exception {

		    login();
		    HomePage h = new HomePage(driver);
		    h.getMorelink();
		    h.getPricelink();
		    PriceBooksPage p = new PriceBooksPage(driver);
		    p.clickCreatePriceBook();
		    p.enterPriceBookName(bookName);
		    p.selectCurrency(currencyName);
		    p.getSave();
		    p.getpricebooklink();
		    p.searchCreatedPriceBook(bookName);
		    p.verifySelectedCurrency(bookName, currencyName);
		}
	// Regression Test: Verify Price Book currency update
	@Test(priority = 3, groups = {"REG"})
	public void verifyPriceBookCurrencyUpdateRegression_Test()
	        throws Exception {

	    login();

	    HomePage h = new HomePage(driver);
	    h.getMorelink();
	    h.getPricelink();

	    PriceBooksPage p = new PriceBooksPage(driver);

	    p.getPricebookname();
	    p.getEditbtn();
	    p.getCurrency();
	    p.getSave();

	    Reporter.log("PASS: Price Book currency update regression test completed.",true);

	    logout();
	}}