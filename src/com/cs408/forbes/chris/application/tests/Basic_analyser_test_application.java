package com.cs408.forbes.chris.application.tests;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import com.cs408.forbes.chris.analysis.classes.Basic_frequency_Analysis;
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
		
		File f = new File("chatlogs/");
		File[] d = f.listFiles();
		try {
			for(int i = 0; i < d.length; i++)
			{
				UserNameRemoval.rmv_usr_nme(d[i]);
				System.out.println("new file");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Basic_frequency_Analysis bfa = new Basic_frequency_Analysis("tempFiles/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TearDown.tearDown();
	}

}
