package com.uhealin.dp2.single_threaded_execution.sample1;

public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";
    public void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        check();
    }
    public String toString() {
        return "No." + counter + ": " + name + ", " + address;
    }
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("***** BROKEN ***** " + toString());
        }
    }
}
