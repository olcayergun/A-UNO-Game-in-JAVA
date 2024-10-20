package com.comp132.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Logger class
 * Handles log write operation
 */
public class Logger {
	/**
	 * The name of log file
	 */
	private static String logfilename = "../Log.txt";

	/**
	 * Delete the log file
	 */
	public static void deleteLogFile() {
		new File("E:\\demo.txt").delete();
	}

	/*
	 * Logs a line
	 */
	public static void writeLog(String logLine) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		FileWriter fw;
		try {
			fw = new FileWriter(logfilename, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(timeStamp + " " + logLine + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
