package com.cn.hash.server.netty.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by 862911 on 2016/4/8.
 */
public class SelectorDemo {
    private final String HOST;
    private final int PORT;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private CharsetDecoder charsetDecoder = Charset.forName("UTF-8").newDecoder();

    public SelectorDemo(String host, int port) {
        HOST = host;
        PORT = port;
    }

    public void startServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(HOST, PORT));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select(100) > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey != null && selectionKey.isAcceptable()) {
                        new Thread(new Read(((ServerSocketChannel) selectionKey.channel()).accept())).start();
                    }
                    selectionKeys.remove(selectionKey);
                }
            }
        }
    }

    public void startMultipleClients(int num) throws IOException {
        for (int i = 0; i < num; i++) {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            new Thread(new Write(socketChannel)).start();
        }
    }


    private class Read implements Runnable {
        private SocketChannel socketChannel;
        private Selector selector;

        private Read(SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);
            selector = Selector.open();
            this.socketChannel.register(selector, SelectionKey.OP_READ);
        }

        public void run() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            try {
                while (true) {
                    if (selector.select(100) > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        for (SelectionKey selectionKey : selectionKeys) {
                            if (selectionKey != null && selectionKey.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                if (socketChannel != null) {
                                    while ((socketChannel.read(byteBuffer)) > 0) {
                                        //把buffer从写模式转换成读取模式
                                        byteBuffer.flip();
                                        System.out.println("Read:" + charsetDecoder.decode(byteBuffer).toString());
                                        byteBuffer.clear();
                                    }
                                }
                            }
                            selectionKeys.remove(selectionKey);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    this.selector.close();
                    this.socketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private class Write implements Runnable {
        private SocketChannel socketChannel;
        private Selector selector;

        private Write(SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.selector = Selector.open();
            this.socketChannel.configureBlocking(false);
            this.socketChannel.register(this.selector, SelectionKey.OP_WRITE);
        }


        public void run() {

            try {
                while (true) {
                    String request = Thread.currentThread().getName() + ":" + new Date();
                    ByteBuffer byteBuffer = ByteBuffer.wrap(request.getBytes("UTF-8"));
                    if (selector.select(100) > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        for (SelectionKey selectionKey : selectionKeys) {
                            if (selectionKey != null && selectionKey.isWritable()) {
                                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                socketChannel.write(byteBuffer);
                            }
                            //必须要手动移除
                            selectionKeys.remove(selectionKey);
                        }
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                //检测到异常，需要关闭
                try {
                    this.selector.close();
                    this.socketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
//        new SelectorDemo("127.0.0.1",8899).startServer();
        new SelectorDemo("127.0.0.1", 8899).startMultipleClients(3);
    }
}
