package com.letskodeit.testclasses;

import com.letskodeit.pageclasses.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class AllCoursesTests {
    WebDriver driver;
    String baseURL;
    LoginPage login;
    NavigationPage nav;
    SearchBarPage search;
    ResultsPage result;
    CategoryFilterPage category;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
        nav = new NavigationPage(driver);
        login = nav.login();
        nav = login.signInWith("test@email.com", "abcabc");
    }

    @Test
    public void verifySearchCourse() {
        nav.allCourses();
        search = new SearchBarPage(driver);
        result = search.course("rest api");
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @Test
    public void filterByCategory() {
        nav.allCourses();
        category = new CategoryFilterPage(driver);
        result = category.select("Software IT");
        int count = category.findCoursesCount("Software IT");
        boolean filterResult = result.verifyFilterCourseCount(count);
        Assert.assertTrue(filterResult);
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
