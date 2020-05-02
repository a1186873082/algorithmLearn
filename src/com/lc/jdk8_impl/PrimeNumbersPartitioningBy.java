package com.lc.jdk8_impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * 用已有收集器进行分区
 */
public class PrimeNumbersPartitioningBy {
    //判断是否为质数
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int)Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(p -> candidate % p == 0);
    }


    public static void main(String[] args) {
        int n = 100;
        System.out.println(isPrime(2));
        //根据是否为质数进行分区
        Map<Boolean, List<Integer>> map =  IntStream.rangeClosed(2, n).boxed().collect(
                partitioningBy(candidate -> isPrime(candidate))
        );
        System.out.println(map);
    }

}
