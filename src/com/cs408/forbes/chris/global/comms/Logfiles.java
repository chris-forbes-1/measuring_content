package com.cs408.forbes.chris.global.comms;

import com.cs408.forbes.chris.analysis.types.Analysed_words;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk> Global comms class,
 *         used for writing log files (allows the data to be automatically
 *         exported after analysis.
 * 
 */

public class Logfiles {

	private static ArrayList<String> additional_data = null;

	/**
	 * Global output function for writing analysis logs, this may be changed
	 * over time to allow some wider functionality however it must ALWAYS be
	 * passed and @see ArrayList of analysed_words
	 * 
	 * @param AnalysedWordList
	 * @throws IOException
	 */
	public static void createLogs(ArrayList<Analysed_words> AnalysedWordList)
			throws IOException {
		DateFormat dtFt = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		System.out.println(dtFt.format(date));
		String fle_nme = "contentLogs/" + dtFt.format(date).toString();
		dtFt = new SimpleDateFormat("dd-mm-yy");
		String cur_dt = dtFt.format(date).toString();
		cur_dt += ".txt";
		System.out.println(cur_dt);
		fle_nme += " " + cur_dt;
		System.out.println("File name: " + fle_nme);
		File f = new File(fle_nme);
		f.createNewFile();
		PrintWriter wrt = new PrintWriter(fle_nme, "UTF-8");
		if (additional_data == null) {
			for (int i = 0; i < AnalysedWordList.size(); i++) {
				String lne = "Black List word : "
						+ AnalysedWordList.get(i).getWord_()
						+ " Total word count : "
						+ AnalysedWordList.get(i).getCounted_();
				wrt.println(lne);
			}
		}else{
			for (int i = 0; i < AnalysedWordList.size(); i++) {
				String lne = "Black List word : "
						+ AnalysedWordList.get(i).getWord_()
						+ " Total word count : "
						+ AnalysedWordList.get(i).getCounted_();
				wrt.println(lne);
			}
			for(String s: additional_data)
			{
				wrt.println("Ratio" + s);
			}
		}

		wrt.close();
	}
	
	public static void addData(String data)
	{
		additional_data.add(data);
	}
}
