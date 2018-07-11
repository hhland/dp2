package com.uhealin.dp2.appendix_b;

import java.util.Date;



public class DoubleCheckedLocking {
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
        public static MySystem getInstance() {
            if (instance == null) {                 // (a) 第一次test
                synchronized (MySystem.class) {     // (b) 进入synchronized代码块
                    if (instance == null) {         // (c) 第二次test
                        instance = new MySystem();  // (d) set
                    }
                }                                   // (e) 从synchronized代码块中退出
            }
            return instance;                        // (f)
        }
    }
    
}
