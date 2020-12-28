package com.lc.Text;

/**
 * 递归实现fibonacci
 */
public class Fibonacci {

    public int fibona(int n) {
        if (n < 2) {
            return n;
        }
        return fibona(n - 1) + fibona(n - 2);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 10; i++) {
            System.out.println(fibonacci.fibona(i));
        }
    }


}
