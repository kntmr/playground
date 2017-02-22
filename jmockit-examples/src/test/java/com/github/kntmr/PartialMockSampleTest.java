package com.github.kntmr;

import mockit.Expectations;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class PartialMockSampleTest {

    @Test
    public void test01() {
        final PartialMockSample mock = new PartialMockSample();
        new Expectations(PartialMockSample.class) {{
            mock.getValue(); result = 123;
        }};

        PartialMockSample sut1 = new PartialMockSample();
        PartialMockSample sut2 = new PartialMockSample(1);

        assertThat(sut1.getValue(), is(123)); // mocked
        assertThat(sut2.getValue(), is(123)); // mocked

        PartialMockSample sut3 = new PartialMockSample(2);

        assertThat(sut3.value, is(2)); // non-mocked
    }

    @Test
    public void test02() {
        final PartialMockSample sut1 = new PartialMockSample(1);
        new Expectations(sut1) {{
            sut1.getValue(); result = 123;
            PartialMockSample.method2(); // static
        }};

        assertThat(sut1.getValue(), is(123)); // mocked
        assertThat(sut1.method1(), is(true)); // non-mocked

        try {
            PartialMockSample.method2(); // non-mocked

        } catch (Throwable e) {
            fail();
        }

        PartialMockSample sut2 = new PartialMockSample(1);
        assertThat(sut2.getValue(), is(1)); // non-mocked
        assertThat(sut1.method1(), is(true)); // non-mocked
    }

}
