package com.base.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @创建人：zhangzhiqiang
 * @创建时间：2019/09/03
 * @描述：
 * @联系方式：QQ：125717901
 **/

public class ServerSocketChannel2 {
    public static void main(String[] args) {
        try {
            // 第一步
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9000));
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    System.out.println("有新的客户端进来连接");
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.read(byteBuffer) != -1) {
                        byteBuffer.flip();
                        System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer));
                        byteBuffer.clear();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}