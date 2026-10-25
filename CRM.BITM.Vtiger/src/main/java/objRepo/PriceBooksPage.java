package objRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class PriceBooksPage {

    WebDriver driver;
    private WebDriverWait wait;
    
    // Constructor
	public PriceBooksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// Price Book navigation
	@FindBy(xpath="(//a[@title='Price Books'])[4]")
	private WebElement pricebookname;
	
	public void getPricebookname(){
		pricebookname.click();
	}
    
	@FindBy(name="Edit")
	private WebElement Editbtn;
 
	public void getEditbtn() {
		Editbtn.click();
	}
	
	@FindBy(name="currency_id")
	private WebElement currency;
	
	public void getCurrency() {
		By currencyLocator = By.name("currency_id");
	    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(currencyLocator));
	    Select s = new Select(dropdown);
	    s.selectByIndex(0);
	}
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	public void getSave() {
		savebtn.click();
	}
	
	// Price Book search
	@FindBy(xpath ="(//a[text()='Price Books'])[3]")
	private WebElement pricebooklink;
	
	public void getpricebooklink() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    By priceBookLink = By.xpath("(//a[text()='Price Books'])[3]");
	    wait.until(ExpectedConditions.elementToBeClickable(priceBookLink)).click();
	}
	
	@FindBy(name="search_text")
	private WebElement searchfield;
	
	public void getsearchfield(String value) {
		searchfield.sendKeys(value);
	}
	
	@FindBy(id="bas_searchfield")
	private WebElement searchIn;
	
	public void getsearchIn() {
		Select s= new Select(searchIn);
		s.selectByIndex(3);
	}
	
	@FindBy(name="submit")
	private WebElement searchnowbtn;
	
	public void getsearchnowbtn() throws InterruptedException {
		searchnowbtn.click();
		Thread.sleep(2000);
	}
	
	// Price Book creation
	@FindBy(xpath = "//img[@title='Create Price Book...']")
	private WebElement createPriceBookButton;
	
	public void clickCreatePriceBook() {
	createPriceBookButton.click();
	}
	@FindBy(name = "bookname")
	private WebElement bookNameField;

	public void enterPriceBookName(String name) {
	bookNameField.sendKeys(name);
	}

	public void selectCurrency(String currencyName) {
	Select s = new Select(currency);
	s.selectByVisibleText(currencyName);
	}
	
	//verification
	@FindBy(css="[title='Price Books']")
	private List<WebElement> orders;
	
	public void verifycurrency() {
		String actualPriceBookName = pricebookname.getText().trim();
		boolean isDisplayed = false;
		for (WebElement ele : orders) {
		    String expectedPriceBookName = ele.getText().trim();
		    if (actualPriceBookName.equals(expectedPriceBookName)) {
		        isDisplayed = true;
		        break;
		    }
		}
		Assert.assertTrue(isDisplayed,"Updated Price Book Name is not displayed in the list");
		Reporter.log("Updated Price Book Name is displayed successfully", true);
	}
		
	public void searchCreatedPriceBook(String bookName) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(searchfield));
	    searchfield.clear();
	    searchfield.sendKeys(bookName);
	    Select s= new Select(searchIn);
		s.selectByIndex(1);
	    searchnowbtn.click();
	    
	}
	
	public void openCreatedPriceBook(String bookName) {
	    List<WebElement> bookNames = driver.findElements(
	        By.cssSelector("a[title='Price Books']"));
	    for (WebElement ele : bookNames) {
	        if (ele.getText().trim().equals(bookName)) {
	            ele.click();
	            return;
	        }
	    }
	    Assert.fail("Price Book could not be opened: " + bookName);
	}
	
	@FindBy(xpath = "//td[span[@vtfieldname='currency_id']]")
	private List<WebElement> currencyElements;
	
	public void verifySelectedCurrency(String bookName, String expectedCurrency) {

	    // Find the row containing the exact Price Book name
	    WebElement row = driver.findElement(By.xpath("//tr[.//a[@title='Price Books' and normalize-space()='" + bookName + "']][1]"));
	    WebElement currencyCell = row.findElement(By.xpath("//td[span[@vtfieldname='currency_id']]"));
	    String actualCurrency = currencyCell.getText().trim();
	    Reporter.log("Price Book Name: " + bookName, true);
	    Reporter.log("Expected Currency: " + expectedCurrency, true);
	    Reporter.log("Actual Currency: " + actualCurrency, true);
	    Assert.assertEquals(actualCurrency,expectedCurrency,"Currency mismatch for Price Book: " + bookName);
	    Reporter.log("Currency verification passed!", true);
	}
}