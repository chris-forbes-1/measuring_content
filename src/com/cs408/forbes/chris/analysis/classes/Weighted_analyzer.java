package com.cs408.forbes.chris.analysis.classes;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.cs408.forbes.chris.analysis.types.Analysed_words;
import com.cs408.forbes.chris.analysis.types.Weighted_analysed_word;
import java.io.BufferedReader;

@SuppressWarnings("unused")
/**
 * This class follows the principles donated in the @see Basic_frequency_Analysis class as it focusing on single words
 * however in a different manner it will focus on adding weights to the words in order to acheive the goal of a more balanced 
 * Keyword Density
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *
 */
public class Weighted_analyzer {

		private static  List<Weighted_analysed_word> WAWList;
		float DocumentStrength = 0.0F;//Current strength of the document
		Map<String,Float> Word_strengthMap; //total wordStrength 
		/**
		 * Generic Constructor no detail needed sets up Weighted Analysed Word list and word_strength map
		 */
		public Weighted_analyzer(){
			 WAWList = new ArrayList<Weighted_analysed_word>();
			 Word_strengthMap = new HashMap<String,Float>();
		}
		/**
		 * Get Word_strengthMap
		 * @return
		 */
		public Map<String,Float> getWord_strengthMap()
		{
			return Word_strengthMap;
		}
		/**
		 * Get WAWList
		 * @return
		 */
		public List<Weighted_analysed_word> getWAWList()
		{
			return WAWList;
		}
		/**
		 * Load the list at given filepath generically this is WordList/WeightedBlackList.txt
		 * @param FilePath
		 * @return
		 * @throws IOException
		 */
		public List<Weighted_analysed_word> LoadWeightList(String FilePath) throws IOException{
			File f = new File(FilePath);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String lne = "";
			while ((lne = br.readLine()) != null){
				if(!lne.startsWith("#")){
				String [] lneS = lne.split(",");
				WAWList . add(new Weighted_analysed_word(lneS[0], Float.parseFloat(lneS[1])));
				}else{
					continue;
				}
			}
			for(Weighted_analysed_word aww : WAWList){
				System.out.println(aww.getWord() + Float.toString(aww.getWeight()));
			}
			br.close();
			return WAWList;
			
		}
		/**
		 * Search the given gile against weightedAnanlysedWordList (WAWList)
		 * @param f
		 * @return
		 * @throws IOException
		 */
		public List<Weighted_analysed_word> search(File f) throws IOException{
			DocumentStrength = 0.0F;
			BufferedReader br = new BufferedReader(new FileReader(f));
			String lne = "";
			while ((lne = br.readLine()) != null){
				String [] lneS = lne.split(" ");
				for(String s : lneS){
					for(Weighted_analysed_word waw : WAWList){
						if(waw.getWord().equalsIgnoreCase(s) && waw.getOccurences() > 0){
							waw.IncrementOccurences();
							DocumentStrength += waw.getWeight();
							Word_strengthMap.put(waw.getWord(),waw.getWeight());
							waw.Already_Occured_Decrement();
						}else if(waw.getWord().equalsIgnoreCase(s) && waw.getOccurences() == 0){
							waw.IncrementOccurences();
							DocumentStrength += waw.getWeight();
						}
					}
				}
			}
			br.close();
			return WAWList;
		}
		/**
		 * Search a specific Line against the WAWList
		 * @param line
		 * @return
		 */
		public List<Weighted_analysed_word> search(String line){
			String [] splitLine = line.split(" ");
			for(String s : splitLine)
			{
				for(Weighted_analysed_word waw : WAWList)
				{
					if(waw.getWord().equalsIgnoreCase(s) && waw.getOccurences() > 0){
						waw.IncrementOccurences();
						DocumentStrength += waw.getWeight();
						Word_strengthMap.put(waw.getWord(),waw.getWeight());
						waw.Already_Occured_Decrement();
					}else if(waw.getWord().equalsIgnoreCase(s) && waw.getOccurences() == 0){
						waw.IncrementOccurences();
						DocumentStrength += waw.getWeight();
					}
				}
			}
			return WAWList;
		}
		
		/**
		 * Search every file in a given directory
		 * @param fileDirectory
		 * @return
		 * @throws IOException
		 */
		public List<List<Weighted_analysed_word>> search(File [] fileDirectory) throws IOException{
			List<List<Weighted_analysed_word>> x = new ArrayList<List<Weighted_analysed_word>>();
			for(File f : fileDirectory)
			{
				 
				x.add(search(f));
			}
			return x;
		}
		
		/**
		 * Gets the document strength
		 * @return
		 */
		public float getDocumentStrength(){
			return DocumentStrength;
		}
}
