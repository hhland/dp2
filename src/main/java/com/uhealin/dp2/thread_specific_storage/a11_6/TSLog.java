package com.uhealin.dp2.thread_specific_storage.a11_6;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TSLog {
    private PrintWriter writer = null;

    // 初始化writer字段
    public TSLog(String filename) {
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
              
        }
    }

    // 写日志
    public void println(String s) {
        writer.println(s);
    }

    // 关闭日志
    public void close() {
        writer.println("==== End of log ====");
        writer.close();
    }
}
