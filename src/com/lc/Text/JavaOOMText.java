package com.lc.Text;

public class JavaOOMText {
    public static void main(String[] args) {
        String str2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(str2.intern()  == str2);

        String str1 = new StringBuilder().append("我").append("是").toString();
        System.out.println(str1.intern() == str1);
    }
}
