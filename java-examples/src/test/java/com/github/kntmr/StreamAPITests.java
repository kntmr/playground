package com.github.kntmr;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    @Test
    public void test05() {
        Stream<Integer> source0 = Stream.of(1, 2, 3, 4, 5 ,6, 7, 8, 9, 10);
        System.out.println(source0.reduce(0, (i, j) -> i + j));

        IntStream source1 = IntStream.rangeClosed(1, 10);
        System.out.println(source1.sum());
    }

    @Test
    public void test06() {
        List<Musician> musicians = Arrays.asList(
            new Musician("Jimi Hendrix", Musician.Category.ROCK),
            new Musician("Eric Dolphy", Musician.Category.JAZZ),
            new Musician("J.S.Bach", Musician.Category.CLASSICAL),
            new Musician("Charles Mingus", Musician.Category.JAZZ)
        );

        Map<Musician.Category, List<String>> map = musicians.stream()
            .collect(Collectors.groupingBy(
                    Musician::getCategory,
                    Collectors.mapping(Musician::getName, Collectors.toList())));

        System.out.println(map);
    }

    static class Musician {

        public enum Category {
            ROCK, JAZZ, CLASSICAL;
        }

        private String name;
        private Category category;

        public Musician(String name, Category category) {
            this.name = name;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public Category getCategory() {
            return category;
        }

    }

    @Test
    public void test07() {
        List<Integer> list = Arrays.asList(7, 2, 5);
        System.out.println(list.stream().min(Comparator.comparing(n -> n)).get());
        // Function.identity() は入力引数を返す関数を返す
        System.out.println(list.stream().min(Comparator.comparing(Function.identity())).get());
    }

    @Test
    public void test08() {
        Stream<String> stream = Stream.of("foo", "bar", "hoge");
        System.out.println(stream.collect(Collectors.partitioningBy(s -> s.contains("a"))));
    }

    @Test
    public void test09() {
        List<List<List<String>>> list = Arrays.asList(
                Arrays.asList(
                        Arrays.asList("A", "B"),
                        Arrays.asList("C", "D", "E"),
                        Arrays.asList("F")
                ),
                Arrays.asList(
                        Arrays.asList("e", "a", "d"),
                        Arrays.asList("c", "f")
                )
        );
        list.stream() // => List<List<List<String>>>
                .flatMap(Collection::stream) // => List<List<String>>
                .filter(l -> l.size() > 2)
                .flatMap(Collection::stream) // => List<String>
                .map(String::toLowerCase)
                .distinct()
                .forEach(System.out::print);
    }

}
