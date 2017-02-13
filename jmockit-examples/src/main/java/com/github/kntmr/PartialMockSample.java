package com.github.kntmr;

public class PartialMockSample {

    int value;

    public PartialMockSample() {
        this.value = -1;
    }

    public PartialMockSample(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    boolean method1() {
        return true;
    }

    static void method2() {
        throw new IllegalStateException();
    }

}
