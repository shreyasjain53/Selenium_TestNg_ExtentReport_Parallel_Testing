package commonFunctions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static Base.Base.driver;

public class JSECommonFunction {

	

	public static void jsClick(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver();
		jse.executeScript("arguments[0].click();", element);
	}
	
	
	
}
