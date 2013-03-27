package com.cs408.forbes.chris.analysis.types;
/**
 * This type was created to solve the multi-word bug, by encapsulating words into their own type, the analyzer
 * would not mistake keys in the map
 * @author daarkfall
 *
 */
public class Analysed_words {

	private String word_;
	private int counted_;
	
	/**
	 * Generic null constructor initializing the variables
	 */
	public Analysed_words() {
		word_ = "";
		counted_ = 0;
	}
	/**
	 * Generic setter
	 * @param word The string to set the word of the class
	 */
	public Analysed_words(String word){
		word_ = word;
	}

	/**
	 * 
	 * @return word generic getter
	 */
	public String getWord_() {
		return word_;
	}

	/**
	 * 
	 * @param word_ generic setter
	 */
	public void setWord_(String word_) {
		this.word_ = word_;
	}

	/**
	 * 
	 * @return count generic getter
	 */
	public int getCounted_() {
		return counted_;
	}
/**
 * 
 * @param counted_ generic setter
 */
	public void setCounted_(int counted_) {
		this.counted_ = counted_;
	}
	
	/**
	 * Incrementation function removes the need to get increment then set the variable
	 */
	public void count(){
		counted_+= 1;
	}
	
}
