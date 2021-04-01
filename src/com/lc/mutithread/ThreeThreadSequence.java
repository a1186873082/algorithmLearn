package com.lc.mutithread;

public class ThreeThreadSequence {

    public volatile int flag = 0;

    public static void main(String[] args) {
        ThreeThreadSequence threeThreadSequence = new ThreeThreadSequence();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threeThreadSequence.second("B");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threeThreadSequence.first("A");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threeThreadSequence.third("C");
            }
        }).start();
    }

    private synchronized void first(String A) {
        try {
            while (flag % 3 != 0) {
                wait();
            }
            System.out.printf(A);
            flag = 1;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void second(String B) {
        try {
            while (flag % 3 != 1) {
                wait();
            }
            System.out.printf(B);
            flag = 2;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void third(String C) {
        try {
            while (flag % 3 != 2) {
                wait();
            }
            System.out.println(C);
            flag = 0;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
