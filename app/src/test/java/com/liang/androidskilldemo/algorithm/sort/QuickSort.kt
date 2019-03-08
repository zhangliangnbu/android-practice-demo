package com.liang.androidskilldemo.algorithm.sort

import org.junit.Test

/**
 * Created by zhangliang on 2019/2/22.
 * Profile https://zhangliangnbu.github.io
 *
 * 快速排序
 */
class QuickSort {

    @Test
    fun testPartition() {
        val nums = intArrayOf(3, 2, 4, 5, 0, 1)
        val partition = partition(nums, 0, nums.size - 1)
        print(partition)
        assert(partition == 3)
    }

    @Test
    fun testSort() {
        val nums = intArrayOf(3, 2, 4, 5, 0, 1)
        val sort = sort(nums, 0, nums.size - 1)
        sort.print()
        val expect = intArrayOf(0, 1, 2, 3, 4, 5)
        assert(expect.contentEquals(sort))
    }

    /**
     * sort
     */
    fun sort(nums: IntArray, left: Int, right: Int): IntArray {
        if (left < right) {
            val pivot = partition(nums, left, right)
            sort(nums, left, pivot - 1)
            sort(nums, pivot + 1, right)
        }
        return nums
    }

    /**
     * 分区 小的放在左边，大的放在右边
     * @return pivot index
     */
    private fun partition(nums: IntArray, left: Int, right: Int): Int {
        val pivot = left // 基准
        var swapIndex = pivot + 1 // 待交换
        for (i in pivot + 1..right) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, swapIndex)
                swapIndex++
            }
        }
        swap(nums, pivot, swapIndex - 1)
        return swapIndex - 1
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}