package com.lc.Text.leetCode;

public class NumArray {
    int[] nums;

//    public NumArray(int[] nums) {
//        this.nums = nums;
//    }
//
//    public int sumRange(int i, int j) {
//        if (nums == null || nums.length <= 0) {
//            return 0;
//        }
//        int sum = 0;
//        for (int k = i; k <= j; k++) {
//            sum += nums[k];
//        }
//        return sum;
//    }

    int[] sums;

    public NumArray(int[] nums) {
        sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                sums[i] = nums[i] + sums[i - 1];
            } else {
                sums[i] = nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        return sums[j] - sums[i];
    }

    public static void main(String[] args) {
        int[] i = {-2, 0, 3, -5, 2, -1};
        System.out.println(new NumArray(i).sumRange(2, 3));
    }
}
