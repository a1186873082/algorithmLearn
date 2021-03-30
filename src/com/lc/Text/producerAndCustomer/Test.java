package com.lc.Text.producerAndCustomer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        System.out.println("开始");
        LinkedList<String> list = new LinkedList<>();
        new Thread(new Customer(list)).start();
        new Thread(new Producer(list)).start();
    }
}
