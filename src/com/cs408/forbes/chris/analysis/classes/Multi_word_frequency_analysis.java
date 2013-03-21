package com.cs408.forbes.chris.analysis.classes;

import com.cs408.forbes.chris.analysis.types.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.global.comms.KeyWordDensity;
import com.cs408.forbes.chris.global.comms.Logfiles;
import com.cs408.forbes.chris.analysis.types.KeyWord_file;
import java.io.BufferedReader;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *         Multi_word_frequency_analysis is an extention of the @see
 *         Basic_frequnecy_Analysis allowing the parsing and comparisons of all
 *         combinations of the line
 */
@SuppressWarnings("unused")
public class Multi_word_frequency_analysis extends Analyzer {
	private static ArrayList<Analysed_words> bl_words = new ArrayList<Analysed_words>();
	private static String fp = " ";
	private static ArrayList<Integer> fle_wrd_count = new ArrayList<Integer>();
	private static List<KeyWord_file> FleLsts = new ArrayList<KeyWord_file>();
	private static List<String> NGram = new ArrayList<String>();

	/**
	 * Pass the file path of all the data (this will most likely be the tempFile
	 * generated by
	 * 
	 * @see com.cs408.forbes.chris.global.comms.UserNameRemoval)
	 * @param FilePath
	 * @return BlackList
	 * 
	 * @throws IOException
	 */
	public Multi_word_frequency_analysis(String fp) throws IOException {
		Multi_word_frequency_analysis.fp = fp;
	}

	public ArrayList<Analysed_words> analyse() throws IOException {
		ld_bl_l();
		prse_dir(Multi_word_frequency_analysis.fp);
		Logfiles.createLogs(bl_words);
		return bl_words;
	}

	/**
	 * Load the wordlist i feel this should be moved to a static class as this
	 * is repeated code and not good software practice.
	 * 
	 * @throws IOException
	 */
	private static void ld_bl_l() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"WordList/wordlist.txt")));
		String lne = " ";
		while ((lne = br.readLine()) != null) {
			bl_words.add(new Analysed_words(lne));
		}
		br.close();
	}

	/**
	 * Iterates over the given file directory
	 * 
	 * @param fle_dir
	 * @return 
	 */
	private static List<KeyWord_file> prse_dir(String fle_dir) throws IOException {
		File[] fls = new File(fle_dir).listFiles();
		for (File f : fls) {
			prse_fle(f);
		}
		return FleLsts;
	}

	private static void prse_fle(File fle) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fle));
		String lne = " ";
		int flewc = 0;
		while ((lne = br.readLine()) != null) {
			flewc += splt_lne(lne);

		}

		float blWT = 0.0f;
		for(Analysed_words x : bl_words)
		{
			if(x.getCounted_()>0)
			{
				blWT += 1.0F;
			}
		}
		float KWD = KeyWordDensity.CalculateKeywordDensity(blWT, flewc);
		FleLsts.add(new KeyWord_file(fle.getName(), KeyWord_file.SearchType.MULTI_WORD_FREQUENCY_ANALYSIS ,KWD));
		br.close();

	}

	private static int splt_lne(String lne) throws IOException {
		String[] x = lne.split("\\s+");
		int wc = 0;
		for (String s : x) {
			wc++;
		}
		//System.out.println("MWBasic " + lne);
		bl_src(x);
		try {
		//	System.out.println("MWNG "+ lne);
			ngramSearch(lne);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wc;
	}

	private static float calculate_ratio(int total_words, float total_blWords) {
		float KeyWordDensity = total_blWords / total_words;
		KeyWordDensity *= 100;
		return KeyWordDensity;
	}

	private static void bl_src(String[] lne_sp) throws IOException {
		// test data : rough you up
		// passed data: rough you up
		// compare each string at each position for a match
		ld_wrd_lst(); // reload
		for (int i = 0; i < bl_words.size(); i++) {
			for (int x = 0; x < lne_sp.length; x++) {
				String z = bl_words.get(i).getWord_();
				String[] zz = z.split("\\s+");
				if (lne_sp.length == zz.length) {
					if (zz[x].equalsIgnoreCase(lne_sp[x])) {
						int zx = bl_words.get(i).getCounted_();
						bl_words.get(i).setCounted_(zx + 1);
					}
				}
			}
		}
	}

	private static void ngramSearch(String lne_sp) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"WordList/gen2.txt"));
		String lne = "";
		String[] s_lne;
		while ((lne = br.readLine()) != null) {
			if(lne.equalsIgnoreCase(lne_sp)){
				NGram.add(lne);
			}
		}
		br.close();
	}
	
	public List<KeyWord_file> getFleList(){
		return FleLsts;
	}

}
