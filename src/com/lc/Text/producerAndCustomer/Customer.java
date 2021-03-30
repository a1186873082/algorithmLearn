package com.lc.Text.producerAndCustomer;

import java.util.LinkedList;
import java.util.List;

public class Customer implements Runnable {

    private LinkedList<String> store;

    public Customer(LinkedList<String> store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (store) {
                if (store.isEmpty()) {
                    try {
                        store.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("消费一条");
                    store.remove(0);
                }
                store.notify();
            }
        }
    }
}
