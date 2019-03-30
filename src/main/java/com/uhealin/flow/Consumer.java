package com.uhealin.flow;

import java.util.Random;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.TimeUnit;

public class Consumer implements Subscriber<Event> {

	  private String name;
	  private Subscription subscription;

	  public Consumer (String name) {
	    this.name = name;
	  }
	//现在，实现Flow.Subscriber接口的四种方法。onComplete()方法和onError()方法只将信息显示到控制台。

	@Override
	public void onComplete() {
	  this.showMessage("No more events");
	}

	@Override
	public void onError(Throwable error) {
	  this.showMessage("An error has ocurred");
	  error.printStackTrace();
	}
	//当消费者希望订阅其通知时，SubmissionPublisher类将调用onSubscribe()方法，作为参数传递的Subscription对象将存放在subscription属性中，然后我们使用request()方法向发布者请求第一条消息。最后，在控制台输出消息。

	@Override
	public void onSubscribe(Subscription subscription) {
	  this.subscription=subscription;
	  this.subscription.request(1);
	  this.showMessage("Subscription OK");
	}
	//最后，对于每个事件，SubmissionPublisher类都将调用onNext()方法。我们在控制台中显示该事件的信息，使用request()方法请求下一个事件，并且调用辅助方法proccesEvent()。

	@Override
	public void onNext(Event event) {
	  this.showMessage("An event has arrived: "+event.getSource()+":"+event.getDate()+": "+event.getMsg());
	  this.subscription.request(1);

	  processEvent(event);
	}
	//使用processEvent()方法模拟消费者处理事件的时间。随机等待0到3秒以实现这一行为。

	private void processEvent(Event event) {
	  Random random = new Random();

	  int number = random.nextInt(3);

	  try {
	    TimeUnit.SECONDS.sleep(number);
	  } catch (InterruptedException e) {
	    e.printStackTrace();
	  }

	}
	//最后，必须实现上一个方法中使用的辅助方法showMessage()。它显示了参数中字符串的内容，其中含有执行消费者的线程的名称，以及消费者的名称。

	  private void showMessage (String txt) {
	    System.out.println(Thread.currentThread().getName()+":"+this
	                       .name+":"+txt);
	  }
	}
