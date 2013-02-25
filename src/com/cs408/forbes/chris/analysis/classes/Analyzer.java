package com.cs408.forbes.chris.analysis.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import com.cs408.forbes.chris.analysis.classes.*;
import com.cs408.forbes.chris.analysis.interfaces.Textual_Analysis;
import com.cs408.forbes.chris.analysis.types.Analysed_words;
/**
 * Analyzer class implementing the Textual_Analysis interface,
 * namely just a simple counter algorithm 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *
 */
@SuppressWarnings("unused")
public class Analyzer implements Textual_Analysis{

	private ArrayList<Analysed_words> awl;
	
	@Override
	public ArrayList<Analysed_words> Analyze(String fileDir) {
		awl = new ArrayList<Analysed_words>();
		
		
		return null;
	}

}
