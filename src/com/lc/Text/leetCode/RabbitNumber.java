package com.lc.Text.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 森林中兔子的数量
 * <p>
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 * <p>
 * 返回森林中兔子的最少数量。
 * <p>
 * 输入： [1, 1, 2]
 * 输出： 5
 * 解释：两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 */
public class RabbitNumber {

    /**
     * 贪心算法
     * 两只颜色相同的兔子看到其他同色兔子数必然是相同的。反之，如果两只兔子看到其他同色兔子数不同，则颜色必不相同
     *
     * 将answer中值相同的分为一组，计算每一组最小兔子数量，最后组的累加即为所求
     *
     * 例如，现在有 13 只兔子回答 5。假设其中有一只红色的兔子，那么森林中必然有 6 只红兔子。再假设其中还有一只蓝色的兔子，同样的道理森林中必然有 6 只蓝兔子。
     * 为了最小化可能的兔子数量，我们假设这 12 只兔子都在这 13 只兔子中。那么还有一只额外的兔子回答 5，这只兔子只能是其他的颜色，这一颜色的兔子也有 6 只。
     * 因此这种情况下最少会有 18 只兔子。
     * 如果有X只兔子回答y
     * 即得出公式   x / (y+1) * (y+1) = 兔子最小数量
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int returnValue = 0;
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            int y = integerIntegerEntry.getKey(), x = integerIntegerEntry.getValue();
            returnValue += (x + y) / (y + 1) * (y + 1);
        }
        return returnValue;
    }

    /**
     * 解法二：
     * 遵循3点思想
     * 1. 喊0的兔子，一定只有一只
     * 2. answer中数量相同的兔子，当出现次数 < 颜色数，代表最少只有一种颜色，当出现次数 > 颜色数时，代表有多种颜色
     * 3. 喊1的兔子，在兔子中最少有2只，以此类推
     * @param answers
     * @return
     */
    public int numRabbits1(int[] answers){
        HashMap<Integer, Integer> map = new HashMap<>();
        int returnValue = 0;
        for (int answer : answers) {
            if (answer > 0) {
                if (map.containsKey(answer)) {
                    Integer integer = map.get(answer);
                    if (integer > answer) {
                        returnValue += integer;
                        map.put(answer, 1);
                    }else{
                        map.put(answer, ++integer);
                    }
                } else {
                    map.put(answer, 1);
                }
            } else {
                returnValue++;
            }
        }
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            returnValue += (integerIntegerEntry.getKey() + 1);
        }
        return returnValue;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 0, 1, 0, 0, 10, 0, 7};
        System.out.println(new RabbitNumber().numRabbits(nums));
    }
}
