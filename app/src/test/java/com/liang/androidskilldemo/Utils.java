package com.liang.androidskilldemo;

import java.util.List;

/**
 * created by zhangliang on 2021/6/21
 */
public class Utils {
    public static void log(List<Integer> list) {
        for (int i = 0, size = list.size(); i < size; i ++) {
            if (i < size - 1) {
                System.out.print(list.get(i) + ", ");
            } else {
                System.out.println(list.get(i));
            }
        }
    }
}
