package com.github.kntmr;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class StreamAPITests {

    @Test
    public void test00() {
        Stream<String> stream = Stream.of("banana", "apple", "orange")
                .filter(str -> str.length() > 5)
                .peek(str -> System.out.print(str + " "))
                .map(str -> str.toUpperCase())
                .peek(str -> System.out.print(str + " "));
        System.out.print("OK! ");
        // 終端操作 (このタイミングで中間処理が実行される)
        System.out.print("count=" + stream.count());
    }

    @Test
    public void test01() {
        Stream.Builder<String> builder = Stream.builder();
        builder.accept("foo"); // void
        builder.add("bar").add("baz"); // Stream.Builderを返す
        Stream<String> stream = builder.build();
        stream.forEach(System.out::println);
    }

    @Test
    public void test02() {
        List<String> source = Arrays.asList("foo", "bar", "baz");

        System.out.println(source.stream().allMatch(str -> str.length() == 3));
        System.out.println(source.stream().allMatch(str -> str.startsWith("b")));

        System.out.println(source.stream().anyMatch(str -> str.startsWith("f")));
        System.out.println(source.stream().anyMatch(str -> str.length() == 4));
    }

    @Test
    public void test03() {
        List<String> source = Arrays.asList("foo", "bar", "baz");

        System.out.println(source.stream().findAny().orElseGet(() -> "NOT FOUND"));
        System.out.println(Stream.empty().findAny().orElseGet(() -> "NOT FOUND"));

        System.out.println(source.stream().findFirst().orElseGet(() -> "NOT FOUND"));
        System.out.println(Stream.empty().findFirst().orElseGet(() -> "NOT FOUND"));
    }

    @Test
    public void test04() {
        Collector<Character, StringBuilder, String> collector1 = Collector.of(
                () -> new StringBuilder(),
                (sb, c) -> sb.append(c),
                (sb1, sb2) -> sb1.append(sb2),
                sb -> sb.toString()
        );
        System.out.println(Stream.of('h', 'e', 'l', 'l', 'o').collect(collector1));

        Collector<Character, StringBuilder, String> collector2 = Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString
        );
        System.out.println(Stream.of('h', 'e', 'l', 'l', 'o').collect(collector2));
    }

}
