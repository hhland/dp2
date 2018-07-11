package com.uhealin.dp2.appendix_b;

import java.util.Date;


public class SingleThreadedExecution {
    public static void main(String[] args) {
        // 线程A
        new Thread() {
            public void run() {
                System.out.println(MySystem.getInstance().getDate());
            }
        }.start();

        // 线程B
        new Thread() {
            public void run() {
                System.out.println(MySystem.getInstance().getDate());
            }
        }.start();
    }
    
    
    
    public static class MySystem {
        private static MySystem instance = null;
        private Date date = new Date();
        private MySystem() {
        }
        public Date getDate() {
            return date;
        }
        public static synchronized MySystem getInstance() {
            if (instance == null) {
                instance = new MySystem();
            }
            return instance;
        }
    }
    
}
