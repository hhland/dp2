package com.uhealin.dp2.single_threaded_execution.a1_6b;

public class Pair {
    private final Tool lefthand;
    private final Tool righthand;
    public Pair(Tool lefthand, Tool righthand) {
        this.lefthand = lefthand;
        this.righthand = righthand;
    }
    public String toString() {
        return "[ " + lefthand + " and " + righthand + " ]";
    }
}
