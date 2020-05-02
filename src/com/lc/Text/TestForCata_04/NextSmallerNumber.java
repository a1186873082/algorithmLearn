package com.lc.Text.TestForCata_04;

import java.util.Arrays;

public class NextSmallerNumber {
    public static long nextBiggerNumber(long n)
    {
        char[] s = Long.valueOf(n).toString().toCharArray();
        boolean flag = false;
        for (int i=s.length-1;i>=0;i--) {
            int pos = -1;
            for (int j=s.length-1;j>i;j--) {
                if (s[j] > s[i] && (pos == -1 || s[j] < s[pos])) {
                    pos = j;
                }
            }
            if (pos != -1) {
                char t = s[pos];
                s[pos] = s[i];
                s[i] = t;
                Arrays.sort(s,i+1,s.length);
                flag = true;
                break;
            }
        }
        if (flag == false)
            return -1;
        else
            return Long.valueOf(new String(s)).longValue();
    }
}
