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

    @Test
    public void test07() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        map.forEach((key, value) -> System.out.printf("key=%s, value=%s%n", key, value));
    }

    @Test
    public void test08() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        System.out.println(map.getOrDefault("bar", "default bar")); // bar の値が出力される
        System.out.println(map.getOrDefault("xxx", "default value")); // defaultValue が出力される
    }

    @Test
    public void test09() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        System.out.println(map);
        map.merge("foo", "HOGE", (value1, value2) -> value1.concat(value2)); // remappingFunction が実行される
        map.merge("xxx", "XXXX", (value1, value2) -> value1.concat(value2)); // 新しく要素が追加される
        map.merge("baz", "PIYO", (value1, value2) -> null); // baz が削除される
        System.out.println(map);
    }

    @Test
    public void test10() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        System.out.println(map);
        map.remove("foo", "XXXX"); // 削除されない
        map.remove("xxx", "hoge"); // 削除されない
        map.remove("baz", "piyo"); // 削除される
        System.out.println(map);
    }

    @Test
    public void test11() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "hoge");
        map.put("bar", "fuga");
        map.put("baz", "piyo");

        System.out.println(map);
        map.replace("foo", "XXXX", "HOGE"); // 更新されない
        map.replace("xxx", "hoge", "HOGE"); // 更新されない
        map.replace("baz", "piyo", "PIYO"); // 更新される
        map.replace("bar", "FUGA"); // 更新される
        map.replace("xxx", "XXXX");
        System.out.println(map);
    }

}
