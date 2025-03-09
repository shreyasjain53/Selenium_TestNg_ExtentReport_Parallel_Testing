package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Base.Base;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import reports.Log;

import java.time.Duration;
import java.util.Set;

public class commonFunctions extends Base {

	public static void moveToElement(WebElement element) {
		Actions act = new Actions(driver());
		act.moveToElement(element).build().perform();
	}

	/**
	 * Waits until the specified WebElement is visible within the given timeout.
	 *
	 * <p>
	 * This method waits for the visibility of an element using
	 * {@link WebDriverWait}.
	 * If a {@link StaleElementReferenceException} occurs, it retries once before
	 * timing out.
	 * If the element does not become visible within the specified timeout, a
	 * {@link TimeoutException} is thrown.
	 *
	 * @param element          The {@link WebElement} to wait for.
	 * @param timeoutInSeconds The maximum time to wait for the element to become
	 *                         visible, in seconds.
	 * @throws TimeoutException If the element is not visible within the given time.
	 */
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

	/**
	 * Switches to a browser tab with the specified title.
	 *
	 * <p>
	 * This method iterates through all open tabs, switching to each one and
	 * checking its title.
	 * If a tab with the given title is found, it switches to that tab and returns
	 * its title.
	 * If no matching tab is found, it returns {@code null}.
	 *
	 * @param titleName The title of the tab to switch to.
	 * @return The title of the switched tab if found, otherwise {@code null}.
	 */
	public static String switchToTabByTitleName(String titleName) {

		Set<String> tabsOpened = driver().getWindowHandles();

		Log.INFO("Total no of tabs opened :" + tabsOpened.size());

		String currentActiveWindowID = driver().getWindowHandle();

		for (String tabID : tabsOpened) {

			if (!currentActiveWindowID.equals(tabID)) {
				driver().switchTo().window(tabID);
				Log.INFO("Switching to :" + driver().getTitle());
			}

			if (driver().getTitle().equalsIgnoreCase(titleName)) {
				Log.INFO("You are inside :" + driver().getTitle());
				return driver().getTitle();
			}
		}
		return null;
	}
}
