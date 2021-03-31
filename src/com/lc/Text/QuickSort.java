package com.lc.Text;

import java.util.HashMap;
import java.util.Stack;

public class QuickSort {

    public void sort(int[] a) {
        int startIndex = 0;
        int endIndex = a.length - 1;

        fenzhi(a, startIndex, endIndex);
    }

    public void fenzhi(int[] a, int startIndex, int endIndex) {

        if (startIndex > endIndex) {
            return;
        }
        //取第一个值为基点
        int base = a[startIndex];
        int jiaohuan = fenqu(a, base, startIndex + 1, endIndex);
        if (jiaohuan != startIndex) {
            temp(a, startIndex, jiaohuan);
        }
        fenzhi(a, startIndex, jiaohuan - 1);
        fenzhi(a, jiaohuan + 1, endIndex);

    }

    public int fenqu(int[] a, int base, int i, int j) {
        //从基点左边找到是否有数比基点大，有则退出循环，并记录该数下标
        while (i <= j) {
            if (a[i] > base) {
                break;
            }
            i++;
        }
        //证明在j+1前的数都比基点小
        if (i == j + 1) {
            return j;
        }
        //如果找到左边数比右边大的时候，开始从右往左找，，找寻长度为i+1
        while (j >= i + 1) {
            if (a[j] < base) {
                break;
            }
            j--;
        }

        if (i < j) {
            temp(a, i, j);
        } else {
            return i - 1;
        }
        return fenqu(a, base, i + 1, j - 1);
    }

    public void temp(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int a[] = {1421, 22, 314, 456, 51, 73, 1, 4, 21, 15, 99, 1, 2, 51, 7, -1, 789};
        new QuickSort().twoChangeSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }


    /**
     * 利用两头交换法实现快排
     */

    public void twoChangeSort(int[] a) {
        //递归
        sortNow(0, a.length - 1, a);
        //非递归
//        stackSortNow(0, a.length-1, a);
    }

    public void sortNow(int startIndex, int endIndex, int[] a) {
        //递归退出的条件   当左下标大于等于右下标，，证明排序完成
        if (startIndex >= endIndex) {
            return;
        }
        //去最开始的数为基准数
        int quite = change(a[0],startIndex, endIndex, a);
        sortNow(startIndex, quite - 1, a);
        sortNow(quite + 1, endIndex, a);
    }

    public void stackSortNow(int startIndex, int endIndex, int[] a) {
        Stack<HashMap<String, Integer>> stack = new Stack<>();
        HashMap<String, Integer> params = new HashMap<>();
        params.put("startIndex", startIndex);
        params.put("endIndex", endIndex);
        stack.push(params);
        while (!stack.isEmpty()) {
            HashMap<String, Integer> map = stack.pop();
            int staIndex = map.get("startIndex");
            int eIndex = map.get("endIndex");
            int periov = change1(staIndex, eIndex, a);
            if (staIndex < periov - 1) {
                HashMap<String, Integer> newMap = new HashMap<>();
                newMap.put("startIndex", startIndex);
                newMap.put("endIndex", periov - 1);
                stack.push(newMap);
            }
            if (endIndex > periov + 1) {
                HashMap<String, Integer> newMap = new HashMap<>();
                newMap.put("startIndex", periov + 1);
                newMap.put("endIndex", endIndex);
                stack.push(newMap);
            }
        }


    }

    /**
     * 双边循环法
     * 1.选取第一个数作为基准数，定义left指针指向第一个数，right指针指向最后一个数
     * 2.right指针开始，如果大于等于基准数，则right--，否则停止移动换成left走动
     * 3.left指针开始，如果小于等于基准数，则left--，否则停止移动，left和right进行交换后，然后继续进行
     *
     * @param base
     * @param startIndex
     * @param endIndex
     * @param a
     * @return
     */
    public int change(int base, int startIndex, int endIndex, int[] a) {
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            while (left < right && a[right] >= base) {
                right--;
            }

            while (left < right && a[left] <= base) {
                left++;
            }

            temp(a, right, left);
        }
        temp(a, startIndex, left);
        return left;
    }

    /**
     * 单边循环法
     * 1.选取第一个数为基准数，找出比第一个数小的数放在左边，找出比第一个数大的数放在右边
     * 2.当循环完毕时，将第一个数与最后mark指针指向的数调换位置
     *
     * @param startIndex
     * @param endIndex
     * @param a
     * @return
     */
    public int change1(int startIndex, int endIndex, int[] a) {
        int base = a[startIndex];
        int mark = startIndex;
        for (int i = startIndex; i <= endIndex; i++) {
            if (a[i] < base) {
                mark++;
                temp(a, mark, i);
            }
        }
        temp(a, mark, startIndex);
        return mark;
    }

}
