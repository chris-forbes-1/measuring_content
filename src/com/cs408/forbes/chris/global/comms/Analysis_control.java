package com.cs408.forbes.chris.global.comms;

import com.cs408.forbes.chris.analysis.types.Analysed_words;
import java.util.ArrayList;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *Global comms class for merging lists of single and multi-term clusters
 */
public class Analysis_control {

	/**
	 * Static class pass 2 Arraylists with contents Analysed_words and it will combine them
	 * @param Multi_word
	 * @param single_word
	 * @return ArrayList<Analysed_words> The combined list
	 */
		public static ArrayList<Analysed_words> Combine_lists(ArrayList<Analysed_words> Multi_word, ArrayList<Analysed_words> single_word)
		{
			
			for(int i = 0; i < Multi_word.size(); i++)
			{
				if(Multi_word.get(i).getCounted_() == 0 && single_word.get(i).getCounted_()!= 0)
				{
					Multi_word.set(i, single_word.get(i));
				}else if(Multi_word.get(i).getCounted_() != 0 && single_word.get(i).getCounted_() != 0 )
				{
					int x = Multi_word.get(i).getCounted_();
					int y = single_word.get(i).getCounted_() + x;
					Multi_word.get(i).setCounted_(y);
				}
			}
			return Multi_word;
		}

}
