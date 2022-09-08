package com.letskodeit.base;

import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean verifyTitle(String expectedTitle) {
        return driver.getTitle().equalsIgnoreCase(expectedTitle);
    }
}
