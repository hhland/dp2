package com.uhealin.dp2.appendix_b;




public class Synchronized1 {
    public static void main(String[] args) {
        final Something obj = new Synchronized1().new Something();

        // 写数据的线程A
        new Thread() {
            public void run() {
                obj.write();
            }
        }.start();

        // 读数据的线程B
        new Thread() {
            public void run() {
                obj.read();
            }
        }.start();
    }
    
    
    class Something {
        private int x = 0;
        private int y = 0;

        public void write() {
            x = 100;
            y = 50;
        }

        public void read() {
            if (x < y) {
                System.out.println("x < y");
            }
        }
    }
    
}
