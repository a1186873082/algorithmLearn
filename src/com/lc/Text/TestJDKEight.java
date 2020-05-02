package com.lc.Text;

import com.lc.model.Account;
import com.lc.model.Trader;
import com.lc.model.Transaction;
import com.lc.model.User;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestJDKEight {

    /**
     * 双list相加所得
     */
    public void test1() {
        User user = new User(1, "123456");
        User user2 = new User(2, "123457");
        User user3 = new User(3, "123458");
        User user4 = new User(4, "123459");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        Account account = new Account();
        account.setAccountId("1");
        account.setAmount(BigDecimal.TEN);
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            accountList.add(account);
        }
        user.setAccountList(accountList);
        user2.setAccountList(accountList);
        user3.setAccountList(accountList);

        long count = userList.stream().map(p -> {
            long t = 0;
            if (p.getAccountList() != null && p.getAccountList().size() > 0) {
                t = p.getAccountList().stream().count();
            }
            return t;
        }).reduce(0L, Long::sum);
        System.out.println(count);
    }

    public void test2() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> transactions1 = transactions.stream().filter(p -> 2011 == p.getYear()).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(transactions1.toString());
        //交易员都在哪些不同的城市工作过
        List<String> strList = transactions.stream().map(p -> p.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(strList);
        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> traderList = transactions.stream().map(p -> p.getTrader()).filter(p -> p.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(traderList);
        //返回所有交易员的姓名字符串，按字母顺序排序
        String nameStr = transactions.stream().map(p -> p.getTrader().getName()).sorted().distinct().reduce((a, b) -> a + ";" + b).orElse("");
        System.out.println(nameStr);
        //有没有交易员是在米兰工作的？
//        boolean b = transactions.stream().map(p -> p.getTrader()).anyMatch(p -> "Milan".equals(p.getCity()));
        //或
//        boolean b = transactions.stream().anyMatch(p -> "Milan".equals(p.getTrader().getCity()));
//        System.out.println(b);
        //打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(p -> "Cambridge".equals(p.getTrader().getCity())).forEach(p -> System.out.println(p.getTrader().getName() + ":" + p.getValue()));
        // 所有交易中，高的交易额是多少
        int max = transactions.stream().mapToInt(p -> p.getValue()).max().orElse(0);
        System.out.println(max);
        //找到交易额小的交易。
        int min = transactions.stream().map(p -> p.getValue()).reduce(Integer::min).orElse(0);
        System.out.println(min);
        Optional<Transaction> transaction = transactions.stream().reduce((a, y) -> a.getValue() < y.getValue() ? a : y);
        System.out.println(transaction.orElse(new Transaction()));
        //求出0-100中的偶数
        long count = IntStream.rangeClosed(1, 100).filter(p -> p % 2 != 0).count();
        System.out.println(count);
        //已知a生成b
//        IntStream.rangeClosed(1,100).filter(f -> Math.sqrt(f*f + j*j)%1 == 0).boxed().map(p -> new int[]{a, b (int)Math.sqrt(f*f + j*j)});
//        IntStream.rangeClosed(1, 100)
//                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
//                .boxed()
//                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
        //a,b均为生成数值 (找出100内，可生成直角三角形的数字集合)
//        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
//                .flatMap(a -> IntStream.rangeClosed(a, 100).
//                        filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
//                );
//        pythagoreanTriples.forEach(p -> {
//            for (int i = 0; i < p.length - 1; i++) {
//                System.out.printf(p[i] + ";");
//            }
//            System.out.println(p[p.length - 1]);
//        });
        //优化勾股数的取值
        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).
                        mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}).
                        filter(p -> p[2] % 1 == 0));
        pythagoreanTriples2.forEach(p -> {
            for (int i = 0; i < p.length - 1; i++) {
                System.out.printf((int) p[i] + ";");
            }
            System.out.println((int) p[p.length - 1]);
        });
        //斐波那契数列
        //用iterate
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).forEach(t -> System.out.println(t[0] + "," + t[1]));
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).map(p -> p[0]).limit(20).forEach(System.out::print);
        //用generate
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldValue = this.previous;
                int newValue = this.previous + this.current;
                this.previous = current;
                this.current = newValue;
                return oldValue;
            }
        };
        IntStream.generate(fib).limit(20).forEach(System.out::print);

    }

    public static void main(String[] args) {

        new TestJDKEight().test2();

    }
}
