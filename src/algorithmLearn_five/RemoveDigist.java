package algorithmLearn.target.algorithmLearn_five;

import java.util.Arrays;

/**
 * 删除一个数，使剩下的数的值最小
 */
public class RemoveDigist {
    /**
     * 找出删除哪个数，使得数字最小
     *
     * @param digist
     */
    public int[] removeDigist(int[] digist) {
        for (int i = 0; i < digist.length - 1; i++) {
            if (digist[i] > digist[i + 1]) {
                //删除该数，使得该整数最小
                int[] returnArray = removeArray(digist, i);
                return returnArray;
            }
            if (i == digist.length - 1) {
                int[] returnArray = removeArray(digist, i);
                return returnArray;
            }
        }
        return digist;
    }


    /**
     * 删除数组中的某一个元素(借用System.arraycopy())
     * System.arraycopy(原数组，原数组开始下标，目标数组，目标数组开始下标，拷贝长度)
     *
     * @param array
     * @param index
     */
    public int[] removeArray(int[] array, int index) {
        int size = array.length;
        int length = array.length - index - 1;
        System.arraycopy(array, index + 1, array, index, length);
        array[--size] = 0;
        int[] temp = Arrays.copyOf(array, size);
        return temp;
    }

    /**
     * 移除k个数后，得到最小的整数
     *
     * @param array
     * @param k
     */
    public void remove(int[] array, int k) {
        for (int i = 0; i < k; i++) {
            array = this.removeDigist(array);
        }
        for (int i : array) {
            System.out.println(i);
        }
    }

    /**
     * 利用栈，存储数组
     *
     * @param array
     * @param k
     */
    public void removeDigit(int[] array, int k) {
        int[] returnArr = new int[array.length - k];
        int top = 0;
        for (int i = 0; i < array.length; i++) {
            //出栈 当栈中有值且 栈顶元素大于进栈元素时
            while (top > 0 && returnArr[top - 1] > array[i] && k > 0) {
                top -= 1;
                k -= 1;
            }
            returnArr[top++] = array[i];
        }
        if (returnArr.length == 0) {
            System.out.println(0);
        }
        boolean flag = true;
        for (int i : returnArr) {
            if (i == 0 && flag) {
                continue;
            }
            flag = false;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 1, 2, 1};
        new RemoveDigist().removeDigit(a, 2);
    }
}
