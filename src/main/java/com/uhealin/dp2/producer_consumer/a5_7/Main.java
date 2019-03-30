package com.uhealin.dp2.producer_consumer.a5_7;
public class Main {
    public static void main(String[] args) {
        // 执行Host的繁重处理的线程
        Thread executor = new Thread() {
            public void run() {
                System.out.println("Host.execute BEGIN");
                try {
                    Host.execute(100);
                } catch (InterruptedException e) {
                      
                }
                System.out.println("Host.execute END");
            }
        };

        // 启动
        executor.start();

        // 休眠约15秒
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
        }

        // 取消
        System.out.println("***** interrupt *****");
        executor.interrupt();
    }
}
