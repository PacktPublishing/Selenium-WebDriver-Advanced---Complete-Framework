package com.letskodeit.base;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomDriver {
    public WebDriver driver;
    private JavascriptExecutor js;

    public CustomDriver(WebDriver driver) {
        this.driver = driver;
        js= (JavascriptExecutor) driver;
    }

    public void refresh() {
        driver.navigate().refresh();
        System.out.println("The current browser location was refreshed");
    }

    public String getTitle() {
        String title = driver.getTitle();
        System.out.println("Title of the page is: " + title);
        return title;
    }

    /***
     * Builds the By type with given locator strategy
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @return Returns By Type
     */
    public By getByType(String locator) {
        By by = null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];
        try {
            if (locatorType.contains("id")) {
                by = By.id(locator);
            } else if (locatorType.contains("name")) {
                by = By.name(locator);
            } else if (locatorType.contains("xpath")) {
                by = By.xpath(locator);
            } else if (locatorType.contains("css")) {
                by = By.cssSelector(locator);
            } else if (locatorType.contains("class")) {
                by = By.className(locator);
            } else if (locatorType.contains("tag")) {
                by = By.tagName(locator);
            } else if (locatorType.contains("link")) {
                by = By.linkText(locator);
            } else if (locatorType.contains("partiallink")) {
                by = By.partialLinkText(locator);
            } else {
                System.out.println("Locator type not supported");
            }
        } catch (Exception e) {
            System.out.println("By type not found with: " + locatorType);
        }
        return by;
    }

    /**
     * Builds The WebElement By given locator strategy
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return WebElement
     */
    public WebElement getElement(String locator, String info) {
        WebElement element = null;
        By byType = getByType(locator);
        try {
            element = driver.findElement(byType);
            System.out.println("Element " + info + " found with locator: " + locator);
        } catch (Exception e) {
            System.out.println("Element not found with: " + locator);
            e.printStackTrace();
        }
        return element;
    }
}
