package com.cs408.forbes.chris.global.comms;

public class KeyWordDensity {

	public static float CalculateKeywordDensity(int WordFrequency , int TotalWords)
	{
		float ration = WordFrequency / TotalWords;
		ration *=100;
		return ration;
	}
	
}
