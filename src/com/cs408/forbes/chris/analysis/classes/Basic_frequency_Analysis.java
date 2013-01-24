package com.cs408.forbes.chris.analysis.classes;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk> Basic Word counter
 */
@SuppressWarnings("unused")
public class Basic_frequency_Analysis {
	File[] dir_lst;
	String vctm, perp;
	int fck_cntr = 0;
	ArrayList<Analysed_words> blk_lst = new ArrayList<Analysed_words>();
	ArrayList<Integer> cnter = new ArrayList<Integer>();

	public Basic_frequency_Analysis(String fl_dir, String vctm, String perp)
			throws IOException {
		this.vctm = vctm;
		this.perp = perp;
		ld_wrd_lst();
		prse_dir(fl_dir);
		for(int i = 0; i < blk_lst.size(); i++){
			System.out.println("Blklist word : " + blk_lst.get(i).getWord_() + " Number of occurences: " + blk_lst.get(i).getCounted_() );
		}
	}

	/**
	 * Private pass file -> make sure you pass an active directory
	 * 
	 * @param fl_dir
	 * @throws IOException
	 */
	private void prse_dir(String fl_dir) throws IOException {
		File fl_dir_ = new File(fl_dir);
		dir_lst = fl_dir_.listFiles();

		for (File fle : dir_lst) {
			prse_fle(fle);
		}
		for (int i = 0; i < dir_lst.length; i++) {
			System.out.println("File Name: " + dir_lst[i].getName());
			System.out.println("\t Word Count : " + cnter.get(i) + " ");
		}
		int total = 0;
		for (int y = 0; y < cnter.size(); y++) {
			total += cnter.get(y);
		}
		System.out.println("Total word counter for " + dir_lst.length
				+ " files is " + total);

		int avrg_wrd_lngth = total / fl_dir.length();
		System.out.println("Average length of documents " + avrg_wrd_lngth);
		System.out
				.println("Assuming most chatlogs on perverted-justic are similar to the average word count");
		System.out.println("We can assume that their is approx. "
				+ avrg_wrd_lngth * 549 + " words in the dataset");
		System.out.println("\n\n Fuck counter: " + fck_cntr);

	}

	private void prse_fle(File fle) throws IOException {
		BufferedReader buf_rdr = new BufferedReader(new FileReader(fle));
		int fle_wrd_cnter = 0;
		String lne;
		try {
			while ((lne = buf_rdr.readLine()) != null) {
				// if(lne.startsWith(vctm))
				// {
				// lne = lne.substring(0, vctm.length()-1);
				// }else if(lne.startsWith(perp)){
				// lne = lne.substring(0, perp.length()-1);
				// }
				String[] words = lne.split(" ");
				for (String s : words) {
					if (s != " ") {
						fle_wrd_cnter++;
						System.out.println("Line: " + s);
						srch_wrd(s);
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cnter.add(fle_wrd_cnter);
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
	private void ld_wrd_lst() throws IOException {
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
	
	private void srch_wrd(String wrd_){
		for(int i = 0; i < blk_lst.size(); i++){
			if(wrd_.compareToIgnoreCase(blk_lst.get(i).getWord_()) == 0)
			{
				blk_lst.get(i).setCounted_(blk_lst.get(i).getCounted_() + 1);
			}
		}
	}
}
