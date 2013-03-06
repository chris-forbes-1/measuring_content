package com.cs408.forbes.chris.global.comms;



public class KeyWordDensity {

	public static float CalculateKeywordDensity(int WordFrequency , int TotalWords)
	{
		float ration = WordFrequency / TotalWords;
		ration *=100;
		return ration;
	}

	public static float CalculateKeywordDensity(float totalBLwords,
			int fle_wrd_cnter) {
		float ratio = totalBLwords /fle_wrd_cnter;
		ratio *= 100;
		return ratio ;
	}
	
}
