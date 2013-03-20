package com.cs408.forbes.chris.analysis.types;

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
	public KeyWord_file(String FILENAME, SearchType search, float KeyWordDensity){
		this.fileName = FILENAME; this.Search = search.toString();this.Keyword_density = KeyWordDensity;
	}
	private String fileName = "";
	private float Keyword_density;
	private String Search = KeyWord_file.SearchType.BASIC_FREQUENCY_ANALYISIS.toString();
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public float getKeyword_density() {
		return Keyword_density;
	}
	public void setKeyword_density(float keyword_density) {
		Keyword_density = keyword_density;
	}
	public String getSearch() {
		return Search;
	}
	public void setSearch(String search) {
		Search = search;
	}
	
	
}
