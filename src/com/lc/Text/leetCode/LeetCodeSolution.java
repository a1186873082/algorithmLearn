package com.lc.Text.leetCode;

import com.lc.Text.NiuKeSolution;

import java.util.*;

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
     *
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

    public static void main(String[] args) {
        String s = "AB";
        System.out.println(new LeetCodeSolution().convert(s, 1));
//        new LeetCodeSolution().numberOfSubarrays({2,2,2,1,2,2,1,2,2,2})
//        LinkedList<Integer> inputList = new LinkedList<>(
//                Arrays.asList(new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4}));
//        TreeNode root = levelCreateTreeNode(inputList);
//
//        List<List<Integer>> list = new LeetCodeSolution().levelOrder(root);
//        System.out.println(list);
    }
}
