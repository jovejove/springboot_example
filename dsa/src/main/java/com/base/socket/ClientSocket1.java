package com.base.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class ClientSocket1 {

    public static void main(String[] args) {
        Socket socket;
        {
            try {
                socket = new Socket("127.0.0.1",9000);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("你好".getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
