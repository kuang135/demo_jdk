package com.demo.jdk8;

import org.junit.Test;

/**
 *  default关键字给接口添加默认方法
 *  static关键字给接口添加静态方法
 *  默认方法 不是抽象方法
 *
 *  Java 8要充分利用Lambda，需要增强大量的类库，但是又希望做到兼容性，只能用默认方法这个大招。
 *  比如 Comparator 接口
 *
 */
public class J1_InterfaceDefaultMethod {

    interface Formula {
        double calculate(int a);
        default double sqrt(int a) {
            return Math.sqrt(a);
        }
        static double abs(int s) {
            return Math.abs(s);
        }
    }

    @Test
    public void test() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(100));
        System.out.println(Formula.abs(-100));
    }

}
