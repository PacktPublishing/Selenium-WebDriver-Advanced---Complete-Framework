package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBarPage extends BasePage {

    public SearchBarPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /***
     * Variables
     * URL
     * Title
     */
    public WebDriver driver;
    private String SEARCH_COURSE_FIELD = "id=>search-courses";
    private String SEARCH_COURSE_BUTTON = "id=>search-course-button";

    /***
     * Methods
     */

    public ResultsPage course(String courseName) {
        sendData(SEARCH_COURSE_FIELD, courseName, "Search Course Field");
        elementClick(SEARCH_COURSE_BUTTON, "Search Course Button");
        return new ResultsPage(driver);
    }
}
