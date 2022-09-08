package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import com.letskodeit.utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class NavigationPage extends BasePage {

    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    /***
     * Variables
     * URL
     * Title
     */
    public WebDriver driver;
    private JavascriptExecutor js;
    private final String URL = "https://learn.letskodeit.com/courses";
    private String ALL_COURSES_LINK = "//a[contains(text(), 'All Courses')]";
    private String MY_COURSES_LINK = "//a[contains(text(), 'My Courses')]";
    private String ACCOUNT_ICON = "gravatar";
    private String LOGIN_LINK = "//a[@href='/sign_in']";
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
            return false;
        }
    }

    public boolean verifyHeader() {
        WebElement link = driver.findElement(By.xpath(ALL_COURSES_LINK));
        String text = link.getText();
        return Util.verifyTextContains(text, "All Courses");
    }

    public LoginPage login() {
        driver.findElement(By.xpath(LOGIN_LINK)).click();
        return new LoginPage(driver);
    }

    public void logout() {
        driver.findElement(By.className(ACCOUNT_ICON)).click();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement logoutLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_LINK)));
        //logoutLink.click();
        js.executeScript("arguments[0].click();", logoutLink);
    }
}













