package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {

    @BeforeClass
    public void setUp() {

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