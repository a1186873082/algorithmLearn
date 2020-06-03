package com.lc.Text.jvm;

/**
 * 动态分配，再运行期根据实际类型确定方法执行版本的分派过程叫做动态分派
 */
public class OverrideTest {

    public static class HuMan{
        public void say(){
            System.out.println("HuMan");
        }
    }

    public static class Man extends HuMan{
        @Override
        public void say() {
            System.out.println("man");
        }
    }

    public static class WoMan extends HuMan{
        @Override
        public void say() {
            System.out.println("woman");
        }
    }

    public static void main(String[] args) {
        HuMan man = new Man();
        HuMan woman = new WoMan();
        man.say();
        woman.say();
        man = new WoMan();
        man.say();
    }
}
