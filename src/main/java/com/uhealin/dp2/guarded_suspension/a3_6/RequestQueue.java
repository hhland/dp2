package com.uhealin.dp2.guarded_suspension.a3_6;



import java.util.Queue;
import java.util.LinkedList;

public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<Request>();
    public synchronized Request getRequest() throws InterruptedException {
        while (queue.peek() == null) {
            wait();
        }
        return queue.remove();
    }
    public synchronized void putRequest(Request request) {
        queue.offer(request);
        notifyAll();
    }
}
