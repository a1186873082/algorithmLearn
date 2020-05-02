package com.lc.Text.TestForCata_02;

public class DirReduction {
    public static String[] dirReduc(String[] arr) {
        String s = "";
        for(int i = 0; i<arr.length; i++){
            s = s+","+arr[i];
            s = s.replace(",EAST,WEST", "")
                    .replace(",WEST,EAST", "")
                    .replace(",SOUTH,NORTH", "")
                    .replace(",NORTH,SOUTH", "");

        }

        if(s.length() == 0){
            return new String[] {};
        }else {
            return s.substring(1).split(",");
        }
    }
}
