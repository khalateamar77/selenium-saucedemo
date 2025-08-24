package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

public class CartTest extends BaseTest {
    private HomePage homePage;
    private CartPage cartPage;

    @BeforeMethod
    public void loginAndNavigateToHome() {
        // Login with existing LoginPage method
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Now create HomePage object explicitly
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed after login");
    }

    @Test
    public void testCartOperations() {
        // Step 1: Sort products by Price (Low to High)
        homePage.sortByPriceLowToHigh();

        // Step 2: Get product list after sorting
        List<String> products = homePage.getProductNames();
        Assert.assertFalse(products.isEmpty(), "No products found after sorting");

        // Pick the first product from the list
        String selectedProduct = products.get(0);

        // Step 3: Add selected product to cart
        homePage.addProductToCartByName(selectedProduct);

        // Step 4: Assert that cart badge is updated
        int cartCount = homePage.getCartItemCount();
        Assert.assertTrue(cartCount > 0, "Cart badge count did not increase");

        // Step 5: Navigate to cart
        homePage.clickCartIcon();
        cartPage = new CartPage(driver);

        // Step 6: Verify the product is displayed in the cart
        List<String> cartItems = cartPage.getCartItemNames();
        Assert.assertTrue(cartItems.contains(selectedProduct),
                "Selected product was not found in the cart. Expected: " + selectedProduct);
    }
}
