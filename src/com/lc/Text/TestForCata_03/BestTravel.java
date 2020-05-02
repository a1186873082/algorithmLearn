package com.lc.Text.TestForCata_03;

import java.util.ArrayList;
import java.util.List;

public class BestTravel {

    public static String remove(String str) {
            // your code

            return str.substring(1,(str.length()-1));
        }

    public static void main(String[] args) {
        String str = "eloquent";
        System.out.println(remove(str));
    }
}
