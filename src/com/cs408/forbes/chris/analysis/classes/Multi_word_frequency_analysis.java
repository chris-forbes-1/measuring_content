package com.cs408.forbes.chris.analysis.classes;

import com.cs408.forbes.chris.analysis.types.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.global.comms.Logfiles;

import java.io.BufferedReader;
/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *         Multi_word_frequency_analysis is an extention of the @see
 *         Basic_frequnecy_Analysis allowing the parsing and comparisons of all
 *         combinations of the line
 */
@SuppressWarnings("unused")
public class Multi_word_frequency_analysis {
	private static ArrayList<Analysed_words> bl_words = new ArrayList<Analysed_words>();
	/**
	 * Pass the file path of all the data (this will most likely be the tempFile generated by
	 * @see com.cs408.forbes.chris.global.comms.UserNameRemoval)
	 * @param FilePath
	 * @throws IOException 
	 */
	public Multi_word_frequency_analysis(String fp) throws IOException {
		ld_bl_l();
		prse_dir(fp);
		Logfiles.createLogs(bl_words);
	}
	/**
	 * Load the wordlist i feel this should be moved to a static class as this is repeated code 
	 * and not good software practice.
	 * @throws IOException
	 */
	private static void ld_bl_l () throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("WordList/wordlist.txt")));
		String lne = " ";
		while ((lne = br.readLine())!= null)
		{
			bl_words.add(new Analysed_words(lne));
		}
		br.close();
	}
	/**
	 * Iterates over the given file directory
	 * @param fle_dir
	 */
	private static void prse_dir(String fle_dir) throws IOException {
		File[] fls = new File(fle_dir).listFiles();
		for(File f: fls)
		{
			prse_fle(f);
		}
	}

	private static void prse_fle(File fle) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fle));
		String lne = " ";
		while ((lne = br.readLine()) != null){
			splt_lne(lne);
		}
		br.close();
	}

	private static void splt_lne(String lne) {
		String[] x = lne.split("\\s+");
		bl_src(x);
	}

	private static void bl_src(String[] lne_sp) {
		//test data : rough you up
		//passed data: rough you up
		//compare each string at each position for a match
		for(int i = 0; i < bl_words.size(); i++)
		{
			for(int x = 0; x < lne_sp.length; x++)
			{
				String z = bl_words.get(i).getWord_();
				String [] zz = z.split("\\s+");
				if(lne_sp.length == zz.length){
					if(zz[x].equalsIgnoreCase(lne_sp[x])){
						int zx = bl_words.get(i).getCounted_();
						bl_words.get(i).setCounted_(zx + 1);
					}
				}
			}
		}
	}

}
