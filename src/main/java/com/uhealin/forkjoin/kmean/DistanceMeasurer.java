package com.uhealin.forkjoin.kmean;

public class DistanceMeasurer {

	public static double euclideanDistance(Word[] words, double[]
            centroid) {
		double distance = 0;
		
		int wordIndex = 0;
		for (int i = 0; i < centroid.length; i++) {
		if ((wordIndex < words.length) &&(words[wordIndex].getIndex()
		        == i)) {
			double tfidf=words[wordIndex].getTfidf(),ceni=centroid[i];
			distance += Math.pow( (tfidf -ceni), 2);
		    wordIndex++;
		} else {
		distance += centroid[i] * centroid[i];
		}
		}
		
		return Math.sqrt(distance);
	}
}
