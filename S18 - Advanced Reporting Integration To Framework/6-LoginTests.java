package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.base.CheckPoint;
import com.letskodeit.utilities.Constants;
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
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean headerResult = nav.verifyHeader();
        CheckPoint.mark("test-01", headerResult, "header verification");
        boolean result = nav.isUserLoggedIn();
        CheckPoint.markFinal("test-01", result, "login verification");
    }

    @Test(enabled = false)
    public void testInvalidLogin() {
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean result = nav.isUserLoggedIn();
        Assert.assertFalse(result);
    }
}