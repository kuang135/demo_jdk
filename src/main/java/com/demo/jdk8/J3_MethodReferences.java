package com.demo.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Java 8中方法也是一种对象，可以By名字来引用
 * 不过方法引用的唯一用途是支持Lambda的简写，使用方法名称来表示Lambda
 * 不能通过方法引用来获得诸如方法签名的相关信息
 * 方法引用仅仅是Lambda的配套服务，主要目的是通过名字来获得Lambda，重复利用已有的方法
 *      引用静态方法
            ContainingClass::staticMethodName
            例子: String::valueOf，对应的Lambda：(s) -> String.valueOf(s)
            比较容易理解，和静态方法调用相比，只是把.换为::
        引用特定对象的实例方法
            containingObject::instanceMethodName
            例子: x::toString，对应的Lambda：() -> this.toString()
            与引用静态方法相比，都换为实例的而已
        引用特定类型的任意对象的实例方法
            ContainingType::methodName
            例子: String::toString，对应的Lambda：(s) -> s.toString()
            太难以理解了。难以理解的东西，也难以维护。建议还是不要用该种方法引用。
            实例方法要通过对象来调用，方法引用对应Lambda，Lambda的第一个参数会成为调用实例方法的对象。
        引用构造函数
            ClassName::new
            例子: String::new，对应的Lambda：() -> new String()
            构造函数本质上是静态方法，只是方法名字比较特殊。
 */
public class J3_MethodReferences {

    static class Person {
        String name;
        public String getName() {
            return name;
        }
        void setName(String name) {
            this.name = name;
        }
        static int staticCompare(Person p1, Person p2) {
            return p1.name.compareTo(p2.name);
        }
        int compare(Person p1, Person p2) {
            return p2.name.compareTo(p1.name);
        }
        int compareTo(Person p) {
            return this.name.compareTo(p.name);
        }
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    class PersonFactory {
        private Supplier<Person> supplier;
        PersonFactory(Supplier<Person> supplier) {
            this.supplier = supplier;
        }
        Person getPerson() {
            return supplier.get();
        }
    }

    /**
     * http://blog.csdn.net/kimylrong/article/details/47255123
     */
    @Test
    public void test2() {
        /** 引用构造函数 ClassName::new */
        PersonFactory factory = new PersonFactory(Person::new);//() -> new Person()
        Person kobe = factory.getPerson();
        Person james = factory.getPerson();
        Person paul = factory.getPerson();
        kobe.setName("Kobe");
        james.setName("James");
        paul.setName("Paul");
        List<Person> players = new ArrayList<>();
        players.add(kobe);
        players.add(james);
        players.add(paul);

        players.forEach(System.out::println);
        System.out.println("");

        /** 引用特定类型的任意对象的实例方法 ContainingType::methodName */
        players.sort(Person::compareTo);//(p) -> p.compareTo()
        players.forEach(System.out::println);
        System.out.println("");

        /** 引用特定对象的实例方法 containingObject::instanceMethodName */
        players.sort(paul::compare);//(p1, p2) -> paul.compare(p1, p2)
        players.forEach(System.out::println);
        System.out.println("");

        /** 引用静态方法 ContainingClass::staticMethodName */
        players.sort(Person::staticCompare);//(p1, p2) -> Person.staticCompare(p1, p2)
        players.forEach(System.out::println);
    }

}
