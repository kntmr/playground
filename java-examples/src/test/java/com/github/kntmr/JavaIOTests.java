package com.github.kntmr;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JavaIOTests {

    @Test
    public void test01() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            System.out.print(br.readLine());
            //br.mark(3); // java.io.IOException: Mark invalid
            br.mark(4);
            System.out.print(br.readLine());
            br.reset();
            System.out.print(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        try (Stream<Path> stream = Files.list(Paths.get("dir"))) { // 指定ディレクトリ直下のみ
            stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03() {
        try (Stream<Path> stream = Files.walk(Paths.get("dir"))) { // 指定ディレクトリ配下を再帰的に探索
            stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Monitor implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("Monitor closed.");
        }
        void monitor() throws Exception {
            System.out.println("Monitoring...");
            throw new Exception("Unable to monitor");
        }
    }

    static class Filter implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("Filter closed.");
        }
        void filter() {
            System.out.println("Filtering...");
        }
    }

    @Test
    public void test04() {
        try (Monitor m = new Monitor();
             Filter f = new Filter()) {
            m.monitor(); // ここで例外がスローされる
            f.filter();
        } catch (Exception e) {
            // catch に入る前に close が呼ばれる
            System.out.println(e.getMessage());
        }
    }

}
