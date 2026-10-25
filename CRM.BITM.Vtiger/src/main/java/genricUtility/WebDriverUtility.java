package genricUtility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtility {
	
	// Launches the specified browser and configures WebDriver
    public static WebDriver launchbrowser(String browser) {
        if (browser == null || browser.trim().isEmpty()) {
            throw new IllegalArgumentException("Browser name is missing in the property file.");
        }
        browser = browser.trim();
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions settings = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.password_manager_leak_detection", false);
            settings.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(settings);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}