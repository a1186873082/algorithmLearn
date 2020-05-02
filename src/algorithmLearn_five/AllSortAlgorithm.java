package algorithmLearn.target.algorithmLearn_five;

/**
 * 求出全排列下该数字的下一个整数,,,,字典序算法
 */
public class AllSortAlgorithm {

    public int[] findNearestNumber(int[] numbers) {
        //找到临界值
        int index = findTransforPoint(numbers);
        //将临界值前一个数与逆序中最小的数进行交换
        exchangeHead(numbers, index);
        //将逆序数组顺序显示
        reverse(numbers, index);
        return numbers;
    }

    /**
     * 将逆序边界中，最小的数
     *
     * @param exchangeCopy
     * @param index
     */
    public void exchangeHead(int[] exchangeCopy, int index) {
        int head = exchangeCopy[index - 1];
        for (int i = exchangeCopy.length - 1; i > 0; i--) {
            if (exchangeCopy[i] > head) {
                exchangeCopy[index - 1] = exchangeCopy[i];
                exchangeCopy[i] = head;
                break;
            }
        }
    }

    /**
     * 将剩下的数组按顺序排列,
     *
     * @param numbersCopy
     * @param index
     */
    public void reverse(int[] numbersCopy, int index) {
        for (int i = numbersCopy.length - 1, j = index; j < i; i--, j++) {
            if (numbersCopy[j] > numbersCopy[i]) {
                int temp = numbersCopy[i];
                numbersCopy[i] = numbersCopy[j];
                numbersCopy[j] = temp;
            }
        }
    }

    /**
     * 找到逆序临界值的边缘，如果值是0，则表示全逆序，没有下一个整数
     *
     * @param numbers
     * @return
     */
    public int findTransforPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 1, 6, 8, 6, 2, 1};
        int[] returnArray = new AllSortAlgorithm().findNearestNumber(a);
        new AllSortAlgorithm().printArr(returnArray);
    }

    public void printArr(int[] arr) {
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
