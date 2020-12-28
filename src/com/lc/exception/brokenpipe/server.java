package com.lc.exception.brokenpipe;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(6379);
            Socket s = serverSocket.accept();
            InputStream is = s.getInputStream();
            byte[] buf = new byte[1024];
            int length = is.read(buf);
            System.out.println("receive" + new String(buf, 0, length));
            TimeUnit.SECONDS.sleep(10L);

            s.getOutputStream().write("hello".getBytes());
            System.out.println("send over");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
