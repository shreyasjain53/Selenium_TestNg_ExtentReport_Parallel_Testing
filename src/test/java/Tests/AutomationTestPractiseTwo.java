package Tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.Base;
import commonFunctions.JSECommonFunction;
import commonFunctions.commonFunctions;


@Listeners(reports.ExtentReport.class)
public class AutomationTestPractiseTwo extends Base{
	
	 
	
	@BeforeMethod
	public void browserLaunch() {
		launchBrowser();
		driver().get("https://testautomationpractice.blogspot.com/");
	}
	
	@AfterMethod(enabled = true)
	public void browserClose() {
		super.browserClose();
	}
	
	@Test(description = "Date Picker verify two", enabled = true)
	public void urlFetchandVerify() {
		WebElement inputStartDate = driver().findElement(By.xpath("//input[@id='start-date']"));
		WebElement inputEndDate = driver().findElement(By.xpath("//input[@id='end-date']"));
		WebElement dateSubmitButton = driver().findElement(By.xpath("//button[@class='submit-btn']"));
		WebElement resultField = driver().findElement(By.xpath("//div[@id='result']"));
		
		inputStartDate.sendKeys("25-01-2025");
		inputEndDate.sendKeys("31-01-2025");
		dateSubmitButton.click();
		String resultText = resultField.getText();
		
		System.out.println("The range between selected day is " + resultText);
		Assert.assertEquals(resultText, "You selected a range of 6 days.");
	}
	
	@Test(description = "Single File Upload", enabled = true)
	public void singleFileUpload() {
		
		WebElement chooseFileButton = driver().findElement(By.xpath("//input[@id='singleFileInput']"));
		WebElement uploadSingleFileButton = driver().findElement(By.xpath("//button[normalize-space()='Upload Single File']"));
		WebElement singleFileStatus = driver().findElement(By.xpath("//p[@id='singleFileStatus']"));
		
		commonFunctions.moveToElement( chooseFileButton);
		String fileOne = System.getProperty("user.dir")+"\\src\\test\\resources\\FilesToUpload\\Shreyas Kumar P.png";
		chooseFileButton.sendKeys(fileOne);
		
		uploadSingleFileButton.click();
		
		String singleFiletext = singleFileStatus.getText();
	
		boolean value =singleFiletext.contains("Shreyas Kumar P");
		
		Assert.assertEquals(value, true);
	}
	
	@Test(description = "Multiple File Upload", enabled = true)
	public void multipleFileUpload() {
		
		WebElement chooseFilesButton = driver().findElement(By.xpath("//input[@id='multipleFilesInput']"));
		WebElement uploadMultipleFileButton = driver().findElement(By.xpath("//button[normalize-space()='Upload Multiple Files']"));
		WebElement multipleFileStatus = driver().findElement(By.xpath("//p[@id='multipleFilesStatus']"));
		
		commonFunctions.moveToElement(chooseFilesButton);
		String fileOne = System.getProperty("user.dir")+"\\src\\test\\resources\\FilesToUpload\\Shreyas Kumar P.png";
		String fileTwo = System.getProperty("user.dir")+"\\src\\test\\resources\\FilesToUpload\\Status code.jpg";
		chooseFilesButton.sendKeys(fileOne+"\n"+fileTwo);
		
		uploadMultipleFileButton.click();
		
		String singleFiletext = multipleFileStatus.getText();
	
		boolean value =singleFiletext.contains("Shreyas Kumar P");
		
		
		Assert.assertEquals(value, true);
	}
	
	@Test(description = "Least Priced Book Details - Static Web table", enabled = true)
	public void leastPricedBookDetails() {
		WebElement table = driver().findElement(By.xpath("//table[@name='BookTable']"));
		
		commonFunctions.moveToElement( table);
		
		List<WebElement> price = driver().findElements(By.xpath("//table[@name='BookTable']//tbody//tr//td[4]"));
		
		ArrayList<Integer> al = new ArrayList<Integer>(price.size());
		
		for (WebElement webElement : price) {
			al.add(Integer.parseInt(webElement.getText().trim()));
		}
		
		Integer  min = Collections.min(al);
		int index = al.indexOf(min);
		
		int selIndex = index+2;
		System.out.println(selIndex);
		HashMap<String, String> bookDetails = new HashMap<String, String>();
		
		String booksName = driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr["+selIndex+"]//td[1]")).getText();
		String authorName = driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr["+selIndex+"]//td[2]")).getText();
		String subjectName = driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr["+selIndex+"]//td[3]")).getText();
		String prices = driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr["+selIndex+"]//td[4]")).getText();
		
		bookDetails.put(driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr[1]/th[1]")).getText(), booksName);
		bookDetails.put(driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr[1]/th[2]")).getText(), authorName);
		bookDetails.put(driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr[1]/th[3]")).getText(), subjectName);
		bookDetails.put(driver().findElement(By.xpath("//table[@name='BookTable']//tbody//tr[1]/th[4]")).getText(), prices);
		
		
		System.out.println(bookDetails);
	}
	
	
	
	

}
