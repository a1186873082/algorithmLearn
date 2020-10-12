package com.lc.mutithread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TestConcurrentHashMap {
    public static void main(String[] args) {
        //TODO 想法有问题，后续继续
        Map<String, String> map = new ConcurrentHashMap<>(10);
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");

        new Thread(() -> map.forEach((k, v) -> {
            System.out.println(k);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();

        new Thread(() -> {
            for (int i = 11; i < 14; i++) {
                map.put(String.valueOf(i), String.valueOf(i));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
