package com.github.kntmr;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapAPIPractices {

    @Test
    public void test01() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        System.out.println(">> " + map);
        map.compute("foo", (key, value) -> value.toLowerCase());
        map.compute("bar", (key, value) -> "XYZ");
        map.compute("baz", (key, value) -> null); // bazのエントリは削除される
        System.out.println("<< " + map);
    }

    @Test
    public void test02() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        System.out.println(">> " + map);
        // キーに紐づくエントリが存在しない場合にmappingFunctionが実行される
        map.computeIfAbsent("foo", (key) -> "XYZ"); // mappingFunctionは実行されない
        map.computeIfAbsent("abc", (key) -> "ABC"); // 新しいエントリが追加される
        map.computeIfAbsent("xyz", (key) -> null); // 追加されない
        System.out.println("<< " + map);
    }

    @Test
    public void test03() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        System.out.println(">> " + map);
        // キーに紐づくエントリが存在する場合にremappingFunctionが実行される
        map.computeIfPresent("foo", (key, value) -> value.toLowerCase());
        map.computeIfPresent("xyz", (key, value) -> "XYZ"); // remappingFunctionは実行されない
        map.computeIfPresent("baz", (key, value) -> null); // bazのエントリは削除される
        System.out.println("<<  " + map);
    }

    @Test
    public void test04() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        map.forEach((key, value) -> System.out.printf("key=%s, value=%s%n", key, value));
    }

    @Test
    public void test05() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        System.out.println(map.getOrDefault("foo", "default value")); // FOOが出力される
        System.out.println(map.getOrDefault("xyz", "default value")); // defaultValueが出力される
    }

    @Test
    public void test06() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        System.out.println(">> " + map);
        map.merge("foo", "Foo", (value1, value2) -> value1.concat(value2)); // remappingFunctionが実行される
        map.merge("xyz", "XYZ", (value1, value2) -> value1.concat(value2)); // 新しいエントリが追加される
        map.merge("baz", "XYZ", (value1, value2) -> null); // bazのエントリは削除される
        System.out.println("<< " + map);
    }

    @Test
    public void test07() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        System.out.println(">> " + map);
        map.remove("foo", "XYZ"); // 削除されない
        map.remove("abc", "FOO"); // 削除されない
        map.remove("baz", "BAZ"); // 削除される
        System.out.println("<< " + map);
    }

    @Test
    public void test08() {
        Map<String, String> map = new HashMap<>();
        map.put("foo", "FOO");
        map.put("bar", "BAR");
        map.put("baz", "BAZ");

        System.out.println(">> " + map);
        map.replace("foo", "XYZ", "Foo"); // 更新されない
        map.replace("abc", "FOO", "Foo"); // 更新されない
        map.replace("bar", "BAR", "Bar"); // 更新される
        map.replace("baz", "Baz"); // 更新される
        map.replace("abc", "ABC");
        System.out.println("<< " + map);
    }

}
