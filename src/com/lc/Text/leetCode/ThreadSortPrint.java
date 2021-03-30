package com.lc.Text.leetCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSortPrint {

    private volatile int falg = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private Semaphore semaphoreA;
    private Semaphore semaphoreB;
    private Semaphore semaphoreC;

    public static void main(String[] args) {
        //解法一  线程池
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 1000; i++){
//            executorService.submit(() -> System.out.printf("A"));
//            executorService.submit(() -> System.out.printf("B"));
//            executorService.submit(() -> System.out.printf("C"));
//            executorService.submit(() -> System.out.println(""));
//        }
//        executorService.shutdown();
        //解法二 原子类
//        ThreadSortPrint threadSortPrint = new ThreadSortPrint();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 10; i++) {
//                    threadSortPrint.second("B");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 10; i++) {
//                    threadSortPrint.first("A");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                threadSortPrint.third("C");
//            }
//        }).start();
        // wait 和notify解决
//        ThreadSortPrint threadSortPrint = new ThreadSortPrint();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i<10;i++){
//                    threadSortPrint.second();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i<10;i++){
//                    threadSortPrint.first();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i<9;i++){
//                    threadSortPrint.third();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        // Condition
//        ThreadSortPrint threadSortPrint = new ThreadSortPrint();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i<10;i++){
//                    threadSortPrint.second();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i<10;i++){
//                    threadSortPrint.first();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i<9;i++){
//                    threadSortPrint.third();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        // semaphore
        ThreadSortPrint threadSortPrint = new ThreadSortPrint();
        threadSortPrint.semaphoreA = new Semaphore(1);
        threadSortPrint.semaphoreB = new Semaphore(1);
        threadSortPrint.semaphoreC = new Semaphore(1);
        try {
            threadSortPrint.semaphoreB.acquire();
            threadSortPrint.semaphoreC.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    threadSortPrint.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    threadSortPrint.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    threadSortPrint.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }

    /**
     * =================================================================================================================
     * 原子类解法
     * @param str
     * @throws InterruptedException
     */
//    public void first(String str) throws InterruptedException {
//        while (falg % 3 != 1) {
//
//        }
//        System.out.println(str);
//        falg++;
//    }
//
//    public void second(String str) throws InterruptedException {
//        while (falg % 3 != 2) {
//
//        }
//        System.out.println(str);
//        falg++;
//    }
//
//    public void third(String str) {
//        while (falg % 3 != 0) {
//
//        }
//        System.out.println(str);
//        falg++;
//    }

    /**
     * =================================================================================================================
     */

    /**
     * wait和notifyAll处理
     */
//    public synchronized void first() throws InterruptedException {
//        while (falg % 3 != 1) {
//            wait();
//        }
//        System.out.println("A");
//        falg = 2;
//        notifyAll();
//
//    }
//
//    public synchronized void second() throws InterruptedException {
//        while (falg % 3 != 2) {
//            wait();
//        }
//        falg = 3;
//        System.out.println("B");
//        notifyAll();
//    }
//
//    public synchronized void third() throws InterruptedException {
//        while (falg % 3 != 0) {
//            wait();
//        }
//        falg = 1;
//        System.out.println("C");
//        notifyAll();
//    }
    /**
     * =================================================================================================================
     */

    /**
     * lock, condition处理
     */
//    public void first() throws InterruptedException {
//        lock.lock();
//        try {
//            while (falg % 3 != 1) {
//                condition.await();
//            }
//            System.out.println("A");
//            falg = 2;
//            condition.signalAll();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//
//    }
//
//    public void second() throws InterruptedException {
//        lock.lock();
//        try {
//            while (falg % 3 != 2) {
//                condition.await();
//            }
//            falg = 3;
//            System.out.println("B");
//            condition.signalAll();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void third() throws InterruptedException {
//        lock.lock();
//        try {
//            while (falg % 3 != 0) {
//                condition.await();
//            }
//            falg = 1;
//            System.out.println("C");
//            condition.signalAll();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
    /**
     * =================================================================================================================
     */

    /**
     *
     */
    public void first() throws InterruptedException {
        semaphoreA.acquire();
        System.out.println("A");
        semaphoreB.release();
    }

    public void second() throws InterruptedException {
        semaphoreB.acquire();
        System.out.println("B");
        semaphoreC.release();
    }

    public void third() throws InterruptedException {
        semaphoreC.acquire();
        System.out.println("C");
        semaphoreA.release();
    }

}
