package com.uhealin.dp2.thread_specific_storage.sample2;
public class Log {
    private static final ThreadLocal<TSLog> tsLogCollection = new ThreadLocal<TSLog>();

    // 写日志
    public static void println(String s) {
        getTSLog().println(s);
    }

    // 关闭日志
    public static void close() {
        getTSLog().close();
    }

    // 获取线程特有的日志
    private static TSLog getTSLog() {
        TSLog tsLog = tsLogCollection.get();

        // 如果该线程是第一次调用本方法，就新生成并注册一个日志
        if (tsLog == null) {
            tsLog = new TSLog(Thread.currentThread().getName() + "-log.txt");
            tsLogCollection.set(tsLog);
        }

        return tsLog;
    }
}
