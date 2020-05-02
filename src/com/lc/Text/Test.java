package com.lc.Text;

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
