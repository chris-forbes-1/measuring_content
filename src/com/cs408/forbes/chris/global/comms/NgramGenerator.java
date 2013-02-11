package com.cs408.forbes.chris.global.comms;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 * This class is defined in order to take a file path to a wordlist (of the form single word or multi word temrs
 * followed by a carraige return) and generate n grams by duplicating a list removing the [0]'th element 
 * and moving everything up one place.
 *
 */

public class NgramGenerator {

	public static boolean generateNgrams(String fp) throws IOException
	{
		ArrayList<String>bl = new ArrayList<String>();
		File f = new File(fp);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String lne = "";
		while((lne = br.readLine())!= null)
		{
			bl.add(lne);
		}
		br.close();
		System.out.println("Black list parsed");
		System.out.println("Launching ngram generation");
		generateNgram(bl);
		return false;
	}
	/**
	 * Generates TempFile(nGram) based on passed ArrayList of BL words
	 * @param x
	 */
	private static void generateNgram(ArrayList<String> x){
		int tLen = x.size();
		ArrayList<String> nGram = new ArrayList<String>();
		for(int i = 0; i < tLen ; i++)
		{
			nGram.add(x.get(i)); //stage 1 copy the arrayList
		}
		//stage 2 remove element 1 and move back the way (this will iterate while nGram.size!=1
		nGram.remove(0);
		
		// simply removing creates the appropriate size so all i have to do is merge the smaller list with the bigger list
		//then i will most likely being a multi-level recursive win to generate the remaining
		//nGram > x
		
		for(int i = 0; i < nGram.size(); i++)
		{
			String tmp = x.get(i) + " " + nGram.get(i); //get i'th from x and merge with ngram [i'th]  
			//replace ith' x element with tmp
			x.set(i, tmp);
		}
		for(String s: x)
		{
			System.out.println(s);
		}
		
	}

}
