package com.cs408.forbes.chris.global.comms;

import com.cs408.forbes.chris.analysis.classes.Analysed_words;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 * Global comms class, used for writing log files (allows the data to be automatically exported after analysis.
 *
 */
public class Logfiles {
	
		public static void createLogs(ArrayList<Analysed_words> x) throws IOException{
			DateFormat dtFt = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			System.out.println(dtFt.format(date));
			String fle_nme = "contentLogs/" + dtFt.format(date).toString() + ".txt";
			System.out.println("File name: " + fle_nme);
			File f = new File(fle_nme);
			f.createNewFile();
			  PrintWriter wrt = new PrintWriter(fle_nme, "UTF-8");
			  for(int i = 0; i < x.size(); i++)
			  {
				  String lne = "Black List word : " + x.get(i).getWord_() + " Total word count : " + x.get(i).getCounted_();
				wrt.println(lne);
			  }
			  
			  wrt.close();
		}
}
