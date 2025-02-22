package reports;

import com.aventstack.extentreports.Status;

public class Log {
	
	public static void INFO(String message) {
		ConsoleToExtent.logToExtent(message, Status.INFO);
        System.out.println(message);
	}
	
	public static void PASS(String message) {
        ConsoleToExtent.logToExtent(message, Status.PASS);
    }

    public static void FAIL(String message) {
        ConsoleToExtent.logToExtent(message, Status.FAIL);
    }

    public static void WARNING(String message) {
        ConsoleToExtent.logToExtent(message, Status.WARNING);
    }

    public static void SKIP(String message) {
        ConsoleToExtent.logToExtent(message, Status.SKIP);
    }

  

}
