package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    static WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }
    public static By addToCartButton = By.id("add-to-cart-button");
    public static By productPrice = By.xpath("//*[contains(@class,'priceToPay')]//span[@class='a-price-whole']");
    public static String captureProductPriceOnDetailsPage() {
        return driver.findElement(productPrice).getText();
    }
    public static void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
