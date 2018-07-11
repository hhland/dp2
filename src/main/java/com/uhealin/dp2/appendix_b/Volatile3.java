package com.uhealin.dp2.appendix_b;



public class Volatile3 {
    public static void main(String[] args) {
        final Something obj =new Volatile3().new Something();

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
        private volatile boolean valid = false;

        public void write() {
            x = 123;
            valid = true;
        }

        public void read() {
            if (valid) {
                System.out.println(x);
            }
        }
    }
}
