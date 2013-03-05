package com.cs408.forbes.chris.analysis.classes;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.cs408.forbes.chris.analysis.types.Weighted_analysed_word;
import java.io.BufferedReader;


public class Weighted_analyzer {

		private  List<Weighted_analysed_word> WAWList;

		public Weighted_analyzer(){
			 WAWList = new ArrayList<Weighted_analysed_word>();
		}
		
		
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
		
		protected static List<Weighted_analysed_word> LoadWeightList(File[] FileDIR){
			//return WAWList;
			return null;
		}
}
