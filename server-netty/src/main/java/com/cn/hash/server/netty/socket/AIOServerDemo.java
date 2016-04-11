package com.cn.hash.server.netty.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by 862911 on 2016/4/8.
 */
public class AIOServerDemo {
    private final String HOST;
    private final int PORT;
    private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AIOServerDemo(String host, int port) {
        HOST = host;
        PORT = port;
    }

    public void startServer() throws IOException {
        asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress(HOST,PORT));
        asynchronousServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {

            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });
    }


}
