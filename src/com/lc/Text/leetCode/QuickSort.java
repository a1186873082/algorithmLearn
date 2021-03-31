package com.lc.Text.leetCode;

public class QuickSort {

    public int baseInt(int[] num, int startIndex, int endIndex) {
        int left = startIndex;
        int right = endIndex;
        int temp = num[startIndex];
        while (left != right) {
            while (left < right && num[right] >= temp) {
                right--;
            }
            while (left < right && num[left] <= temp) {
                left++;
            }
            if (left < right) {
                int a = num[left];
                num[left] = num[right];
                num[right] = a;
            }
        }
        num[startIndex] = num[left];
        num[left] = temp;
        return left;
    }

    public void quickSort(int[] num, int startIndex, int endIndex) {
        if(startIndex > endIndex){
            return;
        }
        int baseInt = baseInt(num, startIndex, endIndex);
        quickSort(num, startIndex, baseInt - 1);
        quickSort(num, baseInt + 1, endIndex);
    }

    public static void main(String[] args) {
        int[] num = {21,551,31,1,87,54,2,4,5,7,51,66,77,88,33,44};
        new QuickSort().quickSort(num, 0, num.length-1);
        System.out.println(num);
    }
}