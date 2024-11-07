package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage {
    WebDriver driver;
    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }
    public static By searchBox = By.id("twotabsearchtextbox");
    public static By searchButton = By.id("nav-search-submit-button");
    public void searchForProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

}
