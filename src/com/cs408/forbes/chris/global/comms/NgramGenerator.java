package com.cs408.forbes.chris.global.comms;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 * This class is defined in order to take a file path to a wordlist (of the form single word or multi word temrs
 * followed by a carraige return) and generate n grams by duplicating a list removing the [0]'th element 
 * and moving everything up one place.
 *This class has some serious memory consumption issues use with caution
 */

public class NgramGenerator {

	private static ArrayList<String> x = new ArrayList<String>();
	public static boolean generateNgrams(String fp) throws IOException
	{
		File f = new File(fp);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String lne = "";
		while((lne = br.readLine())!= null)
		{
			x.add(lne);
		}
		br.close();
		
		System.out.println("Black list parsed");
		System.out.println("Launching ngram generation");
		//generate the first temp file parse and create 1st ngram set
		Stage1generateNgram();
		//force JVM to do full garbage collection prior to heavy lifting
		System.gc();
		//generate 2nd
		Stage2GenerateNgram();
		return true;
	}
	/**
	 * Generates TempFile(nGram) based on passed ArrayList of BL words
	 * @param x
	 */
	private static void Stage1generateNgram() throws IOException{
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
			String tmp = x.get(i) + " " + nGram.get(i) + "\n"; //get i'th from x and merge with ngram [i'th]  
			//replace ith' x element with tmp
			x.set(i, tmp);
			tmp = null;
		}
		nGram = null;
		gen_tmp();
	}
	/**
	 * Generates tmpFiles for ngram list
	 * @throws IOException
	 */
	private static void gen_tmp() throws IOException{
		PrintWriter pw = new PrintWriter(new File("tempFiles/BLTMP.txt"));
		for(int i = 0; i < x.size(); i++)
		{
			pw.write(x.get(i));
		}
		pw.close();
	}
	
	private static void Stage2GenerateNgram() throws IOException{
		
		
	}
	
	private static void writeToFinalFile(String x) throws IOException{
		File f =new File ("tempFiles/ngrams.txt");
		PrintWriter pr = new PrintWriter(f);
		pr.write(x);
		pr.close();
		
	}
	

}
