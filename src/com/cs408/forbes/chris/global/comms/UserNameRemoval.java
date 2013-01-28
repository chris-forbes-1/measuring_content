package com.cs408.forbes.chris.global.comms;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.*;
/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 * Global comms class which can be used staticlly to remove stopwords from datasets
 * this will also include methods for removing usernames
 *
 * ***************************After a bit of thought i realised this was an excercise in futility, as i am 
 * 	processing natural language which requires stop words to provide some kind of context to the words****
 *  as such i am going to refactor this class to soley remove the usernames
 */
@SuppressWarnings("unused")
public class UserNameRemoval{

	
	
	/**
	 * @param args
	 * @return 
	 */
	
	public static void rmv_usr_nme(File f) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(f));
		String lne = " ";
		while((lne = br.readLine()) != null){
			String re1="((?:[a-z][a-z]+))";	// Word 1

		    Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(lne);
		    if (m.find())
		    {
		        String word1=m.group(1);
		        System.out.print("("+word1.toString()+")"+"\n");
		    }
		  }
		}
	}
	
	

