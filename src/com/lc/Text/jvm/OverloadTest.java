package com.lc.Text.jvm;

import java.io.Serializable;

/**
 * 静态分派，与重载挂钩，编译阶段依赖静态类型来定位方法执行版本的分派动作
 */
public class OverloadTest {
    public static void sayHello(Object arg){
        System.out.println("hello Object");
    }

    public static void sayHello(int arg){
        System.out.println("hello int");
    }

    public static void sayHello(char arg){
        System.out.println("hello char");
    }

    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }

    public static void sayHello(Character arg){
        System.out.println("hello Character");
    }

    public static void sayHello(long arg){
        System.out.println("hello long");
    }

    public static void sayHello(Serializable serializable){
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        OverloadTest.sayHello('a');
    }
}

