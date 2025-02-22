package reports;

import java.io.PrintStream;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ConsoleToExtent {

	private static ThreadLocal<ExtentTest> extentTestThreadLocal;

	public ConsoleToExtent(ThreadLocal<ExtentTest> extentTest) {
		ConsoleToExtent.extentTestThreadLocal = extentTest;
//		redirectConsoleOutput();
	}

//	private void redirectConsoleOutput() {
//
//		PrintStream printStream = new PrintStream(System.out) {
//
//			@Override
//			public void println(String message) {
////				logToExtent(message, Status.INFO);
//				super.println(message);
//			}
//
//		};
//
////		System.setOut(printStream); // Redirect System.out
//	}

	public static void logToExtent(String message, Status status) {
		ExtentTest test = extentTestThreadLocal.get();
		if (test != null) {
			test.log(status, message);
		}

	}

}
