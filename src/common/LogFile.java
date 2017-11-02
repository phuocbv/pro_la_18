/**
 * Copyright(C) 2017  Luvina
 * LogFile.java, 20/10/2017 phuocbv
 */
package common;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * write log
 * 
 * @author da
 *
 */
public class LogFile {
	//

	// try {
	// FileHandler handler = new FileHandler("default.log", true);
	// SimpleFormatter simpleFormatter = new SimpleFormatter();
	// handler.setFormatter(simpleFormatter);
	// logger.addHandler(handler);
	// } catch (SecurityException | IOException e) {
	// }
	// return logger;

	// static Logger logger = Logger.getLogger("log");

	public static Logger getLogger() {
		Logger logger = Logger.getLogger(Logger.class.getName());

		//
		// Create an instance of FileHandler that write log to a file called
		// app.log. Each new message will be appended at the at of the log file.
		//
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler("app.log", true);
			logger.addHandler(fileHandler);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return logger;
	}

	static Logger logger = Logger.getLogger("log");

	public static void main(String[] args) {

		try {
			FileHandler handler = new FileHandler("default.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			handler.setFormatter(simpleFormatter);
			logger.addHandler(handler);
		} catch (SecurityException | IOException e) {
		}
		// return logger;
	}
}
