package basicweb;

import java.time.Duration;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium4NewSyntax {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		
		// Implicit Wait
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		// Explicit Wait
		//WebDriverWait wait = new WebDriverWait(driver, 3);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/login']")));
		
		// Desired Capabilities -> Deprecated. Replaced with Options
		// DesiredCapabilities caps = DesiredCapabilities.firefox();
		// Example
		ChromeOptions options = new ChromeOptions();
	}
}