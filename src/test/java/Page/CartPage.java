package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    static WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public static By cartPrice = By.xpath("//span[@class='a-price sw-subtotal-amount']//span[@class='a-price-whole']");
    public static String captureCartPrice() {
        return driver.findElement(cartPrice).getText();
    }
}
