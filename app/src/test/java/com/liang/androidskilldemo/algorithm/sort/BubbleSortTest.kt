package com.liang.androidskilldemo.algorithm.sort

import org.junit.Test

/**
 * Created by zhangliang on 2019/2/22.
 * Profile https://zhangliangnbu.github.io
 *
 * 冒泡排序
 */
class BubbleSortTest {

    @Test
    fun testSort() {
        val nums = intArrayOf(3, 2, 4, 5, 0, 1)
        val sort = sort(nums)
        sort.print()
        val expect = intArrayOf(0, 1, 2, 3, 4, 5)
        assert(expect.contentEquals(sort))
    }

    /**
     * 大数沉底
     */
    private fun sort(nums: IntArray): IntArray {
        val size = nums.size
        for (i in 0 until size - 1) {
            for (j in 0 until size - 1 - i) {
                if (nums[j] > nums[j + 1]) {
                    val temp = nums[j]
                    nums[j] = nums[j + 1]
                    nums[j + 1] = temp
                }
            }
        }
        return nums
    }

    private fun printArray(array: IntArray) {
        array.forEach {
            print("$it,")
        }
    }
}
