package com.uhealin.phaser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;

public class DocumentParser {

	public static Document parse(String path) {
	    Document ret = new Document();
	    Path file = Paths.get(path);
	    ret.setFileName(file.toString());

	    try (BufferedReader reader =
	          Files.newBufferedReader(file)) {
	      for(String line : Files.readAllLines(file)) {
	        parseLine(line, ret);
	      }
	    } catch (IOException x) {
	      x.printStackTrace();
	    }
	    return ret;

	  }
	
	private static void parseLine(String line, Document ret) {

	    // 清理字符串
	    line = Normalizer.normalize(line, Normalizer.Form.NFKD);
	    line = line.replaceAll("[^\\p{ASCII}]", "");
	    line = line.toLowerCase();

	    // 分词程序

	    for(String w: line.split("\\W+")) {
	      ret.addWord(w);
	    }
	  }
}
