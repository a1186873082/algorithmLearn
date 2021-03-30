package com.lc.Text.leetCode;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockQueueTest {

    private ArrayBlockingQueue<Integer> arrayBlockingQueueA = new ArrayBlockingQueue(1);
    private ArrayBlockingQueue<Integer> arrayBlockingQueueB = new ArrayBlockingQueue(1);
    private ArrayBlockingQueue<Integer> arrayBlockingQueueC = new ArrayBlockingQueue(1);

    private ArrayBlockingQueue<Integer> store = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {
//        BlockQueueTest blockQueueTest = new BlockQueueTest();
//        try {
//            blockQueueTest.arrayBlockingQueueA.put(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(() -> {
//            for (int i = 0; i< 10; i++){
//                blockQueueTest.second();
//            }
//        }).start();
//        new Thread(() -> {
//            for (int i = 0; i< 10; i++){
//                blockQueueTest.first();
//            }
//        }).start();
//        new Thread(() -> {
//            for (int i = 0; i< 10; i++){
//                blockQueueTest.third();
//            }
//        }).start();

        //生产者消费者
        BlockQueueTest blockQueueTest = new BlockQueueTest();
        new Thread(() -> {
            try {
                blockQueueTest.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                blockQueueTest.customer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void produce() throws Exception {
        for (int i = 0; i < 150; i++) {
            System.out.println("生产者生产：" + i);
            store.put(i);
        }
    }

    public void customer() throws Exception {
        while (true){
            Integer take = store.take();
            System.out.println("消费者消费:" + take);
        }

    }

    public void first() {
        try {
            arrayBlockingQueueA.take();
            System.out.println("A");
            arrayBlockingQueueB.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void second() {
        try {
            arrayBlockingQueueB.take();
            System.out.println("B");
            arrayBlockingQueueC.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void third() {
        try {
            arrayBlockingQueueC.take();
            System.out.println("C");
            arrayBlockingQueueA.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
