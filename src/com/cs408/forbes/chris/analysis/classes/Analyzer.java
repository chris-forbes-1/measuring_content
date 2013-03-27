package com.cs408.forbes.chris.analysis.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.cs408.forbes.chris.analysis.classes.*;
import com.cs408.forbes.chris.analysis.interfaces.Textual_Analysis;
import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.global.comms.Logfiles;
/**
 * Analyzer class implementing the Textual_Analysis interface,
 * namely just a simple counter algorithm 
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *
 */
@SuppressWarnings("unused")
public abstract class Analyzer implements Textual_Analysis{

	private static File[] dir_lst;
	static String fp = " ";
	int test_counter = 0;
	private static ArrayList<Analysed_words> blk_lst = new ArrayList<Analysed_words>();
	static ArrayList<Integer> counter = new ArrayList<Integer>();

	/**
	 * Analyser constructor 
	 * @param fl_dir custom directory for files
	 * @throws IOException on empty directory
	 */
	public Analyzer(String fl_dir)
			throws IOException {
		fp = fl_dir;
	}
	public Analyzer(){
		super();
		fp = "tempFiles/";
	}
	/**
	 * Generic analyze (basic work analysis)This method should be overridden during each implementation
	 * @return ArrayList<Analysed_words> The list of analysed words from BFA
	 * @throws IOException
	 */
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
		
		
		buf_rdr.close();

	}

	/**
	 * Private method parsing the blacklist of words. Subsequently it compares
	 * the passed word to the word word parameter if it finds the word in the
	 * black list it will add it to the list as an analysed_word type
	 * 
	 * 
	 * @return returns the loaded blacklist of analysed words
	 * @throws IOException
	 */
	protected static ArrayList<Analysed_words> ld_wrd_lst() throws IOException {
		
		BufferedReader bfrdr = new BufferedReader(new FileReader(
				"WordList/wordlist.txt"));
		String lne;
		while ((lne = bfrdr.readLine()) != null) {
			blk_lst.add(new Analysed_words(lne));
		}
		bfrdr.close();
//		for (Analysed_words aw : blk_lst) {
//			System.out.println("black list: " + aw.getWord_());
//		}

			return blk_lst;
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
	@Override
	public ArrayList<Analysed_words> Analyze(String fileDir) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Analysed_words> Analyze() {
		// TODO Auto-generated method stub
		return null;
	}
}