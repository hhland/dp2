package com.uhealin.phaser;

public class Keyword implements Comparable<Keyword>{

	private String word;
	private int df;
	@Override
	public int compareTo(Keyword o) {
		// TODO Auto-generated method stub
		 return Integer.compare(o.getDf(), this.getDf());
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
	}
	
	
}
