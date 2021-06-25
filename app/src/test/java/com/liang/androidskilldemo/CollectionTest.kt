package com.liang.androidskilldemo

import org.junit.Test

/**
 * Created by zhangliang on 2019/2/26.
 * Profile https://zhangliangnbu.github.io
 */
class CollectionTest {

    @Test
    fun testContainAll() {
        val set = hashSetOf<String>()
        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            set.add("$i")
            list.add("$i")
        }

        assert(set.containsAll(list))
    }

    @Test
    fun testListRemove() {
    }
}