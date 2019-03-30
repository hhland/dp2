package com.uhealin.forkjoin.kmean;

public class Document {

	private DocumentCluster cluster;
	private Word[] data;

	public Document(String string, int size) {
		// TODO Auto-generated constructor stub
	}

	public boolean setCluster(DocumentCluster cluster) {
		  if (this.cluster == cluster) {
		    return false;
		  } else {
		    this.cluster = cluster;
		    return true;
		  }
		}

	public Word[] getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

	
}
