package com.lovely.pig.kotlindemo.mvp.contract

import com.lovely.pig.kotlindemo.base.IBaseView
import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean

/**
 * 作者 swg
 * 时间 2019/5/30 17:36
 * 文件 KotlinDemo
 * 描述
 */
interface HomeContract {

    interface View : IBaseView {

        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList:ArrayList<HomeBean.Issue.Item>)

        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)


    }

    interface Presenter {

        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData()

    }

}