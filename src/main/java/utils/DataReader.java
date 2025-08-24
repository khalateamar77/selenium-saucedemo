package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class DataReader {
    
    // ✅ DataProvider for login test
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce", true},
            {"locked_out_user", "secret_sauce", false},
            {"problem_user", "secret_sauce", true},
            {"performance_glitch_user", "secret_sauce", true},
            {"invalid_user", "invalid_password", false}
        };
    }
    
    // Method to provide checkout data
    public static Map<String, String> getCheckoutData() {
        Map<String, String> data = new HashMap<>();
        data.put("firstName", "John");
        data.put("lastName", "Doe");
        data.put("postalCode", "12345");
        return data;
    }
    
    // Method to provide product names for testing
    public static List<String> getExpectedProducts() {
        List<String> products = new ArrayList<>();
        products.add("Sauce Labs Backpack");
        products.add("Sauce Labs Bike Light");
        products.add("Sauce Labs Bolt T-Shirt");
        products.add("Sauce Labs Fleece Jacket");
        products.add("Sauce Labs Onesie");
        products.add("Test.allTheThings() T-Shirt (Red)");
        return products;
    }
}
