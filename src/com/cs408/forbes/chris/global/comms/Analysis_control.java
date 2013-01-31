package com.cs408.forbes.chris.global.comms;

import com.cs408.forbes.chris.analysis.types.Analysed_words;
import java.util.ArrayList;
import java.util.HashMap;

public class Analysis_control {

		public static void Combine_lists(ArrayList<Analysed_words> Multi_word, ArrayList<Analysed_words> single_word, HashMap<String,Integer> file_info)
		{
			
			for(int idx = 0; idx < Multi_word.size(); idx ++)
			{
				if(Multi_word.get(idx).getCounted_() != single_word.get(idx).getCounted_())
				{
					int x = single_word.get(idx).getCounted_();
					int y = Multi_word.get(idx).getCounted_();
					Multi_word.get(idx).setCounted_(x + y);
				}
			}
			for(int idx = 0 ; idx < Multi_word.size(); idx ++)
			{
				System.out.println("Combined Lists:");
				System.out.println("Word: " + Multi_word.get(idx).getWord_() + " Count : " + Multi_word.get(idx).getCounted_() );
			}
		}

}
