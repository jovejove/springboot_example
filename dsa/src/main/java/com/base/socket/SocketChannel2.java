package com.base.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @创建人：zhangzhiqiang
 * @创建时间：2019/09/03
 * @描述：
 * @联系方式：QQ：125717901
 **/

public class SocketChannel2 {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9000));
            socketChannel.configureBlocking(false);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(str.length());
                byteBuffer.put(str.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}