package com.cs408.forbes.chris.application.tests;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.analysis.types.KeyWord_file;
import com.cs408.forbes.chris.analysis.types.Weighted_analysed_word;
import com.cs408.forbes.chris.analysis.classes.Basic_frequency_Analysis;
import com.cs408.forbes.chris.analysis.classes.Multi_word_frequency_analysis;
import com.cs408.forbes.chris.analysis.classes.SWDLParser;
import com.cs408.forbes.chris.analysis.classes.SWDL_Lemmatization;
import com.cs408.forbes.chris.analysis.classes.Weighted_analyzer;
import com.cs408.forbes.chris.analysis.interfaces.SWDL;
import com.cs408.forbes.chris.global.comms.Analysis_control;
import com.cs408.forbes.chris.global.comms.Grapher;
import com.cs408.forbes.chris.global.comms.KeyWordDensity;
import com.cs408.forbes.chris.global.comms.Logfiles;
import com.cs408.forbes.chris.global.comms.NgramGenerator;
import com.cs408.forbes.chris.global.comms.UserNameRemoval;
import com.cs408.forbes.chris.global.comms.TearDown;

@SuppressWarnings("unused")
public class basicAnalysis {
	private static Scanner sc;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		init();

	}

	protected void menu() {

	}

	public static void init() {
		TearDown.tearDown();
		DisplayLogo();
		System.out
				.println("\n Do you want to use the default dataset??\n (y/n)");
		String param = sc.next();
		switch (param) {
		case "y":
			DefaultRemoval();
			break;
		case "n":
			System.out.println("please enter your folder path/\n:");
			try {
				UserNameRemoval.rmv_usr_nme(new File(sc.next()));
			} catch (IOException e) {
				System.out.println("Error with file location");
			}
			break;
		// default: DefaultRemoval();
		}
		System.out
				.println("Do you want to generate Ngrams of the dataset? \n (y/n)");

		param = sc.next();
		switch (param) {
		case "y":
			try {
				NgramGenerator.generateNgrams("WordList/wordlist.txt");
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
		// File [] tmp = f.listFiles();
		// /**
		// * Memory Intensive part incoming so clear up things i don't need;
		// */
		// f = null;
		// System.gc();
		case "n":
			;
			break;
		default:
			;
		}

		System.out.println("Starting Single word Term search...\nD: 0.0\n");

		System.out.println("Beginning Analysis:");
		ArrayList<Analysed_words> wordList = new ArrayList<Analysed_words>();
		Basic_frequency_Analysis bfa = new Basic_frequency_Analysis();
		try {
			wordList = bfa.analyse();
			//Charted_KWD.Charted_KWD(wordList, "BFA test", "all");
//			for (Analysed_words AL : wordList) {
//				System.out.println(AL.getWord_() + " " + AL.getCounted_());
//			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		// System.out.println("Do you want to test SWDLParser?");
		// String param2 = sc.next();
		// switch(param2)
		// {
		// case "y" :try {
		// SWDLParser SWP = new SWDLParser("WordList/lem_wrd.swdl");
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }
		// break;
		// case "n" : ;
		// break;
		// }

		System.out.println(":::Starting SWDL, Lemmatization:::");
		SWDL swdl_ = new SWDL_Lemmatization();
		File f = new File("tempFiles/");
		File[] ff = f.listFiles();
		for (File d : ff) {
			System.out.println("Starting test on " + f.getName());
			List<String> lemLne = new ArrayList<String>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(d));
				String lne = "";
				while ((lne = br.readLine()) != null) {
					lemLne.add(swdl_.Search(lne));
				}
//				for (String s : lemLne) {
//					System.out.println(s);
//				}
				br.close();
				swdl_.writeLemitizedFile(lemLne, d.getName());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException IOE) {
				IOE.printStackTrace();
			}
		}
		// System.out.println("Single Term Analysis Complete \n\n Starting Multi- term analysis\n\n");
		//
		// try {
		// Multi_word_frequency_analysis mwfa = new
		// Multi_word_frequency_analysis("tempFiles/");
		// ArrayList<Analysed_words> mwfa_a = mwfa.analyse();
		// for(Analysed_words A : mwfa_a)
		// {
		// System.out.println(A.getWord_() + " " + A.getCounted_());
		// }
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }
		// System.out.println("Multi-word term complete");

		// TearDown.tearDown();
		System.out.println(":::Performing BFA on LEM:::");
		List<KeyWord_file> KWDList = new ArrayList<KeyWord_file>();
		List<KeyWord_file> KWDD = new ArrayList<KeyWord_file>();
		try {
			bfa = new Basic_frequency_Analysis("lem/");
			bfa.analyse();
			Map<String, Float> kwd = bfa.getKWDs();
			Iterator<Entry<String, Float>> IT = kwd.entrySet().iterator();
			while(IT.hasNext()){
				
				Map.Entry<String, Float> pairs = (Map.Entry<String, Float>)IT.next();
				System.out.println("File: " + pairs.getKey() + " "+ "KWD: " + pairs.getValue());
				KWDList.add(new KeyWord_file(pairs.getKey(), KeyWord_file.SearchType.BASIC_FREQUENCY_ANALYISIS, pairs.getValue()));
			}
			
			
		} catch (IOException IOE) {
			IOE.printStackTrace();
		}
		System.out.println(":::Perfoming Multi word analysis:::");
		ArrayList<Analysed_words> combinedLists = new ArrayList<Analysed_words>();
		try {
			Multi_word_frequency_analysis mwfa = new Multi_word_frequency_analysis("lem/");
			ArrayList<Analysed_words> multiWordlList = mwfa.analyse();
			System.out.println(":::Performing Merge:::");
			combinedLists  = Analysis_control.Combine_lists(multiWordlList, wordList);
//			for(Analysed_words AW : combinedLists){
//				System.out.println("Word : "+ AW.getWord_() + " Total count : " + AW.getCounted_());
//			}
			KWDD = mwfa.getFleList();
			Logfiles.createLogs(combinedLists);
			Grapher ll = new Grapher(mwfa.getFleList(),KWDList);
			ll.pack();
			RefineryUtilities.centerFrameOnScreen(ll);
			ll.setVisible(true);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Logfiles.createLogs(combinedLists);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(":::Launching Weighted Analysis test:::");
		Weighted_analyzer wa = new Weighted_analyzer();
		try {
			wa.LoadWeightList("WordList/WeightedBlackList.txt");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
//		try {
//			List<Weighted_analysed_word> wawl = wa.search(new File("tempFiles/tempjackies2cool4u.txt"));
//			for(Weighted_analysed_word waa : wawl)
//			{
//				System.out.println("waa : "+ waa.getWord() + " " + waa.getOccurences() + "totalWeight" + waa.getWeight());
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		System.out.println("Total Document strength = " + Float.toString(wa.getDocumentStrength()));
		try {
			wa.LoadWeightList("WordList/WeightedBlackList.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TearDown.tearDown();
	}

	private static void DefaultRemoval() {
		File f = new File("chatlogs/");
		File[] d = f.listFiles();
		try {
			for (File fl : d)
				UserNameRemoval.rmv_usr_nme(fl);
		} catch (IOException e) {
			System.out
					.println("Error found with default locations please contct \n christopher.forbes@strath.ac.uk");
		}

	}

	public static void DisplayLogo() {

		System.out
				.println("****************************************************");
		System.out
				.println("*                    S.T.E.L.A                     *");
		System.out
				.println("*Strathclyde Toolkit for explicit Language Analysis*");
		System.out
				.println("****************************************************");
	}

}
