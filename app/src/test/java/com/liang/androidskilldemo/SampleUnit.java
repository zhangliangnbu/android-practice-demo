package com.liang.androidskilldemo;

import org.junit.Test;

/**
 * created by zhangliang on 2021/7/5
 */
public class SampleUnit {

    @Test
    public void testSample() {
        boolean a = false;
        System.out.println("sample == " + (a && isGood(1)));
    }

    private boolean isGood(int g) {
        System.out.println("isGood g ==" + g);
        return g > 0;
    }
}
