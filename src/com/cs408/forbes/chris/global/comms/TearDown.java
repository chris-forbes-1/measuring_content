package com.cs408.forbes.chris.global.comms;

import java.io.File;

/**
 * Global tear down method mainly used for removing the temp files that are being generated, however
 * may be given further responsibility afterwards
 * @author cforbes2013 <christopher.forbes@strath.ac.uk> Basic Word counter
 *
 */
public class TearDown {

	/**
	 * 
	 */
	public static void tearDown() {
		File f = new File("tempFiles/");
		File [] ff = f.listFiles();
		for(File d : ff)
		{
			if(d.delete())
			{
				
			}else{
				System.out.println("error during tempfile removal");
			}
		}
		f = new File("lem/");
		ff = f.listFiles();
		for(File d : ff)
		{
			if(d.delete())
			{
				
			}else{
				System.out.println("error during tempfile removal");
			}
		}
		System.out.println("Tear Down Complete...\n");
		
	}

}
