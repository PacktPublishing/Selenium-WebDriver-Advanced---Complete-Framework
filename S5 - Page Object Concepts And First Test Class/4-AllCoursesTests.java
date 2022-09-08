package com.letskodeit.testclasses;

import org.openqa.seleniun.WebDriver;
import org.openqa.seleniun.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AllCoursesTests {
    WebDriver driver;
    String baseURL;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
    }

    public void verifySearchCourse() {
        open();
        signInWith("test@email.com", "abcabc");
        searchCourse("rest api");
        boolean searchResult = verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
