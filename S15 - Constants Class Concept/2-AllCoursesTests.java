package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.pageclasses.CategoryFilterPage;
import com.letskodeit.pageclasses.SearchBarPage;
import com.letskodeit.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AllCoursesTests extends BaseTest {

    @BeforeClass
    public void setUp() {
        nav = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
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
