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
	private static Logger logger = Logger.getLogger("log");

	static {
		try {
			FileHandler handler = new FileHandler("default.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			handler.setFormatter(simpleFormatter);
			logger.addHandler(handler);
		} catch (SecurityException | IOException e) {

		}
	}

	/**
	 * write log type warning
	 * 
	 * @param value
	 *            write into file log
	 */
	public static void warning(String value) {
		logger.warning(value);
	}

	/**
	 * write log type info
	 * 
	 * @param value
	 *            write into file log
	 */
	public static void info(String value) {
		logger.info(value);
	}

	public static void main(String[] args) {
		LogFile.info("ok");
		LogFile.warning("not good");
	}
}
