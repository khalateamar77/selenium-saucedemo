package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(className = "subheader")
    private WebElement pageSubheader;
    
    @FindBy(css = "span.title")
    private WebElement pageTitle;
    
    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItemNames;
    
    @FindBy(linkText = "CHECKOUT")
    private WebElement checkoutButton;
    
    @FindBy(className = "cart_quantity")
    private List<WebElement> itemQuantities;
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public boolean isCartPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(pageSubheader));
            return pageSubheader.getText().trim().equalsIgnoreCase("Your Cart");
        } catch (Exception e) {
            System.out.println("Cart page not displayed: " + e.getMessage());
            return false;
        }
    }
    
    public List<String> getCartItemNames() {
        return cartItemNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    public void proceedToCheckout() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

            // Scroll to the button in case it is not in view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

            checkoutButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Checkout button: " + e.getMessage());
        }}
    public int getCartItemsCount() {
        return cartItemNames.size();
    }
}
