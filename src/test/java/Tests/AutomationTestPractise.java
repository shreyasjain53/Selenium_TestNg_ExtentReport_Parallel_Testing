package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.Base;
import reports.Log;

@Listeners(reports.ExtentReport.class)
public class AutomationTestPractise extends Base{
	
	 
	
	@BeforeMethod
	public void browserLaunch() {
		launchBrowser();
		driver().get("https://testautomationpractice.blogspot.com/");
	}
	
	@AfterMethod(enabled = true)
	public void browserClose() {
		super.browserClose();
	}
	
	@Test(description = "Verify URL of the practise page", enabled = true)
	public void urlFetchandVerify() {
		String currentURL = driver().getCurrentUrl();
		System.out.println("The URL is "+ currentURL);
		Assert.assertEquals(currentURL, "https://testautomationpractice.blogspot.com/");
	}
	
	@Test(description = "Verify the input text is presenr or not", enabled= true)
	public void inputtextFieldVerify() {
		WebElement inputName = driver().findElement(By.xpath("//input[@id='name']"));
		explictWait(inputName);
		inputName.sendKeys("Shreyas Kumar P");
		Log.INFO("Entering Shreyas Kumar P to the input field");
		JavascriptExecutor jse = (JavascriptExecutor)driver();
		 String enteredName= (String)jse.executeScript("return arguments[0].value;",inputName);
		Assert.assertEquals(enteredName, "Shreyas Kumar P");
	}
	
	@Test(description = "Radio buttons verify on defaultes as not selected", enabled = true)
	public void radioButtonsVerifyOnDefauly() {
		boolean maleRadioButton = driver().findElement(By.xpath("//input[@id='male']")).isSelected();
		boolean femaleRadioButton = driver().findElement(By.xpath("//input[@id='female']")).isSelected();
		
		Assert.assertEquals(maleRadioButton, false);
		Assert.assertEquals(femaleRadioButton, false);
	}
	
	@Test(description = "Radio buttons selection verify ", enabled = true)
	public void radioButtonsSelectionVerify() {
		WebElement maleRadioButtonElement = driver().findElement(By.xpath("//input[@id='male']"));
		explictWait(maleRadioButtonElement);
		maleRadioButtonElement.click();
		boolean maleRadioButton = maleRadioButtonElement.isSelected();
		boolean femaleRadioButton = driver().findElement(By.xpath("//input[@id='female']")).isSelected();
		
		Assert.assertEquals(maleRadioButton, true);
		Assert.assertEquals(femaleRadioButton, false);
	}
	
	@Test(description = "Text area height verify on default", enabled = true)
	public void textAreaVerifyOnDefault() {
		WebElement textArea = driver().findElement(By.xpath("//textarea[@id='textarea']"));
		explictWait(textArea);
		JavascriptExecutor jse = (JavascriptExecutor)driver();
		long textAreaDetails = (Long)jse.executeScript("return arguments[0].offsetHeight;", textArea);
		
		Assert.assertEquals(textAreaDetails, 62);
	}
	
	@Test(description = "Text Area Height Verify After Increse", enabled = true)
	public  void textAreaHeightVerifyAfterIncrese() {
		WebElement textArea = driver().findElement(By.xpath("//textarea[@id='textarea']"));

		JavascriptExecutor jse = (JavascriptExecutor)driver();
		
		jse.executeScript("arguments[0].style.height='300px';", textArea);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long textAreaDetails = (Long)jse.executeScript("return arguments[0].offsetHeight;", textArea);
		
		System.out.println("The value is " + textAreaDetails);
		Assert.assertEquals(textAreaDetails, 300);
	}
	
	@Test(description = "Days checkbox verify by default", enabled = true)
	public void daysCheckBoxVerifyByDefault() {
		List<WebElement> daysCheckbox= driver().findElements(By.xpath("//label[@for='days']/..//div//input[@class='form-check-input' and @type='checkbox']"));
		
		Assert.assertEquals(daysCheckbox.size(), 7);
		
		List<Boolean> defaultValuesOfDays = new ArrayList<Boolean>();
		
		for (WebElement webElement : daysCheckbox) {
			defaultValuesOfDays.add(webElement.isSelected());
		}
		
		List<Boolean> actaulValuesOfDays = new ArrayList<Boolean>(Arrays.asList(false,false,false,false,false,false,false));
		
		Assert.assertEquals(defaultValuesOfDays, actaulValuesOfDays);
		
	}
	
	@Test(description = "Select a day and verify wheather it is selected or not", enabled = true)
	public void selectADay() {
		String day = "tuesday";
		
		List<WebElement> daysCheckbox= driver().findElements(By.xpath("//label[@for='days']/..//div//input[@class='form-check-input' and @type='checkbox']"));
		
		for (WebElement webElement : daysCheckbox) {
			if(webElement.getDomAttribute("id").equalsIgnoreCase(day)) {
				webElement.click();
				break;
			}
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<Boolean> defaultValuesOfDays = new ArrayList<Boolean>();
		
		for (WebElement webElement : daysCheckbox) {
			defaultValuesOfDays.add(webElement.isSelected());
		}
		
		List<Boolean> expectedValuesOfDays = new ArrayList<Boolean>(Arrays.asList(false,false,true,false,false,false,false));
		
		Assert.assertEquals(defaultValuesOfDays, expectedValuesOfDays);
	}
	
	@Test(description = "Select country from dropdown and verify selected country", enabled =true)
	public void selectCountryAndVerify() {
		WebElement countryDropdown = driver().findElement(By.id("country"));
		explictWait(countryDropdown);
		Select sel = new Select(countryDropdown);
		
		sel.selectByVisibleText("India");
		
		String selectedOption = sel.getFirstSelectedOption().getText();
		
		System.out.println("Selected option from the dropdown "+selectedOption);
		
		Assert.assertEquals(selectedOption, "India");
	}
	
	@Test(description = "Country list Verify", enabled = true)
	public void countryListVerify() {
		WebElement countryDropdown = driver().findElement(By.id("country"));
		Select sel = new Select(countryDropdown);
		
		List <WebElement>  countrylist = sel.getOptions();
		
		List<String> expectedCountryList = new ArrayList<String>(Arrays.asList("United States","Canada","United Kingdom","Germany","France","Australia","Japan","China","Brazil","India"));
		List<String> actualCountryList = new ArrayList<String>();
		
		for (WebElement country : countrylist) {
			System.out.println("Adding "+country.getText()+ " to the expectedCountryList "  );
			actualCountryList.add(country.getText());
		}
		
		Assert.assertEquals(actualCountryList,expectedCountryList);
	}
	
	@Test(description = "Date picker and verify Type one", enabled = true)
	public void datePickerOne() {
		String month = "January";
		String date = "25";
		int year = 2020;

		// Click on the date input box
		driver().findElement(By.xpath("//input[@ID='datepicker']")).click();

		WebElement yearEle = driver().findElement(By.xpath("//span[@class='ui-datepicker-year']"));

		// year selection
		int currentYear = Integer.parseInt(yearEle.getText());

		while (currentYear != year) {
			if (currentYear < year) {
				driver().findElement(By.xpath("//a[@data-handler='next']")).click();
			} else {
				driver().findElement(By.xpath("//a[@data-handler='prev']")).click();
			}
			currentYear = Integer
					.parseInt(driver().findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
		}

		List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December");

		while (true) {

			int currentMonthIndex = months
					.indexOf(driver().findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
			int targetMonthIndex = months.indexOf(month);

			if (currentMonthIndex < targetMonthIndex) {
				driver().findElement(By.xpath("//a[@data-handler='next']")).click();
			} else if (currentMonthIndex > targetMonthIndex) {
				driver().findElement(By.xpath("//a[@data-handler='prev']")).click();
			} else {
				break;
			}
		}

		List<WebElement> dateEle = driver()
				.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));

		for (WebElement dateElement : dateEle) {
			if (dateElement.getText().equals(date)) {
				dateElement.click();
			}
		}
		
		WebElement selectedDateEle = driver().findElement(By.xpath("//input[@ID='datepicker']"));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver();
		
		String selectedDate  = (String)jse.executeScript("return arguments[0].value;", selectedDateEle);
		System.out.println("The selected date is "+ selectedDate);
		
		Assert.assertEquals(selectedDate, "01/25/2020");
	}
	
	
	
	
	
	
	
	
	

}
