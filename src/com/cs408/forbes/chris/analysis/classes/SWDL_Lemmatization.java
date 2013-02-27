package com.cs408.forbes.chris.analysis.classes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;

import com.cs408.forbes.chris.analysis.interfaces.SWDL;
@SuppressWarnings("unused")
public class SWDL_Lemmatization implements SWDL {
	protected static Map<String, List<String>> SWDL_FILE_DATA = new HashMap<String,List<String>>(); 
	public SWDL_Lemmatization() {
		try {
			SWDLParser fleP = new SWDLParser("WordList/lem_wrd.swdl");
			SWDL_FILE_DATA = fleP.getSWDLData();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SWDL_Lemmatization(String FilePath)
	{
		
		try{
			SWDLParser SWD = new SWDLParser(FilePath);
			SWDL_FILE_DATA.putAll(SWD.getSWDLData());
		}catch (IOException IOE){
			IOE.printStackTrace();
		}
	}

	@Override
	public boolean Search(String Line) throws NullPointerException {
		String [] x = Line.split(" ");
		if(SWDL_FILE_DATA == null){
			throw new NullPointerException("Search NPE");
		}
		boolean repacelement_takenPlace = false;
//		File f = new File ("tempFiles/");
//		File [] tmp = f.listFiles();
//		/**
//		 * Memory Intensive part incoming so clear up things i don't need;
//		 */
//		f = null;
//		System.gc();
		
		Iterator <Entry<String, List<String>>> MapIterator = SWDL_FILE_DATA.entrySet().iterator();
		
		while(MapIterator.hasNext()){
			Map.Entry<String,List<String>> pairs = (Map.Entry<String, List<String>>)MapIterator.next();
			List<String> verb_noun = pairs.getValue();
			for(int y = 0 ; y < verb_noun.size(); y++)
			{
				for(int i = 0; i < x.length; i++)
				{
					if(x[i].compareToIgnoreCase(verb_noun.get(y)) == 0){
					System.out.println("Replaced " + x[i] + "with " + pairs.getKey());
					replacement_takenPlace = true;
					}else{
						continue;
					}
				}
			}
		}
		System.out.println("::Writing to lemmatized logFiles:::");
		
		return replacement_takenPlace;
		
	}
	@Override
	public Map<String, List<String>> getSWDLData() throws NullPointerException {

		if(SWDL_FILE_DATA == null)
		{
			throw new NullPointerException("SWDL Not Parsed");
		}else{
			return SWDL_FILE_DATA;
		}
	}
	@Override
	public boolean writeLemitizedFile() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
