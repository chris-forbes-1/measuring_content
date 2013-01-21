package com.cs408.forbes.chris.analysis.interfaces;
import java.util.ArrayList;

import com.cs408.forbes.chris.analysis.classes.Analysed_words;
/**
 * Interface for Textual Analysis back end.
 * Simply a parsing word counter
 * part of the statistical analysis element
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *
 */
public interface Textual_Analysis {
	
	public ArrayList<Analysed_words> Analyze(String fileDir);  
	
}
