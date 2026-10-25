package objRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
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

public class VendorsPage {

    WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public VendorsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Create Vendor
    @FindBy(xpath = "//img[@title='Create Vendor...']")
    private WebElement createVendorButton;

    public void clickCreateVendor() {
        wait.until(ExpectedConditions.elementToBeClickable(createVendorButton)).click();
        Reporter.log("Create Vendor form opened successfully.", true);
    }

    @FindBy(name = "vendorname")
    private WebElement vendorNameField;

    public void verifyCreateVendorForm() {
        wait.until(ExpectedConditions.urlContains("action=EditView"));
        Assert.assertTrue(vendorNameField.isDisplayed(),"Vendor Name field is not displayed.");
        Reporter.log("Create Vendor form is displayed successfully.", true);
    }

    public void getVendorName(String vendorName) {
        vendorNameField.clear();
        vendorNameField.sendKeys(vendorName);
    }

    // Vendor details
    @FindBy(id = "email")
    private WebElement emailField;

    public void getEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    @FindBy(id = "phone")
    private WebElement phoneField;

    public void enterPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    @FindBy(name = "glacct")
    private WebElement glAccountDropdown;

    public void selectGLAccountByIndex() {
        Select select = new Select(glAccountDropdown);
        select.selectByIndex(1);
    }

    @FindBy(name = "website")
    private WebElement websiteField;

    public void enterWebsite(String website) {
        websiteField.clear();
        websiteField.sendKeys(website);
    }

    @FindBy(id = "category")
    private WebElement categoryField;

    public void enterCategory(String category) {
        categoryField.clear();
        categoryField.sendKeys(category);
    }

    @FindBy(name = "street")
    private WebElement streetField;

    public void enterStreet(String street) {
        streetField.clear();
        streetField.sendKeys(street);
    }

    @FindBy(id = "pobox")
    private WebElement poBoxField;

    public void enterPOBox(String poBox) {
        poBoxField.clear();
        poBoxField.sendKeys(poBox);
    }

    @FindBy(id = "city")
    private WebElement cityField;

    public void enterCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
    }

    @FindBy(id = "state")
    private WebElement stateField;

    public void enterState(String state) {
        stateField.clear();
        stateField.sendKeys(state);
    }

    @FindBy(name = "postalcode")
    private WebElement postalCodeField;

    public void enterPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    @FindBy(name = "country")
    private WebElement countryField;

    public void enterCountry(String country) {
        countryField.clear();
        countryField.sendKeys(country);
    }

    @FindBy(name = "description")
    private WebElement descriptionField;

    public void enterDescription(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    // Save Vendor
    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveButton;

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        Reporter.log("Save button clicked.", true);
    }

    // Mandatory Vendor Name validation
    public void verifyVendorNameMandatory() {
        wait.until(ExpectedConditions.visibilityOf(vendorNameField));

        Assert.assertTrue(vendorNameField.getAttribute("value").trim().isEmpty(),
            "Vendor Name field should be blank.");
        Reporter.log("Vendor Name field is blank.", true);
    }

    public void clickSaveAndVerifyVendorNameAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertMessage = alert.getText();
        Assert.assertEquals(alertMessage,"Vendor Name cannot be empty","Incorrect validation message displayed.");
        Reporter.log("Validation alert displayed correctly: " + alertMessage, true);

        alert.accept();
    }

    // Open existing Vendor
    @FindBy(css = "[name='search_text']")
    private WebElement searchTextField;

    @FindBy(id = "bas_searchfield")
    private WebElement searchFieldDropdown;

    @FindBy(xpath = "//input[@name='submit']")
    private WebElement searchNowButton;

    public void openVendor(String vendorName) {
        wait.until(ExpectedConditions.visibilityOf(searchTextField));
        searchTextField.clear();
        searchTextField.sendKeys(vendorName);

        Select select = new Select(searchFieldDropdown);
        select.selectByVisibleText("Vendor Name");

        wait.until(ExpectedConditions.elementToBeClickable(searchNowButton)).click();
        By vendorLink = By.xpath("//table[contains(@class,'lvt')]//a[normalize-space()='"+ vendorName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(vendorLink)).click();
    }

    // Purchase Order
    @FindBy(xpath = "//a[normalize-space()='Create Purchase Order']")
    private WebElement createPurchaseOrderLink;

    public void clickCreatePurchaseOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(createPurchaseOrderLink)).click();

        Reporter.log("Create Purchase Order clicked.", true);
    }

    // More Information tab
    @FindBy(xpath = "//a[text()='More Information']")
    private WebElement moreInformationTab;

    @FindBy(id = "show_Vendors_Products")
    private WebElement productsSectionArrow;

    @FindBy(id = "show_Vendors_PurchaseOrder")
    private WebElement purchaseOrderSectionArrow;

    @FindBy(id = "show_Vendors_Contacts")
    private WebElement contactsSectionArrow;

    @FindBy(id = "show_Vendors_Emails")
    private WebElement emailSection;

    public void clickMoreInformationTab() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInformationTab)).click();
        Assert.assertTrue(MoreInformationPage(),"More Information page verification failed.");
        Reporter.log("More Information tab verified.", true);
    }

    public boolean MoreInformationPage() {
        boolean products = wait.until(ExpectedConditions.visibilityOf(productsSectionArrow)).isDisplayed();

        boolean purchaseOrder = purchaseOrderSectionArrow.isDisplayed();
        boolean contacts = contactsSectionArrow.isDisplayed();
        boolean emails = emailSection.isDisplayed();

        return products && purchaseOrder && contacts && emails;
    }

    public void clickPurchaseOrderSectionArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseOrderSectionArrow)).click();
        Reporter.log("Purchase Order section expanded.", true);
    }

    @FindBy(id = "tbl_Vendors_PurchaseOrder")
    private WebElement purchaseList;

    public void verifypurchaseList() {
        wait.until(ExpectedConditions.visibilityOf(purchaseList));
        List<WebElement> rows = purchaseList.findElements(
            By.xpath(".//tr[@bgcolor='white']"));
        Assert.assertTrue(rows.size() > 0, "No purchase found.");
        for (WebElement row : rows) {
            Reporter.log(row.getText(), true);
        }
    }
}