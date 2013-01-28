package com.cs408.forbes.chris.application.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import com.cs408.forbes.chris.analysis.classes.Basic_frequency_Analysis;
import com.cs408.forbes.chris.global.comms.UserNameRemoval;

@SuppressWarnings("unused")
public class Basic_analyser_test_application {
	
	
	public static void main(String[] args){
//		String x = " ", y = " ";
//		try {
//			System.out.println("Inititialsing the analysis..");
//			Basic_frequency_Analysis bfa = new Basic_frequency_Analysis("chatlogs",x,y);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			UserNameRemoval.rmv_usr_nme(new File("chatlogs/DavieWants2.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
