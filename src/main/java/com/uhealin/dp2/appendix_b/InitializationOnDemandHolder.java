package com.uhealin.dp2.appendix_b;

import java.util.Date;


public class InitializationOnDemandHolder {
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
        private static class Holder {
            public static MySystem instance = new MySystem();
        }
        private Date date = new Date();
        private MySystem() {
        }
        public Date getDate() {
            return date;
        }
        public static MySystem getInstance() {
            return Holder.instance;
        }
    }


}


