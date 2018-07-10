package com.uhealin.dp2.producer_consumer.a5_8;
public class LazyThread extends Thread {
    private final Table table;
    public LazyThread(String name, Table table) {
        super(name);
        this.table = table;
    }
    public void run() {
        while (true) {
            try {
                synchronized (table) {
                    table.wait();
                }
                System.out.println(getName() + " is notified!");
            } catch (InterruptedException e) {
            }
        }
    }
}
