package com.letskodeit.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationPage {

    public NavigationPage(WebDriver driver) {
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
    private String MY_COURSES_LINK = "//a[contains(text(),'My Courses')]";

    /***
     * Methods
     */

    public void allCourses() {
      driver.findElement(By.xpath(ALL_COURSES_LINK)).click()
    }

    public void myCourses() {
      driver.findElement(By.xpath(MY_COURSES_LINK)).click()
    }

    // public boolean isOpen() {
    //     return URL.equalsIgnoreCase(driver.getCurrentUrl());
    // }
}
