package com.cs408.forbes.chris.analysis.types;

/**
 * Used with the weighted analysis class to keep track of word strength
 * @author christopher forbes 2013 <christopher.forbes@strath.ac.uk>
 *
 */
public class Weighted_analysed_word {
	private String word = "";
	private int occurences = 0;
	private float weight = 0.0F;
	
	public Weighted_analysed_word(String word_phrase, float weight){
		this.word = word_phrase;
		this.weight = weight;
	}
	/**
	 * 
	 * @return word generic getter
	 */
	public String getWord() {
		return word;
	}
	/**
	 * 
	 * @param word generic setter
	 */
	public void setWord(String word) {
		this.word = word;
	}
	/**
	 * 
	 * @return occurrences generic getter
	 */
	public int getOccurences() {
		return occurences;
	}
	/**
	 * 
	 * @param occurences generic setter
	 */
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
	/**
	 * 
	 * @return weight generic getter
	 */
	public float getWeight() {
		return weight;
	}
	/**
	 * 
	 * @param weight generic setter (allows for overriding of pre-defined word weight)
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
	/**
	 * used to stop word from over running e.g. a document filled with one word would have a lower 
	 * strength than a genuinley sexually explicit document
	 */
	public void Already_Occured_Decrement(){
		if(occurences >=1){
			this.weight -= 0.01F;
		}else{
			this.weight -= 0.00F;
		}
	}
	/**
	 * Increments occurences, used to aid in incrementation rather than getting the occurences, incremented then 
	 * setting the occurences call this method
	 */
	public void IncrementOccurences(){
		this.occurences++;
	}
	
}
