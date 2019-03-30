package com.uhealin.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {
    // 定义监听端口
    public static final int PORT = 6488;

    
    
    public static void main(String[] args) throws IOException {
        // 创建服务端监控线程
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        // 设置日志记录器
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        // 设置编码过滤器
        acceptor.getFilterChain().addLast(
            "codec",
            new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        // 指定业务逻辑处理器
        acceptor.setHandler(new TimeServerHandler());
        // 设置端口号
        acceptor.bind(new InetSocketAddress(PORT));
        // 启动监听线程
        acceptor.bind();
    }
    
    
    
    
    public static class TimeServerHandler extends IoHandlerAdapter {

        /**
         * 连接创建事件
         */
        @Override
        public void sessionCreated(IoSession session){
            // 显示客户端的ip和端口
            System.out.println(session.getRemoteAddress().toString());
        }
        
        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            cause.printStackTrace();
        }
        
        /**
         * 消息接收事件
         */
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            String strMsg = message.toString();
            if (strMsg.trim().equalsIgnoreCase("quit")) {
                session.close(true);
                return;
            }
            // 返回消息字符串
            session.write("Hi Client!");
            // 打印客户端传来的消息内容
            System.out.println("Message written : " + strMsg);
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
            System.out.println("IDLE" + session.getIdleCount(status));
        }
        
    }
}
