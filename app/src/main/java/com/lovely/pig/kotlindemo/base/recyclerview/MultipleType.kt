package com.lovely.pig.kotlindemo.base.recyclerview

/**
 * 作者 swg
 * 时间 2019/6/3 10:12
 * 文件 KotlinDemo
 * 描述
 */
interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}