package com.lc.Text.leetCode;

import java.util.Stack;

/**
 * 直方图的水量
 * <p>
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 */
public class WaterNumberForHistogram {

    /**
     * 解法一， 动态规划
     * <p>
     * 建立从左到右最大高度和从右到左最大高度
     * <p>
     * if 1 < i < n-1; leftMax[i] = Math.max(leftMax[i-1], height[i])
     * if 0 < i < n-2; rightMax[i] = Math.max(rightMax[i+1], height[i])
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < leftMax.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[height.length];
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int count = 0;
        for (int i = 0; i < height.length; i++) {
            count += (Math.min(rightMax[i], leftMax[i]) - height[i]);
        }
        return count;
    }

    /**
     * 解法二：
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int count = 0;
        for (int i = 1; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int width = i - left - 1;
                int heig = Math.min(height[left], height[i]) - height[top];
                if (width > 0 && heig > 0) {
                    count += (width * heig);
                }
            }
            stack.push(i);
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] num = {0, 2, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] num = {2, 1, 0, 2};
        System.out.println(new WaterNumberForHistogram().trap1(num));
    }
}
