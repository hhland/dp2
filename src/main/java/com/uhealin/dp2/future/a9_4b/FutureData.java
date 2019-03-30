package com.uhealin.dp2.future.a9_4b;



import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;

public class FutureData extends FutureTask<RealData> implements Data {
    public FutureData(Callable<RealData> callable) {
        super(callable);
    }
    public String getContent() throws ExecutionException {
        String string = null;
        try {
            string = get().getContent();
        } catch (InterruptedException e) {
              
        }
        return string;
    }
}
