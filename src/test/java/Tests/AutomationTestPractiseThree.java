package Tests;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.Base;
import commonFunctions.commonFunctions;
import reports.Log;

@Listeners(reports.ExtentReport.class)
public class AutomationTestPractiseThree extends Base {

	@BeforeMethod
	public void browserLaunch() {
		launchBrowser();
		driver().get("https://testautomationpractice.blogspot.com/");
	}

	@AfterMethod(enabled = false)
	public void browserClose() {
		super.browserClose();
	}

	@Test(description = "Pagination Web Table", enabled = true)
	public void paginationWebTableVerify() {

		String product = "Wireless Mouse 20";
		Log.INFO("Searching for product named: " + product + ".");
		List<WebElement> noOfPages = driver().findElements(By.xpath("//ul[@id='pagination']//li/a"));
		commonFunctions.moveToElement(noOfPages.getFirst());

		boolean found = false;
		int pageNumber = 1;
		int index = 0;
		while (!found) {
			List<WebElement> products = driver().findElements(By.xpath("//table[@id='productTable']//tbody/tr/td[2]"));
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getText().equalsIgnoreCase(product)) {
					index = 1 + i;
					driver().findElement(By.xpath("//table[@id='productTable']/tbody/tr[" + index + "]/td[4]/input"))
							.click();
					found = true;
					Log.INFO(product + " found.");
					break;
				}
			}

			if (found == false && 1 <= noOfPages.size()) {
				driver().findElement(By.xpath("//ul[@id='pagination']//li[" + pageNumber + "]/a")).click();
			}
			pageNumber++;
		}
		boolean checked = driver()
				.findElement(By.xpath("//table[@id='productTable']/tbody/tr[" + index + "]/td[4]/input")).isSelected();

		Assert.assertEquals(checked, true);

	}

	@Test(description = "Dynamic button", enabled = false)
	public void dynamicButton() {
		WebElement startBtn = driver().findElement(By.xpath("//button[@name='start']"));
		Log.INFO("Button text: " + startBtn.getText());
		Assert.assertEquals(startBtn.getText(), "START");

		startBtn.click();

		WebElement stopBtn = driver().findElement(By.xpath("//button[@name='stop']"));
		commonFunctions.waitUntilVisibilityOfAnElement(stopBtn, 5);
		Log.INFO("Button text: " + stopBtn.getText());
		stopBtn.click();

		Assert.assertEquals(startBtn.getText(), "START");

		System.out.println("Done");
	}

	@Test(description = "Simple Alert", enabled = true)
	public void simpleAlert() {
		WebElement simpleAlertBtn = driver().findElement(By.xpath("//button[@id='alertBtn']"));

		commonFunctions.moveToElement(simpleAlertBtn);
		simpleAlertBtn.click();

		Alert alert = driver().switchTo().alert();

		String simpleAlertText = alert.getText();

		alert.accept();

		Assert.assertEquals(simpleAlertText, "I am an alert box!");

		Log.INFO("The  text from simple alerts: " + simpleAlertText);
	}

	@Test(description = "Confirmation Alert")
	public void confirmationAlert() {
		WebElement confirmationAlert = driver().findElement(By.xpath("//button[@id='confirmBtn']"));

		commonFunctions.moveToElement(confirmationAlert);

		confirmationAlert.click();

		Alert alert = driver().switchTo().alert();

		String alertText = alert.getText();

		SoftAssert assertion = new SoftAssert();

		assertion.assertEquals(alertText, "Press a button!");

		alert.accept();

		WebElement alertConfirmationBoxText = driver().findElement(By.xpath("//p[@id='demo']"));
		Log.INFO(
				"The text recived after clicking on the confirmation alert is : " + alertConfirmationBoxText.getText());
		assertion.assertAll();

		Assert.assertEquals(alertConfirmationBoxText.getText(), "You pressed OK!");
	}

	@Test(description = "Tab verify and selection")
	public void tabVerify() {

		WebElement newTabClick = driver().findElement(By.xpath("//button[@onclick='myFunction()']"));

		newTabClick.click();

		Set<String> tabs = driver().getWindowHandles();

		Log.INFO(tabs.toString());

		String currentActiveWindow = driver().getWindowHandle();

		for (String string : tabs) {

			if (!currentActiveWindow.equals(string)) {
				driver().switchTo().window(string);
				
			}

			if (driver().getTitle().equalsIgnoreCase("Your Store")) {
				Log.INFO("You are inside :"+ driver().getTitle());
				break;
			}
		}

		Assert.assertEquals(driver().getTitle(), "Your Store");

		driver().switchTo().window(currentActiveWindow);

		Assert.assertEquals(driver().getTitle(), "Automation Testing Practice");

		Log.INFO("You are inside :"+ driver().getTitle());
	}
}
