package com.github.kntmr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentTests {

    @Test
    public void test01() {
        ExecutorService es = Executors.newSingleThreadExecutor(); // 1スレッド
        for (int i = 0; i < 5; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getId()));
        }
    }

    @Test
    public void test02() {
        ExecutorService es = Executors.newFixedThreadPool(3); // 3スレッド
        for (int i = 0; i < 5; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getId()));
        }
    }

    @Test
    public void test03() throws Exception {
        ExecutorService es = Executors.newCachedThreadPool(); // 60秒キャッシュする
        for (int i = 0; i < 5; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getId()));
        }

        Thread.sleep(5000);
        System.out.println("----- after 5 sec -----");

        for (int i = 0; i < 5; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getId()));
        }

        Thread.sleep(70000);
        System.out.println("----- after 70 sec -----");

        for (int i = 0; i < 5; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getId()));
        }
    }

    @Test
    public void test04() throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<Long>> fs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fs.add(es.submit(() -> Thread.currentThread().getId()));
        }

        for (Future<Long> f : fs) {
            System.out.println(f.get()); // 実行結果を取得する
        }
    }

    @Test
    public void test05() throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<Long>> fs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fs.add(es.submit(() -> {
                Thread.sleep(3000);
                return Thread.currentThread().getId();
            }));
        }

        try {
            for (Future<Long> f : fs) {
                System.out.println(f.get(1, TimeUnit.SECONDS)); // 1秒待つ
            }
        } catch (TimeoutException e) {
            System.out.println("Timeout... > " + e); // タイムアウト
        }
    }

    @Test
    public void test06() throws Exception {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
        ScheduledFuture<?> f = es.scheduleWithFixedDelay(
                () -> System.out.println(Thread.currentThread().getId()), 5, 3, TimeUnit.SECONDS); // 5秒遅延してから3秒間隔でタスクを実行する

        Thread.sleep(30000);
        f.cancel(true); // タスクの取り消し
    }

}
