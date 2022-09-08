package com.letskodeit.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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
    private final String URL = "https://learn.letskodeit.com/courses";
    private String ALL_COURSES_LINK = "//a[contains(text(), 'All Courses')]";
    private String MY_COURSES_LINK = "//a[contains(text(), 'My Courses')]";
    private String ACCOUNT_ICON = "gravatar";
    private String LOGOUT_LINK = "//a[@href='/sign_out']";

    /***
     * Methods
     */

    public void allCourses() {
        driver.findElement(By.xpath(ALL_COURSES_LINK)).click();
    }

    public void myCourses() {
        driver.findElement(By.xpath(MY_COURSES_LINK)).click();
    }

    public boolean isUserLoggedIn() {
        try {
            WebElement accountImage = driver.findElement(By.className(ACCOUNT_ICON));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void logout() {
        driver.findElement(By.className(ACCOUNT_ICON)).click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement logoutLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_LINK)));
        logoutLink.click();
    }

//    public boolean isOpen() {
//        return URL.equalsIgnoreCase(driver.getCurrentUrl());
//    }
}










