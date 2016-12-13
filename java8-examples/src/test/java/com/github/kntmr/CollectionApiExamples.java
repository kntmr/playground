package com.github.kntmr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionApiExamples {

    @Test
    public void test01() {
        List<String> list = new ArrayList<>(Arrays.asList("foo", "bar", "baz"));

        System.out.println(">> " + list);
        list.removeIf(str -> str.startsWith("f"));
        System.out.println("<< " + list);
    }

    @Test
    public void test02() {
        List<String> list = new ArrayList<>(Arrays.asList("foo", "bar", "baz"));

        System.out.println(">> " + list);
        list.replaceAll(str -> str.toUpperCase());
        System.out.println("<< " + list);
    }

    @Test
    public void test03() {
        List<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "orange"));

        System.out.println(">> " + list);
        list.sort((s1, s2) -> s2.compareTo(s1)); // 逆順
        System.out.println("<< " + list);
    }

}
