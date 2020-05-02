package com.lc.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ConnectionUtils {
    public static void main(String[] args) {
        String address = "http://bj.lianjia.com/ershoufang/pg1/";
        HttpURLConnection httpURLConnection;
        URL url;
        InputStream in;
        BufferedReader reader;
        StringBuffer stringBuffer;

        try {
            url = new URL(address);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            in = httpURLConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));
            stringBuffer = new StringBuffer(1000000);
            String line = "";
            while ((line = reader.readLine()) != ""){
                stringBuffer.append(line);
                System.out.println(stringBuffer.toString());
                System.gc();
            }
            reader.close();
            in.close();
            System.out.println(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
