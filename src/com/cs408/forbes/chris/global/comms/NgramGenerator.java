package com.cs408.forbes.chris.global.comms;
//TODO Generate NGrams properly
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author cforbes2013 <christopher.forbes@strath.ac.uk> This class is defined
 *         in order to take a file path to a wordlist (of the form single word
 *         or multi word temrs followed by a carraige return) and generate n
 *         grams by duplicating a list removing the [0]'th element and moving
 *         everything up one place. This class has some serious memory
 *         consumption issues use with caution
 */
@SuppressWarnings("unused")
public class NgramGenerator {

	private static ArrayList<String> BLKLST = new ArrayList<String>();

	/**
	 * Pass the File Path For the blacklist
	 * 
	 * @param fp
	 * @return
	 * @throws IOException
	 */
	public static boolean generateNgrams(String fp) throws IOException {
		File f = new File(fp);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String lne = "";
		while ((lne = br.readLine()) != null) {
			BLKLST.add(lne);
		}
		br.close();
		lne = null;
		System.out.println("Black list parsed");
		System.out.println("Launching ngram generation");
		System.gc();
		ExperimentalGeneration();
	 
		return true;
	}
	

	/**
	 * This is an experimental build of the Ngram Generation Algorithm at the moment only generating 
	 *two coulmns i think the best way to aid in the generation of the rest of the file would be to generate a third file (though im not sure how good an idea this is)
	 *and from there parse the newest file and add to it, how do i iterate over this.....	
	 * @throws IOException
	 */
	private static void ExperimentalGeneration() throws IOException{
		PrintWriter pr = new PrintWriter(new File("tempFiles/ngramExpeimental.txt"));
		BufferedReader br = new BufferedReader(new FileReader(new File("tempFiles/ngramExpeimental.txt")));

		for(int y = 0; y < BLKLST.size();y++) //control col1
			{
				for(int z = 0; z < BLKLST.size();z++) //control line addition
				{
					pr.write(BLKLST.get(y)+" "+BLKLST.get(z)+"\n");
					
				}
			}
//		System.out.println("Performing enforced Garbage Collection");
//			//Move onto parsing adding then writing
//		String lne = "";
//		for(int x = 0; x < BLKLST.size();x++){
//		while((lne = br.readLine())!= null)
//		{
//			for(int i = 0; i < BLKLST.size(); i++)
//			{
//				pr.write(lne + " " + BLKLST.get(i) + "\n");
//			}
//		}
//		}
			
		
		pr.close();
		br.close();
	}

}


/*	****Ideas Area****
 * The Black list is already in memory
 * Imagine the list 
 * a b c
 * all ngrams for the list are 
 * aa
 * ab
 * ac
 * ba
 * bb
 * bc
 * ca
 * cb
 * cc
 * in order to create the list we will read A from the file assuming we already have ABC in memory
 * the process subsequently would be
 * parse line 1 (a)
 * ~attach a from memory
 * write aa to file 
 */



