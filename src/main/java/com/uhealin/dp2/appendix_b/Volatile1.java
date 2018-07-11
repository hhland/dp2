package com.uhealin.dp2.appendix_b;



public class Volatile1 {
    public static void main(String[] args) {
        Runner runner =new Volatile1().new Runner();

        // 启动线程
        runner.start();

        // 终止线程
        runner.shutdown();
    }
    
    
    class Runner extends Thread {
        private boolean quit = false;

        public void run() {
            while (!quit) {
                // ...
            }
            System.out.println("Done");
        }

        public void shutdown() {
            quit = true;
        }
    }
}
