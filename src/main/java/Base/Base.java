package Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class for managing WebDriver instances and utility methods for test automation.
 */
public class Base {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	

	
	 /**
     * Launches a new browser instance, maximizes the window, 
     * sets implicit wait, and assigns the WebDriver instance to ThreadLocal storage.
     */
	public void launchBrowser() {
		ChromeOptions options  = new ChromeOptions();
		options.addArguments("--disable-web-security");
		WebDriver localdriver = new ChromeDriver(options);
		localdriver.manage().window().maximize();
		localdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.set(localdriver);
	}

	
	
	 /**
     * Retrieves the WebDriver instance associated with the current thread.
     *
     * @return the WebDriver instance for the current thread.
     */
	public static WebDriver driver() {
		return driver.get();
	}

	/**
     * Closes the browser associated with the current thread and removes
     * the WebDriver instance from ThreadLocal storage.
     */
	public void browserClose() {
		WebDriver localDriver = driver.get();
		if (localDriver != null) {
			localDriver.quit(); // Properly quit the browser

			driver.remove(); // Remove the WebDriver instance from ThreadLocal
		}
	}
	
	
	/**
     * Waits until the specified web element is visible on the page.
     *
     * @param element the WebElement to wait for visibility.
     */
	public void explictWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void folderCreation() {
		String	path = System.getProperty("user.dir") + "/reports";

		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
			System.out.println("Folder created successfully at: " + file.getAbsolutePath());
		}
	}
	
	
	
//	public void takeScreenShot(String path) {
//	    // Get the screenshot as a file
//	    TakesScreenshot tc = (TakesScreenshot) driver.get();
//	    File screenshot = tc.getScreenshotAs(OutputType.FILE);
//	    
//	    // Generate a unique file name with timestamp
////	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//	    String screenshotName = "screenshot.png";
//	    
//	    // Specify the directory where the screenshot will be saved
//	    String screenshotDirectory = System.getProperty("user.dir") +"/reports/"+ path+"/screenshots";
//	    File directory = new File(screenshotDirectory);
//	    if (!directory.exists()) {
//	        directory.mkdir(); // Create the directory if it doesn't exist
//	    }
//	    
//	    // Save the screenshot to the specified directory
//	    File destinationFile = new File(screenshotDirectory + "/" + screenshotName);
//	    try {
//	        FileUtils.copyFile(screenshot, destinationFile);
//	        System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        System.err.println("Failed to save screenshot: " + e.getMessage());
//	    }
//	}
	
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String destinationFile = (System.getProperty("user.dir") + "\\reports\\screenshot\\" + testCaseName + ".png");
		try {
			FileUtils.copyFile(source, new File(destinationFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationFile;
	}
	

}
