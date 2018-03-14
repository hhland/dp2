package com.uhealin.dp2.active_object.q12_2b;

import com.uhealin.dp2.active_object.a12_2b.ActiveObject;
import com.uhealin.dp2.active_object.a12_2b.ActiveObjectFactory;

public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        try {
            new AddClientThread("Diana", activeObject).start();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        } finally {
            System.out.println("*** shutdown ***");
            activeObject.shutdown();
        }
    }
}
