package com.cs408.forbes.chris.analysis.classes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
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
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *
 */
public class SWDLParser {
	
	private static Map<String,List<String>> SWDLData ;
	private static BufferedReader br ;
	
	/**
	 * Main Constructor 
	 * @param FileP
	 * @throws IOException
	 */
	public SWDLParser (String FileP) throws IOException{
		br = new BufferedReader(new FileReader(new File(FileP)));
		setSWDLData((br.ready() == true) ? (parseswdl()) : (null)); 
	}
	
	/**
	 * 
	 * @return Map<String,List<String>>
	 * @throws IOException
	 */
	protected Map<String,List<String>> parseswdl() throws IOException
	{
		Pattern p = Pattern.compile("((?:[a-z][a-z0-9_]*))", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		String associated_word = "";
		List<String> thesaurs = new ArrayList<String>();
		Map<String, List<String>>SWDLDatas = new HashMap<String,List<String>>();
		String lne = "";
		while ((lne = br.readLine()) != null){
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
					thesaurs.add(x);
				}
				SWDLDatas.put(associated_word, thesaurs);
			}
			
		}
		return SWDLDatas;
	}

	public static Map<String,List<String>> getSWDLData() throws NullPointerException {
		return SWDLData;
	}

	public static void setSWDLData(Map<String,List<String>> sWDLData) {
		SWDLData = sWDLData;
	}
	
}
