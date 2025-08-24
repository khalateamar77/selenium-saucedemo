package tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DataReader;

public class CheckoutTest extends BaseTest {
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    @BeforeMethod
    public void setupForCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("validUsername"), config.getProperty("validPassword"));

        // ✅ create page objects AFTER login
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Login should be successful");

        homePage.sortByPriceLowToHigh();
        homePage.addFirstProductToCart();
        Assert.assertTrue(homePage.isProductAddedToCart(), "Product should be added to cart");
        homePage.clickCartIcon();
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page should be displayed");
        
    }
    
    @Test(description = "Verify complete checkout process")
    public void testCompleteCheckoutProcess() {
        cartPage.proceedToCheckout();

        Map<String, String> checkoutData = DataReader.getCheckoutData();
        checkoutPage.fillCheckoutInformation(
            checkoutData.get("firstName"),
            checkoutData.get("lastName"),
            checkoutData.get("postalCode")
        );

        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isOrderComplete(), "Order should be completed successfully");

        String confirmationMessage = checkoutPage.getConfirmationMessage();
        System.out.println("Order confirmation: " + confirmationMessage);

        Assert.assertTrue(
        	    confirmationMessage.contains("THANK YOU FOR YOUR ORDER"),
        	    "Confirmation message should contain 'Thank you for your order!'"
        	);

    }
}
