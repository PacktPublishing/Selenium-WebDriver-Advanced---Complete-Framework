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

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
    }

    @Test
    public void verifySearchCourse() {
        LoginPage login = new LoginPage(driver);
        login.open();
        NavigationPage nav = login.signInWith("test@email.com", "abcabc");
        nav.allCourses();
        SearchBarPage search = new SearchBarPage(driver);
        ResultsPage result = search.course("rest api");
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @Test(dependsOnMethods = "verifySearchCourse")
    public void filterByCategory() {
        NavigationPage nav = new NavigationPage(driver);
        nav.allCourses();
        CategoryFilterPage category = new CategoryFilterPage(driver);
        ResultsPage result = category.select("Software IT");
        int count = category.findCoursesCount("Software IT");
        boolean filterResult = result.verifyFilterCourseCount(count);
        Assert.assertTrue(filterResult);
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
