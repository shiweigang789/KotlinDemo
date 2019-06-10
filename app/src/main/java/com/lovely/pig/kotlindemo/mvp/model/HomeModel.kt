package com.lovely.pig.kotlindemo.mvp.model

import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean
import com.lovely.pig.kotlindemo.net.RetrofitManager
import com.lovely.pig.kotlindemo.utils.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * 作者 swg
 * 时间 2019/5/30 18:04
 * 文件 KotlinDemo
 * 描述
 */
class HomeModel {

    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num: Int): Observable<HomeBean> {
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url: String): Observable<HomeBean> {

        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }

}