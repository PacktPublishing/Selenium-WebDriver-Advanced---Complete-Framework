package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /***
     * Variables
     * Locators
     * URL
     */
    public WebDriver driver;
    private String EMAIL_FIELD = "id=>user_email";
    private String PASSWORD_FIELD = "id=>user_password";
    private String LOG_IN_BUTTON = "name=>commit";

    /***
     * Methods
     */

    public NavigationPage signInWith(String email, String password) {
        sendData(EMAIL_FIELD, email, "Email Field");
        sendData(PASSWORD_FIELD, password, "Password Field");
        elementClick(LOG_IN_BUTTON, "Login Button");
        return new NavigationPage(driver);
    }
}
