package com.liang.androidskilldemo

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testSS2MMSS() {
        val millis = 500000000L
        val tz = TimeZone.getTimeZone("UTC")
        val df = SimpleDateFormat("hh:mm:ss")
        df.timeZone = tz
        val time = df.format(Date(millis))
        println(time)
    }
}
