package com.uhealin.dp2.future.a9_3b;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;

class AsyncContentImpl extends FutureTask<SyncContentImpl> implements Content {
    public AsyncContentImpl(Callable<SyncContentImpl> callable) {
        super(callable);
    }
    public byte[] getBytes() {
        byte[] bytes = null;
        try {
            bytes = get().getBytes();
        } catch (InterruptedException e) {
              
        } catch (ExecutionException e) {
              
        }
        return bytes;
    }
}
