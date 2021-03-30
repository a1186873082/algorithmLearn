package com.lc.Text.producerAndCustomer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{

    private LinkedList<String> store;
    private AtomicInteger atomicInteger = new AtomicInteger(100);

    public Producer(LinkedList<String> store){
        this.store = store;
    }

    @Override
    public void run() {
        while (true){
            synchronized (store){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(atomicInteger.intValue() == store.size()){
                    try {
                        store.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("线程"+ Thread.currentThread().getName() + "生产消息");
                    store.add("线程"+ Thread.currentThread().getName() + "生产消息");
                    store.notify();
                }
            }
        }
    }
}
