package com.cn.hash.server.netty.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by 862911 on 2016/4/8.
 */
public class ServerSocketChannelDemo {
    private final String HOST;
    private final int PORT;
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private CharsetDecoder charsetDecoder = Charset.forName("UTF-8").newDecoder();

    public ServerSocketChannelDemo(String host, int port) {
        HOST = host;
        PORT = port;
    }

    public void startServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(HOST, PORT));
        serverSocketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            SocketChannel socket = serverSocketChannel.accept();
            if (socket != null) {
                int size = 0;
                while ((size = socket.read(byteBuffer)) > 0) {
                    //把buffer从写模式转换成读取模式
                    byteBuffer.flip();
                    System.out.print(charsetDecoder.decode(byteBuffer).toString());
                    byteBuffer.clear();
                }
            }
        }
    }

    public void startClient() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(HOST, PORT));
        String request = "Hello!张镐薪";
        ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes("UTF-8"));
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
//        new ServerSocketChannelDemo("127.0.0.1", 8899).startServer();
        new ServerSocketChannelDemo("127.0.0.1",8899).startClient();
    }
}
