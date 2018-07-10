package com.uhealin.dp2.guarded_suspension.sample;



import java.util.Queue;
import java.util.LinkedList;

public class RequestQueue extends LinkedList<Request>{
    //private final Queue<Request> queue = new LinkedList<Request>();
    public synchronized Request getRequest() {
        while (this.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return this.remove();
    }
    public synchronized void putRequest(Request request) {
        this.offer(request);
        notifyAll();
    }
}
