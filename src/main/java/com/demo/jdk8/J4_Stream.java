package com.demo.jdk8;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Stream的功能是, 支持集合的各种操作, 比如filter, sum, max, min, average, map, reduce等等
 * Stream的出现是基于以下原因:
 *      增强集合操作
 *      拥抱函数式编程
 *      充分利用Lambda
 *      执行效率的提高 - 透明支持多线程集合操作
 *  任务简单的情况，For Loop更快，任务复杂一点，并行Stream后来居上，并行带来的改进足以cover创建Stream的开销
 *  创建Stream
 *      通过Stream接口的静态工厂方法
 *      通过Collection接口的默认方法
 *  filter, sort, map, match, count, reduce
 */
public class J4_Stream {
    class TimeRecorder {
        private long startTime;
        private long endTime;
        void start() {
            startTime = System.currentTimeMillis();
        }
        long end() {
            endTime = System.currentTimeMillis();
            return endTime - startTime;
        }
        long getDuration() {
            return endTime - startTime;
        }
    }

    @Test
    public void test() {
        List<Integer> intList = new LinkedList<>();
        for (int i = 1; i <= 1000000; i++) {
            intList.add(i);
        }
        TimeRecorder recorder = new TimeRecorder();
        recorder.start();
        intList.stream().forEach(i -> i.intValue());
        recorder.end();
        System.out.println("Stream iterator: " + recorder.getDuration());

        recorder.start();
        intList.parallelStream().forEach(i -> i.intValue());
        recorder.end();
        System.out.println("ParallelStream iterator: " + recorder.getDuration());

        recorder.start();
        for (Integer i : intList) {
            i.intValue();
        }
        recorder.end();
        System.out.println("Normal iterator: " + recorder.getDuration());
    }

    @Test
    public void test2() {
        List<Integer> intList = new LinkedList<>();
        for (int i = 1; i <= 1000000; i++) {
            intList.add(i);
        }
        TimeRecorder recorder = new TimeRecorder();
        recorder.start();
        intList.stream().forEach(i -> {
            i.intValue();
            i.intValue();
            i.toString();

            i.intValue();
            i.intValue();
            i.toString();
        });
        recorder.end();
        System.out.println("Stream iterator: " + recorder.getDuration());

        recorder.start();
        intList.parallelStream().forEach(i -> {
            i.intValue();
            i.intValue();
            i.toString();

            i.intValue();
            i.intValue();
            i.toString();
        });
        recorder.end();
        System.out.println("ParallelStream iterator: " + recorder.getDuration());

        recorder.start();
        for (Integer i : intList) {
            i.intValue();
            i.intValue();
            i.toString();

            i.intValue();
            i.intValue();
            i.toString();
        }
        recorder.end();
        System.out.println("Normal iterator: " + recorder.getDuration());
    }

}