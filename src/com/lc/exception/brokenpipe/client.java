package com.lc.exception.brokenpipe;

import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket();
            socket.setSoLinger(true, 0);
            socket.connect(new InetSocketAddress("10.14.32.166", 6380));
            OutputStream os = socket.getOutputStream();
            os.write("hello".getBytes());
            TimeUnit.SECONDS.sleep(1);
            socket.close();

            int read = System.in.read();
//            if(read == 1){
//                socket.close();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
