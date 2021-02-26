package com.lc.Text.leetCode;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.omg.CORBA.INTERNAL;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

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
     * 由 n 个连接的字符串 s 组成字符串 S，记作 S = [s,n]。例如，["abc",3]=“abcabcabc”。
     * <p>
     * 如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。
     * <p>
     * 现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 、S2=[s2,n2] 。
     * <p>
     * 请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。
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
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
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
     * [1,null,3,2],
     * [3,2,null,1],
     * [3,1,null,null,2],
     * [2,1,3],
     * [1,null,2,null,3]
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
     * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
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
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为
     * (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
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
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     * <p>
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     * <p>
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
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
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
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
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     * <p>
     * 注意：
     * <p>
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
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
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
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
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
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
        while (!queue.isEmpty()) {
            ++vis;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                --visited[v];
                if (visited[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return vis == numCourses;
    }

    /**
     * =================================================================================================================
     * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
     * <p>
     * 重复出现的子串要计算它们出现的次数。
     * <p>
     * 输入: "00110011"
     * 输出: 6
     * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
     * <p>
     * 请注意，一些重复出现的子串要计算它们出现的次数。
     * <p>
     * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
     * <p>
     * 解答:
     * 中心扩散思想，并统计相邻个数
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        int left = 0;
        int right = left + 1;
        int prev = -1;
        int count = 0;
        while (right < s.length()) {
            if (left >= 0 && s.charAt(left) != s.charAt(right)
                    &&
                    (prev == -1 || prev != -1 && s.charAt(prev) == s.charAt(right))) {
                //两边扩散
                count++;
                prev = right;
                left--;
                right++;
            } else {
                left = prev == -1 ? right : prev;
                right = left + 1;
                prev = -1;
            }
        }
        return count;
    }

    /**
     * 解答2
     * 利用统计分组解决
     */
    public int countBinarySubstrings1(String s) {
        List<Integer> list = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                count++;
            }
            if (i == s.length() - 1) {
                list.add(count);
            } else if (s.charAt(i) != s.charAt(i + 1)) {
                list.add(count);
                count = 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            sum += Math.min(list.get(i), list.get(i + 1));
        }
        return sum;
    }

    //优化
    public int countBinarySubstrings2(String s) {
        int count = 0, last = 0, curr = 0, sum = 0;
        while (curr < s.length()) {
            char c = s.charAt(curr);
            while (curr < s.length() && c == s.charAt(curr)) {
                count++;
                curr++;
            }
            sum += Math.min(count, last);
            last = count;
            count = 0;
        }
        return sum;
    }

    /**
     * =================================================================================================================
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射与电话按键相同。注意 1 不对应任何字母。
     * <p>
     * 思考
     * 固定参数，循环调用
     * <p>
     * 将上一次匹配完成的list，循环加上这一次的参数，最后结果即为所得
     */
    int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    String[] mapperArray = {"!@#", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> s = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            s = returnArray(digits.charAt(i) - '0', s);
        }
        return s;
    }

    public List<String> returnArray(int i, List<String> list) {
        List<String> returnArray = new ArrayList<>();
        String mapper = mapperArray[i - 1];
        for (int j = 0; j < mapper.length(); j++) {
            if (list.size() > 0) {
                for (String s : list) {
                    s = s + Character.toString(mapper.charAt(j));
                    returnArray.add(s);
                }
            } else {
                String s = String.valueOf(mapper.charAt(j));
                returnArray.add(s);
            }
        }
        return returnArray;
    }

    /**
     * =================================================================================================================
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * 示例:
     * <p>
     * 思路，图形解答一般首先会想到dfs和bfs两种思路
     * <p>
     * dfs:深度优先搜索
     *
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x - 1, y);
        dfs(board, x + 1, y);
        dfs(board, x, y - 1);
        dfs(board, x, y + 1);
    }

    /**
     * bfs:广度优先搜索
     */
    int[] fx = {-1, 1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public void solve1(char[][] board) {
        int m = board.length;
        if (m == 0) {
            return;
        }
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[i][n - 1] == 'O') {
                queue.offer(new int[]{i, n - 1});
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                queue.offer(new int[]{0, i});
            }
            if (board[m - 1][i] == 'O') {
                queue.offer(new int[]{m - 1, i});
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], nx = y + fx[i];
                if (mx < 0 || nx < 0 || mx >= board.length || nx >= board[0].length || board[mx][nx] != 'O') {
                    continue;
                }
                queue.offer(new int[]{mx, nx});
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * =================================================================================================================
     * 克隆图
     * <p>
     * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
     * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
     * <p>
     * 思考
     * 看到图表类型的结构，第一想到的是dfs和bfs这2种搜索遍历方式进行解决
     * 因为是无向图，为了保证不出现死循环，则需要用一个hash表解决死循环问题
     */
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    HashMap<Integer, Node> nodeMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (nodeMap.containsKey(node.val)) {
            return nodeMap.get(node.val);
        }
        Node cloneNode = new Node(node.val, new ArrayList<>());
        nodeMap.put(node.val, cloneNode);
        if (node.neighbors != null && node.neighbors.size() > 0) {
            for (Node neighbor : node.neighbors) {
                cloneNode.neighbors.add(cloneGraph(neighbor));
            }
        }
        return cloneNode;
    }

    /**
     * =================================================================================================================
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * <p>
     * 思考，乘法可以理解为另类的加法
     * <p>
     * 比如 345*123= 300*123+40*123+5*123
     * 其中300*123等于123+123+123后面加00
     * 依次类推 40*123等于123+123+123+123后面加0
     */
    public String multiply(String num1, String num2) {
        if (num1 == "0" || num2 == "0") {
            return "0";
        }
        Queue<String> list = new LinkedList<>();
        String sum = "";
        //以num1为基
        String lastNum = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            //数据累加
            for (int j = num2.charAt(i) - '0' - 1; j >= 0; j--) {
                list.offer(num1 + lastNum);
            }
            lastNum += "0";
        }
        //将queue中的数据累加起来
        while (!list.isEmpty()) {
            String curr = list.poll();
            int size = Math.max(sum.length(), curr.length());
            int jinwei = 0;
            StringBuffer jubu = new StringBuffer();
            for (int i = 0; i < size; i++) {
                int first = 0, second = 0;
                if (i < sum.length()) {
                    first = sum.charAt(sum.length() - 1 - i) - '0';
                }
                if (i < curr.length()) {
                    second = curr.charAt(curr.length() - 1 - i) - '0';
                }
                jubu.append((first + second + jinwei) % 10);
                jinwei = (first + second + jinwei) / 10;
            }
            if (jinwei > 0) {
                jubu.append(jinwei);
            }
            sum = jubu.reverse().toString();
        }
        return sum;
    }


    /**
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * <p>
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * <p>
     * 思考
     * 采用分治法解决，因为左右子树高度差绝对值不超过1，所以需要找到其根结点，为中结点
     */
//    public TreeNode sortedListToBST(ListNode head) {
//
//    }

    //采用快慢指针找到根结点
    public ListNode getMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null || fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 找到和为特定值的3个数
     * <p>
     * hash寻址法
     *
     * @return
     */
    public List<List<Integer>> specialValue(int[] a, int n) {
        List<List<Integer>> returnValue = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            int sub = n - a[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < a.length; j++) { // j=0? j=0.相当于重复走了一遍i=0时的可能情况，因此j = i + 1就好
                int findStr = sub - a[j];
                if (map.containsKey(findStr)) {
                    List<Integer> array = new ArrayList<>();
                    array.add(a[i]);
                    array.add(a[j]);
                    array.add(findStr);
                    returnValue.add(array);
                }
                map.put(a[j], a[j]); //每一次都需要把值放入map中
            }
        }
        return returnValue;
    }

    /**
     * 找到和为特定值的3个数
     * 双指针法
     */
    public List<List<Integer>> specialValue1(int[] a, int n) {
        Arrays.sort(a);
        List<List<Integer>> returnValue = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (i > 0 && a[i] == a[i - 1]) { // 剔除重复出现的概率 因为有了排序，所以重复出现的数字一定会在一起
                continue;
            }
            int sub = n - a[i];
            for (int left = i + 1, right = a.length - 1; left < a.length; left++) {
                if (left > i + 1 && a[left] == a[left - 1]) {
                    continue;
                }
                while (left < right && a[left] + a[right] > sub) {
                    right--;
                }
                if (left == right) {
                    break;
                }
                if (a[left] + a[right] == sub) {
                    returnValue.add(Arrays.asList(a[i], a[left], a[right]));
                }
            }
        }
        return returnValue;
    }

    /**
     * ==================================================================================================================
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * <p>
     * 用buy[i][j]表示prices[0...i]中，恰好做了j次交易，且手上持有股票的交易
     * 用sell[i][j]表示prices[0...i]中，恰好做了j次交易，且手上没有持股
     * <p>
     * 可以做如下推算:
     * 如果是第i天买的，第i-1天手上不持有股票，则为sell[i-1][j],但第i天买入股票-prices[i]
     * 如果不是第i天买的，则i-1天和第i天花费相同
     * buy[i][j] = Math.max(buy[i-1][j], sell[i-1][j] - prices[i])
     * <p>
     * 同理。如果是第i天卖的，第i-1天手上持股,则第i天卖掉股票+prices[i]
     * 如果不是第i天卖的，则i-1天河第i天花费相同
     * sell[i][j] = Math.max(sell[i-1][j], buy[i-1][j]+prices[i])
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        //思想，存储卖出，卖入的状态，k最多不能大于prices的一半，因为2次prices为1次完整交易
        int n = prices.length;
        k = Math.min(k, n / 2);

        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];
        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        //因为第0天无法有正常的交易，所以可以把第0天当成动态规划边界,设置为较小值
        for (int i = 1; i <= k; i++) {
            sell[0][i] = buy[0][i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < n; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    /**
     * 解法2
     * 在解法1中，可以发现规则方程中，用到了 [i-1]转移而来，所以我们可以用一维数组进行状态转移
     */
    public int maxProfit1(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        //思想，存储卖出，卖入的状态，k最多不能大于prices的一半，因为2次prices为1次完整交易
        int n = prices.length;
        k = Math.min(k, n / 2);

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        buy[0] = -prices[0];
        sell[0] = 0;
        //因为第0天无法有正常的交易，所以可以把第0天当成动态规划边界,设置为较小值
        for (int i = 1; i <= k; i++) {
            sell[i] = buy[i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < n; i++) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell).max().getAsInt();
    }


    /**
     * =================================================================================================================
     * 给定一个已排序的正整数数组 nums，和一个正整数n 。从[1, n]区间内选取任意个数字补充到nums中，使得[1, n]区间内的任何数字都可以用nums中
     * 某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
     * <p>
     * 贪心算法
     * 对于正整数x，如果区间[1, x-1]内的所有数字都已经被覆盖，则x再数组中，[1, 2x-1]在内的所有数字也被覆盖
     */
    public int minPatches(int[] nums, int n) {
        //
        long x = 1;
        int index = 0;
        int count = 0;
        while (x <= n) {
            if (index < nums.length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                count++;
            }
        }
        return count;
    }

    /**
     * =================================================================================================================
     * <p>
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查第一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * <p>
     * <p>
     * <p>
     * 解法一：暴力解法
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        long i = 0;
        boolean isDigit = true, rel = true;
        for (char s1 : chars) {
            if (' ' == s1 && isDigit) {
                continue;
            }
            if ('-' == s1 && isDigit) {
                rel = false;
                isDigit = false;
                continue;
            }
            if ('+' == s1 && isDigit) {
                isDigit = false;
                continue;
            }
            if (s1 >= '0' && s1 <= '9') {
                i = i * 10 + (s1 - '0');
                if (i > Integer.MAX_VALUE && rel) {
                    i = Integer.MAX_VALUE;
                    break;
                } else if (-i < Integer.MIN_VALUE) {
                    i = Integer.MIN_VALUE;
                    break;
                }
            } else {
                break;
            }
            isDigit = false;
        }
        if (!rel) {
            i = -i;
        }
        return (int) i;
    }

    /**
     * 解法二：状态机解法
     * <p>
     * 分四种状态，start, sign, number, end
     * ' '      +/-     number    other
     * start     start    sign    number    end
     * sign      end      end     number    end
     * number    end      end     number    end
     * end       end      end     end       end
     *
     * @param s
     * @return
     */
    public int myAtoi1(String s) {
        char[] chars = s.toCharArray();
        AutoMich autoMich = new AutoMich();
        for (char a : chars) {
            autoMich.myatoi(a);
        }
        return (int) (autoMich.ais * autoMich.res);
    }

    class AutoMich {
        int ais = 1;
        long res = 0;
        String autoState = "start";
        public Map<String, String[]> map = new HashMap<>();

        {
            map.put("start", new String[]{"start", "sign", "number", "end"});
            map.put("sign", new String[]{"end", "end", "number", "end"});
            map.put("number", new String[]{"end", "end", "number", "end"});
            map.put("end", new String[]{"end", "end", "end", "end"});
        }

        public void myatoi(char a) {
            String s = map.get(autoState)[translate(a)];
            if ("number".equalsIgnoreCase(s)) {
                res = res * 10 + a - '0';
                res = ais == 1 ? Math.min(res, Integer.MAX_VALUE) : Math.min(res, -(long) Integer.MIN_VALUE);
            } else if ("sign".equalsIgnoreCase(s)) {
                if (a == '-') {
                    ais = -1;
                }
            }
            autoState = s;
        }

        public int translate(char a) {
            if (a == ' ') {
                return 0;
            }
            if (a == '+' || a == '-') {
                return 1;
            }
            if (a >= '0' && a <= '9') {
                return 2;
            }
            return 3;
        }
    }

    /**
     * =================================================================================================================
     * <p>
     * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
     * <p>
     * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
     * <p>
     * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
     * <p>
     * 解法一：
     * 通过索引表解决
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int ret = 0;
        int n = row.length;
        int[] indexMap = new int[n];
        for (int i = 0; i < n; i++) {
            indexMap[row[i]] = i;
        }

        for (int i = 0; i < n; i += 2) {
            int p1 = row[i];
            int p2 = p1 % 2 == 0 ? p1 + 1 : p1 - 1;
            if (row[i + 1] == p2) {
                //情侣在一起，无需交换
                continue;
            }
            //情侣不在一起
            swap(row, indexMap, i + 1, indexMap[p2]);
            ret++;
        }
        return ret;
    }

    public void swap(int[] row, int[] indexMap, int index, int indexp2) {
        int temp = row[index];
        row[index] = row[indexp2];
        row[indexp2] = temp;
        indexMap[row[index]] = indexp2;
        indexMap[row[indexp2]] = index;
    }


    /**
     * =================================================================================================================
     * <p>
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * <p>
     * 回溯, 深度优先遍历
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<>();
        ArrayDeque<Integer> arrayQueue = new ArrayDeque<>(nums.length);
        boolean[] used = new boolean[nums.length];
        backRound(nums.length, returnList, arrayQueue, nums, used, 0);
        return returnList;

    }

    public void backRound(int high, List<List<Integer>> returnList, ArrayDeque<Integer> arrayQueue, int[] nums, boolean[] used, int n) {
        if (high == n) {
            returnList.add(new ArrayList<>(arrayQueue));
            return;
        }
        for (int i = 0; i < high; i++) {
            if (!used[i]) {
                arrayQueue.addLast(nums[i]);
                used[i] = true;
                System.out.println("递归之前:" + arrayQueue + ",标记变量:" + Arrays.asList(used));
                backRound(high, returnList, arrayQueue, nums, used, n + 1);
                System.out.println("递归之后:" + arrayQueue + ",标记变量:" + Arrays.asList(used));
                used[i] = false;
                arrayQueue.pollLast();
            }
        }
    }

    /**
     * 解法2：
     * 广度优先遍历
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<>();
        if (nums.length < 2) {
            List<Integer> integers = Arrays.stream(nums).boxed().collect(Collectors.toList());
            returnList.add(integers);
            return returnList;
        }
        Deque<List<Integer>> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> objects = new ArrayList<>();
            objects.add(nums[i]);
            queue.addLast(objects);
            while (!queue.isEmpty()) {
                List<Integer> integers = queue.pollFirst();
                for (int j = 0; j < nums.length; j++) {
                    if (!integers.contains(nums[j])) {
                        ArrayList<Integer> newInteger = new ArrayList<>(integers);
                        newInteger.add(nums[j]);
                        if (newInteger.size() == nums.length) {
                            returnList.add(newInteger);
                            break;
                        }
                        queue.addLast(newInteger);
                    }
                }
            }
        }
        return returnList;
    }

    /**
     * =================================================================================================================
     * <p>
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     * <p>
     * 返回该 最大总和 。
     * <p>
     * 解法一：
     *
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        //取巧，顺序排序时，min最大
        List<Integer> array = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        int returnSum = 0;
        for (int i = 0; i < array.size(); i += 2) {
            int max = Math.min(array.get(i), array.get(i + 1));
            returnSum += max;
        }
        return returnSum;
    }

    /**
     * 优化
     *
     * @param nums
     * @return
     */
    public int arrayPairSum1(int[] nums) {
        Arrays.sort(nums);
        int returnSum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            returnSum += nums[i];
        }
        return returnSum;
    }

    /**
     * =================================================================================================================
     * <p>
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> returnList = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        boolean[] used = new boolean[nums.length];

        dfs1(nums, deque, returnList, used, 0);
        return returnList;
    }

    public void dfs1(int[] nums, Deque<Integer> deque, List<List<Integer>> returnList, boolean[] used, int depth) {
        if (depth == nums.length) {
            returnList.add(new ArrayList<>(deque));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            deque.addLast(nums[i]);
            used[i] = true;
            dfs1(nums, deque, returnList, used, depth + 1);
            deque.pollLast();
            used[i] = false;

        }
    }

    /**
     * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
     * <p>
     * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
     * <p>
     * 输入：A = [0,1,0], K = 1
     * 输出：2
     * 输入：A = [1,1,0], K = 2
     * 输出：-1
     * <p>
     * 如果长度为K，翻转的子数组为f[k],翻转的起始下标为i。
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        //如果0的个数 < k,则无法翻转
        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : A) {
            deque.addLast(num);
        }
        int returnVal = 0;
        while (!deque.isEmpty()) {
            if (deque.poll() == 0) {
                if (deque.size() >= (K - 1)) {
                    //触发翻转
                    Deque<Integer> dequeArray = new ArrayDeque<>();
                    dequeArray.addLast(1);
                    for (int j = 1; j < K; j++) {
                        if (deque.poll() == 0) {
                            dequeArray.addLast(1);
                        } else {
                            dequeArray.addLast(0);
                        }
                    }
                    dequeArray.addAll(deque);
                    deque = dequeArray;
                    returnVal++;
                    continue;
                } else {
                    returnVal = -1;
                }
            }
        }
        return returnVal;
    }

    /**
     * 滑动窗口解答
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips2(int[] A, int K) {
        int res = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            if (!deque.isEmpty() && deque.getFirst() + K <= i) {
                deque.pollFirst();
            }
            if (deque.size() % 2 == A[i]) {
                if (i + K > A.length) {
                    return -1;
                }
                //此时当前A为0
                deque.addLast(i);
                res++;
            }
        }
        return res;
    }

    /**
     * 滑动窗口解答2,采用标记法
     *
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips3(int[] A, int K) {
        int res = 0, recover = 0;
        for (int i = 0; i < A.length; i++) {
            if (i >= K && A[i - K] > 1) {
                recover ^= 1; //已过一次翻转点，减少一次翻转
                A[i - K] -= 2;
            }
            if (recover == A[i]) {//表明此时该点的数为0，需要翻转
                if (i + K > A.length) {
                    return -1;
                }
                res++;
                recover ^= 1; //翻转一次，recover值就会变化
                A[i] += 2;
            }
        }
        return res;
    }

    /**
     * =================================================================================================================
     * <p>
     * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
     * <p>
     * 返回仅包含 1 的最长（连续）子数组的长度。
     * <p>
     * 使用滑动窗口解答
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int returnSize = 0, lsum = 0, rsum = 0, left = 0;
        for (int right = 0; right < A.length; right++) {
            rsum += 1 - A[right];
            while (rsum - lsum > K) {
                lsum += 1 - A[left];
                left++;
            }
            returnSize = Math.max(returnSize, right - left + 1);
        }
        return returnSize;
    }

    /**
     * =================================================================================================================
     * 给定一个二进制数组， 计算其中最大连续 1 的个数。
     *
     * @param
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int sum = 0, ret = 0;
        for (int num : nums) {
            if ((1 & num) == 1) {
                sum++;
            } else {
                sum = 0;
            }
            ret = Math.max(ret, sum);
        }
        return ret;
    }

    /**
     * =================================================================================================================
     * <p>
     * 给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。
     * <p>
     * 滑动窗口
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes1(int[] nums) {
        int ret = 0, rsum = 0, lsum = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            rsum += 1 - nums[right];
            if (rsum - lsum > 1) {
                lsum += 1 - nums[left];
                left++;
            }
            ret = Math.max(right - left + 1, ret);
        }
        return ret;
    }

    /**
     * =================================================================================================================
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
     * <p>
     * 注意：字符串长度 和 k 不会超过 104。
     * <p>
     * 解法：滑动窗口解答
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int ret = 0, max = 0, change = 0, left = 0;
        int[] num = new int[26];
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            num[chars[right] - 'A']++;
            max = Math.max(max, num[chars[right] - 'A']);
            if (right - left + 1 - max > k) {
                num[chars[left] - 'A']--;
                left++;
            } else {
                ret = Math.max(right - left + 1, ret);
            }
        }
        return ret;
    }

    public int characterReplacement1(String s, int k) {
        int ret = 0, max = 0, change = 0, left = 0, right = 0;
        int[] num = new int[26];
        char[] chars = s.toCharArray();
        for (right = 0; right < chars.length; right++) {
            num[chars[right] - 'A']++;
            max = Math.max(max, num[chars[right] - 'A']);
            if (right - left + 1 - max > k) {
                num[chars[left] - 'A']--;
                left++;
            }
        }
        return right - left;
    }

    /**
     * =================================================================================================================
     * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
     * <p>
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     * <p>
     * 1.找出数据中最大频数
     * <p>
     * 2.相同频数最短子数组
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        int max = commonMaxRes(nums);
        int[] angle = new int[50000];
        int max2 = 0;
        int min = 50000;
        for (int right = 0; right < nums.length; right++) {
            int left = 0;
            angle[nums[right]]++;
            max2 = Math.max(max2, angle[nums[right]]);
            while (max2 >= max) {
                if (nums[left] == nums[right]) {
                    max2--;
                    min = Math.min(min, right - left + 1);
                }
                left++;
            }
        }
        return min;
    }

    /**
     * 优化
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray1(int[] nums) {
        int max = commonMaxRes(nums);
        int[] angle = new int[50000];
        int max2 = 0;
        int min = 50000;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            angle[nums[right]]++;
            while (angle[nums[right]] == max) {
                min = Math.min(min, right - left + 1);
                angle[nums[left]]--;
                left++;
            }
        }
        return min;
    }

    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray2(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            if (map.containsKey(nums[right])) {
                int[] integers = map.get(nums[right]);
                integers[0]++;
                integers[2] = right;
            } else {
                int[] integers = {1, right, right};
                map.put(nums[right], integers);
            }
        }
        int max = 0, min = nums.length;
        for (Map.Entry<Integer, int[]> integerEntry : map.entrySet()) {
            int[] value = integerEntry.getValue();
            if (max < value[0]) {
                max = value[0];
                min = value[2] - value[1] + 1;
            } else if (max == value[0]) {
                if (min > value[2] - value[1] + 1) {
                    min = value[2] - value[1] + 1;
                }
            }
        }
        return min;
    }

    public int commonMaxRes(int[] nums) {
        int[] angle = new int[50000];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            angle[nums[i]]++;
            max = Math.max(max, angle[nums[i]]);
        }
        return max;
    }

    /**
     * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
     * <p>
     * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
     * <p>
     * 满足该矩阵，必须从左下开始，到右上结束，对角线的值都相等
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int resource = -1;
        for (int i = 0; i < matrix.length; i++) {
            int j = 0, ireplace = i;
            while (ireplace < matrix.length && j < matrix[0].length) {
                if (j == 0) {
                    resource = matrix[ireplace][j];
                } else {
                    if (resource != matrix[ireplace][j]) {
                        return false;
                    }
                }
                j++;
                ireplace++;
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            int i = 0, jreplace = j;
            while (i < matrix.length && jreplace < matrix[0].length) {
                if (i == 0) {
                    resource = matrix[i][jreplace];
                } else {
                    if (resource != matrix[i][jreplace]) {
                        return false;
                    }
                }
                i++;
                jreplace++;
            }
        }
        return true;
    }

    /**
     * =================================================================================================================
     * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
     * <p>
     * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
     * <p>
     * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
     * <p>
     * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        //1. 找到X分钟内失望最多的客户数，
        //滑动窗口
        int right = 0, left = 0;
        int angular = 0, maxAngular = 0, res = 0;
        for (right = 0; right < customers.length; right++) {
            res += (customers[right] * (1 ^ grumpy[right]));
            angular += (customers[right] * (0 ^ grumpy[right]));

            if (right - left + 1 > X) {
                angular -= (customers[left] * (0 ^ grumpy[left]));
                left++;
            }
            if (maxAngular < angular) {
                maxAngular = angular;
            }
        }
        return res + maxAngular;

    }

    /**
     * =================================================================================================================
     * <p>
     * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
     * <p>
     * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
     * <p>
     * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
     *
     * @param A
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] returnVal = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            int k = 0;
            for (int j = A[0].length - 1; j >= 0; j--) {
                returnVal[i][k] = (1 - A[i][j]);
                k++;
            }
        }
        return returnVal;
    }

    /**
     * 解法二，如果非相等的情况，水平翻转和翻转图片后，值没有变化
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage1(int[][] A) {
        for (int i = 0; i < A.length - 1; i++) {
            int left = 0, right = A[0].length - 1;
            while (left < right) {
                if (A[i][left] == A[i][right]) {
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right) {
                A[i][left] ^= 1;
            }
        }
        return A;
    }

    /**
     * =================================================================================================================
     * <p>
     * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
     * <p>
     * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
     *
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int[][] returnValue = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                returnValue[j][i] = matrix[i][j];
            }
        }
        return returnValue;
    }

    /**
     * =================================================================================================================
     * <p>
     * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
     * <p>
     * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
     * <p>
     * 单词 word 中包含谜面 puzzle 的第一个字母。
     * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
     * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
     * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目
     * <p>
     * //二进制压缩法
     *
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> answerList = new ArrayList<>();
        Map<Integer, Integer> mapWord = new HashMap<>();
        for (String word : words) {
            int mark = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                mark |= (1 << (c - 'a'));
            }
            if (Integer.bitCount(mark) <= 7) {
                mapWord.put(mark, mapWord.getOrDefault(mark, 0) + 1);
            }
        }

        for (String puzzle : puzzles) {
            int answer = 0;
            //首字母必须存在，首字母为1
            char[] chars = puzzle.toCharArray();
            for (int i = 0; i < (1 << (chars.length - 1)); i++) {
                int mark = 0;
                for (int j = 0; j < (chars.length - 1); j++) {
                    if ((i & (1 << j)) != 0) {
                        mark |= (1 << (puzzle.charAt(j + 1) - 'a'));
                    }
                }
                mark |= 1 << (puzzle.charAt(0) - 'a');
                if (mapWord.containsKey(mark)) {
                    answer += mapWord.get(mark);
                }
            }
            answerList.add(answer);
        }
        return answerList;
    }


    public List<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {
        List<Integer> answerList = new ArrayList<>();
        Map<Integer, Integer> mapWord = new HashMap<>();
        for (String word : words) {
            int mark = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                mark |= (1 << (c - 'a'));
            }
            if (Integer.bitCount(mark) <= 7) {
                mapWord.put(mark, mapWord.getOrDefault(mark, 0) + 1);
            }
        }

        for (String puzzle : puzzles) {
            int answer = 0;
            //首字母必须存在，首字母为1
            char[] chars = puzzle.toCharArray();
//            for (int i = 0; i < (1 << (chars.length - 1)); i++) {
//                int mark = 0;
//                for (int j = 0; j < (chars.length - 1); j++) {
//                    if ((i & (1 << j)) != 0) {
//                        mark |= (1 << (puzzle.charAt(j + 1) - 'a'));
//                    }
//                }
//                mark |= 1 << (puzzle.charAt(0) - 'a');
//                if (mapWord.containsKey(mark)) {
//                    answer += mapWord.get(mark);
//                }
//            }
            int mark = 0;
            for (int i = 1; i < chars.length - 1; i++) {
                mark |= (1 << (chars[i] - 'a'));
            }
            int subset = mark;
            do {
                int s = subset | (1 << (chars[0] - 'a'));
                if (mapWord.containsKey(s)) {
                    answer += mapWord.get(s);
                }
                subset = (subset - 1) & mark;
            } while (subset != mark);
            answerList.add(answer);
        }
        return answerList;
    }

    /**
     * =================================================================================================================
     * <p>
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<>();
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    list.add(nums[j]);
                }
            }
            returnList.add(list);
        }
        return returnList;
    }

    public static void main(String[] args) {
//        String[] customers = {"apple","pleas","please"};
//        String[] grumpy = {"aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"};
//        System.out.println(new LeetCodeSolution().findNumOfValidWords(customers, grumpy));
//        System.out.println(getPastDate(90, new Date(1614099600000L)));
        int[] nums = {0};
        System.out.println(new LeetCodeSolution().subsets(nums));

//        char[] chars = "abcef".toCharArray();
//        for (int i = 0; i < (1 << (chars.length - 1)); i++) {
//            int mark = 0;
//            for (int j = 0; j < (chars.length - 1); j++) {
//                if ((i & (1 << j)) != 0) {
//                    mark |= (1 << (chars[j + 1] - 'a'));
//                }
//            }
//            mark |= 1 << (chars[0] - 'a');
//            System.out.println(mark);
//        }
//        char[] chars = "aabbcef".toCharArray();
//        int mark = 0;
//        for (int i = 1; i < chars.length; i++) {
//            mark |= (1 << (chars[i] - 'a'));
//        }
//        int subset = mark;
//        do {
//            int s = subset | (1 << chars[0] - 'a');
//            System.out.println(s);
//            subset = (subset - 1) & mark;
//        } while (subset != mark);

    }

    public static Date getPastDate(int past, Date fromDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);

        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
