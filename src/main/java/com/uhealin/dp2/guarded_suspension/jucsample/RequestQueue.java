package com.uhealin.dp2.guarded_suspension.jucsample;



import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue extends LinkedBlockingQueue<Request> {
    //private final BlockingQueue<Request> queue = new LinkedBlockingQueue<Request>();
    public Request getRequest() {
        Request req = null;
        try {
            req = this.take();
        } catch (InterruptedException e) {
        }
        return req;
    }
    public void putRequest(Request request) {
        try {
            this.put(request);
        } catch (InterruptedException e) {
        }
    }
}
