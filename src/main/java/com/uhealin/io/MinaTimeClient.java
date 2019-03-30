package com.uhealin.io;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaTimeClient {

	
	public static void main(String[] args){
        // 创建客户端连接器.
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", 
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        
        // 设置连接超时检查时间
        connector.setConnectTimeoutCheckInterval(30);
        connector.setHandler(new TimeClientHandler());
        
        // 建立连接
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", MinaTimeServer.PORT));
        // 等待连接创建完成
        cf.awaitUninterruptibly();
        
        IoSession ioSession=cf.getSession();
        ioSession.write("Hi Server!");
        ioSession.write("quit");
        
        // 等待连接断开
        ioSession.getCloseFuture().awaitUninterruptibly();
        // 释放连接
        connector.dispose();
    }
	
	
	public static class TimeClientHandler extends IoHandlerAdapter {

	    public void messageReceived(IoSession session, Object message) throws Exception {
	        String content = message.toString();
	        System.out.println("client receive a message is : " + content);
	    }

	    public void messageSent(IoSession session, Object message) throws Exception {
	        System.out.println("messageSent -> ：" + message);
	    }
	    
	}
	
}
