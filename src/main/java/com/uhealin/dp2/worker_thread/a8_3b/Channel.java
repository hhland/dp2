package com.uhealin.dp2.worker_thread.a8_3b;



public final class Channel {
    public Channel(int threads) {
    }
    public void startWorkers() {
    }
    public void putRequest(final Request request) {
        new Thread() {
            public void run() {
                request.execute();
            }
        }.start();
    }
}
