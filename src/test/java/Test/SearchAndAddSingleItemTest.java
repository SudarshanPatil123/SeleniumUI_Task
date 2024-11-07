package Test;

import Base.BaseClass;
import Page.AmazonHomePage;
import Page.CartPage;
import Page.ProductPage;
import Page.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(BaseClass.class)
public class SearchAndAddSingleItemTest extends BaseClass {
    @Test
    public void searchAndValidatePrices() throws InterruptedException {
        AmazonHomePage homePage = new AmazonHomePage(driver);
        homePage.searchForProduct("toys");
        // Step 2: Select a product and capture price on search results page
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        String searchPagePrice = searchResultsPage.captureFirstProductPrice();
        System.out.println("Price on Search Page: " + searchPagePrice);
        // Step 3: Click on the product and capture price on product details page
        searchResultsPage.clickOnFirstProduct();
        ProductPage productDetailsPage = new ProductPage(driver);
        String productDetailsPagePrice = productDetailsPage.captureProductPriceOnDetailsPage();
        System.out.println("Price on Product Details Page: " + productDetailsPagePrice);
        // Validate that the prices match between search page and product details page
        Assert.assertEquals(searchPagePrice, productDetailsPagePrice, "Prices do not match!");
        // Step 4: Add the product to the cart
        productDetailsPage.addToCart();
        System.out.println("Product added to cart successfully.");
        CartPage cartPage = new CartPage(driver);
        String cartPrice = cartPage.captureCartPrice();
        System.out.println("Price in Cart: " + cartPrice);
        // Step 7: Compare the product price on the product page with the cart price
        Assert.assertEquals(productDetailsPagePrice, cartPrice, "Prices do not match between Product Page and Cart!");
    }
}
