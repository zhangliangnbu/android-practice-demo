package com.liang.androidskilldemo;

import android.content.IntentFilter;
import android.text.InputType;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import kotlin.collections.ArrayDeque;

/**
 * created by zhangliang on 2021/6/18
 */
public class ListRemoveTest {


    // 在集合中删除部分特定索引的元素
    // 比如，删除前 N 个元素，或删除 索引为奇数的元素等
    // 比较好的方法，元素可标记：第一步，标记待删除的元素；第二步，通过迭代器删除标记的元素
    // 元素不可标记呢？
    @Test
    public void testListRemove() {
        List<Integer> list = new ArrayDeque<>();
        for (int i = 0; i < 100; i ++) {
            list.add(i);
        }

        // 删除索引为3的倍数的元素
        for (int i = 0, size = list.size(); i < size; i ++) {
            if (list.get(i) % 3 == 0) {
                list.set(i, Integer.MIN_VALUE);
            }
        }

        Iterator<Integer> iterator = list.listIterator();
        iterator.remove();

    }

    @Test
    public void testRemoveOneElement() {
        List<Integer> list = createList();
        Integer special = 9;
        list.remove(special);

        // 删除一个值为 val 的元素
        for (Integer i : list) {
            if (i.equals(special)) {
                list.remove(i);
                break;
            }
        }
        Utils.log(list);


        // 删除值为 val 的所有元素
        // 从后面开始删除
        list = createList();
        for (int index = list.size() - 1; index >= 0; index --) {
            Object element = list.get(index);
            if (special.equals(element)) {
                list.remove(index);
            }
        }
        Utils.log(list);

        // 删除值为 val 的所有元素
        // 使用 iterator
        list = createList();
        Iterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element.equals(special)) {
                iterator.remove();
            }
        }
        Utils.log(list);
    }

    @Test
    public void testRemoveIndexedElement() {
        List<Integer> list = createList();
        int start = 3, end = 7;

        // 移除第4个元素
        list.remove(start);
        Utils.log(list);

        // 移除第4~8个元素
        // 从后到前移除
        list = createList();
        for (int index = end; index >= start; index --) {
            list.remove(index);
        }
//        list.subList(start, end + 1).clear();
        Utils.log(list);

        // 移除第4~8个元素
        // 标记 负无穷 （前提 其他元素不能为此数），然后迭代删除
        list = createList();
        for (int index = start; index <= end; index ++) {
            list.set(index, Integer.MIN_VALUE);
        }
        Iterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == Integer.MIN_VALUE) {
                iterator.remove();
            }
        }
        Utils.log(list);
    }

    public void testJavaAPI() {
        List<Integer> list = createList();
        // 删除index = [3, 7) 之间的元素
        list.subList(3, 7).clear();

        list = createList();
        list.removeIf(integer -> integer == 9);
        List<Integer> list1 = list.stream().filter(integer -> integer != 9).collect(Collectors.toList());
    }


    private List<Integer> createList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 20; i ++) {
            if (i > 10) {
                list.add(20 - i);
            } else {
                list.add(i);
            }
        }
        return list;
    }

}
