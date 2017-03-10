package com.github.kntmr;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

}
