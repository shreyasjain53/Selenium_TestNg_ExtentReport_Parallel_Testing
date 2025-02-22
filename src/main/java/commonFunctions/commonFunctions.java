package commonFunctions;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Base.Base;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.Log;

import java.time.Duration;

public  class commonFunctions extends Base {

	public static void moveToElement(WebElement element) {
		Actions act = new Actions(driver());
		act.moveToElement(element).build().perform();
	}

	public static void waitUntilVisibilityOfAnElement(WebElement element, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(timeoutInSeconds));

		try {
			Log.INFO("Waiting for the element...");
			wait.until(ExpectedConditions.visibilityOf(element));
			Log.INFO("Element found.");
		} catch (StaleElementReferenceException e) {
			Log.INFO("StaleElementReferenceException occurred. Retrying...");
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			throw new TimeoutException("Element not visible after " + timeoutInSeconds + " seconds: " + element, e);
		}
	}
}

