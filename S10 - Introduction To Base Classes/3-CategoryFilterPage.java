package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryFilterPage extends BasePage {

    public CategoryFilterPage(WebDriver driver) {
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
    private String CATEGORY_DROPDOWN = "(//div[contains(@class,'course-filter')])[1]//button";
    private String CATEGORY_OPTION = "//a[@href='/courses/category/%s']";

    /***
     * Methods
     */

    public void clickCategoryDropdown() {
        // Find category dropdown
        WebElement categoryDropdown = driver.findElement(By.xpath(CATEGORY_DROPDOWN));
        categoryDropdown.click();
    }

    public WebElement findCategory(String categoryName) {
        clickCategoryDropdown();
        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement categoryOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(String.format(CATEGORY_OPTION, categoryName))));
        return categoryOption;
    }

    public ResultsPage select(String categoryName) {
        WebElement categoryOption = findCategory(categoryName);
        // Used JavaScript click because this element was having issues with usual click method
        js.executeScript("arguments[0].click();", categoryOption);
        return new ResultsPage(driver);
    }

    public int findCoursesCount(String categoryName) {
        WebElement categoryOption = findCategory(categoryName);
        // Get category text
        String categoryText = categoryOption.getText();
        // Split on ( symbol
        // Example: Software IT (2)
        // Value of arrayTemp[0] ->Software IT
        // Value of arrayTemp[1] ->2)
        String[] arrayTemp = categoryText.split("\\(");
        // Split arrayTemp[1] on ) symbol
        // Value of [0] ->2
        String courseCountString = arrayTemp[1].split("\\)")[0];
        int courseCount = Integer.parseInt(courseCountString);
        // Click the dropdown again to close the menu
        clickCategoryDropdown();
        return courseCount;
    }
}