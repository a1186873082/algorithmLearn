package com.lc.jdk8_impl;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 自定义收集器（用作质数和非质数的分区）
 */
public class ParallelStreamTest {
    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    public static void main(String[] args) {
        int n = 50;
        System.out.println(new ParallelStreamTest().partitionPrimesWithCustomCollector(n));
    }
}
