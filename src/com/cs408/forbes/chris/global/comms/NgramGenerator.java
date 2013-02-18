package com.cs408.forbes.chris.global.comms;

//TODO Generate NGrams properly
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.cs408.forbes.chris.analysis.types.Analysed_words;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk> This class is defined
 *         in order to take a file path to a wordlist (of the form single word
 *         or multi word temrs followed by a carraige return) and generate n
 *         grams by duplicating a list removing the [0]'th element and moving
 *         everything up one place. This class has some serious memory
 *         consumption issues use with caution.....
 */
@SuppressWarnings("unused")
public class NgramGenerator {

	private static ArrayList<String> BLKLST = new ArrayList<String>();
	private static PrintWriter PR;

	/**
	 * Pass the File Path For the blacklist 
	 * 
	 * @param fp
	 * @return
	 * @throws IOException
	 */
	public static boolean generateNgrams(String fp) throws IOException {
		File f = new File(fp);
		ld_wrd_lst();
		System.out.println("Black list parsed");
		System.out.println("Launching ngram generation");
		System.gc();
		ExperimentalGeneration();
		return true;
	}
private static void ld_wrd_lst() throws IOException {
		
		BufferedReader bfrdr = new BufferedReader(new FileReader(
				"WordList/wordlist.txt"));
		String lne;
		while ((lne = bfrdr.readLine()) != null) {
		BLKLST.add(lne);
		}
		bfrdr.close();
	}

/**
 * Rather ghetto work around to avoid @see ConcurrentModificationException i simply use this 
 * to write the original blacklist to the temp File tempFiles/Dest.txt
 * The call the gen() function
 * @throws IOException
 */
	private static void ExperimentalGeneration() throws IOException {
		File Dest = new File("tempFiles/Dest.txt");
		PR = new PrintWriter(Dest,"UTF-8");
		for (String s : BLKLST) {
			PR.write(s);
		}
		gen();
	}

	
	/**
	 * Private method which does the actual generation of the ngrams, 
	 * As discussed with Dr Weir, the algorithm, duplicates this Blacklist
	 * then removes the [0]'th element from the list then merges the list with the original and writes it.
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	private static void gen() throws IOException {
		BufferedReader BR = new BufferedReader(new FileReader(new File(
				"tempFiles/Dest.txt")));
		
		for(int i = 0; i < BLKLST.size(); i++)
		{
			String ELEMENT = BLKLST.get(0);
			BLKLST.remove(0);
			for(int x = 0;  x < BLKLST.size(); x++)
			{
				ELEMENT += " " + BLKLST.get(x) + " ";
			}
			PR.write(ELEMENT + "\n");
		}
		
	}
}
