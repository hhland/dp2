package com.uhealin.phaser;

import java.util.HashMap;

public class Document {

	private String fileName;
	  private HashMap <String, Word> voc;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public HashMap<String, Word> getVoc() {
		return voc;
	}
	public void setVoc(HashMap<String, Word> voc) {
		this.voc = voc;
	}
	  
	
	 public void addWord(String string) {
		    voc.computeIfAbsent(string, k -> new Word(k)).addTf();
		  }
	
	  
}
