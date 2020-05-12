package com.lc.Text.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class MinStack {

    int minValue = Integer.MAX_VALUE;

    int lineIndex = -1;

    Integer[] minStack = null;
    LinkedList<Integer> queue = new LinkedList();

    public MinStack() {
        minStack = new Integer[10];
    }

    public void push(int x) {
        if (x <= minValue) {
            minValue = x;
            queue.add(x);
        }
        lineIndex++;
        if (lineIndex >= minStack.length) {
            Integer[] despArray = new Integer[minStack.length * 2];
            System.arraycopy(minStack, 0, despArray, 0, minStack.length);
            minStack = despArray;
        }
        minStack[lineIndex] = x;

    }

    public void pop() {
        if(minStack[lineIndex] == minValue){
            queue.removeLast();
            if(!queue.isEmpty()){
                minValue = queue.getLast();
            }else {
                minValue = Integer.MAX_VALUE;
            }
        }
        lineIndex--;
    }

    public int top() {
        return minStack[lineIndex];
    }

    public int getMin() {
        return minValue;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.top();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.push(2147483647);
        minStack.top();
        minStack.push(-2147483648);
        minStack.pop();
        System.out.println(minStack.getMin());

//        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
