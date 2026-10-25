package objRepo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class LogoutPage {

	WebDriver driver;
	
	// Constructor
	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//td[@class='small'])[2]")
	private WebElement btn;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signoutbtn;

	public void getBtn() {
		btn.click();
	}

	public void getSignoutbtn() {
		signoutbtn.click();
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains("http://49.249.29.4:8888/index.php?"), "Signout is not successful");
		Reporter.log("Signout is successful", true);
	}
	
	
}