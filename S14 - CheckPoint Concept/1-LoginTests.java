package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.base.CheckPoint;
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
        System.out.println("****** After Method ******");
        if (nav.isUserLoggedIn()) {
            nav.logout();
            nav.login();
        }
    }

    @Test
    public void testLogin() {
        nav = login.signInWith("test@email.com", "abcabc");
        boolean headerResult = nav.verifyHeader();
        Assert.assertTrue(headerResult); // code stops, I fix this
        boolean result = nav.isUserLoggedIn(); // there are issues with this method also
        Assert.assertTrue(result);
    }

    @Test(enabled = false)
    public void testInvalidLogin() {
        nav = login.signInWith("test@email", "abcabc");
        boolean result = nav.isUserLoggedIn();
        Assert.assertFalse(result);
    }
}