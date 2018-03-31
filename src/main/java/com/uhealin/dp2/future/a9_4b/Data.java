package com.uhealin.dp2.future.a9_4b;


import java.util.concurrent.ExecutionException;

public interface Data {
    public abstract String getContent() throws ExecutionException;
}
