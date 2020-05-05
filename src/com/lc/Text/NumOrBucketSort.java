package com.lc.Text;

import java.util.Arrays;

public class NumOrBucketSort {

    /**
     * 计数排序
     */
    public void countSort(int[] a) {
        int min = a[0];
        int max = a[0];
        //找出最小值和最大值
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            } else if (a[i] > max) {
                max = a[i];
            }
        }
        //求出数组长度
        int d = max - min;
        int[] num = new int[d + 1];
        //统计数组值
        for (int i = 0; i < a.length; i++) {
            num[a[i] - min]++;
        }

        int index = 0;
        //只做排序，不管什么名字排哪里
        int[] sortArray = new int[a.length];
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i]; j++) {
                sortArray[index++] = i+min;
            }
        }
        System.out.println(Arrays.asList(sortArray));
    }

    public static void main(String[] args) {
        int a[] = {1421, 22, 314, 456, 51, 73, 1, 4, 21, 15, 99, 1, 2, 51, 7, -1, 789};
        new NumOrBucketSort().countSort(a);
    }
}
