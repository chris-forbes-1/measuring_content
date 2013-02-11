package com.cs408.forbes.chris.global.comms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk> This class is defined
 *         in order to take a file path to a wordlist (of the form single word
 *         or multi word temrs followed by a carraige return) and generate n
 *         grams by duplicating a list removing the [0]'th element and moving
 *         everything up one place. This class has some serious memory
 *         consumption issues use with caution
 */
@SuppressWarnings("unused")
public class NgramGenerator {

	private static ArrayList<String> BLKLST = new ArrayList<String>();

	/**
	 * Pass the File Path For the blacklist
	 * 
	 * @param fp
	 * @return
	 * @throws IOException
	 */
	public static boolean generateNgrams(String fp) throws IOException {
		File f = new File(fp);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String lne = "";
		while ((lne = br.readLine()) != null) {
			BLKLST.add(lne);
		}
		br.close();
		lne = null;
		System.out.println("Black list parsed");
		System.out.println("Launching ngram generation");
		// generate the first temp file parse and create 1st ngram set
		Stage1generateNgram();
		// force JVM to do full garbage collection prior to heavy lifting
		System.gc();
		// generate 2nd
		Stage2GenerateNgram();
		return true;
	}

	/**
	 * Generates TempFile(nGram) based on passed ArrayList of BL words
	 * 
	 * @param BLKLST
	 */
	private static void Stage1generateNgram() throws IOException {
		int tLen = BLKLST.size();
		ArrayList<String> nGram = new ArrayList<String>();
		for (int i = 0; i < tLen; i++) {
			nGram.add(BLKLST.get(i)); // stage 1 copy the arrayList
		}
		// stage 2 remove element 1 and move back the way (this will iterate
		// while nGram.size!=1
		nGram.remove(0);

		// simply removing creates the appropriate size so all i have to do is
		// merge the smaller list with the bigger list
		// then i will most likely being a multi-level recursive win to generate
		// the remaining
		// nGram > x

		for (int i = 0; i < nGram.size(); i++) {
			String tmp = BLKLST.get(i) + " " + nGram.get(i) + "\n"; // get i'th
																	// from x
																	// and merge
																	// with
																	// ngram
																	// [i'th]
			// replace ith' x element with tmp
			BLKLST.set(i, tmp);
			tmp = null;
		}
		nGram = null;
		gen_tmp();
	}

	/**
	 * Generates tmpFiles for ngram list
	 * 
	 * @throws IOException
	 */
	private static void gen_tmp() throws IOException {
		PrintWriter pw = new PrintWriter(new File("tempFiles/BLTMP.txt"));
		for (int i = 0; i < BLKLST.size(); i++) {
			pw.write(BLKLST.get(i));
		}
		pw.close();
	}

	private static void Stage2GenerateNgram() throws IOException {
		File f = new File("tempFiles/ngramstates.txt");
		String lne = "";
		BufferedReader bfrdr = new BufferedReader(new FileReader(
				"tempFiles/BLTMP.txt"));
		PrintWriter pr = new PrintWriter(f);
		int pntr = BLKLST.size();
		for (int i = 0; i < BLKLST.size();) {
			String x = BLKLST.remove(i);
			while ((lne = bfrdr.readLine()) != null) {
				pr.write(lne + " " + x + "\n");
			}
		}
		bfrdr.close();
		pr.close();
	}

	private static void writeToFinalFile(String x) throws IOException {
		File f = new File("tempFiles/ngrams.txt");
		PrintWriter pr = new PrintWriter(f);
		pr.write(x);
		pr.close();

	}

}
