package com.github.kntmr;

import org.junit.Test;

import java.util.Base64;

public class Base64Tests {

    @Test
    public void test01() {
        String source = "hogefuga";

        String encoded = Base64.getEncoder().encodeToString(source.getBytes());
        System.out.println("encodeToString : " + source + " -> " + encoded);

        byte[] decoded = Base64.getDecoder().decode(encoded);
        System.out.println("decode : " + encoded + " -> " + new String(decoded));
    }

}
