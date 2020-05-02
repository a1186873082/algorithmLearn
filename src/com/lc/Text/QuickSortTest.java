package com.lc.Text;

import java.util.Stack;

public class QuickSortTest {
    //快排
    //首先选出合适的基准值，并对数组进行归并
    public int getModelInt(int[] array, int left, int right) {
        if (array[left] > array[right]) {
            swap(array, left, right);
        }
        if (array[left] > array[(left + right) / 2]) {
            swap(array, left, (left + right) / 2);
        }
        if (array[(left + right) / 2] > array[right]) {
            swap(array, (left + right) / 2, right);
        }
        int model = array[left];
        while (left < right) {
            while (left < right && array[right] > model) {
                right--;
            }
            array[left] = array[right];
            while (left < right && array[left] < model) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = model;
        return left;
    }

    public void arrayQuicksort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        getModelInt(array, left, right);
        arrayQuicksort(array, left, (left + right) / 2 - 1);
        arrayQuicksort(array, (left + right) / 2 + 1, right);
    }

    public void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] i = {4,0,1,10,5,4,3};
        new QuickSortTest().arrayQuicksort(i, 0, i.length-1);
        System.out.println(i);
        //测试循环
//        int i = 2;
//        System.out.println(i << 5);
//
//        String s = "1413";
//        int digit = Character.digit(s.charAt(1),10);
//        System.out.println("digit -> " + digit);

    }
}
