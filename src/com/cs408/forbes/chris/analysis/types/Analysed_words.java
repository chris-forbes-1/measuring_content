package com.cs408.forbes.chris.analysis.types;

public class Analysed_words {

	private String word_;
	private int counted_;
	
	public Analysed_words() {
		word_ = "";
		counted_ = 0;
	}
	
	public Analysed_words(String word){
		word_ = word;
	}

	public String getWord_() {
		return word_;
	}

	public void setWord_(String word_) {
		this.word_ = word_;
	}

	public int getCounted_() {
		return counted_;
	}

	public void setCounted_(int counted_) {
		this.counted_ = counted_;
	}
	
	public void count(){
		counted_+= 1;
	}
	
}
