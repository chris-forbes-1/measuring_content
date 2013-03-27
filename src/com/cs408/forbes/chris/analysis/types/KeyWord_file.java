package com.cs408.forbes.chris.analysis.types;

/**
 * The keyword_file type was created as a method of encapsulating the data required for graphing the data
 * 
 * @author Christopher Forbes 2013 <christopher.forbes@strath.ac.uk>
 *
 */
public class KeyWord_file {
	public enum SearchType{
		/**
		 * Code Adapted from http://stackoverflow.com/questions/6667243/using-enum-values-as-string-literalshttp://stackoverflow.com/questions/6667243/using-enum-values-as-string-literals
		 */
		BASIC_FREQUENCY_ANALYISIS("BASIC_FREQUENCY_ANALYISIS"), MULTI_WORD_FREQUENCY_ANALYSIS("MULTI_WORD_FREQUENCY_ANALYSIS"), LEMMATIZED_BASIC_FREQUENCY("LEMMATIZED_BASIC_FREQUENCY"), LEMMATIZED_MULTI_WORD_FREQUENCY("LEMMATIZED_MULTI_WORD_FREQUENCY")
		,WEIGHTED_ANALYSIS("WEIGHTED_ANALYSIS");
		private final String searchtype;
		private SearchType(String s){
			searchtype = s;
		}
		public boolean equalsName(String OtherSearchType){
			return (OtherSearchType == null) ? false: searchtype.equals(OtherSearchType);
			// if the other search type is null return false else compare the values and return the result of that operation
		}
		
		public String toString(){
			return searchtype;
		}
	}
	/**
	 * Generic constructor method
	 * @param FILENAME filename of the file that has been analysed
	 * @param search the search type used (searchtype enum declared above)
	 * @param KeyWordDensity What this entire application is about
	 */
	public KeyWord_file(String FILENAME, SearchType search, float KeyWordDensity){
		this.fileName = FILENAME; this.Search = search;this.Keyword_density = KeyWordDensity;
	}
	private String fileName = "";
	private float Keyword_density;
	private SearchType Search = KeyWord_file.SearchType.BASIC_FREQUENCY_ANALYISIS;
	
	/**
	 * 
	 * @return filename generic getter
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 
	 * @param fileName generic setter method 
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 
	 * @return keyword_density generic getter method
	 */
	public float getKeyword_density() {
		return Keyword_density;
	}
	/**
	 * 
	 * @param keyword_density generic setter method 
	 */
	public void setKeyword_density(float keyword_density) {
		Keyword_density = keyword_density;
	}
	/**
	 * 
	 * @return search type generic getter
	 */
	public SearchType getSearch() {
		return Search;
	}
	/**
	 * 
	 * @param search generic setter
	 */
	public void setSearch(SearchType search) {
		Search = search;
	}
	
	
}
