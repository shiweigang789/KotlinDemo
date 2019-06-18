package com.lovely.pig.kotlindemo.mvp.contract

import com.lovely.pig.kotlindemo.base.IBaseView
import com.lovely.pig.kotlindemo.base.IPresenter
import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean

/**
 * Created by xuhao on 2017/11/30.
 * desc: 契约类
 */
interface RankContract {

    interface View: IBaseView {
        /**
         * 设置排行榜的数据
         */
        fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String,errorCode:Int)
    }


    interface Presenter: IPresenter<View> {
        /**
         * 获取 TabInfo
         */
        fun requestRankList(apiUrl:String)
    }
}