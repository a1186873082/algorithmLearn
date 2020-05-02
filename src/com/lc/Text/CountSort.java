package com.lc.Text;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountSort {
    //普通计数排序
    public void countSort(int[] a) {
        //1.求出数组中最大的元素
        int max = 0;
        for (int i : a) {
            if (max < i) {
                max = i;
            }
        }
        //定义数组
        int[] tempArray = new int[max + 1 + 2];
        //将值填充到数组中
        for (int i : a) {
            tempArray[i + 2]++;
        }
        //
        for (int i = 0; i < tempArray.length; i++) {
            if (tempArray[i] > 0) {
                for (int j = 0; j < tempArray[i]; j++) {
                    System.out.println(i - 2);
                }
            }

        }
    }

    //优化后的计数排序
    public void betterCountSort(int[] a) {
        int max = 0;
        int min = 0;
        //求出最大值，最小值
        for (int i : a) {
            if (max < i) {
                max = i;
            }
            if (min > i) {
                min = i;
            }
        }
        int sub = max - min;
        //创建容器
        int[] tempArray = new int[sub + 1];
        //往模板容器中填充值
        for (int i = 0; i < a.length; i++) {
            tempArray[a[i] - sub + 1]++;
        }
        //将模板容器变形成后面元素等于前面之和
        for (int i = 1; i < tempArray.length; i++) {
            tempArray[i] += tempArray[i - 1];
        }
        //输出原数组
        int[] sortArray = new int[a.length - 1];
        for (int i = a.length - 1; i >= 0; i--) {
            sortArray[tempArray[i - min] - 1] = a[i];
            tempArray[i-min]--;
        }
        System.out.println(Arrays.toString(sortArray));
    }


    public static void main(String[] args) {
        int a[] = {1421, 22, 314, 456, 51, 73, 1, 4, 21, 15, 99, 1, 2, 51, 7, -1};
        new CountSort().countSort(a);


    }
}
