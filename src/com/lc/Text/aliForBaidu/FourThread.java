package com.lc.Text.aliForBaidu;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FourThread implements Runnable {
    private static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static volatile int currentCount = 0;

    private int flag;
    private PrintFunc printFunc;

    public FourThread(int flag, PrintFunc printFunc) {
        this.flag = flag;
        this.printFunc = printFunc;
    }

    @FunctionalInterface
    interface PrintFunc {
        void print(int n);
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (currentCount < array.length && check(array[currentCount]) % 4 != flag) {
                    condition.await();
                }
                if (currentCount < array.length) {
                    printFunc.print(array[currentCount]);
                    currentCount++;
                    condition.signalAll();
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public int check(int n) {
        if (n % 15 == 0) {
            return 0;
        } else if (n % 5 == 0) {
            return 1;
        } else if (n % 3 == 0) {
            return 2;
        } else {
            return 3;
        }
    }

    public static void main(String[] args) {
        new Thread(new FourThread(0, (n) -> System.out.println("C"))).start();
        new Thread(new FourThread(1, (n) -> System.out.println("B"))).start();
        new Thread(new FourThread(2, (n) -> System.out.println("A"))).start();
        new Thread(new FourThread(3, (n) -> System.out.println(n))).start();
    }
}
