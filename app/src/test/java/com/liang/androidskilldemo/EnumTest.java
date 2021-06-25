package com.liang.androidskilldemo;

import com.liang.androidskilldemo.algorithm.SampleEnum;

import org.junit.Test;

/**
 * created by zhangliang on 2021/6/21
 */
public class EnumTest {

   @Test
   public void testEqual() {
       SampleEnum a = SampleEnum.ONE;
       assert a == SampleEnum.ONE;
       assert a.equals(SampleEnum.ONE);
   }

}

