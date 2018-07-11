package com.uhealin.dp2.appendix_b;





public class Volatile2 {
    public static void main(String[] args) {
        Runner runner =new Volatile2().new Runner();
        runner.start();
        runner.shutdown();
    }
    
    
    
    class Runner extends Thread {
        private volatile boolean quit = false;

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
