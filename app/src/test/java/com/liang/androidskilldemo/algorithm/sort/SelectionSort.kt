package com.liang.androidskilldemo.algorithm.sort

import org.junit.Test

/**
 * Created by zhangliang on 2019/2/22.
 * Profile https://zhangliangnbu.github.io
 *
 * 选择排序
 */
class SelectionSort {

    @Test
    fun testSort() {
        val nums = intArrayOf(3, 2, 4, 5, 0, 1)
        val sort = sort(nums)
        sort.print()
        val expect = intArrayOf(0, 1, 2, 3, 4, 5)
        assert(expect.contentEquals(sort))
    }

    /**
     * sort
     */
    fun sort(nums: IntArray): IntArray {
        val size = nums.size
        var minIndex:Int
        var temp:Int
        for(i in 0 until size - 1) {
            minIndex = i
            for (j in i until size) {
                if(nums[j] < nums[minIndex]) {
                    minIndex = j
                }
            }
            temp = nums[i]
            nums[i] = nums[minIndex]
            nums[minIndex] = temp
        }
        return nums
    }
}