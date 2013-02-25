package com.cs408.forbes.chris.application.tests;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.analysis.classes.Basic_frequency_Analysis;
import com.cs408.forbes.chris.analysis.classes.SWDLParser;
import com.cs408.forbes.chris.global.comms.NgramGenerator;
import com.cs408.forbes.chris.global.comms.UserNameRemoval;
import com.cs408.forbes.chris.global.comms.TearDown;
public class basicAnalysis {
	private static Scanner sc;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		init();

	}
	
	protected void menu(){

	}
	
	public static void init(){
	DisplayLogo();
	System.out.println("\n Do you want to use the default dataset??\n (y/n)");
	String param = sc.next();
	switch(param){
	case "y" :DefaultRemoval();
	break;
	case "n":System.out.println("please enter your folder path/\n:"); try {
			UserNameRemoval.rmv_usr_nme(new File(sc.next()));
		} catch (IOException e) {
			System.out.println("Error with file location");
		}
	break;
	default: DefaultRemoval();
	}
	System.out.println("Do you want to generate Ngrams of the dataset? \n (y/n)");

	param = sc.next();
	switch(param)
	{
	case "y":try {
			NgramGenerator.generateNgrams("WordList/wordlist.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "n" : ;
	break;
	default: ;
	}
	
	System.out.println("Starting Single word Term search...\n\n");
	
	System.out.println("Beginning Analysis:");
	Basic_frequency_Analysis bfa = new Basic_frequency_Analysis();
	try {
		ArrayList<Analysed_words> wordList = bfa.analyse();
		for(Analysed_words AL : wordList)
		{
			System.out.println(AL.getWord_() + " " + AL.getCounted_());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Do you want to test SWDLParser?");
	String param2 = sc.next();
	switch(param2)
	{
	case "y" :try {
			SWDLParser SWP = new SWDLParser("WordList/lem_wrd.swdl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	break;
	case "n" : ;
	break;
	}
//	System.out.println("Single Term Analysis Complete \n\n Starting Multi- term analysis\n\n");
//	
//	try {
//		Multi_word_frequency_analysis mwfa = new Multi_word_frequency_analysis("tempFiles/");
//		ArrayList<Analysed_words> mwfa_a = mwfa.analyse();
//		for(Analysed_words A : mwfa_a)
//		{
//			System.out.println(A.getWord_() + " " + A.getCounted_());
//		}
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.println("Multi-word term complete");
	TearDown.tearDown();
	}
	
	private static void DefaultRemoval() {
		File f = new File("chatlogs/");
		File [] d = f.listFiles();
		try {
			for(File fl : d)
			UserNameRemoval.rmv_usr_nme(fl);
		} catch (IOException e) {
			System.out.println("Error found with default locations please contct \n christopher.forbes@strath.ac.uk");
		}
		
		//TODO Remove for final
		//TearDown.tearDown();
		
	}

	public static void DisplayLogo(){
		
		System.out.println("****************************************************");
		System.out.println("*                    S.T.E.L.A                     *");
		System.out.println("*Strathclyde Toolkit for explicit Language Analysis*");
		System.out.println("****************************************************");
	}

}
