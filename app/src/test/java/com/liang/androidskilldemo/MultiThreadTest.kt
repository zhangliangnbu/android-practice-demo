package com.liang.androidskilldemo

import org.junit.Test
import java.util.concurrent.Executors
import kotlin.math.roundToLong

/**
 * Created by zhangliang on 2019/2/26.
 * Profile https://zhangliangnbu.github.io
 *
 * 多线程管理和同步
 * 1. 线程管理-线程池
 * 2. 数据同步-锁
 */
class MultiThreadTest {

    @Test
    fun testFixedThreadPool() {
        val es = Executors.newFixedThreadPool(5)
        for (i in 0 until 20) {
            val runnable = Runnable {
                Thread.sleep(100)
                println("testFixedThreadPool $i -> ${Thread.currentThread().name} ")
            }
            es.execute(runnable)
        }

        Thread.sleep(2000)
    }

    @Test
    fun testCachedThreadPool() {

        val es = Executors.newCachedThreadPool()
        for (i in 0 until 20) {
            val runnable = Runnable {
                Thread.sleep(100)
                println("testCachedThreadPool $i -> ${Thread.currentThread().name} ")
            }
            es.execute(runnable)
        }

        Thread.sleep(2000)
    }

    @Test
    fun testCachedThreadPoolAndDataSync() {
        val inputs = mutableListOf<Int>()
        for (i in 0 until 24) {
            inputs.add(i)
        }

//        val resultCache = ConcurrentHashMap<Int, String>()
        val resultCache = hashMapOf<Int, String>()
        val es = Executors.newCachedThreadPool()
        inputs.forEach { input ->
            val runnable = Runnable {
                Thread.sleep(500 + (10 *  Math.random()).roundToLong())
//                Thread.sleep(500)
                synchronized(resultCache){
                    resultCache[input] = "result-$input"
                    println("testCachedThreadAndSync: input->$input, thread->${Thread.currentThread().name} ")

                    // progress
                    println("testCachedThreadAndSync: progress->${(100.0f * resultCache.size / inputs.size).toInt()}")
                    if(resultCache.size == inputs.size) {
                        println("testCachedThreadAndSync: progress->success")
                    }
                }
            }
            es.execute(runnable)
        }


        Thread.sleep(2000)
    }




}