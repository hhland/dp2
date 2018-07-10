package com.uhealin.dp2.worker_thread.a8_3b;



public class Request {
    private final String name;
    private final int number;
    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executes " + this);
    }
    public String toString() {
        return "[ Request from " + name + " No." + number + " ]";
    }
}
