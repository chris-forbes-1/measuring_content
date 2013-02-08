package com.cs408.forbes.chris.analysis.classes;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.global.comms.Logfiles;

/**
 * The most simple analysis this package will offer, a simple word counter looking at the  
 * the amount of words in the file for reference, and then comparing to the "wordlist.txt" a very
 * small black list
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *  Basic Word counter
 * References: Stackoverflow
 * 	terms:	creating files to write
 */

public class Basic_frequency_Analysis {
	private static File[] dir_lst;
	static String fp = " ";
	int fck_cntr = 0;
	private static ArrayList<Analysed_words> blk_lst = new ArrayList<Analysed_words>();
	static ArrayList<Integer> counter = new ArrayList<Integer>();

	public Basic_frequency_Analysis(String fl_dir)
			throws IOException {
		fp = fl_dir;
		
		
	}
	public ArrayList<Analysed_words> analyse () throws IOException{
		System.out.println("Beginning Basic_frequency analysis \n please wait...");
		System.out.println("Loading blacklist");
		ld_wrd_lst();
		System.out.println("Beginning analysis");
		Parse_directory(fp);
		Logfiles.createLogs(blk_lst);
		return blk_lst;
	}
	
	/**
	 * Private pass file -> make sure you pass an active directory
	 * 
	 * @param file_Directory
	 * @throws IOException
	 */
	private static void Parse_directory(String file_Directory) throws IOException {
		File fl_dir_ = new File(file_Directory);
		dir_lst = fl_dir_.listFiles();

		for (File fle : dir_lst) {
			parse_file(fle);
		}
		for (int i = 0; i < dir_lst.length; i++) {
			System.out.println("File Name: " + dir_lst[i].getName());
			System.out.println("\t Word Count : " + counter.get(i) + " ");
		}
		int total = 0;
		for (int y = 0; y < counter.size(); y++) {
			total += counter.get(y);
		}
		System.out.println("Total word counter for " + dir_lst.length
				+ " files is " + total);

		int average_word_length = total / file_Directory .length();
		System.out.println("Average length of documents " + average_word_length);
		System.out
				.println("Assuming most chatlogs on perverted-justic are similar to the average word count");
		System.out.println("We can assume that their is approx. "
				+ average_word_length * 549 + " words in the dataset");

	}

	/**
	 * Private parse method for basic frequency analysis reads each line individually splitting them over the " " space character
	 * 	from their the word counter is incremented and each word is searched against the blacklist if they are equal then one is added to
	 * it's count.
	 * @param fle
	 * @throws IOException
	 */
	private static void parse_file(File fle) throws IOException {
		BufferedReader buf_rdr = new BufferedReader(new FileReader(fle));
		int fle_wrd_cnter = 0;
		String lne;
		float totalBLwords = 0.0F;
		try {
			while ((lne = buf_rdr.readLine()) != null) {

				String[] words = lne.split(" ");
				for (String s : words) {
					if (s != " ") {
						fle_wrd_cnter++;
						
						totalBLwords+=srch_wrd(s);
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		counter.add(fle_wrd_cnter);
		System.out.println("total word count for file " + fle.getName() + " is " +fle_wrd_cnter);
		System.out.println("KeyWord Density : "+ calculate_ratio(fle_wrd_cnter, totalBLwords) + "%");
		
		buf_rdr.close();

	}

	/**
	 * Private method parsing the blacklist of words. Subsequently it compares
	 * the passed word to the word word parameter if it finds the word in the
	 * black list it will add it to the list as an analysed_word type
	 * 
	 * @param word
	 * @throws IOException
	 */
	private static void ld_wrd_lst() throws IOException {
		@SuppressWarnings("resource")
		BufferedReader bfrdr = new BufferedReader(new FileReader(
				"WordList/wordlist.txt"));
		String lne;
		while ((lne = bfrdr.readLine()) != null) {
			blk_lst.add(new Analysed_words(lne));
		}
//		for (Analysed_words aw : blk_lst) {
//			System.out.println("black list: " + aw.getWord_());
//		}

	}
	
	/**
	 * Private method for basic frequency searches the given param against the blacklist to check for existence and increment the counter
	 * @param wrd_
	 * @return blwrd
	 */
	private static int srch_wrd(String wrd_){
		int blwrd = 0;
		for(int i = 0; i < blk_lst.size(); i++){
			if(wrd_.compareToIgnoreCase(blk_lst.get(i).getWord_()) == 0)
			{
				blk_lst.get(i).setCounted_(blk_lst.get(i).getCounted_() + 1);
				blwrd+=1;
			}
		}
		return blwrd;
	}
	/**
	 * Private method used to calculate what ratio of the text is using words on the black list,
	 * simply this runs on single words not idioms and as such cannot give a full view of each chat logs 
	 * however it is an inital implementation, i may multply the number slightly as it is very low at the moment 
	 * @param totalWords
	 * @param totalBLwords
	 * @return
	 */
	private static  float calculate_ratio(int totalWords, float totalBLwords){
		float ration = totalBLwords / totalWords;
		ration *=100;
		return ration;
	}
}
