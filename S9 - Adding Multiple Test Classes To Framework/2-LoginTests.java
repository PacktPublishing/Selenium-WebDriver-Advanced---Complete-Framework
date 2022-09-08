package com.letskodeit.testclasses;

import com.letskodeit.pageclasses.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {
    WebDriver driver;
    String baseURL;
    LoginPage login;
    NavigationPage nav;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
        nav = new NavigationPage(driver);
        login = nav.login();
    }

    @AfterMethod
    public void afterMethod() {
        if (nav.isUserLoggedIn()) {
            nav.logout();
            nav.login();
        }
    }

    @Test
    public void testLogin() {
        nav = login.signInWith("test@email.com", "abcabc");
        boolean result = nav.isUserLoggedIn();
        Assert.assertTrue(result);
    }

    @Test
    public void testInvalidLogin() {
        nav = login.signInWith("test@email", "abcabc");
        boolean result = nav.isUserLoggedIn();
        Assert.assertFalse(result);
    }

}
