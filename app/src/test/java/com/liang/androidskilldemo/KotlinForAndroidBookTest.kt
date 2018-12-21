package com.liang.androidskilldemo

import org.junit.Test

/**
 * created by zhangliang on 2018/12/20
 * profile: zhangliangnbu@163.com
 */
class KotlinForAndroidBookTest {
    class Person(map:Map<String, Any?>) {
        val name:String by map
        val age: Int by map
    }

    @Test
    fun testByMap() {
        val map = mapOf("name" to "light", "age" to 30)
        val name by map
        val age by map
        assert(name == map["name"])
        assert(age == map["age"])

        val p = Person(map)
        assert(p.name == "light")
        assert(p.age == 30)
    }
}