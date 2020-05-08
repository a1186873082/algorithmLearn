package com.lc.Text;

import javax.xml.soap.Node;

public class Test {

    private static String v1;

    public static  String returnV1 = v1 + "124";

    public void test(){
        v1 = "124";
    }

    public static void main(String[] args) {
        new Test().test();
        System.out.println(v1);
    }
}
