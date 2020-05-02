package com.lc.Text;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList(5);
        for (int i = 0; i < list.size(); i++) {
            list.add("123");
        }
        System.out.println(list);
        String[] s = new String[10];

        String[] s1 = list.toArray(s);
        for (String s2 : s1) {
//            System.out.printf(s2);
        }
    }
}
