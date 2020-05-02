package com.lc.Text;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class RomanNumeralsEncoder {
    public String solution(int n) {
        StringBuffer s = new StringBuffer();
        int thousand,hundred,ten,one;
        thousand = n%10000 / 1000;
        hundred = n%1000/100;
        ten = n%100/10;
        one = n%10/1;
        int[] a = new int[4];
        int num = 10;
        int j = 1;
        for(int i = 0; i < a.length; i++){
            a[i] = n%num/j;
            num *= 10;
            i *=10;
        }
        if(n<0){
            return "";
        }
        if (thousand !=0 && thousand <= 3){
            for (int i = 1; i <=thousand ; i++) {
                s.append("M");
            }
        }

        if (hundred != 0){
            if(0 < hundred && hundred <=3){
                for (int i = 1 ; i <= hundred;i++){
                    s.append("C");
                }
            }
            else if (3 < hundred && hundred < 5){
                for (int i = 4 ; i<= hundred ; i++){
                    s.append("C");
                }
                s.append("D");
            }
            else if (hundred == 5){
                s.append("D");
            }
            else if (5 < hundred && hundred <= 8){
                s.append("D");
                for (int i = 6 ; i<= hundred ; i++){
                    s.append("C");
                }
            }
            else {
                for (int i = 9; i <= hundred; i++) {
                    s.append("C");
                }
                s.append("M");
            }
        }

        if (ten != 0){
            if(0 < ten && ten <=3){
                for (int i = 1 ; i <= ten;i++){
                    s.append("X");
                }
            }
            else if (3 < ten && ten < 5){
                for (int i = 4 ; i<= ten ; i++){
                    s.append("X");
                }
                s.append("L");
            }
            else if (ten == 5){
                s.append("L");
            }
            else if (5 < ten && ten <= 8){
                s.append("L");
                for (int i = 6 ; i<= ten ; i++){
                    s.append("X");
                }
            }
            else {
                for (int i = 9; i <= ten; i++) {
                    s.append("X");
                }
                s.append("C");
            }
        }

        if (one != 0){
            if(0 < one && one <=3){
                for (int i = 1 ; i <= one;i++){
                    s.append("I");
                }
            }
            else if (3 < one && one < 5){
                for (int i = 4 ; i<= one ; i++){
                    s.append("I");
                }
                s.append("V");
            }
            else if (one == 5){
                s.append("V");
            }
            else if (5 < one && one <= 8){
                s.append("V");
                for (int i = 6 ; i<= one ; i++){
                    s.append("I");
                }
            }
            else {
                for (int i = 9; i <= one; i++) {
                    s.append("I");
                }
                s.append("X");
            }
        }
        return s.toString();
    }

    public static void main(String[] args) {

//        System.out.println(new RomanNumeralsEncoder().solution(3334));
        int n = 333334;
        int thousand,hundred,ten,one;
        thousand = n%10000 / 1000;
        hundred = n%1000/100;
        ten = n%100/10;
        one = n%10/1;
        System.out.println(""+thousand+","+hundred+","+ten+","+one);
        int[] a = new int[6];
        int j = 1;
        for(int i = 0; i < a.length; i++){
            a[i] = n%(j*10)/j;
            j *=10;
        }

        for(Integer f : a){
            System.out.println(f);
        }
    }
}
