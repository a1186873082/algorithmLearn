package com.lc.Text.leetCode;

public class MaxCommonSequence {

    /**
     * 动态规划：
     *
     *
     *
     * 当text1为0或者text2为0时，返回为0，因此将边界情况都赋值为0
     *
     * if text1.length>0 && text2.length > 0
     * 当 text1[i-1] = text2[j-1],代表该值为公共子序列,此时 dp[i][j] = dp[i-1][j-1] + 1;
     * 当 text1[i-1] != text2[j-1],代表该值不为公共子序列，此时有2种情况
     *    可能1：text1[0:i-1]与text2[j]为最长公共子序列
     *    可能2：text1[0:i]与text2[0:j-1]为最长公共子序列
     * 因此 dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j])
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < text1.length() + 1; i++) {
            char char1 = text1.charAt(i - 1);
            for (int j = 1; j < text2.length() + 1; j++) {
                char char2 = text2.charAt(j - 1);
                if (char1 == char2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        String text1 = "akfuy";
        String text2 = "ayf";
        System.out.println(new MaxCommonSequence().longestCommonSubsequence(text1, text2));
    }
}
