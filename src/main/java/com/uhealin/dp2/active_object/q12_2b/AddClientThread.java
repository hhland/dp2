package com.uhealin.dp2.active_object.q12_2b;



import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import com.uhealin.dp2.active_object.a12_2b.ActiveObject;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class AddClientThread extends Thread {
    private final ActiveObject activeObject;
    private String x = "1";
    private String y = "1";
    public AddClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }
    public void run() {
        try {
            for (int i = 0; true; i++) {
                // 有返回值的调用
                Future<String> future = activeObject.add(x, y);
                Thread.sleep(100);
                String z = future.get();
                System.out.println(Thread.currentThread().getName() + ": " + x + " + " + y + " = " + z);
                x = y;
                y = z;
            }
        } catch (RejectedExecutionException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        } catch (CancellationException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        } catch (ExecutionException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        }
    }
}
