package com.uhealin.dp2.two_phase_termination.a10_2;
public class CountupThread extends Thread {
    // 计数值
    private long counter = 0;

    // 终止请求
    public void shutdownRequest() {
        interrupt();
    }

    // 线程体
    public void run() {
        try {
            while (!isInterrupted()) {
                doWork();
            }
        } catch (InterruptedException e) {
        } finally {
            doShutdown();
        }
    }

    // 操作
    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork: counter = " + counter);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    // 终止处理
    private void doShutdown() {
        System.out.println("doShutdown: counter = " + counter);
    }
}
