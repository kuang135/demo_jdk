package com.demo.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * lambda表达式
 *      (String a, String b) -> {return b.compareTo(a);}
 *      (a, b) -> b.compareTo(a)
 */
public class J2_Lambda {

    //@FunctionalInterface
    interface MyFunctionalInterface {
        void println(String name);
    }
    private void forEach(List<String> names, MyFunctionalInterface function) {
        for (String name : names) {
            function.println(name);
        }
    }

    @Test
    public void lambda() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //传统方式
        forEach(names, new MyFunctionalInterface() {
            @Override
            public void println(String name) {
                System.out.println(name);
            }
        });
        System.out.println("");
        //lambda方式, lambda表达式当作任意只包含一个抽象方法的接口类型
        //在接口上添加 @FunctionalInterface 可以确保接口只有一个抽象方法
        MyFunctionalInterface function = name -> System.out.println(name);
        forEach(names, function);
    }

    @Test
    public void lambda2() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        names.forEach(name -> System.out.println(name));
    }

}
