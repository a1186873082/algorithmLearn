package com.lc.Text;

import sun.reflect.generics.tree.Tree;

import javax.xml.soap.Node;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class NiuKeSolution {

    /**
     * 算法学习(一步一脚印)
     * <p>
     * 作为编程的基础，我会一直坚持自己对算法的理解
     * <p>
     * 时间复杂度是总运算次数表达式中受n的变化影响最大的那一项(不含系数)
     * 比如：一般总运算次数表达式类似于这样：
     * a*2^n+b*n^3+c*n^2+d*n*lg(n)+e*n+f
     * a ！ =0时，时间复杂度就是O(2^n);
     * a=0,b<>0 =>O(n^3);
     * a,b=0,c<>0 =>O(n^2)依此类推
     * <p>
     * 1.青蛙跳：
     * 递归了n次
     * 时间复杂度: O(n)
     *
     * @param s
     * @return
     */
    public static int getQingWaBt(int s) {
        if (s == 1) {
            return 1;
        } else if (s == 0) {
            return 0;
        } else if (s == 2) {
            return 2;
        } else {
            return 2 * getQingWaBt(s - 1);
        }
    }

    //-----------------------------------------------------------------
    // 循环了n+n = 2n次，不考虑系数，此时复杂度为O(n)
    //利用栈的特性反向输出链表
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }

        while (!stack.isEmpty()) {

            list.add(stack.pop().val);
        }
        return list;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    //---------------------------------------------------------------------
    /**
     * 利用2个栈实现队列 解题思路:
     * 1.利用2次先进后出的机制，将1,2,3放入stack1中,弹出
     * 2.弹出时为3,2,1，然后将3,2,1放入另一个栈,弹出
     * 3.此时变为1，2，3弹出的顺序了，但是每次只弹出一个，所以再弹出过后，得将数据还原到stack1栈中
     */
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        stack1.push(node);

    }

    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int i = stack2.pop();
        return i;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，
     * 输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * <p>
     * 解题思路:
     * 1.因为判断数组是有序数组还是旋转数组，有序数组:左边小于右边，，旋转数组 左边大于等于右边
     * 2.如果左边比中间小，而左边又比右边大，证明左边到中间的数组时有序数组，且不会为最小值所在地，，最小值再右边
     * 3.如果右边比中间大，而左边比右边大，则中间到右边为有序数组，最小值只会在左边到中间，或中间这个地方,所以取左边到中间的数组取值
     * 4.如果左边等于右边的标记，证明已经找完，找到的是最小值，退出
     *
     * @param array
     * @return
     */
    public int minNumberInarray(int[] array) {
        if (array.length == 0) {
            return 0;
        } else {
            int start = 0;
            int end = array.length - 1;

            while (start < end) {
                if (array[start] >= array[end]) {
                    int mode = (end - start) / 2 + start;
                    if (array[start] < array[mode]) {
                        start = mode + 1;
                    } else if (array[end] > array[mode]) {
                        end = mode;
                    } else {
                        start++;
                    }
                } else {
                    return array[start];
                }
            }
            return array[start];
        }
    }

    /**
     * 整数变为2进制码，可以用取余法来实现
     * 二进制中，将n与(n-1)相与,会将最左边为1的数字变为0;比如1100与1011想与，得到1000，1000与0111相与，得到0000
     *
     * @param
     */
    public int NumberOf(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * 1.target为大方块的n，所以n为0时，有0种变化
     * 2.n为1时，只有1种变化
     * 3.n为2时，只有2种变化
     * 4.n为3时，只有3种变化
     * 得到结论: f(n) = f(n-1)+f(n-2)
     *
     * @param target
     */
    public int RectCover(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }

    //-----------------------------------------------------------------------------------------------------
    //解法1:输入一个链表，输出该链表中倒数第k个结点。
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode firstNode, secondNode;
        //将指针指向头结点
        firstNode = head;
        secondNode = head;
        //让第一个指针先跑k，
        int count = 0;
        int a = k;
        while (firstNode != null) {
            firstNode = firstNode.next;
            if (k < 1) {
                //此时跑了K个结点时，第二个指针开始跑，第一个指针和第二个指针永远只隔K个结点，所以当第一个指针到达终点时，第二个指针位于
                //倒数第K个结点
                secondNode = secondNode.next;
            }
            count++;
            k--;
        }
        if (count < a) {
            return null;
        }
        return secondNode;
    }

    //解法二：利用栈的特性解答
    public ListNode FindKthToTail1(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        //将链表放进栈中;
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        int count = 1;
        while (!stack.isEmpty()) {
            if (k == count) {
                return stack.pop();
            }
            stack.pop();
            count++;
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------
    //输入一个链表，反转链表后，输出新链表的表头。
    //TODO 链表方面需要着重练习
    public ListNode ReverseList(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode get = head;
        while (get != null) {
            stack.push(get.val);
            get = get.next;
        }
        //输出栈头
        ListNode next = head;
        while (!stack.isEmpty()) {
            //获得下一个结点
            next.val = stack.pop();
            next = next.next;
        }
        return head;
    }


//    public ListNode ReverseList(ListNode head) {
//        if(head == null || head.next == null)
//            return head;//只有一个
//        Stack<Integer> stack1 = new Stack<Integer>();
//        ListNode getValueToStack = head;
//        while(getValueToStack != null) {
//            stack1.push(getValueToStack.val);//保存列表的值
//            getValueToStack = getValueToStack.next;
//        }//第一次遍历
//        ListNode stackValueToList = head;
//        //第二次遍历
//        while(stackValueToList!=null) {
//            stackValueToList.val = stack1.pop();
//            stackValueToList = stackValueToList.next;
//        }
//        return head;
//    }

    //不用栈的方式
    public ListNode ReverseList1(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        ListNode template = null;
        while (head != null) {
            //循环下一个结点
            template = head.next;
            //头结点-》前一个结点
            head.next = pre;
            //前一个节点赋予头结点
            pre = head;
            //开始下一个结点循环
            head = template;
        }
        return pre;
    }

    // ----------------------------------------------
    // 合并链表, 时间复杂度巨高
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode mergeNode = list1;
        while (list1 != null) {
            if (list1.next == null) {
                list1.next = list2;
                break;
            }
            list1 = list1.next;

        }
        ListNode x = mergeNode;

        while (x != null) {
            ListNode y = x.next;
            while (y != null) {
                if (x.val > y.val) {
                    int temp = x.val;
                    x.val = y.val;
                    y.val = temp;
                }
                y = y.next;
            }
            x = x.next;
        }
        return mergeNode;
    }

    //不合并链表,利用递归方式查询
    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val > list2.val) {
            list2.next = Merge2(list1, list2.next);
            return list2;
        } else if (list1.val < list2.val) {
            list1.next = Merge2(list1.next, list2);
            return list1;
        }
        return null;
    }

    //不使用递归方式 时间复杂度为 O(n)
    public ListNode Merge3(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode mergeNode = null;
        ListNode currentNode = null;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                if (mergeNode == null) {
                    //第一次给mergeNode赋值，赋值小的Node
                    mergeNode = list1;
                    currentNode = mergeNode;
                } else {
                    mergeNode.next = list1;
                    mergeNode = mergeNode.next;
                }
                list1 = list1.next;
            } else {
                if (mergeNode == null) {
                    //第一次给mergeNode赋值，赋值小的Node
                    mergeNode = list2;
                    currentNode = mergeNode;
                } else {
                    mergeNode.next = list2;
                    mergeNode = mergeNode.next;
                }
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            mergeNode.next = list2;
        } else {
            mergeNode.next = list1;
        }
        return currentNode;
    }


    // ----------------------------------------------------------------

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     */

    public static class TreeNode {
        Integer val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(Integer val) {
            this.val = val;

        }

    }

    //此时，时间复杂度为 O(n*m)
    //此方法为找到主节点相等的节点，，如果没有相等节点时，，直接返回false
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        //此时分为2中情况，，找到，，找不到，，找不到时找子树上的节点
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = find(root1, root2);
            }
            //如果节点没成功找到时,把右子树当做主树继续找
            if (!result) {
                result = HasSubtree(root1.right, root2);
            }
            //如果右节点也没有成功找到时，把左子树当做主树继续找
            if (!result) {
                result = HasSubtree(root1.left, root2);
            }

        }
        return result;
    }

    //此方法是找到主节点后，，遍历是否子节点也相等
    public Boolean find(TreeNode root1, TreeNode root2) {
        //证明已经找到 这里必须先判断子树是否找完，否则有可能出现分支为叶子结点的情况
        if (root2 == null) {
            return true;
        }
        //证明主树找完都没找到
        if (root1 == null) {
            return false;
        }

        if (root1.val == root2.val) {
            return find(root1.left, root2.left) && find(root1.right, root2.right);
        }
        return false;
    }

    //------------------------------------------------------------------------------
    //操作给定的二叉树，将其变换为源二叉树的镜像。

    /**
     * 解题思路
     * 1. 3种情况:1有左子树，有右子树； 2有左子树没右子树； 3有右子树没有左子树
     * 1.转换一次，此时左子树和右子树都需要成为新节点继续递归
     * 2.转换一次，此时右边没有结点了，，所以只开始左子树
     * 3.转换一次，此时左边没有结点了，，所以只递归右子树
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        TreeNode temp = null;
        if (root == null) {
            return;
        }
        if (root.left != null && root.right == null) {
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            Mirror(root.right);
        } else if (root.right != null && root.left == null) {
            temp = root.right;
            root.right = root.left;
            root.left = temp;
            Mirror(root.left);
        } else if (root.right != null && root.left != null) {
            temp = root.right;
            root.right = root.left;
            root.left = temp;
            Mirror(root.left);
            Mirror(root.right);
        }
    }

    //优化思路
    //结点不为空时，则开始替换结点位置，  如果左子树不为空，则迭代左子树，，右子树不为空，则迭代右子树
    public void Mirror1(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            if (root.left != null) {
                Mirror(root.left);
            }
            if (root.right != null) {
                Mirror(root.right);
            }
        }
    }


    //---------------------------------------------------------
    //输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

    /**
     * 解题思路:
     * 1. 这种回型读取方式，需要判定用什么作为循环条件， 这里我用的是默认循环一圈后，起始数+1，结束数-1
     * 1,当起始数小于结束数时，代表循环结束
     * 2.1次回型循环，相当于有4次循环，，一次从左到右，一次从上到下，一次从右到左，一次从下到上
     * 3.注意不要出现重复打印情况，，比如从上到下打印是最后一次，，结果又执行了一遍从下到上打印
     *
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> returnItems = new ArrayList<>();
        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int columStart = 0;
        int columEnd = matrix[0].length - 1;
        while (rowStart <= rowEnd && columStart <= columEnd) {
            for (int i = columStart; i <= columEnd; i++) {
                returnItems.add(matrix[rowStart][i]);
            }
            rowStart++;
            for (int i = rowStart; i <= rowEnd; i++) {
                returnItems.add(matrix[i][columEnd]);
            }
            columEnd--;
            if (columEnd < columStart) {
                break;
            }
            if (rowEnd < rowStart) {
                break;
            }
            for (int i = columEnd; i >= columStart; i--) {
                returnItems.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            for (int i = rowEnd; i >= rowStart; i--) {
                returnItems.add(matrix[i][columStart]);
            }
            columStart++;
        }
        return returnItems;
    }

    /**
     * 解题思路2.
     *
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix_1(int[][] matrix) {
        ArrayList<Integer> al = new ArrayList<>();
        int row = matrix.length;
        if (row == 0)
            return al;
        int col = matrix[0].length;
        // 短的边/2，向上取整
        int circle = ((row > col ? col : row) + 1) / 2;
        for (int i = 0; i < circle; i++) {

            // 从左向右打印，j=i; j<col-i,
            // 这一行的前i个已经在第i圈从下往上被打印，故j=i
            // 倒数i个都已经在第i圈从上往下被打印，故j=col-i-1<col-i
            for (int j = i; j < col - i; j++)
                al.add(matrix[i][j]);

            // 从上往下打印，j=i+1;j<row-i,
            // 这一列的前i+1个已经在从左向右打印时被打印,故j=i+1
            // 倒数i个已经在第i圈从右往左被打印,故j=row-i-1<row-i
            for (int j = i + 1; j < row - i; j++)
                al.add(matrix[j][col - i - 1]);

            // 从右往左打印，j=col-i-2;j>=i&&row-i-1!=i;，
            // 这一行倒数i个已经在第i圈从上往下被打印
            // 这一行倒数第i+1个已经在从上往下时被打印，故j=col-1-(i+1)=col-i-2
            // 这一行的前i个已经在从下往上时被打印，故j=i>=i
            // 当第i圈为0时即从未由上往下打印时，col有多列时，会造成重复打印，故判断row-i-1!=i以避免
            for (int j = col - i - 2; j >= i && row - i - 1 != i; j--)
                al.add(matrix[row - i - 1][j]);

            // 从下往上打印，j=row-i-2;j>i&&col-i-1!=i，
            // 这一列倒数i个已经在第i圈从右往作被打印
            // 这一列倒数第i+1个已经在从右往左时被打印，故j=row-1-(i+1)=row-i-2
            // 这一列的前i个已经在第i圈从左往右时被打印，
            // 这一列的第i+1个已经在本圈从左往右被打印，故j=i+1>i
            // 当第i圈为0时即从未由右向左打印时，row有多行时，会造成重复打印，故判断col-i-1!=i以避免
            for (int j = row - i - 2; j > i && col - i - 1 != i; j--)
                al.add(matrix[j][i]);
        }
        return al;
    }

    //-----------------------------------------------------------------------------

    /**
     * 实现一个栈的pop push top 和min(最小值)的功能，时间复杂度为O(1)
     * @param args
     */

    /**
     * 解题思路，，在时间复杂度为O(1)的情况，证明不能用循环，，只能在push值的时候就确定最小值
     * <p>
     * 1.首先定义一个正常接收数据的list，再定义一个接受比第一个数据小的数据集合
     * 2.将第一个数据放入小数据集合中，，后面如果有比一个个小的，倒序放入小集合中，使得最后一位永远是最小的数
     */

    public ArrayList<Integer> trackList = new ArrayList<Integer>();
    public ArrayList<Integer> min = new ArrayList<Integer>();
    int minValue = 0;

    public void push1(int node) {
        if (minValue == 0) {
            min.add(node);
            minValue = node;
        } else {
            if (minValue > node) {
                minValue = node;
                min.add(minValue);
            }
        }
        trackList.add(node);
    }

    public void pop1() {
        int pop = trackList.get(trackList.size() - 1);
        trackList.remove(trackList.size() - 1);
        if (minValue == pop) {
            min.remove(min.get(min.size() - 1));
            minValue = min.get(min.size() - 1);
        }
    }

    public int top() {
        return trackList.get(trackList.size() - 1);
    }

    public int min() {
        return minValue;
    }


    //-------------------------------------------------------------------------

    /**
     * 判断其中一个数组是否是另一个数组的弹出序列
     */
    /**
     * 时间复杂度o(n2)
     * 解题思路，，利用栈的弹出判断是否是另一个栈的弹出序列，，从一开始就往里面放数字
     *
     * @param pushA
     * @param popA
     * @return
     */

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<Integer>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);

            while (j < popA.length && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    //----------------------------------------------------------------------------

    /**
     * 打印二叉树
     * <p>
     * 同层次从左到右打印
     *
     * @param args
     */
//    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
//        System.out.println(root.val);
//        printTLeft(root.left);
//        printTRight(root.right);
//    }
    public void printTLeft(TreeNode root) {
        System.out.println(root.val);
        printTLeft(root.left);
    }

    public void printTRight(TreeNode root) {
        System.out.println(root.val);
        printTLeft(root.right);
    }


    public int[] twoSum(int[] nums, int target) {
        int[] returnData = new int[2];
        HashMap<Integer, Integer> nunMap = new HashMap<>();
        for (int num = 0; num < nums.length; num++) {
            nunMap.put(nums[num], num);
        }
        for (int num = 0; num < nums.length; num++) {
            int temp = 0;
            int target1 = target;
            temp = nums[num];
            target1 = target1 - temp;
            Integer nextNum = nunMap.get(target1);
            if (nextNum != null && nextNum != 0) {
                returnData[0] = num;
                returnData[1] = nextNum;
            }
        }
        return returnData;
    }

    //两数之和
    public int[] twoSum1(int[] nums, int target) {
        int[] returnData = new int[2];
        HashMap<Integer, Integer> nunMap = new HashMap<>();
        for (int num = 0; num < nums.length; num++) {
            int target1 = target;
            target1 = target1 - nums[num];
            if (nunMap.containsKey(target1)) {
                returnData[0] = nunMap.get(target1);
                returnData[1] = num;
            }
            nunMap.put(nums[num], num);
        }
        return returnData;
    }

    //两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //需要将链表的数字转换成其他格式存储
        long first = 0, secondVal = 0;
        long num = 1;
        while (l1 != null) {
            first = first + l1.val * num;
            num = num * 10;
            l1 = l1.next;
        }
        long newnum = 1;
        while (l2 != null) {
            secondVal = secondVal + l2.val * newnum;
            newnum = newnum * 10;
            l2 = l2.next;
        }
        Long returnVal = first + secondVal;
        String returnStr = returnVal.toString();
        String[] strArray = returnStr.split("");
        Stack<String> stack = new Stack<>();
        for (String str : strArray) {
            stack.push(str);
        }
        ListNode returnListNode = null;
        ListNode firstListNode = null;
        while (!stack.isEmpty()) {
            if (returnListNode == null) {
                returnListNode = new ListNode(Integer.parseInt(stack.pop()));
                firstListNode = returnListNode;
            } else {
                firstListNode.next = new ListNode(Integer.parseInt(stack.pop()));
                firstListNode = firstListNode.next;
            }
        }
        return returnListNode;
    }

    /**
     * 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        char[] strArray = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        ArrayList<String> returnArray = new ArrayList<>();
        String str = "";
        int strIndex = 0;
        for (char a : strArray) {
            if (map.containsKey(a)) {
                returnArray.add(str);
                Integer index = str.indexOf(a);
                str = str.substring(index + 1);
                map.put(a, strIndex);
                str = str + a;
            } else {
                str = str + a;
                map.put(a, strIndex);
                strIndex++;
            }
        }
        returnArray.add(str);
        int returnValue = 0;
        for (String oneStr : returnArray) {
            if (oneStr.length() > returnValue) {
                returnValue = oneStr.length();
            }
        }
        return returnValue;
    }

    /**
     * 无重复字符的最长子串
     * 较优解:没看懂
     */
    public int lengthOfLongestSubstring1(String s) {
        int l = 0;
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(l, i).indexOf(s.charAt(i)) != -1)
                l = s.indexOf(s.charAt(i), l) + 1;
            dp[i + 1] = Math.max(dp[i], i - l + 1);
        }
        return dp[s.length()];
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double[] newNums = new double[nums1.length + nums2.length];
        //可以用归并排序思想
        int j = 0;
        int i = 0;
        int t = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    newNums[t++] = nums1[i];
                    if (i < nums1.length) {
                        i++;
                    }
                } else if (nums1[i] >= nums2[j]) {
                    newNums[t++] = nums2[j];
                    if (j < nums2.length) {
                        j++;
                    }
                }
            } else {
                if (i == nums1.length) {
                    newNums[t++] = nums2[j];
                    j++;
                } else {
                    newNums[t++] = nums1[i];
                    i++;
                }
            }
        }
        double returnValue = 0.0;
        if (newNums.length % 2 != 0) {
            returnValue = newNums[newNums.length / 2];
        } else {
            returnValue = (newNums[newNums.length / 2 - 1] + newNums[newNums.length / 2]) / 2;
        }
        return returnValue;
    }

    public String longestPalindrome(String s) {
        String[] array = s.split("");
        String reverser = new StringBuilder().append(s).reverse().toString();
        String[] reverArray = reverser.split("");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < reverArray.length; j++) {
                if (array[i] == reverArray[j]) {

                }
            }
        }
        String returnStr = "";
        return returnStr;
    }

    public static int longestSlideDown(int[][] pyramid) {
        // Code Goes Here..
        for (int j = pyramid.length - 1; j >= 0; j--) {
            for (int i = 0; i < pyramid[j].length - 1; i++) {
                if (pyramid[j][i] < pyramid[j][i + 1]) {
                    pyramid[j - 1][i] = pyramid[j - 1][i] + pyramid[j][i + 1];
                } else {
                    pyramid[j - 1][i] = pyramid[j - 1][i] + pyramid[j][i];
                }
            }
        }
        return pyramid[0][0];
    }

    /**
     * 前序遍历创建二叉树
     *
     * @param inputList
     * @return
     */
    public static TreeNode createTreeNode(LinkedList<Integer> inputList) {
        if (inputList == null || inputList.size() == 0) {
            return null;
        }
        Integer data = inputList.removeFirst();
        TreeNode treeNode = null;
        if (data != null) {
            treeNode = new TreeNode(data);
            treeNode.left = createTreeNode(inputList);
            treeNode.right = createTreeNode(inputList);
        }
        return treeNode;
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

    public static void printLevelTree(TreeNode treeNode) {
        LinkedList<TreeNode> linkedList = new LinkedList();
        linkedList.add(treeNode);
        TreeNode temp = null;
        while (!linkedList.isEmpty()) {
            temp = linkedList.peek();
            System.out.println(temp.val);
            if (temp.left != null) {
                linkedList.add(temp.left);
            }
            if (temp.right != null) {
                linkedList.add(temp.right);
            }
            linkedList.removeFirst();
        }
    }

    /**
     * 前序遍历
     *
     * @param treeNode
     */
    public static void preTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        preTraverse(treeNode.left);
        preTraverse(treeNode.right);
    }

    public static void twoTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;

        twoTree(treeNode.left);
        System.out.println(treeNode.val);
        twoTree(treeNode.right);
    }

    public static void inTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inTraverse(treeNode.left);
        System.out.println(treeNode.val);
        inTraverse(treeNode.right);
    }

    public static void lastTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        lastTraverse(treeNode.left);
        lastTraverse(treeNode.right);
        System.out.println(treeNode.val);
    }

    /**
     * 非递归做前序遍历
     */
    public static void nonRecursionPrevTraverse(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        if (treeNode == null) {
            return;
        }
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.println(treeNode.val);
                stack.push(treeNode);
                treeNode = treeNode.left;
            }

            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 非递归做前序遍历
     */
    public static void nonRecursionInTraverse(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }

            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.println(treeNode.val);
                treeNode = treeNode.right;
            }
        }
    }

    public int[] maopao(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        return a;
    }

    /**
     * 遍历所有文件
     * @param path
     */
    public void printDir(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if(file1.isDirectory()){
                printDir(file1.getPath());
            }else {
                System.out.println(file1.getName());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NiuKeSolution().printDir("C:\\Users\\admin\\Desktop\\ue4文档");
//        LinkedList<Integer> inputList = new LinkedList<>(
//                Arrays.asList(new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4}));
//        int[] a = {6, 1, 4, 2, 1, 5, 8, 21, 55, 3, 2, 10, 91, 0};
//        a = new NiuKeSolution().maopao(a);
//        TreeNode root = levelCreateTreeNode(inputList);
//        twoTree(root);
//        System.out.println("前序");
//        nonRecursionPrevTraverse(root);
//        System.out.println("中序");
//        printLevelTree(root);
//        Long s = System.currentTimeMillis();
//        String str = "tiapwqirjasjfasjfasifjvjzxvuzxvjxznvnsjsafasnfasjvxvasfnasjfasfhhzxvuzxvxznvzxggggggxxxxxxxxxxxxxxxccccccccccvasaaaaaaaaagdetwewqrwqoiuroiuyerpyfbnx,bmcvxn,blkjdafsf";
//        Set setText2 = new HashSet<>(Arrays.asList(
//                str.split(""))).stream().map(String :: toUpperCase).collect(Collectors.toSet());
//        System.out.println(System.currentTimeMillis()-s + ":" + setText2);


//        Long s1 = System.currentTimeMillis();
//        String str2 = "tiapwqirjasjfasjfasifjvjzxvuzxvjxznvnsjsafasnfasjvxvasfnasjfasfhhzxvuzxvxznvzxggggggxxxxxxxxxxxxxxxccccccccccvasaaaaaaaaagdetwewqrwqoiuroiuyerpyfbnx,bmcvxn,blkjdafsf";
//        Set setText = new HashSet<>(Arrays.asList(
//                str2.split(""))).stream().map(p -> p.toUpperCase()).collect(Collectors.toSet());
//        System.out.println(System.currentTimeMillis()-s1 + ":" + setText);
    }

}
