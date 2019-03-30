package com.uhealin.dp2.thread_specific_storage.sample1;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static PrintWriter writer = null;

    // 初始化writer字段
    static {
        try {
            writer = new PrintWriter(new FileWriter("log.txt"));
        } catch (IOException e) {
              
        }
    }

    // 写日志
    public static void println(String s) {
        writer.println(s);
    }

    // 关闭日志
    public static void close() {
        writer.println("==== End of log ====");
        writer.close();
    }
}
