package com.lovely.pig.kotlindemo.base

/**
 * 作者 swg
 * 时间 2019/5/30 17:29
 * 文件 KotlinDemo
 * 描述
 */
interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}