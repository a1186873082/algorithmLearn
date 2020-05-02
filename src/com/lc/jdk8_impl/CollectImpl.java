package com.lc.jdk8_impl;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectImpl<T, A, R> implements Collector {

    //建立新的结果容器
    @Override
    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<T>();
    }

    //将元素添加到结果容器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> list.add(item);
    }

    //合并2个结果容器
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    //对结果容器应用最终转换 finisher
    @Override
    public Function finisher() {
        return Function.identity();
    }

    //返回一个不可变的Characteristics集合，定义了收集器的行为
    @Override
    public Set<Characteristics> characteristics() {
        return Stream.of(Collector.Characteristics.CONCURRENT,
                Collector.Characteristics.UNORDERED,
                Collector.Characteristics.IDENTITY_FINISH).collect(Collectors.toSet());
    }
}
