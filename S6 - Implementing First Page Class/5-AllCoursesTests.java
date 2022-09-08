package com.letskodeit.testclasses;

import org.openqa.seleniun.WebDriver;
import org.openqa.seleniun.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.letskodeit.pageclasses.LoginPage;
import com.letskodeit.pageclasses.NavigationPage;
import com.letskodeit.pageclasses.SearchBarPage;
import com.letskodeit.pageclasses.ResultsPage;

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
        LoginPage login = new LoginPage(driver);
        login.open();
        login.signInWith("test@email.com", "abcabc");
        NavigationPage nav = new AllCoursesPage(driver);
        nav.allCourses();
        SearchBarPage search = new SearchBarPage(driver);
        search.course("rest api");
        ResultsPage result = new ResultsPage(driver);
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
