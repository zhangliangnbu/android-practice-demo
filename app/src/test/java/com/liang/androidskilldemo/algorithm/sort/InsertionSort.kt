package com.liang.androidskilldemo.algorithm.sort

import org.junit.Test

/**
 * Created by zhangliang on 2019/2/22.
 * Profile https://zhangliangnbu.github.io
 *
 * 插入排序 O(n2)
 */
class InsertionSort {

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
        var temp:Int
        for (i in 1 until size) {
            temp = nums[i]
            for (j in i-1 downTo 0) {
                if(nums[j] > temp) {
                    nums[j + 1] = nums[j]
                    if(j == 0) {
                        nums[j] = temp
                    }
                } else {
                    nums[j + 1] = temp
                    break
                }
            }
        }
        return nums
    }
}