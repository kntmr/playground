package com.github.kntmr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamApiExamples {

    @Test
    public void test00() {
        Stream<String> stream = Stream.of("banana", "apple", "orange")
                .filter(str -> str.length() > 5)
                .peek(str -> System.out.print(str + " "))
                .map(str -> str.toUpperCase())
                .peek(str -> System.out.print(str + " "));
        System.out.print("OK! ");
        System.out.print("count=" + stream.count()); // 終端操作
    }

}
