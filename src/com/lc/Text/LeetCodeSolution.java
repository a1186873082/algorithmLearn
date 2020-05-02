package algorithmLearn.src.com.lc.Text;

import com.sun.deploy.util.StringUtils;

public class LeetCodeSolution {
    /**
     * 整数反转-----------------------------
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long returnVal = 0;
        boolean addordel = true;
        String int2Str = String.valueOf(x);
        if (x < 0) {
            int2Str = int2Str.replace("-", "");
            addordel = false;
        }
        char[] chars = int2Str.toCharArray();
        for (int i = 0; i < int2Str.length() / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[int2Str.length() - i - 1];
            chars[int2Str.length() - i - 1] = temp;
        }
        returnVal = Long.parseLong(String.copyValueOf(chars));
        if (!addordel) {
            returnVal = -returnVal;
        }
        if (returnVal < Integer.MIN_VALUE) {
            return 0;
        } else if (returnVal > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) returnVal;
    }

    public int reverse2(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (n > Integer.MAX_VALUE || n < Integer.MIN_VALUE) ? 0 : (int) n;
    }

    /**
     * ----------------------------
     * 由 n 个连接的字符串 s 组成字符串 S，记作 S = [s,n]。例如，["abc",3]=“abcabcabc”。
     * <p>
     * 如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。
     * <p>
     * 现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 、S2=[s2,n2] 。
     * <p>
     * 请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。
     * <p>
     * 示例：
     * 输入：
     * s1 ="acb",n1 = 4
     * s2 ="ab",n2 = 2
     * 返回：
     * 2
     */
    //寻找循环体
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();
        int s2Index = 0;
        int count = 0;
        //记录第i次时，s2遍历的次数
        int[] time = new int[n1];
        //记录第i次时，s2所在位置
        int[] index = new int[n1];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < s1Char.length; j++) {
                if (s1Char[j] == s2Char[s2Index]) {
                    s2Index++;
                }
                if (s2Index == s2Char.length) {
                    count++;
                    s2Index = 0;
                }
            }
            time[i] = count;
            index[i] = s2Index;

            if (i > 0 && s2Index == index[0]) {
                //寻找循环前的次数
                int preCount = time[0];

                //寻找循环体
                int circulCount = ((n1 - 1) / i) * (time[i] - time[0]);

                //循环体后数据
                int endCount = time[(n1 - 1) % i] - time[0];
                return (preCount + circulCount + endCount) / n2;
            }
        }
        return count;
    }

    /**
     * 奇美子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int count = k;
        int returnValue = 0;
        for (int num : nums) {
            if (isjishu(num)){
                count--;
            }
            if(count == 0){
                count = k;
                returnValue++;
            }
        }
        return returnValue;
    }

    private boolean isjishu(int num) {
        if (num != 0 && num % 2 != 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        new LeetCodeSolution().numberOfSubarrays({2,2,2,1,2,2,1,2,2,2})
    }
}
