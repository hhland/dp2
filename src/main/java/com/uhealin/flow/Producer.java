package com.uhealin.flow;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	  private SubmissionPublisher<Event> publisher;
	  private String name;

	  public Producer(SubmissionPublisher<Event> publisher, String name) {
	    this.publisher = publisher;
	    this.name = name;
	  }
	//然后，实现run()方法。在该方法中，生成10个事件。在一个事件和下一事件之间，随机等待一个随机秒数（0到10之间）。该方法的源代码如下：

	@Override
	public void run() {

	  Random random = new Random();

	  for (int i=0 ; i < 10; i++) {
	    Event event = new Event();
	    event.setMsg("Event number "+i);
	    event.setSource(this.name);
	    event.setDate(new Date());

	    publisher.submit(event);

	    int number = random.nextInt(10);

	    try {
	      TimeUnit.SECONDS.sleep(number);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }

	  }
	}
	
	
}