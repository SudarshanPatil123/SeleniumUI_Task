package Test;

import Base.BaseClass;
import Page.AmazonHomePage;
import Page.CartPage;
import Page.ProductPage;
import Page.SearchResultsPage;
import Utils.Utilities;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(BaseClass.class)
public class SearchAndAddTwoItemsValidatePrice extends BaseClass {
    @Test
    public void searchAndValidatePrices() throws InterruptedException {
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver);
        SearchResultsPage amazonSearchResultsPage = new SearchResultsPage(driver);
        ProductPage amazonProductDetailsPage = new ProductPage(driver);
        CartPage amazonCartPage = new CartPage(driver);
        Utilities waitUtil = new Utilities(driver);
        waitUtil.waitForElementPresence(AmazonHomePage.searchBox, 15, "SeachBoxIsPresent");
        // Step 1: Navigate to Amazon and search for toys
        amazonHomePage.searchForProduct("toys");
        String currentURL = driver.getCurrentUrl();
        waitUtil.waitForElementPresence(SearchResultsPage.locationUpdate, 20, "Click on location").click();
        waitUtil.waitForElementPresence(SearchResultsPage.enterPin, 30, "PIN ").sendKeys("43081");
        waitUtil.waitForElementPresence(SearchResultsPage.applyButton, 30, "Apply button").click();
        waitUtil.waitForElementPresence(SearchResultsPage.closePopup, 30, "Continue").click();
        // Step 2: Capture price and navigate to product details page for the first product
        Thread.sleep(5000);
        waitUtil.waitForElementPresence(SearchResultsPage.getProductPriceByIndex(1), 30, "First product price");
        String searchPagePrice1 = amazonSearchResultsPage.captureProductPrice(1);
        System.out.println("searchPagePrice1 : " + searchPagePrice1);
        amazonSearchResultsPage.clickOnProductByIndex(1);
        waitUtil.waitForElementPresence(ProductPage.productPrice, 10, "Price of Prodcut for 1 on details page");
        String productPagePrice1 = amazonProductDetailsPage.captureProductPriceOnDetailsPage();
        System.out.println("productPagePrice1 : " + productPagePrice1);
        // Validate that price on product details page matches the price on the search page
        Assert.assertEquals(productPagePrice1, searchPagePrice1, "Product 1 price mismatch!");
        amazonProductDetailsPage.addToCart();
        waitUtil.waitForElementPresence(CartPage.cartPrice, 5, "Caprture cart price");
        String cartPrice1 = String.valueOf(amazonCartPage.captureCartPrice());
        Assert.assertEquals(cartPrice1, productPagePrice1, "Cart price for product 1 mismatch!");
        // Step 3: Go back to search results, repeat for the second product
        driver.navigate().to(currentURL);
        waitUtil.waitForElementPresence(SearchResultsPage.getProductPriceByIndex(3), 10, "second product price");
        String searchPagePrice2 = amazonSearchResultsPage.captureProductPrice(3);
        System.out.println("searchPagePrice2 : " + searchPagePrice2);
        amazonSearchResultsPage.clickOnProductByIndex(3);
        waitUtil.waitForElementPresence(ProductPage.productPrice, 5, "Price of Prodcut for 2 on details page");
        String productPagePrice2 = amazonProductDetailsPage.captureProductPriceOnDetailsPage();
        System.out.println("productPagePrice2:" + productPagePrice2);
        // Validate that price on product details page matches the price on the search page
        Assert.assertEquals(productPagePrice2, searchPagePrice2, "Product 2 price mismatch!");
        ProductPage.addToCart();
    }
}
