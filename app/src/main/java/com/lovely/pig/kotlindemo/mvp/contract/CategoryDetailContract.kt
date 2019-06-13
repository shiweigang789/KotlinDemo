package com.lovely.pig.kotlindemo.mvp.contract

import com.lovely.pig.kotlindemo.base.IBaseView
import com.lovely.pig.kotlindemo.base.IPresenter
import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean

/**
 * Created by xuhao on 2017/11/30.
 * desc: 分类详情契约类
 */
interface CategoryDetailContract {

    interface View: IBaseView {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)




    }

    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}