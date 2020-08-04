package com.lc.Text.leetCode;

import com.lc.Text.NiuKeSolution;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

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
        List<Integer> cnt = new ArrayList<>();
        int count = 0;
        int returnValue = 0;
        for (int i = 0; i < nums.length; i++) {
            if (isjishu(nums[i])) {
                cnt.add(count + 1);
                count = 0;
            } else {
                count++;
            }
        }

        cnt.add(count + 1);
        for (int i = 0; i < cnt.size() - k; i++) {
            returnValue += cnt.get(i) * cnt.get(i + k);
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
     * <p>
     * 如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。
     * <p>
     * 现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 、S2=[s2,n2] 。
     * <p>
     * 请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。
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
        if (i == s.length()) {
            return s;
        }
        return new StringBuffer(s.substring(i)).reverse() + shortestPalindrome(s.substring(0, i)) + s.substring(i);
    }

    /**
     * Definition for a binary tree node.
     * 验证二叉搜索树
     * <p>
     * 采用中序遍历验证，因为中序遍历 为 左中右，二叉搜索树必须为左<中<右
     */
//    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//        TreeNode(int x) { val = x; }
//    }
    long pre = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }


    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(Integer val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (judgeSubtree(s, t)) {
            return true;
        } else {
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }

    public boolean judgeSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val == t.val) {
            if (judgeSubtree(s.left, t.left)) {
                return judgeSubtree(s.right, t.right);
            }
            return false;
        }
        return false;

    }

//    public TreeNode createNode(LinkedList<Integer> nodeList) {
//        if (nodeList == null || nodeList.size() == 0) {
//            return null;
//        }
//        Integer data = nodeList.removeFirst();
//        TreeNode treeNode = null;
//        if (data != null) {
//            treeNode = new TreeNode(data);
//            treeNode.left = createNode(nodeList);
//            treeNode.right = createNode(nodeList);
//        }
//        return treeNode;
//    }

    //按层级从上到下转换为树
    public TreeNode createNode(LinkedList<Integer> nodeList) {
        TreeNode p = new TreeNode(nodeList.get(0));
        TreeNode returnNode = p;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int i = 0;
        while (p != null) {
            if (2 * i + 1 < nodeList.size()) {
                p.left = new TreeNode(nodeList.get(2 * i + 1));
                queue.add(p.left);
            }
            if (2 * i + 2 < nodeList.size()) {
                p.right = new TreeNode(nodeList.get(2 * i + 2));
                queue.add(p.right);
            }
            p = queue.poll();
            i++;
        }
        return returnNode;
    }

    /**
     * 暴力法
     * <p>
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double returnValue = x;
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        boolean flag = n > 0 ? true : false;
        if (!flag) {
            n = -n;
        }
        for (int i = 1; i < n; i++) {
            returnValue = returnValue * x;
        }
        if (!flag) {
            returnValue = 1 / returnValue;
        }
        return returnValue;
    }

    /**
     * 求出最大的基准数(比如2的6次方=4的3次方,   3的4次方=9的平方)
     */
    public double myPow1(double x, long n) {
        double jishu = x;
        double ans = 1.0;
        while (n > 0) {
            if (n % 2 == 1) {
                ans *= jishu;
            }
            jishu *= jishu;
            n /= 2;
        }
        return ans;
    }

    /**
     * 分治递归
     * x3 = y2*x
     */
    public double myPow2(double x, int n) {
        long N = n;
        return N > 0 ? myPow1(x, N) : 1 / myPow1(x, -N);
    }

    public double quick(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = quick(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> returnList = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> liceng = new ArrayList<>();
            int count = queue.size();
            while (count > 0) {
                root = queue.peek();
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                count--;
                queue.removeFirst();
                liceng.add(root.val);
            }
            returnList.add(liceng);
        }
        return returnList;
    }


    /**
     * 层次顺序创建二叉树
     */
    public static TreeNode levelCreateTreeNode(LinkedList<Integer> inputList) {
        TreeNode treeNode = new TreeNode(inputList.get(0));
        TreeNode returnNode = treeNode;
        Queue<TreeNode> queue = new LinkedList();
        int i = 0;
        while (treeNode != null) {
            if ((2 * i + 1) < inputList.size()) {
                treeNode.left = new TreeNode(inputList.get(2 * i + 1));
                queue.add(treeNode.left);
            }

            if ((2 * i + 2) < inputList.size()) {
                treeNode.right = new TreeNode(inputList.get(2 * i + 2));
                queue.add(treeNode.right);
            }

            treeNode = queue.poll();
            i++;
        }
        return returnNode;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        for (int num = 0; num < nums.length; num++) {
            int count = 0;
            x:
            for (int j = 0; j < nums.length; j++) {
                if (num != j && nums[num] == nums[j]) {
                    count++;
                }
                if (count > 0) {
                    break x;
                }
            }
            if (count == 0) {
                return nums[num];
            }
        }
        return -1;
    }

    /**
     * 采取异或特性解决
     * 0^n=n
     * n^n=0
     *
     * @param nums
     * @return
     */
    public int singleNumber1(int[] nums) {
        int returnValue = 0;
        for (int num : nums) {
            returnValue ^= num;
        }
        return returnValue;
    }

    /**
     * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
     * <p>
     * 解題思路：
     */
    public int findTheLongestSubstring(String s) {
        int returnValue = 0, status = 0;
        //此时状态值可能有以下几种情况，[00000]-[11111],总共有1^5种情况
        int[] temp = new int[1 << 5];
        Arrays.fill(temp, -1);
        //最開始的狀態表狀態為：00000
        temp[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            char car = s.charAt(i);
            switch (car) {
                case 'a':
                    status ^= (1 << 0);
                    break;
                case 'e':
                    status ^= (1 << 1);
                    break;
                case 'i':
                    status ^= (1 << 2);
                    break;
                case 'o':
                    status ^= (1 << 3);
                    break;
                case 'u':
                    status ^= (1 << 4);
                    break;
                default:
                    break;
            }
            //已经有状态值了，此时当前状态值到前一次状态值之间的值肯定为出现偶次数的元音字符
            //比如[00001] - 下一次[00001]，中间出现的元音字符肯定为偶次出现的
            if (temp[status] >= 0) {
                returnValue = Math.max(returnValue, i + 1 - temp[status]);
            } else {
                //第一次设置状态值
                temp[status] = i + 1;
            }
        }
        return returnValue;
    }

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int root = preorder[0];
        int i = 0;
        for (i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return myTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode myTree(int[] preorder, int[] inorder, int pre_left, int pre_right, int in_left, int in_right) {
        if (pre_left > pre_right) {
            return null;
        }

        int rootValue = preorder[pre_left];
        TreeNode rootNode = new TreeNode(rootValue);
        int i = map.get(rootValue);
        //求出中序遍历左子树长度为;
        int left_length = i - in_left;
        //求出中序遍历右子树长度
        int right_length = in_right - i;

        rootNode.left = myTree(preorder, inorder, pre_left + 1, pre_left + left_length, in_left, i - 1);
        rootNode.right = myTree(preorder, inorder, pre_left + left_length + 1, pre_right, i + 1, in_right);
        return rootNode;
    }

    /**
     * =========================================================================================================================
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * 说明:
     *
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     */

    /**
     * 解法一
     * 思路: 将数组映射为hash数组，for循环，将target减去第一个数， 拿着剩下的数去hash中寻找，找到就返回数据，未找到继续找
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] returnValue = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int shengxia = target - numbers[i];
            if (map.containsKey(shengxia)) {
                returnValue[0] = i + 1;
                returnValue[1] = map.get(shengxia) + 1;
                return returnValue;
            } else {
                continue;
            }
        }
        return returnValue;
    }

    /**
     * 解法二
     * 思路 双指针算法
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int iv = 0, jv = numbers.length - 1;
        while (numbers[iv] + numbers[jv] != target) {
            //如果i到达,j未到达，则i+j>target,证明j要左移
            //如果j到达,i未到达，则i+j<target,证明i要右移
            if (numbers[iv] + numbers[jv] > target) {
                --jv;
            } else if (numbers[iv] + numbers[jv] < target) {
                ++iv;
            }
        }
        return new int[]{iv + 1, jv + 1};
    }

    /**
     * =================================================================================================
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * <p>
     * 思路：循环时将每一个字符确定行数，扔到对应的行里面，然后将每一行拼接起来，就是最终结果
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuffer> rows = new ArrayList<>();
        //初始化rows.
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuffer());
        }
        int currows = 0;
        boolean goDown = false;
        for (char c : s.toCharArray()) {
            if (currows == 0 || currows == rows.size() - 1) {
                goDown = !goDown;
            }
            rows.get(currows).append(c);
            currows += goDown ? 1 : -1;
        }
        StringBuffer returnValue = new StringBuffer();
        rows.forEach(p -> {
            returnValue.append(p);
        });
        return returnValue.toString();
    }

    /**
     * ====================================================================================
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     * <p>
     * 输入：3
     * 输出：
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> returnNode = new ArrayList<>();
        return generateTrees(0, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (start >= end) {
            treeNodes.add(null);
            return treeNodes;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTreeNode = generateTrees(start, i - 1);
            List<TreeNode> rightTreeNode = generateTrees(i + 1, end);
            for (TreeNode left : leftTreeNode) {
                for (TreeNode right : rightTreeNode) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    treeNodes.add(root);
                }
            }
        }
        return treeNodes;
    }

    /**
     * ==============================================================================================
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
     * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     *
     * @param numbers
     * @return
     */
    /**
     * 不管旋转，循环整体找到最小值 O(n)
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }

    /**
     * 二分法查找
     * <p>
     * 使用二分查找法，可以缩短时间定位出log的位置。 O(logn)
     *
     * @param numbers
     * @return
     */
    public int minArray2(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int middle = (right + left) / 2;
            if (numbers[right] > numbers[middle]) {
                //证明最小值在这个区间
                right = middle;
            } else if (numbers[right] < numbers[middle]) {
                left = middle + 1;
            } else {
                right--;
            }
        }
        return numbers[left];
    }

    /**
     * =================================================================================================================
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * <p>
     * 此时，因为*可以动态匹配前面元素的1个和多个，所以可以用动态规划思想解决
     * 假设现在f[i][j] 代表着s的前i个数与p的前j个数匹配(也就是s[i] = p[j])，
     * 则存在以下情况
     * p[j]不为*号
     * 1. f[i][j] = f[i-1][j-1], if s[i]=p[j] or p[j]='.'
     * p[j]为*号时。则有以下情况
     * 1. f[i][j] = f[i][j-2], if s[i] != p[j-1]
     * 2. f[i][j] = f[i-1][j] or f[i][j-1] or f[i][j-2], if s[i] = p[j-1]
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    if (isMatch(s, p, i, j - 1)) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (isMatch(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * =================================================================================================================
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     * 输入:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     * <p>
     * 假设dp[i][j]表示从左上角(0,0)到右下角(i,j)的最短路劲。除了第一行第一列外的数据，都可能是上面向下一步得到，或者左边向右移动得到
     * 所以有以下几种情况
     * 1. i > 0 and j = 0， 则 dp[i][0] = dp[i-1][0] + grid[i][0]
     * 2. i = 0 and j > 0， 则 dp[0][j] = dp[0][j-1] + grid[0][j]
     * 3. i > 0 and j > 0， 则dp[i][j] = Math.min(dp[i-1][j]+grid[i][j], dp[i][j-1]+grid[i][j]) = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j]
     * <p>
     * 由动态规划推导出以上结论
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i > 0 && j == 0) {
                    dp[i][0] = dp[i - 1][0] + grid[i][0];
                }
                if (i == 0 && j > 0) {
                    dp[0][j] = dp[0][j - 1] + grid[0][j];
                }
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    /**
     * =================================================================================================================
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 
     * (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * <p>
     * 暴力解法
     *
     * @param height
     */
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if (max < Math.min(height[i], height[j]) * (j - i)) {
                    max = Math.min(height[i], height[j]) * (j - i);
                }
            }
        }
        return max;
    }

    /**
     * 双指针解决
     *
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (right > left) {
            if (max < Math.min(height[left], height[right]) * (right - left)) {
                max = Math.min(height[left], height[right]) * (right - left);
            }
            if (height[right] > height[left]) {
                left++;
            } else if (height[right] <= height[left]) {
                right--;
            }
        }
        return max;
    }

    /**
     * =================================================================================================================
     * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     * <p>
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     * <p>
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     * <p>
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
     * <p>
     * 输入：2
     * 输出：true
     * 解释：爱丽丝选择 1，鲍勃无法进行操作。
     * <p>
     * N=1 的时候，区间 (0, 1)(0,1) 中没有整数是 nn 的因数，所以此时 Alice 败。
     * N=2 的时候，Alice 只能拿 1，NN 变成 1，Bob 无法继续操作，故 Alice 胜。
     * N=3 的时候，Alice 只能拿 1，NN 变成 2，根据 N=2 的结论，我们知道此时 Bob 会获胜，Alice 败。
     * N=4 的时候，Alice 能拿 1 或 2，如果 Alice 拿 1，根据 N=3 的结论，Bob 会失败，Alice 会获胜。
     * N=5 的时候，Alice 只能拿 1，根据 N=4 的结论，Alice 会失败。
     * <p>
     * 列出猜想，偶数Alice成功，奇数Alice失败
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }

    /**
     * 另一种解法
     * 当N=K时，Alice先手做一步操作，则使得Bob处于N = m（m<k）状态。
     * 即：
     * f(i)表示当前数字i时先手处于必胜态还是必败态，true为必胜态，false为必败态
     * 则f(i-j)必须处于必败态才行
     *
     * @param N
     * @return
     */
    public boolean divisorGame1(int N) {
        boolean[] f = new boolean[N + 3];
        f[1] = false;
        f[2] = true;
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !f[i - j]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[N];
    }

    /**
     * =================================================================================================================
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 例如:
     * 输入: 3
     * 输出: "III"
     * <p>
     * 思路，算出每一位的最高值
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuffer returnValue = new StringBuffer();
        Stack<String> stack = new Stack<>();
        int f = 0;
        do {
            f++;
            String I = "", V = "", IV = "", IX = "";
            switch (f) {
                case 1:
                    I = "I";
                    V = "V";
                    IV = "IV";
                    IX = "IX";
                    break;
                case 2:
                    I = "X";
                    V = "L";
                    IV = "XL";
                    IX = "XC";
                    break;
                case 3:
                    I = "C";
                    V = "D";
                    IV = "CD";
                    IX = "CM";
                    break;
                case 4:
                    I = "M";
                    break;
            }
            StringBuffer stringBuffer = new StringBuffer();
            int yushu = num % 10;

            if (yushu >= 5 && yushu < 9) {
                yushu = yushu - 5;
                stringBuffer.append(V);
                if (yushu > 3) {
                    stringBuffer.append(IV);
                } else {
                    for (int i = 0; i < yushu; i++) {
                        stringBuffer.append(I);
                    }
                }
            } else if (yushu < 5) {
                if (yushu > 3) {
                    stringBuffer.append(IV);
                } else {
                    for (int i = 0; i < yushu; i++) {
                        stringBuffer.append(I);
                    }
                }
            } else {
                stringBuffer.append(IX);
            }
            stack.push(stringBuffer.toString());
        } while ((num /= 10) >= 1);
        while (!stack.isEmpty()) {
            returnValue.append(stack.pop());
        }
        return returnValue.toString();
    }

    public String intToRoman2(int num) {
        int[] arrayInt = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] arrayStr = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuffer sb = new StringBuffer();
        for (int i = arrayInt.length - 1; i >= 0; i--) {
            if (num >= arrayInt[i]) {
                num = num - arrayInt[i];
                sb.append(arrayStr[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，
     * 若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
     * <p>
     * 输入：nums = [0, 2, 3, 4, 5]
     * 输出：0
     * 说明: 0下标的元素为0
     *
     * @param nums
     */
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public int findMagicIndex1(int[] nums) {
        return twoSearch(0, nums.length - 1, nums);
    }

    public int twoSearch(int left, int right, int[] nums) {
        if (left >= right) {
            return -1;
        }
        int mod = (left + right) / 2;
        int f = twoSearch(left, mod + 1, nums);
        if (f != -1) {
            return f;
        } else {
            if (mod == nums[mod]) {
                return mod;
            }
        }
        return twoSearch(mod + 1, right, nums);
    }

    /**
     * =================================================================================================================
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * <p>
     * 注意：
     * <p>
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     * <p>
     * 思路1，拿着ascii码，硬算
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuffer sb = new StringBuffer();
        int jinwei = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || jinwei > 0) {
            int n1 = 0, n2 = 0;
            if (i >= 0) {
                n1 = num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                n2 = num2.charAt(j) - '0';
                j--;
            }
            int sum = (n1 + n2 + jinwei) % 10;
            jinwei = (n1 + n2 + jinwei) / 10;
            sb.append((char) (sum + '0'));
        }
        sb.reverse();
        return sb.toString();
    }

    /**
     * =================================================================================================================
     * <p>
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 1. 通过hash表寻找数据. 固定a,b时,有且仅有1个c于其对应,即a+b+c = 0;
     * 2. 通过保证不重复通过以下步骤表明
     * ①.数组有序时, 不重复,且a+b+c为0的基本条件必须是:  a<b<c
     * ②.如果相邻数相等时,如{-1,0,0,1,1,1}, 则取出{-1,0,1}后, 下个j=0时,认为该数已经存在过,即过滤掉
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arrayList = new ArrayList<>();
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int num = nums[i] + nums[j];
                if (map.containsKey(0 - num) && map.get(0 - num) > j) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(0 - num);
                    arrayList.add(list);
                }
            }
        }
        return arrayList;
    }

    /**
     * 不用hash方式,采用循环寻找C.(双指针) a为第一层循环,b为第二层循环,c为第三层循环
     * c比b大,所以一定是在b的右边,且c的值也必须比b大.所以可以定位c为最右边数组,做c--,b作为数组左边做b++;
     * 因此.在固定a的情况, b,c做双指针减少即可.
     * <p>
     * 有个优化点:
     * 设 a + b + c = 0;  a + b' + c' = 0, 即可得出 b'>b, c'<c; b==c时,此时打破了b<c的情况.就算继续continue让b'出现,也不会存在c'的情况.因此当b == c时,可以重新定义a的位置
     * 即此时最坏也只会有双重循环,时间复杂度为O(n^2)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> arrayList = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int three = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (three > j && nums[three] + nums[j] > target) {//要么找到了,要么就是 b==c
                    three--;
                }
                if (three == j) {
                    break;
                }
                if (nums[j] + nums[three] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[three]);
                    arrayList.add(list);
                }
            }
        }
        return arrayList;
    }

    /**
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * <p>
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * <p>
     * 思考:
     * 以下几种情况不可能完成课程的学习
     * 课程不能循环 比如完成 1必须完成0 且完成0必须完成1
     * <p>
     * 此题是一道典型的topo排序的题目，只要保证topo图中不存在环，即可为true
     * 解法：
     * 1. 深度优先搜索
     * 2. 广度优先搜索
     *
     * @param numCourses
     * @param prerequisites
     */
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            } else {
                return;
            }
            visited[u] = 2;
        }
    }

    /**
     * 广度优先搜索
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //有多少必修课程，要完成前需要有多少选修课程
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        //默认课程都为1
        visited = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
            visited[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                queue.offer(i);
            }
        }

        int vis = 0;
        while(!queue.isEmpty()){
            ++vis;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                --visited[v];
                if(visited[v] == 0){
                    queue.offer(v);
                }
            }
        }
        return vis==numCourses;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        System.out.println(new LeetCodeSolution().threeSum1(nums));
    }
}
