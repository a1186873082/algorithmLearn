package com.lc.Text;

/**
 * 求最大公约数算法
 */
public class GreastCommonDivisor {
    //辗转相除法
    public static int getDivisor(int firstInt, int secondInt) {
        int max = firstInt >= secondInt ? firstInt : secondInt;
        int min = firstInt < secondInt ? firstInt : secondInt;
        if (max % min == 0) {
            return min;
        } else {
            return getDivisor(min, max % min);
        }
    }

    /**
     * 更相减损术
     *
     * @param firstInt
     * @param secondInt
     * @return
     */
    public static int getDivisorBySub(int firstInt, int secondInt) {
        if (firstInt == secondInt) {
            return firstInt;
        }
        int max = firstInt >= secondInt ? firstInt : secondInt;
        int min = firstInt < secondInt ? firstInt : secondInt;
        return getDivisorBySub(max - min, min);
    }

    public static void main(String[] args) {
//        System.out.println(getDivisorBySub(125, 100));
        System.out.println(6&1);
    }

}
