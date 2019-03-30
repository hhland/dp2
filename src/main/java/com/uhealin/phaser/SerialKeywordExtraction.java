package com.uhealin.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SerialKeywordExtraction {

	
	public static void main(String[] args) {

	    Date start, end;

	    File source = new File("data");
	    File[] files = source.listFiles();
	    HashMap<String, Word> globalVoc = new HashMap<>();
	    HashMap<String, Integer> globalKeywords = new HashMap<>();
	    int totalCalls = 0;
	    int numDocuments = 0;

	    start = new Date();
	    
	    
	    if(files == null) {
	    	  System.err.println("Unable to read the 'data' folder");
	    	  return;
	    	}
	    	for (File file : files) {

	    	  if (file.getName().endsWith(".txt")) {
	    	    Document doc = DocumentParser.parse (file.getAbsolutePath());
	    	    for (Word word : doc.getVoc().values()) {
	    	     // globalVoc.merge(word.getWord(), word, Word::merge);
	    	    }
	    	    numDocuments++;
	    	  }
	    	}
	    	System.out.println("Corpus: " + numDocuments + " documents.");
	    	
	    	
	    	for (File file : files) {
	    		  if (file.getName().endsWith(".txt")) {
	    		    Document doc = DocumentParser.parse(file.getAbsolutePath());
	    		    List<Word> keywords = new ArrayList<>( doc.getVoc().values());

	    		    int index = 0;
	    		    for (Word word : keywords) {
	    		      Word globalWord = globalVoc.get(word.getWord());
	    		      word.setDf(globalWord.getDf(), numDocuments);
	    		    }

	    		
	    		    Collections.sort(keywords);

	    		    int counter = 0;

	    		    for (Word word : keywords) {
	    		      addKeyword(globalKeywords, word.getWord());
	    		      totalCalls++;
	    		    }
	    		  }
	    	}
}

	private static void addKeyword(HashMap<String, Integer> globalKeywords, String word) {
		// TODO Auto-generated method stub
		
	}
}
