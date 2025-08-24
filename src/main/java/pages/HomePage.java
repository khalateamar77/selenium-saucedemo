package pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(className = "app_logo")
    private WebElement logo;
    
    @FindBy(className = "title")
    private WebElement pageTitle;
    
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;
    
    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;
    
    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;
    
    @FindBy(css = "button.btn_inventory")
    private List<WebElement> addToCartButtons;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    // Verify homepage is displayed
    public boolean isHomePageDisplayed() {
    	 try {
    	        wait.until(ExpectedConditions.urlContains("inventory.html"));
    	        wait.until(ExpectedConditions.or(
    	            ExpectedConditions.visibilityOf(logo),
    	            ExpectedConditions.visibilityOf(pageTitle)
    	        ));
    	        return driver.getTitle().equals("Swag Labs"); // browser title
    	    } catch (Exception e) {
    	        System.out.println("Error in isHomePageDisplayed: " + e.getMessage());
    	        return false;
    	    }
    	}
    
    // Sort dropdown by Price Low to High
    public void sortByPriceLowToHigh() {
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        Select dropdown = new Select(sortDropdown);
        dropdown.selectByValue("lohi");
    }
    
    // Get product names
    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    public void addFirstProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("button.btn_inventory")
        ));
        
        if (buttons.isEmpty()) {
            throw new RuntimeException("No 'Add to cart' button found!");
        }
        buttons.get(0).click();
    }

    
    // Add product to cart by name
    public void addProductToCartByName(String productName) {
        for (WebElement product : productNames) {
            if (product.getText().equals(productName)) {
                WebElement addButton = product.findElement(
                        By.xpath("./ancestor::div[@class='inventory_item']//button"));
                wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
                break;
            }
        }
    }
    
    // Click cart icon
    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
    
    // Get cart item count
    public int getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }
    
    // Verify if any product is added to cart
    public boolean isProductAddedToCart() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return Integer.parseInt(cartBadge.getText()) > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
