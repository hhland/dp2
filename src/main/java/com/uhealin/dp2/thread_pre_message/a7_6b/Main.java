package com.uhealin.dp2.thread_pre_message.a7_6b;
import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        try {
            new MiniServer(8888).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
