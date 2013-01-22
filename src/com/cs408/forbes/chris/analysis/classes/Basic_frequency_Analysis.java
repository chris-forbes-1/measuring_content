package com.cs408.forbes.chris.analysis.classes;
import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;
/**
 * 
 *@author cforbes2013 <christopher.forbes@strath.ac.uk>
 *Basic Word counter
 */
@SuppressWarnings("unused")
public class Basic_frequency_Analysis {
	File [] dir_lst; 
	public Basic_frequency_Analysis(String fl_dir){
		prse_dir(fl_dir);
	}
	/**
	 * Private pass file -> make sure you pass an active directory
	 * @param fl_dir
	 */
	private void prse_dir (String fl_dir){
		File fl_dir_ = new File(fl_dir);
		dir_lst = fl_dir_.listFiles();
		
		for(File fle : dir_lst)
		{
			
		}
	}
	
	private void prse_fle(File f){
		
	}
	
}
