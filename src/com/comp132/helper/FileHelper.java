package com.comp132.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * handing general file operation, like open, write
 */
public class FileHelper {
	/*
	 * The file name which working on.
	 */
	String myfilename;

	/**
	 * Construction with file name
	 * 
	 * @param myfilename the name of the file that working on.
	 */
	public FileHelper(String myfilename) {
		super();
		this.myfilename = myfilename;
	}

	/**
	 * Read all lines in a file, put it in a String array list and
	 * return it
	 * @return a string array list, on failure returns null
	 */
	public ArrayList<String> readAllLines () {
		 Logger.writeLog("Read all line of the file: " +this.myfilename);
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(this.myfilename));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        lines.add(line);
		    }
		} catch (IOException e) {
		    e.printStackTrace();	
		    Logger.writeLog("An exception while reading all lines of the filenmae :"+this.myfilename);
		    Logger.writeLog(e.getMessage());
		    lines = null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
				}
		}
		
		Logger.writeLog("DONE: Read all line of the file: " +this.myfilename);
		return lines;
	}

	/**
	 * Writes all element of a string array list into the file
	 * 
	 * @param lines : string lines to write
	 * @return true if is succesfull, otherwise false
	 */
	public boolean writeAllLines(ArrayList<String> lines) {
		Logger.writeLog("write all line into the file: " +this.myfilename);
		boolean rst = true;
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(this.myfilename));
			
			for (String line : lines) {
				writer.write(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			Logger.writeLog("An exception while writing all lines into the filename :"+this.myfilename);
			Logger.writeLog(e.getMessage());
			rst =  false;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
		}
		
		Logger.writeLog("DONE: write all line into the file: " +this.myfilename);
		return rst;
	}

}
