package com.cs408.forbes.chris.global.comms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class UserNameRemoval{

	private static ArrayList<String> no_uname_text = new ArrayList<String>();
	
	/**
	 * Global function for the removal of usernames from the chatlogs
	 * all files will then be written to the folder tempFiles/ for further analysis
	 * @param @see File
	 * @return 
	 */
	
	public static void rmv_usr_nme(File f) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(f));
		String lne = " ";
		while((lne = br.readLine()) != null){
			String re1="(.*?[A-Z][a-z].:)";	// All words before ':'
			Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE);
		    
			
			lne = p.matcher(lne).replaceAll(" ");
			no_uname_text.add(lne);
		   
		  }
		br.close();
		generate_tempFiles(f);
		}
	
		private static void generate_tempFiles(File f)
		{
			String fle_nme = "tempFiles/temp"+f.getName();
			File tmp_fle = new File(fle_nme);
			try {
				tmp_fle.createNewFile();
				PrintWriter pw = new PrintWriter(tmp_fle, "UTF-8");
				for(int i = 0 ; i < no_uname_text.size(); i++)
				{
					pw.write(no_uname_text.get(i));
					pw.write("\n");
				}
				pw.close();
			} catch (IOException e) {
				System.out.println("Error generating file");
				e.printStackTrace();
			}
			no_uname_text.clear();
			
			
		}
	}



	
	

