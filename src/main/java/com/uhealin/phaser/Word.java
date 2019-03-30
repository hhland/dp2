package com.uhealin.phaser;

public class Word implements Comparable<Word>{

	private String word;
	private int tf;
	private int df;
	private double tfIdf;
	
	public Word(String word) {
		// TODO Auto-generated constructor stub
		this.word=word;
	}

	@Override
	public int compareTo(Word o) {
		// TODO Auto-generated method stub
		return Double.compare(o.getTfIdf(), this.getTfIdf());
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getTf() {
		return tf;
	}

	public void setTf(int tf) {
		this.tf = tf;
	}

	public int getDf() {
		return df;
	}

	public void setDf(int df) {
		this.df = df;
	}
	
	public void setDf(int df, int N) {
		  this.df = df;
		  tfIdf = tf * Math.log(Double.valueOf(N) / df);
		}

	public double getTfIdf() {
		return tfIdf;
	}

	public void setTfIdf(double tfIdf) {
		this.tfIdf = tfIdf;
	}

	public void addTf() {
		// TODO Auto-generated method stub
		
	}
	
	public Word merge(Word word) {
		this.setDf(this.getDf()+word.getDf());
		this.setDf(this.getTf()+word.getTf());
		return  this;
	}

}
