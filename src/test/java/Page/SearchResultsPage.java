package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
    static WebDriver driver;
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    public static By firstProductPrice = By.xpath("(//span[@class='a-price-whole'])[1]");
    public static By firstProductTitle = By.xpath("(//div[contains(@class, 's-product-image-container')])[1]");
    public static By locationUpdate = By.id("glow-ingress-block");
    public static By enterPin = By.id("GLUXZipUpdateInput");
    public static By applyButton = By.id("GLUXZipUpdate");
    public static By closePopup = By.xpath("//*[@class='a-icon a-icon-close']");
    public static By getProductPriceByIndex(int index) {
        return By.xpath("(//span[@class='a-price-whole'])[" + index + "]");
    }
    private By getProductLinkByIndex(int index) {
        return By.xpath("(//div[contains(@class, 's-product-image-container')])[" + index + "]");
    }
    public String captureProductPrice(int index) {
        return driver.findElement(getProductPriceByIndex(index)).getText();
    }
    public void clickOnProductByIndex(int index) {
        driver.findElement(getProductLinkByIndex(index)).click();
    }
    public String captureFirstProductPrice() {
        return driver.findElement(firstProductPrice).getText();
    }
    public void clickOnFirstProduct() {
        driver.findElement(firstProductTitle).click();
    }

}
