package reports;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Base.Base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static reports.ConsoleToExtent.logToExtent;

public class ExtentReport extends Base implements ITestListener {
	
	public  ExtentReports extent = ExtentReportManager.getExtentReporter();
    public ThreadLocal<ExtentTest> parallelTest = new ThreadLocal<>();
    public ExtentTest test;
	
    @Override
	public void onStart(ITestContext context) {
		
		folderCreation();
	
	}


	@Override
	public void onTestStart(ITestResult result) {
		 String methodname= result.getMethod().getMethodName();
		 test = extent.createTest(methodname);
		 parallelTest.set(test);
		 parallelTest.get().info("-----------Test case ("+result.getMethod().getMethodName()+") execution started in thread "+Thread.currentThread().threadId()+"-----------");
		new ConsoleToExtent(parallelTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		parallelTest.get().info("-----------Test case ("+result.getMethod().getMethodName()+") execution completed using thread "+Thread.currentThread().threadId()+"-----------");
		parallelTest.remove();
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		parallelTest.get().fail(result.getThrowable());

		WebDriver driver = driver();
		String failMethodName = result.getMethod().getMethodName();

		parallelTest.get().addScreenCaptureFromPath(getScreenShotPath(failMethodName, driver),result.getMethod().getMethodName());
		parallelTest.get().info("-----------Test case ("+result.getMethod().getMethodName()+") execution failed in thread "+Thread.currentThread().threadId()+"-----------");
		parallelTest.remove();
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		parallelTest.get().info("-----------Test case ("+result.getMethod().getMethodName()+") execution skipped in thread "+Thread.currentThread().threadId()+"-----------");
		 parallelTest.remove();
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		ITestListener.super.onFinish(context);
	}
}
