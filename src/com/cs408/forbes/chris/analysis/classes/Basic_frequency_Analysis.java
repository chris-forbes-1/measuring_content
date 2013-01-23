package com.cs408.forbes.chris.analysis.classes;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 *@author cforbes2013 <christopher.forbes@strath.ac.uk>
 *Basic Word counter
 */
@SuppressWarnings("unused")
public class Basic_frequency_Analysis {
	File [] dir_lst; 
	String vctm, perp;
	ArrayList<Integer> cnter = new ArrayList<Integer>();
	public Basic_frequency_Analysis(String fl_dir, String vctm, String perp) throws FileNotFoundException{
		this.vctm = vctm;
		this.perp = perp;
		prse_dir(fl_dir);
	}
	/**
	 * Private pass file -> make sure you pass an active directory
	 * @param fl_dir
	 * @throws FileNotFoundException 
	 */
	private void prse_dir (String fl_dir) throws FileNotFoundException{
		File fl_dir_ = new File(fl_dir);
		dir_lst = fl_dir_.listFiles();
		
		for(File fle : dir_lst)
		{
		prse_fle(fle);	
		}
		for(int i = 0; i < dir_lst.length; i++){
			System.out.println("File Name: " + dir_lst[i].getName() + "\n");
			System.out.println("\t Word Count : "+ cnter.get(i) +" ");
		}
		int total = 0;
		for(int y = 0; y < cnter.size(); y++)
		{
			total+= cnter.get(y);
		}
		System.out.println("Total word counter for " + dir_lst.length + " files is " + total);
		
		int avrg_wrd_lngth = total / fl_dir.length();
		System.out.println("Average length of documents " + avrg_wrd_lngth );
		
	}
	
	private void prse_fle(File fle) throws FileNotFoundException{
		BufferedReader buf_rdr = new BufferedReader(new FileReader(fle));
			int fle_wrd_cnter = 0;
			String lne;
			try {
				while((lne = buf_rdr.readLine())!=null){
//					if(lne.startsWith(vctm))
//					{
//						lne = lne.substring(0, vctm.length()-1);
//					}else if(lne.startsWith(perp)){
//						lne = lne.substring(0, perp.length()-1);
//					}
					String [] words = lne.split("\\s+");
					for(String s : words){
						fle_wrd_cnter ++;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnter.add(fle_wrd_cnter);
		
	}
	
}
