package com.cs408.forbes.chris.global.comms;



public class KeyWordDensity {

	/**
	 * Old iteration of the keyword density using integer values (this was decremented as the precision was off)
	 * @param WordFrequency total word frequencies 
	 * @param TotalWords total words
	 * @return float value
	 */
	public static float CalculateKeywordDensity(int WordFrequency , int TotalWords)
	{
		float ration = WordFrequency / TotalWords;
		ration *=100;
		return ration;
	}

	/**
	 * Current implementation of the kwd calc, the use of floating points provides some better results due to
	 * a higher precision
	 * @param totalBLwords
	 * @param fle_wrd_cnter
	 * @return keyword_density 
	 */
	public static float CalculateKeywordDensity(float totalBLwords,
			int fle_wrd_cnter) {
		float ratio = totalBLwords /fle_wrd_cnter;
		ratio *= 100;
		return ratio ;
	}
	
}
