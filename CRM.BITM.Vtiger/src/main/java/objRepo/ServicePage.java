package objRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServicePage {

    WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public ServicePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Create Service
    @FindBy(xpath = "//img[@title='Create Service...']")
    private WebElement createIcon;

    public void getcreateIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(createIcon)).click();
    }

    // Create Service page title
    @FindBy(xpath = "//span[text()='Creating New Service']")
    private WebElement creatingservice;

    // Return title to Test class for assertion
    public String getCreatingServiceTitle() {
        return wait.until(ExpectedConditions.visibilityOf(creatingservice)).getText();
    }

    // Service name field
    @FindBy(name = "servicename")
    private WebElement serviceNameField;

    public void enterServiceName(String name) {
        wait.until(ExpectedConditions.visibilityOf(serviceNameField)).sendKeys(name);
    }

    // Services links
    @FindBy(css = "[title='Services']")
    private List<WebElement> services;

    public void servicesname() {
        for (WebElement service : services) {
            if (service.isDisplayed() && service.isEnabled()) {
                service.click();
                return;
            }
        }

        throw new RuntimeException("Services link is not available");
    }

    // Check whether a Service exists in the current results
    public boolean isServicePresent(String serviceName) {

        By serviceLocator = By.xpath( "//a[normalize-space()='" + serviceName + "']");
        List<WebElement> matchingServices = driver.findElements(serviceLocator);
        for (WebElement service : matchingServices) {
            if (service.isDisplayed() && serviceName.equals(service.getText().trim())) {
                return true;
            }
        }

        return false;
    }

    // Service search field
    @FindBy(name = "search_text")
    private WebElement searchField;

    public void searchServiceName(String name) {
        WebElement field = wait.until(ExpectedConditions.visibilityOf(searchField));

        field.clear();
        field.sendKeys(name);
    }

    // Search button
    @FindBy(name = "submit")
    private WebElement searchButton;

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    // Active status in Service details
    @FindBy(id = "dtlview_Active")
    private WebElement verifyActive;

    // Return status text to Test class
    public String getActiveStatus() {
        return wait.until(ExpectedConditions.visibilityOf(verifyActive)).getText().trim();
    }

    // Edit Service
    @FindBy(xpath = "//input[@title='Edit [Alt+E]']")
    private WebElement editlink;

    public void getEditlink() {
        wait.until(ExpectedConditions.elementToBeClickable(editlink)).click();
    }

    // Active/Discontinued checkbox
    @FindBy(name = "discontinued")
    private WebElement activeCheck;

    public void getactivercheck() {
        wait.until(ExpectedConditions.elementToBeClickable(activeCheck)).click();
    }

    // Save Service
    @FindBy(xpath = "//input[@value='  Save  ']")
    private WebElement savebtn;

    public void getsavebtn() {
        wait.until(ExpectedConditions.elementToBeClickable(savebtn)).click();
    }

    // Read checkbox state if required by another test
    public boolean isActiveCheckboxSelected() {
        return wait.until( ExpectedConditions.visibilityOf(activeCheck)).isSelected();
    }
}