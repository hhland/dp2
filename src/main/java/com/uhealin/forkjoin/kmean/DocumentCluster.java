package com.uhealin.forkjoin.kmean;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DocumentCluster {

	
	
	private List<Document> documents;
	private double[] centroid;


	public DocumentCluster(int vocSize) {
		// TODO Auto-generated constructor stub
	}


	public void calculateCentroid() {

		  Arrays.fill(centroid, 0);

		  for (Document document : documents) {
		    Word vector[] = document.getData();

		    for (Word word : vector) {
		      centroid[word.getIndex()] += word.getTfidf();
		    }
		  }

		  for (int i = 0; i < centroid.length; i++) {
		    centroid[i] /= documents.size();
		  }
		}
	
	
	public void initialize(Random random) {
		  for (int i = 0; i < centroid.length; i++) {
		    centroid[i] = random.nextDouble();
		  }
		}


	public void clearClusters() {
		// TODO Auto-generated method stub
		
	}


	public double[] getCentroid() {
		// TODO Auto-generated method stub
		return centroid;
	}


	public void addDocument(Document document) {
		// TODO Auto-generated method stub
		
	}
	
	
	public int getDocumentCount() {
		return documents.size();
	}
}
