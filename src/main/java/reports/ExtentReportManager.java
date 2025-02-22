package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	public static  ExtentReports extent;

	public static  ExtentReports getExtentReporter() {
		
			String path = System.getProperty("user.dir") + "/reports/" +"extentReport.html";

			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("Web Automation Result");
			reporter.config().setCss(".test .node { display: flex; flex-direction: row; }");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		
		return extent;
	}

}
