package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;

public class Utilities {
    private static WebDriver driver;
    // Constructor to initialize the driver
    public Utilities(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresence(By locator, int seconds, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element;  // Return the WebElement after waiting
        } catch (TimeoutException e) {
            System.out.println("Timeout after " + seconds + " seconds waiting for element: " + message);
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred: " + message);
            e.printStackTrace();
            return null;
        }
    }

}
