package com.letskodeit.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AllCoursesPage {

    public AllCoursesPage(WebDriver driver) {
        this.driver = driver;
    }

    /***
     * Variables
     * URL
     * Title
     */
    public WebDriver driver;
    private String URL = "https://learn.letskodeit.com/courses";
    private String ALL_COURSES_LINK = "//a[contains(text(),'All Courses')]";

    /***
     * Methods
     */

    public void open() {
      driver.findElement(By.xpath(ALL_COURSES_LINK)).click()
    }
    public boolean isOpen() {
        return URL.equalsIgnoreCase(driver.getCurrentUrl());
    }
}
