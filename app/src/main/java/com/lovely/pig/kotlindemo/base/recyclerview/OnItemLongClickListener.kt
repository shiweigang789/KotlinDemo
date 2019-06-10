package com.lovely.pig.kotlindemo.base.recyclerview

/**
 * 作者 swg
 * 时间 2019/6/3 10:14
 * 文件 KotlinDemo
 * 描述
 */
interface OnItemLongClickListener {
    fun onItemLongClick(obj: Any?, position: Int): Boolean
}