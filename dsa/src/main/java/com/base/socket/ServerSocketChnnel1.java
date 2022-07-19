package com.base.socket;

import java.io.IOException;

import java.net.InetSocketAddress;

import java.nio.ByteBuffer;

import java.nio.channels.ServerSocketChannel;

import java.nio.channels.SocketChannel;

import java.nio.charset.Charset;

public class ServerSocketChnnel1 {

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9000));
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                while (socketChannel != null) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int i = socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
