package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage {

	WebDriver driver ;
	
	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver , this);
	}
	
	// Navigation links
	
	@FindBy(xpath = "//a[text()='More']")
	private WebElement morelink;
	
	@FindBy(name = "Services")
	private WebElement serviceslink;
	
	@FindBy(name = "Assets")
	private WebElement assetslink;
	
	@FindBy(name = "Purchase Order")
	private WebElement purchaselink;
	
	@FindBy(name = "Price Books")
	private WebElement pricelink;
	
	@FindBy(name = "Vendors")
	private WebElement vendorslink;

	
	public void getMorelink() {
		morelink.click();
	}
	
	public void verifyHome() {
		Assert.assertTrue(driver.getTitle().contains("ACOE Fireflink - Home"), "HomePage is not displayed");
		Reporter.log("Home Page is diaplayed" , true);
	}

	public void getServiceslink() {
		serviceslink.click();
		Assert.assertTrue(driver.getTitle().contains("ACOE Fireflink - Services"),"service page is not displayed");
		Reporter.log("Services page is displayed" , true);
	}

	public void getAssetslink() {
		assetslink.click();
		Assert.assertTrue(driver.getTitle().contains("ACOE Fireflink - Assets"),"Assets page is not displayed");
		Reporter.log("Assets page is displayed" , true);
	}

	public void getPurchaselink() throws InterruptedException {
		purchaselink.click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.getTitle().contains("ACOE Fireflink - Purchase"),"Purchase page is not displayed");
		Reporter.log("Purchase page is displayed" , true);
	}

	public void getPricelink() {
		pricelink.click();
		Assert.assertTrue(driver.getTitle().contains("ACOE Fireflink - Price"),"Price page is not displayed");
		Reporter.log("Price page is displayed" , true);
	}

	public void getVendorslink() {
		vendorslink.click();
		Assert.assertTrue(driver.getTitle().contains("ACOE Fireflink - Vendors"),"Vendors page is not displayed");
		Reporter.log("Vendors page is displayed" , true);
	}
}