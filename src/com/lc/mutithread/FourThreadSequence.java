package com.lc.mutithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FourThreadSequence implements Runnable {
    public int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int flag = 0;
    static volatile int currentNumber = 0;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private PrintFunc printFunc;

    public FourThreadSequence(int flag, PrintFunc printFunc) {
        this.flag = flag;
        this.printFunc = printFunc;
    }

    @FunctionalInterface
    interface PrintFunc {
        void print(int N);
    }

    public int getTrueFlag(int n) {
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

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (currentNumber < numbers.length && getTrueFlag(numbers[currentNumber]) != flag) {
//                    printFunc.print(numbers[currentNumber]);
//                    System.out.println("正在等待");
                    condition.await();
                }
//                printFunc.print(numbers[currentNumber]);
                if (currentNumber < numbers.length) {
                    printFunc.print(numbers[currentNumber]);
                    currentNumber++;
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

    public static void main(String[] args) {
        new Thread(new FourThreadSequence(0, (n) -> System.out.printf("A"))).start();
        new Thread(new FourThreadSequence(1, (n) -> System.out.printf("B"))).start();
        new Thread(new FourThreadSequence(2, (n) -> System.out.printf("C"))).start();
        new Thread(new FourThreadSequence(3, (n) -> System.out.printf(""+n))).start();
    }
}
