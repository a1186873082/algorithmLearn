package algorithmLearn.target.algorithmLearn_five;

import java.util.Arrays;

/**
 * 求2个大数相加所得的和
 */
public class BigNumAdd {
    /**
     * 求2个大数相加所得的和
     * 比如 235458744+41234564564651
     * 实际上就是
     * 447854532 + 15646546543214
     *
     * @param firstBigNum
     * @param secondBigNum
     */
    public void addTest(int[] firstBigNum, int[] secondBigNum) {
        //求出数组最大的大小
        int maxLength = firstBigNum.length > secondBigNum.length ? firstBigNum.length : secondBigNum.length;
        int[] maxBigNum = new int[maxLength + 1];
        int first = 0, second = 0;
        int model = 0;
        for (int i = 0; i < maxLength; i++) {
            if (firstBigNum[first] + secondBigNum[second] + model > 10) {
                model = (firstBigNum[first] + secondBigNum[second] + model) / 10;
                maxBigNum[i] = (firstBigNum[first] + secondBigNum[second] + model) % 10;
            } else {
                maxBigNum[i] = firstBigNum[first] + secondBigNum[second] + model;
            }
            if (i < firstBigNum.length - 1) {
                first++;
            }
            if (i < secondBigNum.length - 1) {
                second++;
            }
        }
        boolean flag = false;
        for (int j = maxBigNum.length - 1; j >= 0; j--) {
            if (maxBigNum[j] == 0 && !flag) {
                continue;
            }
            flag = true;
            System.out.printf("" + maxBigNum[j] + ",");
        }
    }

    //解析string字符串，并变成将字符串变为int数组,如果不是int，抛出异常
    public int[] strToIntArray(String s) {
        String[] bigNum = s.split("");
        int[] reverseNum = new int[bigNum.length + 1];
        try {
            for (int i = bigNum.length - 1, j = 0; i >= 0; i--, j++) {
                reverseNum[j] = Integer.parseInt(bigNum[i]);
            }
            reverseNum[bigNum.length] = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reverseNum;
    }

    /**
     * 字符串数字相加 算出最后结果(O(N))非最优解，再想想
     *
     * @param firstStr
     * @param secondStr
     */
    public void strNumAdd(String firstStr, String secondStr) {
        char[] firstChar = firstStr.toCharArray();
        char[] secondChar = secondStr.toCharArray();
        int maxLength = firstChar.length > secondChar.length ? firstChar.length : secondChar.length;
        char[] newFirstChar = Arrays.copyOf(firstChar, maxLength);
        char[] newSecondChar = Arrays.copyOf(secondChar, maxLength);
        int[] result = new int[maxLength];
        int model = 0;
        for (int i = maxLength - 1, j = 0; i >= 0; i--, j++) {
            int newNum = newFirstChar[i] - '0' + newSecondChar[i] - '0';
            //代表大于10，需要进位
            if (newNum + model >= 10) {
                result[j] = newNum + model - 10;
                model = 1;
            } else {
                result[j] = newNum + model;
                model = 0;
            }
        }
        for (int j = maxLength - 1; j >= 0; j--) {
            System.out.printf("" + result[j] + ",");
        }
    }

    public static void main(String[] args) {
        BigNumAdd bigNumAdd = new BigNumAdd();
        String s1 = "234";
        String s2 = "587";
//        int[] firstBigNum = bigNumAdd.strToIntArray(s1);
//        int[] secondBigNum = bigNumAdd.strToIntArray(s2);
        bigNumAdd.strNumAdd(s1, s2);
    }
}
