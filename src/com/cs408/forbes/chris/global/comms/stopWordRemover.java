package com.cs408.forbes.chris.global.comms;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 * Global comms class which can be used staticlly to remove stopwords from datasets
 * this will also include methods for removing usernames
 *
 * ***************************After a bit of thought i realised this was an excercise in futility, as i am 
 * 	processing natural language which requires stop words for contextual analysis*************************
 */
@SuppressWarnings("unused")
public class stopWordRemover{

	private static ArrayList<String> stopWords = new ArrayList<String>();
	
	/**
	 * @param args
	 * @return 
	 */
	
	public static void rmv_stp_wrds(File f)
	{
		try {
			init();
			rmv_stp_wrd(f);
		} catch (IOException e) {
			System.out.println("Stop word removal IOException");
			e.printStackTrace();
		}
		tearDown();
		
	}
	
	private static void tearDown(){
		stopWords = null;
	}
	
	private static void init() throws IOException{
		
		BufferedReader bfr_rdr = new BufferedReader(new FileReader("resourceFiles/common-english-words.txt"));
		String lne = null;
		StringTokenizer str_tkn = null;
		
		while((lne = bfr_rdr.readLine()) !=null){
			str_tkn = new StringTokenizer(lne,",");
			while(str_tkn.hasMoreTokens()){
				stopWords.add(str_tkn.nextToken());
			}
		}
		bfr_rdr.close();
	}
	
	private static void rmv_stp_wrd(File f) throws IOException
	{
		File fle = f;
		ArrayList<String>s = new ArrayList<String>();
		
		BufferedReader buf_rdr = new BufferedReader(new FileReader(f));
		String lne = " ";
		while((lne = buf_rdr.readLine()) != null)
		{
			s = new ArrayList<String>();
			String [] lne_a = lne.split(" ");
			for(String d : lne_a)
			{
				s.add(d);
			}
			for(int i =0 ; i < s.size(); i++)
			{
				if(stp_wrd_src(s.get(i))){
					s.remove(i);
				}
			}
		
		}
		
		for(int i = 0; i < s.size(); i ++)
		{
			System.out.println("lne: " + s.get(i));
		}
		buf_rdr.close();
	}
	
	private static boolean stp_wrd_src(String s){
		for(int i = 0; i < stopWords.size(); i++)
		{
			if(s.compareToIgnoreCase(stopWords.get(i)) == 0){
				return true;
			}
		}
		return false;
	}

}
