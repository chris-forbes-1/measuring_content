package com.cs408.forbes.chris.application.tests;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import com.cs408.forbes.chris.analysis.classes.Basic_frequency_Analysis;
import com.cs408.forbes.chris.analysis.classes.Multi_word_frequency_analysis;
import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.global.comms.Analysis_control;
import com.cs408.forbes.chris.global.comms.TearDown;
import com.cs408.forbes.chris.global.comms.UserNameRemoval;

@SuppressWarnings("unused")
public class Basic_analyser_test_application {
	
	
	public static void main(String[] args){
		/**
		 * Old Test code for initial implementation of the basic frequency analysis
		 */
//		String x = " ", y = " ";
//		try {
//			System.out.println("Inititialsing the analysis..");
//			Basic_frequency_Analysis bfa = new Basic_frequency_Analysis("chatlogs",x,y);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/**
		 *old test
		 */
		System.out.println("Removing Usernames from chatlogs for analysis");
		File f = new File("chatlogs/");
		File[] d = f.listFiles();
		try {
			for(int i = 0; i < d.length; i++)
			{
				UserNameRemoval.rmv_usr_nme(d[i]);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n");
		try {
			Basic_frequency_Analysis bfa = new Basic_frequency_Analysis("tempFiles/"); //old test
			Multi_word_frequency_analysis mwfa = new Multi_word_frequency_analysis("tempFiles/");
			ArrayList<Analysed_words> aw = mwfa.analyse();
			ArrayList<Analysed_words> awsf = bfa.analyse();
			System.out.println("test return analysed MFWA: ");
			for(Analysed_words aws : aw)
			{
				System.out.println("MW W:" + aws.getWord_());
				System.out.println("MW C:"+ aws.getCounted_());
			}
			System.out.println("test Return BFS: ");
			for(Analysed_words aws : awsf)
			{
				System.out.println("MW W:" + aws.getWord_());
				System.out.println("MW C:"+ aws.getCounted_());
			}
			ArrayList<Analysed_words> CL = Analysis_control.Combine_lists(aw, awsf);
			System.out.println("********************Converged lists *****************");
			for(Analysed_words aa : CL)
			{
				System.out.print("\n Word: "+ aa.getWord_());
				System.out.print("  Count = "+ aa.getCounted_());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TearDown.tearDown();
	}

}
