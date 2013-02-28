package com.cs408.forbes.chris.analysis.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;

import sun.util.logging.PlatformLogger;

import com.cs408.forbes.chris.analysis.interfaces.SWDL;
@SuppressWarnings("unused")
/**
 * @desc Used to perform Lemmatization a method not unlike the process of stemming in information retrival
 * lemmatization has the benefits of stemming (removing duplicate words etc.) with the additional 
 * bonus of access to a thesaurus or in the case of STELA SWDL files, see the exemplar file lem_wrd.swdl
 * for details on the format of an SWDL file.
 * @author cforbes2013 <christopher.forbes@strath.ac.uk> 
 *
 */
public class SWDL_Lemmatization implements SWDL {
	protected Map<String, List<String>> SWDL_FILE_DATA = new HashMap<String,List<String>>(); 
	public SWDL_Lemmatization() {
		try {
			SWDLParser fleP = new SWDLParser("WordList/lem_wrd.swdl");
			SWDL_FILE_DATA = fleP.getSWDLData();
		
		} catch (IOException e) {
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
	/**
	 * @desc This method searches the given line with the SWDL file loaded into memory
	 * it will modify the line neutralising the language by replacing the noun/verb
	 * with it's associated word see exemplar SWDL file 'lem_wrd.swdl' this will be extended to 
	 * operate of the files instead
	 * @param String
	 * @return String
	 */
	public String Search(String Line) throws NullPointerException {
		String [] x = Line.split(" ");
		if(SWDL_FILE_DATA == null){
			throw new NullPointerException("Search NPE");
		}
		boolean repacelement_takenPlace = false;		
		Iterator <Entry<String, List<String>>> MapIterator = SWDL_FILE_DATA.entrySet().iterator();
		while(MapIterator.hasNext()){
			Map.Entry<String,List<String>> pairs = (Map.Entry<String, List<String>>)MapIterator.next();
			List<String> verb_noun = pairs.getValue();
			for(int y = 0 ; y < verb_noun.size(); y++)
			{
				for(int i = 0; i < x.length; i++)
				{
					if(x[i].compareToIgnoreCase(verb_noun.get(y)) == 0){
						x[i] = pairs.getKey();
					// <TESTING LINE > System.out.println("Replaced " + x[i] + "with " + pairs.getKey());
					System.out.println("HIT");
					}else{
						continue;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(String s : x){
			sb.append(s + " ");
		}
		sb.append("\n");
		return sb.toString();
		
	}
	@Override
	/**
	 * Get data incase you need it :)
	 */
	public Map<String, List<String>> getSWDLData() throws NullPointerException {

		if(SWDL_FILE_DATA == null)
		{
			throw new NullPointerException("SWDL Not Parsed");
		}else{
			return this.SWDL_FILE_DATA;
		}
	}
	@Override
	/**
	 * :::IN PROGRESS:::
	 * Simply writes the lemitized data back to a tempFile/tmp followed by the existing temp filename
	 */
	public boolean writeLemitizedFile(List<String> LineList,String Filename) throws IOException{
		PrintWriter pr = new PrintWriter(new File("tempFiles/lem/" + "tmp" + Filename));
		for(int i = 0; i < LineList.size(); i ++){
			pr.write(LineList.get(i));
		}
		pr.close();
		return true;
	}
	

}
