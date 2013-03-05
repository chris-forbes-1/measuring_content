package com.cs408.forbes.chris.analysis.types;

public class Weighted_analysed_word {
	private String word = "";
	private int occurences = 0;
	private float weight = 0.0F;
	
	public Weighted_analysed_word(String word_phrase, float weight){
		this.word = word_phrase;
		this.weight = weight;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getOccurences() {
		return occurences;
	}
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void Already_Occured_Decrement(){
		if(occurences >=1){
			this.weight -= 0.01F;
		}else{
			this.weight -= 0.00F;
		}
	}
	
}
