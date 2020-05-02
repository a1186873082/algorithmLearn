package com.lc.Text;

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

    /**
     * 国王与金矿第一种解法
     */
    public static int getAnswer(int w, int[] g, int[] p) {
        int[] preResults = new int[p.length];
        int[] results = new int[w + 1];

        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 0; j--) {
                if (j >= p[i - 1]) {
                    results[j] = Math.max(results[j], results[j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        System.out.println(results);
        return results[w];
    }

    /**
     * 国王与金矿第二种解法
     */
    public static int getAnswer2(int n, int w, int[] g, int[] p) {
        int[] preResults = new int[w + 1];
        int[] results = new int[w + 1];
        //填充边界格子
        for (int i = 0; i <= w; i++) {
            if (i < p[0]) {
                preResults[i] = 0;
            } else {
                preResults[i] = g[0];
            }
        }
        //填充其余格子
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i]) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j - p[i]] + g[i]);
                }
            }
//            preResults = results;
            for (int j = 0; j <= w; j++) {
                preResults[j] = results[j];
            }
        }
        return results[w];
    }

    /**
     * 国王与金矿的第三种解法
     */
    public static int getAnswer3(int n, int w, int[] g, int[] p) {
        if (w == 0 || n == 0) {
            return 0;
        }
        if (w >= p[n - 1]) {
            return Math.max(getAnswer3(n - 1, w, g, p), getAnswer3(n - 1, w - p[n - 1], g, p) + g[n - 1]);
        } else {
            return getAnswer3(n - 1, w, g, p);
        }

    }


    /**
     * ===============================================================
     * 由 n 个连接的字符串 s 组成字符串 S，记作 S = [s,n]。例如，["abc",3]=“abcabcabc”。
     *
     * 如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。
     *
     * 现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 、S2=[s2,n2] 。
     *
     * 请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。
     *
     */
    public int getMaxRepetitions3(String s1, int n1, String s2, int n2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();
        int s2Index = 0;
        int count = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < s1Chars.length; j++) {
                if (s1Chars[j] == s2chars[s2Index]) {
                    s2Index++;
                }
                if (s2Index == s2chars.length) {
                    count++;
                    s2Index = 0;
                }
            }
        }
        return count / n2;
    }

    /**
     * 寻找循环体解法
     *
     */
    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();
        int s2Index = 0;
        int count = 0;
        //s1循环i次时，s2的循环次数
        int[] s2Time = new int[n1];
        //s1循环i次时，S2的下标
        int[] s2Next = new int[n1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < s1Chars.length; j++) {
                if (s1Chars[j] == s2chars[s2Index]) {
                    s2Index++;
                }
                if (s2Index == s2chars.length) {
                    count++;
                    s2Index = 0;
                }

            }
            s2Time[i] = count;
            s2Next[i] = s2Index;

            if (i > 0 && s2Index == s2Next[0]) {
                //循环体开头
                int headCount = s2Time[0];

                //中间循环部分
                int circullateCount = ((n1 - 1) / i) * (s2Time[i] - s2Time[0]);

                int endCount = s2Time[(n1 - 1) % i] - s2Time[0];

                return (headCount + circullateCount + endCount) / n2;
            }

        }
        return count / n2;
    }

    /**
     * ========================================================================
     * 岛屿数量
     * <p>
     * 分析，遇到0，则代表遇到水，则第下一格到减1必须为0，否则重新算岛屿
     * <p>
     * 深度搜索
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    area(grid, j, i);
                    count++;
                }
            }
        }
        return count;
    }

    public void area(char[][] grid, int x, int y) {
        if (!inArea(grid, x, y)) {
            return;
        }
        if (grid[y][x] == '0') {
            return;
        }
        grid[y][x] = '0';
        area(grid, x + 1, y);
        area(grid, x, y + 1);
        area(grid, x - 1, y);
        area(grid, x, y - 1);
    }

    //判断是否在区域内
    public boolean inArea(char[][] grid, int x, int y) {
        if (grid.length == 0 || grid[0].length == 0) {
            return false;
        } else if (y >= grid.length || x >= grid[0].length) {
            return false;
        } else if (y < 0 || x < 0) {
            return false;
        } else {
            return true;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 链表合并
     *
     * @param lists
     * @return
     */

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode returnNode = new ListNode(0);
        ListNode tempNode = returnNode;
        while (true) {
            ListNode preNode = null;
            int flag = -1;
            for (int k = 0; k < lists.length; k++) {
                if (lists[k] == null) {
                    continue;
                }
                if (preNode == null || lists[k].val < preNode.val) {
                    preNode = lists[k];
                    flag = k;
                }
            }
            if (flag == -1) {
                break;
            }
            tempNode.next = preNode;
            tempNode = tempNode.next;
            lists[flag] = lists[flag].next;
        }
        return returnNode.next;
    }

    /**
     * 求出字符串中的最大回文子串
     * 中心往两边扩展法进行处理
     *
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        //回文子串的最大长度
        int maxCount = 1;
        //记录回文子串的开始
        int startIndex = 0;
        char[] sChars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 1; i - j >= 0 & j + i < n; j++) {
                if (sChars[i - j] != sChars[i + j]) {
                    break;
                } else {
                    if (2 * j + 1 > maxCount) {
                        maxCount = 2 * j + 1;
                        startIndex = i - j;
                    }
                }
            }
            for (int j = 0; i - j >= 0 & i + j + 1 < n; j++) {
                if (sChars[i - j] != sChars[i + j + 1]) {
                    break;
                } else {
                    if (2 * j + 2 > maxCount) {
                        maxCount = 2 * j + 2;
                        startIndex = i - j;
                    }
                }
            }
        }
        return s.substring(startIndex, startIndex + maxCount);
    }

    /**
     * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
     * 输入: "aacecaaa"
     * 输出: "aaacecaaa"
     */
    /**
     * 暴力法
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        String appendStr = "";
        char[] chars = s.toCharArray();
        for (int n = s.length() - 1; n >= 0; n--) {
            if (isSelf(s.substring(0, n + 1))) {
                return appendStr + s;
            } else {
                appendStr = appendStr + chars[n];
            }
        }
        return appendStr + s;
    }

    public boolean isSelf(String s) {
        char[] ss = s.toCharArray();
        for (int i = 0; i < s.length() / 2; i++) {
            if (ss[i] != ss[s.length() - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 双指针法
     */
    public String shortestPalindrome1(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (j >= 0) {
            if (s.charAt(i) != s.charAt(j)) {
                j--;
            } else {
                i++;
                j--;
            }
        }
        if(i == s.length()){
            return s;
        }
        return new StringBuffer(s.substring(i)).reverse() + shortestPalindrome(s.substring(0, i)) + s.substring(i);
    }


    public static void main(String[] args) {
//        new LeetCodeSolution().numberOfSubarrays({2,2,2,1,2,2,1,2,2,2})
    }
}
