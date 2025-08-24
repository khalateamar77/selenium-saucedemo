package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    
    public ConfigReader() {
        try {
            properties = new Properties();
          
            properties.setProperty("baseUrl", "https://www.saucedemo.com/v1/");
            properties.setProperty("validUsername", "standard_user");
            properties.setProperty("validPassword", "secret_sauce");
            properties.setProperty("invalidUsername", "invalid_user");
            properties.setProperty("invalidPassword", "invalid_password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
