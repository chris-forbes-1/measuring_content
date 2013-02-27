package com.cs408.forbes.chris.analysis.interfaces;


import java.io.IOException;
import java.util.*;
/**
 * Written as an interface for working with the SWDL filetype for word lemmatization
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *
 */
public interface SWDL{
	public Map<String, List<String>> getSWDLData();
	public String Search(String Line);
	
	public boolean writeLemitizedFile(List<String> LineList, String Filename) throws IOException;
}
