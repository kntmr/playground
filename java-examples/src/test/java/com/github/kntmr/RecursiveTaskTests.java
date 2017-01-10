package com.github.kntmr;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskTests {

    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ForkJoinPool service = new ForkJoinPool();
        int ret = service.invoke(new MyTask(list));
        System.out.println(ret);
    }

    class MyTask extends RecursiveTask<Integer> {

        private static final int DEFAULT_THRESHOLD = 4;
        private List<Integer> list;
        private int threshold;

        public MyTask(List<Integer> list) {
            this(list, DEFAULT_THRESHOLD);
        }

        public MyTask(List<Integer> list, int threshold) {
            this.list = list;
            this.threshold = threshold;
        }

        @Override
        protected Integer compute() {
            if (this.list.size() < threshold) {
                return list.stream().reduce(0, (i, j) -> i + j);
            } else {
                int pivot = list.size() / 2;

                MyTask task1 = new MyTask(list.subList(0, pivot), threshold);
                task1.fork(); // task1を非同期に実行

                MyTask task2 = new MyTask(list.subList(pivot, list.size()), threshold);
                return task2.compute() + task1.join(); // computeでtask2を再帰的に実行 + task1の実行結果を待つ
            }
        }

    }

}
