package com.uhealin.dp2.active_object.jucsample;

import java.util.concurrent.Future;

public interface ActiveObject {
    public abstract Future<String> makeString(int count, char fillchar);
    public abstract void displayString(String string);
    public abstract void shutdown();
}
