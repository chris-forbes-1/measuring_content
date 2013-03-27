package com.cs408.forbes.chris.analysis.classes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The SWDL file is related to the STELA framework, containing a list of words with the associated words along side it
 * it follows the format <associate word> : <verb/nound associated with associate> , @ 
 * All lines must end in , @ or a malformed SWDL error will be thrown
 * Lines can also reference existing items using the ! operator e.g.
 * 
 * fruit : apple,tomato,pineapple, @
 * salad : lettuce,!fruit,cucumber, @
 * 
 * the @ sign is their in case the user of the framework wants to do character operations on the strings
 * the @ is simply a terminator
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 * TODO: Referencing for SWDL syntax ! operator
 */
public class SWDLParser {
	
	private Map<String,List<String>> SWDLData ;
	private static BufferedReader br ;
	
	/**
	 * Main Constructor 
	 * @param FileP
	 * @throws IOException
	 */
	public SWDLParser (String FileP) throws IOException{
		br = new BufferedReader(new FileReader(new File(FileP)));
//		setSWDLData((br.ready() == true) ? (parseswdl()) : (null)); 
		SWDLData = new HashMap<String, List<String>>();
		SWDLData.putAll(parseswdl());
	}
	
	/**
	 * Although obvious from the name the parseswdl() function parses the the swdl file and places it into
	 * a Map<String,List<String>> for easy access to associated words and their variations.
	 * @return Map<String,List<String>>
	 * @throws IOException
	 */
	protected Map<String,List<String>> parseswdl() throws IOException
	{
		Pattern p = Pattern.compile("((?:[a-z][a-z0-9_]*))", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		
		String associated_word = "";
		List<String> thesaurs;
		Map<String, List<String>>SWDLDatas = new HashMap<String,List<String>>();
		String lne = "";
		while ((lne = br.readLine()) != null){
			thesaurs = new ArrayList<String>();
			if(lne.startsWith("#") || lne.startsWith(" #")){
				continue;
			}else if(!lne.endsWith(", @")){
				throw new IOException("Malformed SWDL file");
			}
			Matcher m = p.matcher(lne);
			
			if(m.find()){
				associated_word = m.group(1);
				lne.replaceFirst(p.pattern(), "");
				String [] s = lne.split(",", lne.length());
				for(String x : s){
					if(x == " @"){
						continue;
					}else if(x.startsWith("!")){
						if(SWDLDatas.containsKey(x)){
							Iterator <Entry<String, List<String>>> IT = SWDLDatas.entrySet().iterator();
							while(IT.hasNext()){
								
							}
							
						}
					}else{
					thesaurs.add(x);
					//System.out.println("Thesaurus word: "+ x);
					}
				}
				SWDLDatas.put(associated_word, thesaurs);
			}
			
		}
		return SWDLDatas;
	}
/**
 * Getter Should be called after parser
 * @return Map<String, List<String>> returns all SWDL parsed from the file
 * @throws NullPointerException
 */
	public Map<String,List<String>> getSWDLData() throws NullPointerException {
		return SWDLData;
	}	
}
