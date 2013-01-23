package com.cs408.forbes.chris.application.tests;

import java.io.FileNotFoundException;

import com.cs408.forbes.chris.analysis.classes.Basic_frequency_Analysis;

public class Basic_analyser_test_application {
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		String x = " ", y = " ";
		try {
			Basic_frequency_Analysis bfa = new Basic_frequency_Analysis("chatlogs",x,y);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
