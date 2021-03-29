package com.lc.Text.leetCode;

import java.util.concurrent.*;

public class CycleBarrieAndCountDownLatch implements Runnable {
    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    @Override
    public void run() {

        System.out.println("打印值");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("打印完毕");
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new CycleBarrieAndCountDownLatch());
        }



    }
}
