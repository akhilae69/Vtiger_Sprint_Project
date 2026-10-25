package genricUtility;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtility {
	
	// Captures and saves a screenshot of the current browser
    public static void takeScreenshot(WebDriver driver, String name)throws IOException {
        TakesScreenshot tks = (TakesScreenshot) driver;
        File src = tks.getScreenshotAs(OutputType.FILE);
        File des = new File("./Screenshots/" + name + ".png");
        FileHandler.copy(src, des);
        System.out.println("Screenshot saved successfully: "+ des.getAbsolutePath());
    }
}



















