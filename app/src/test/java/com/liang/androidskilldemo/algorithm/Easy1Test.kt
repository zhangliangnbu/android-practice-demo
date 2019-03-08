package com.liang.androidskilldemo.algorithm

import org.junit.Test

/**
 * Created by zhangliang on 2019/2/22.
 * Profile https://zhangliangnbu.github.io
 */
class Easy1Test {

    @Test
    fun testTwoSum() {
        val nums = intArrayOf(2, 7, 11, 15)
        val target = 9
        val indexs = twoSumByHashMap(nums, target)
        assert(indexs?.get(0) == 0 && indexs[1] == 1)
    }

    @Test
    fun testTwoSum1() {
        val nums = intArrayOf(11, 15, 7, 11, 15, 2)
        val target = 9
        val indexs = twoSumByHashMap(nums, target)
        assert(indexs?.get(0) == 2 && indexs[1] == 5)
    }

    /**
     * Two Sum
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     */
    private fun twoSum(nums: IntArray, target: Int): IntArray? {
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if(nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return null
    }

    /**
     * by hash map
     */
    private fun twoSumByHashMap(nums: IntArray, target: Int): IntArray? {
        val map = mutableMapOf<Int, Int>()
        for (i in 0 until nums.size) {
            if(map.containsKey(nums[i])) {
                return intArrayOf(map[nums[i]]!!, i)
            } else {
                map[target - nums[i]] = i
            }
        }
        return null
    }
}