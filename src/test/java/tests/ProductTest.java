package tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;

public class ProductTest extends BaseTest {
    private HomePage homePage;
    
    @BeforeMethod
    public void loginToApplication() {
        LoginPage loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        
        // Login with valid credentials
        loginPage.login(config.getProperty("validUsername"), config.getProperty("validPassword"));
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Login should be successful");
    }
    
    @Test(description = "Verify product filtering by price low to high")
    public void testProductFilteringLowToHigh() {
        // Apply filter
        homePage.sortByPriceLowToHigh();
        
        // Get product names after filtering
        List<String> productNames = homePage.getProductNames();
        
        // Print product names
        System.out.println("Products after sorting by price (Low to High):");
        for (String productName : productNames) {
            System.out.println("- " + productName);
        }
        
        // Assert that products are displayed
        Assert.assertTrue(productNames.size() > 0, "Products should be displayed after filtering");
        
        // You can add additional assertions here to verify the actual sorting
        Assert.assertTrue(productNames.contains("Sauce Labs Onesie"), 
                         "Expected product should be present in the list");
    }
    
}
