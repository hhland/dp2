package com.uhealin.flow;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class Main {

	
	
	public static void main(String[] args) {

	    SubmissionPublisher<Event> publisher = new SubmissionPublisher();

	    for (int i = 0; i < 5; i++) {
	      Consumer consumer = new Consumer("Consumer "+i);
	      publisher.subscribe(consumer);
	    }

	    Producer system1 = new Producer(publisher, "System 1");
	    Producer system2 = new Producer(publisher, "System 2");

	    ForkJoinTask<?>task1 = ForkJoinPool.commonPool().submit(system1);
	    ForkJoinTask<?>task2 = ForkJoinPool.commonPool().submit(system2);
	    
	    
	    do {
	    	  System.out.println("Main: Task 1: "+task1.isDone());
	    	  System.out.println("Main: Task 2: "+task2.isDone());

	    	  System.out.println("Publisher: MaximunLag:"+
	    	                     publisher.estimateMaximumLag());
	    	  System.out.println("Publisher: Max Buffer Capacity: "+
	    	                     publisher.getMaxBufferCapacity());

	    	  try {
	    	    TimeUnit.SECONDS.sleep(10);
	    	  } catch (InterruptedException e) {
	    	    e.printStackTrace();
	    	  }

	    	} while ((!task1.isDone()) || (!task2.isDone()) ||
	    	         (publisher.estimateMaximumLag() > 0));
	    
	}
	
}
