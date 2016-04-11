package com.cn.hash.server.netty.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by 862911 on 2016/4/8.
 */
public class ByteBufferDemo {
    private RandomAccessFile randomAccessFile;
    private FileChannel fileChannel;

    public ByteBufferDemo(String fileName) throws IOException {
        randomAccessFile = new RandomAccessFile(fileName, "rw");
        fileChannel = randomAccessFile.getChannel();
        int size = 0;
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while ((size = fileChannel.read(byteBuffer)) > 0) {
            //把buffer从写模式转换成读取模式
            byteBuffer.flip();
            System.out.print(charsetDecoder.decode(byteBuffer).toString());
            byteBuffer.clear();
        }
        fileChannel.close();
        randomAccessFile.close();
    }

    public static void main(String[] args) {
        try {
            new ByteBufferDemo("D:\\apache-jmeter-2.13\\bin\\jmeter.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
