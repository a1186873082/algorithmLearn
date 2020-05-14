package com.lc.Text.minHeap;

public class MinHeap {
    private Integer[] minHeapValues = {};

    int size = 0;

    public MinHeap(int x) {
        minHeapValues = new Integer[x];
    }

    //将堆顶数据移除,将最后一个数据提到堆顶
    private void poll() {
        if (minHeapValues.length > 0) {
            int minValue = minHeapValues[0];
            minHeapValues[0] = minHeapValues[--size];
            minHeapValues[size] = null;
            //下沉
            fixDown();
        }
    }

    private void push(int e) {
        minHeapValues[size++] = e;
        fixUp();
    }

    private int peek() {
        if (size >= 1) {
            return minHeapValues[0];
        }
        throw new NullPointerException("空堆");
    }

    //上浮
    private void fixUp() {
        //拿着最后一位进行与父节点比较
        int i = size - 1;
        int parent = (i - 1) / 2;
        while (i > 0 && minHeapValues[i] < minHeapValues[parent]) {
            //判断当前结点属于什么结点
            int temp = minHeapValues[i];
            minHeapValues[i] = minHeapValues[parent];
            minHeapValues[parent] = temp;
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    //下沉
    private void fixDown() {
        int parent = 0;
        int child = 0;
        if (2 * parent + 2 < size) {
            child = minHeapValues[2 * parent + 1] < minHeapValues[2 * parent + 2]
                    ? 2 * parent + 1 : 2 * parent + 2;
        } else {
            child = 2 * parent + 1;
        }
        while (child < size && minHeapValues[parent] > minHeapValues[child]) {
            int temp = minHeapValues[child];
            minHeapValues[child] = minHeapValues[parent];
            minHeapValues[parent] = temp;
            parent = child;
            if (2 * parent + 2 < size) {
                child = minHeapValues[2 * parent + 1] < minHeapValues[2 * parent + 2]
                        ? 2 * parent + 1 : 2 * parent + 2;
            } else {
                child = 2 * parent + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] test = {6, 4, 200, 1, 5, 7, 8, 10, 665, 23, 123};

        MinHeap minHeap = new MinHeap(5);
        minHeap.push(6);
        minHeap.push(4);
        minHeap.push(200);
        minHeap.push(1);
        minHeap.push(5);
        for (int i = 3; i < test.length; i++) {
            if (test[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.push(test[i]);
            }
        }
        System.out.println(minHeap);
    }
}
