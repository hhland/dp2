package com.uhealin.dp2.thread_pre_message.a7_2;
public class Host {
    private final Helper helper = new Helper();
    public void request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");
        helper.handle(count, c);
        System.out.println("    request(" + count + ", " + c + ") END");
    }
}
