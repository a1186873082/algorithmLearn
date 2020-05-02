package com.lc.jdk8_impl;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class PrimeNumbersCollector
        implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    /**
     * 从一个有2个空list开始收集
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    /**
     * 将元素添加到容器中
     *
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    /**
     * 将累加器并行工作
     *
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> a, Map<Boolean, List<Integer>> b) -> {
            a.get(true).addAll(b.get(true));
            a.get(false).addAll(b.get(false));
            return a;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    /**
     * 判断质数是否大于被测数的平方根
     *
     * @param list
     * @param predicate
     * @param <A>
     * @return
     */
    public static <A> List<A> taskWhile(List<A> list, Predicate<A> predicate) {
        int i = 0;
        for (A a : list) {
            if (!predicate.test(a)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    /**
     * @param primes
     * @param candicate
     * @return
     */
    public static boolean isPrime(List<Integer> primes, int candicate) {
        double candicateRoot = Math.sqrt(Double.valueOf(candicate));
        return taskWhile(primes, (i) -> i <= candicateRoot).stream().noneMatch(p -> candicate % p == 0);
    }

}
