package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.DataReader;

public class LoginTest extends BaseTest {

    /**
     * Simple test with valid credentials from config.properties
     */
    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Login with valid credentials from config
        loginPage.login(config.getProperty("validUsername"), config.getProperty("validPassword"));
        // Initialize HomePage AFTER login
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(),
                "Homepage should be displayed after valid login");
    }

    /**
     * Simple test with invalid credentials from config.properties
     */
    @Test(description = "Verify login failure with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Login with invalid credentials from config
        loginPage.login(config.getProperty("invalidUsername"), config.getProperty("invalidPassword"));

        // Assert error message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid login");
    }
}
