package com.lc.Text.minHeap;

public class MinHeap {
    private int[] minHeapValues = {};

    int size = 0;

    public MinHeap(int x) {
        minHeapValues = new int[x];
    }

    //将堆顶数据移除,将最后一个数据提到堆顶
    private void poll() {
        if (minHeapValues.length > 0) {
            int minValue = minHeapValues[0];
            minHeapValues[0] = minHeapValues[--size];
            minHeapValues[size] = 0;
            //下沉
            fixDown();
        }
    }

    //上浮
    private void fixUp() {
        //拿着最后一位进行与父节点比较

    }

    //下沉
    private void fixDown() {
        int i = 0;
        int swapIndex = 0;
        while (2 * i + 1 < minHeapValues.length) {
            if (2 * i + 2 < minHeapValues.length) {
                swapIndex = minHeapValues[2 * i + 1] < minHeapValues[2 * i + 2] ? 2 * i + 1 : 2 * i + 2;
            } else {
                swapIndex = 2 * i + 1;
            }

            if (minHeapValues[i] > minHeapValues[swapIndex]) {
                int temp = minHeapValues[i];
                minHeapValues[i] = minHeapValues[swapIndex];
                minHeapValues[swapIndex] = temp;
            }
            i = swapIndex;
        }
    }
}
