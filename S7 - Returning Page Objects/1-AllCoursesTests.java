package com.letskodeit.testclasses;

import com.letskodeit.pageclasses.NavigationPage;
import com.letskodeit.pageclasses.LoginPage;
import com.letskodeit.pageclasses.ResultsPage;
import com.letskodeit.pageclasses.SearchBarPage;
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
        search.course("rest api");
        ResultsPage result = new ResultsPage(driver);
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
