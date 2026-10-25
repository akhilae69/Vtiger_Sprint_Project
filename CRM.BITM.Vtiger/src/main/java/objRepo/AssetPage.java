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

public class AssetPage {
	WebDriver driver;
	private WebDriverWait wait;
	// Constructor
	public AssetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver , this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	// Asset creation
	@FindBy(css ="[title='Create Asset...']")
	private WebElement createIcon;
	
	public void getcreateIcon() {
		createIcon.click();
	}
	
	@FindBy(xpath="//span[text()='Creating New Asset']")
	private WebElement creatingassest;
	
	public void verifycreatingassest() {
		creatingassest.getText();
		Reporter.log("Create Assest Page opens Successfully",true);
	}
	
	@FindBy(name="serialnumber")
	private WebElement serialno;
	
	public void getserialno(String no) {
		serialno.sendKeys(no);
	}
	
	@FindBy(name="assetstatus")
	private WebElement AssestStatus;
	
	public void getassetstatus() {
		Select s = new Select(AssestStatus);
		s.selectByIndex(1);
	}
	
	@FindBy(name="assetname")
	private WebElement AssestName;
	
	public void getassetname(String name) {
		AssestName.sendKeys(name);
	}
	
	@FindBy(id="assetname")
	private WebElement assetNameField;
	public void changeAssetName(String newName) {
	    wait.until(ExpectedConditions.visibilityOf(assetNameField));
	    assetNameField.clear();
	    assetNameField.sendKeys(newName);
	    Reporter.log("Asset Name changed to: " + newName, true);
	}
	
	@FindBy(css="[title='Edit [Alt+E]']")
	private WebElement editButton;
	
	public void editButton() {
		editButton.click();
	}
	
	@FindBy(css="[title='Cancel [Alt+X]']")
	private WebElement cancelButton;
	
	public void getcancelButton() {
		cancelButton.click();
	}
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])")
    private WebElement saveButton;
	
	public void getsaveButton() {
		saveButton.click();
	}
	
	// Asset search
	@FindBy(name="search_text")
	private WebElement searchfield;
	
	public void getsearchfield(String value) {
		searchfield.sendKeys(value);
	}
	
	@FindBy(id="bas_searchfield")
	private WebElement searchIn;
	
	public void getsearchIn() {
		Select s= new Select(searchIn);
		s.selectByIndex(1);
	}
	
	@FindBy(name="submit")
	private WebElement searchnowbtn;
	
	public void getsearchnowbtn() throws InterruptedException {
		searchnowbtn.click();
		Thread.sleep(1000);
	}
	
	// Product selection
	@FindBy(xpath="(//img[@title='Select'])[1]")
	private WebElement assestProductIcon;
	
	public void getassestProductIcon() throws InterruptedException {
    	String parentWindow = driver.getWindowHandle();
    	assestProductIcon.click();
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }
	    By organizationLocator = By.xpath("//a[@href='javascript:window.close();']");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(organizationLocator));
	    List<WebElement> organizations = driver.findElements(organizationLocator);
	    if (organizations.isEmpty()) {
	        throw new RuntimeException("No organizations found in popup");
	    }
	    organizations.get(0).click();
	    Thread.sleep(1000);
	    driver.switchTo().window(parentWindow);
	}
	
	// Customer selection
	@FindBy(xpath="(//img[@title='Select'])[2]")
	private WebElement assestCustomerIcon;
	
	public void getassestCustomerIcon() throws InterruptedException {
    	String parentWindow = driver.getWindowHandle();
    	assestCustomerIcon.click();
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }
	    By organizationLocator = By.xpath("//a[@href='javascript:window.close();']");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(organizationLocator));
	    List<WebElement> organizations = driver.findElements(organizationLocator);
	    if (organizations.isEmpty()) {
	        throw new RuntimeException("No organizations found in popup");
	    }
	    organizations.get(0).click();
	    Thread.sleep(1000);
	    driver.switchTo().window(parentWindow);
	}
	
	// Asset navigation
	@FindBy(xpath="(//a[text()='Assets'])[3]")
	private WebElement verifyassest;
	
	public void getverifyassest() {
		verifyassest.click();
	}
	
	// Invoice selection
	@FindBy(xpath = "(//img[@title='Select'])[3]")
	private WebElement invoiceIcon;
	
	public void getInvoiceIcon() throws InterruptedException {
		String parentWindow = driver.getWindowHandle();
		invoiceIcon.click();
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    for (String window : driver.getWindowHandles()) {
	        if (!window.equals(parentWindow)) {
	            driver.switchTo().window(window);
	            break;
	        }
	    }
	    By organizationLocator = By.xpath("//a[@href='javascript:window.close();' and normalize-space()]");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(organizationLocator));
	    List<WebElement> organizations = driver.findElements(organizationLocator);
	    if (organizations.isEmpty()) {
	        throw new RuntimeException("No organizations found in popup");
	    }
	    organizations.get(0).click();
	    Thread.sleep(1000);
	    driver.switchTo().window(parentWindow);
	}
	
	// Asset verification
	@FindBy(xpath = "(//a[text()='Assets'])[3]")
	private WebElement verifyAsset;
	
	public void getAssetlink() {
		verifyAsset.click();
	}
	
	@FindBy(css ="[title='Assets']")
	private WebElement assetsTable;

	public void verifyAssetPresent(String assetName) {
	    wait.until(ExpectedConditions.visibilityOf(assetsTable));
	    List<WebElement> assetNames = assetsTable.findElements(By.xpath("//a[normalize-space(.)='" + assetName + "']"));
	    boolean isDisplayed = false;
	    for(WebElement ele: assetNames) {
	    		String Expectedname=ele.getText();
	    		if (assetName.equals(Expectedname)) {
	    			System.out.println(Expectedname);
    		        isDisplayed = true;
    		        break;
    			}
	    }
	    Assert.assertTrue(isDisplayed,"Asset is not present in the Assets table: " + assetName);
	    Reporter.log("Asset is present in the Assets table: " + assetName, true);
	}
	
	// Opens the asset by its name
	public void openAsset(String assetName) {

	    By assetLocator = By.xpath("//a[normalize-space(.)='" + assetName + "']");
	    WebElement visibleAsset = wait.until(driver -> {
	        List<WebElement> assets = driver.findElements(assetLocator);
	        for (WebElement asset : assets) {
	            if (asset.isDisplayed() && asset.isEnabled()) {
	                return asset;
	            }
	        }
	        return null;
	    });
	    visibleAsset.click();
	    System.out.println("Asset opened successfully: " + assetName);
	}
	
	public void verifyUpdatedAssetNotPresent(String updatedName) {
	    By updatedAsset = By.xpath("//table[contains(@class,'lvt')]//a[normalize-space()='" + updatedName + "']");
	    Assert.assertTrue(driver.findElements(updatedAsset).isEmpty(),"FAIL: Updated Asset Name was saved unexpectedly: " + updatedName);
	    Reporter.log("PASS: Updated Asset Name was not saved: " + updatedName,true);
	}
}
