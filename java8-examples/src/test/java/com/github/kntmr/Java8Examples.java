package com.github.kntmr;

import org.junit.Test;

import java.util.*;

public class Java8Examples {

    @Test
    public void test01() {
        List<String> list = new ArrayList<>(Arrays.asList("foo", "bar", "baz"));

        System.out.println(list);
        list.removeIf(str -> str.startsWith("f"));
        System.out.println(list);
    }

    @Test
    public void test02() {
        List<String> list = new ArrayList<>(Arrays.asList("foo", "bar", "baz"));

        System.out.println(list);
        list.replaceAll(str -> str.toUpperCase());
        System.out.println(list);
    }

    @Test
    public void test03() {
        List<String> list = new ArrayList<>(Arrays.asList("banana", "apple", "orange"));

        System.out.println(list);
        list.sort((s1, s2) -> s2.compareTo(s1)); // 逆順
        System.out.println(list);
    }

    @Test
    public void test04() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        System.out.println(map);
        map.compute("foo", (key, value) -> value.toUpperCase());
        map.compute("bar", (key, value) -> "XXXX");
        map.compute("baz", (key, value) -> null); // baz が削除される
        System.out.println(map);
    }

    @Test
    public void test05() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        System.out.println(map);
        map.computeIfAbsent("foo", (key) -> "HOGE"); // mappingFunction は実行されない
        map.computeIfAbsent("xxx", (key) -> "XXXX"); // 新しく要素が追加される
        map.computeIfAbsent("yyy", (key) -> null); // 追加されない
        System.out.println(map);
    }

    @Test
    public void test06() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        System.out.println(map);
        map.computeIfPresent("foo", (key, value) -> value.toUpperCase());
        map.computeIfPresent("xxx", (key, value) -> "XXXX"); // remappingFunction は実行されない
        map.computeIfPresent("baz", (key, value) -> null); // baz が削除される
        System.out.println(map);
    }

}
