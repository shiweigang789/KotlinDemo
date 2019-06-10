package com.lovely.pig.kotlindemo.ui.fragment

import android.os.Bundle
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.BaseFragment
import com.lovely.pig.kotlindemo.mvp.contract.HomeContract
import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean

/**
 * 作者 swg
 * 时间 2019/5/30 17:13
 * 文件 KotlinDemo
 * 描述
 */
class HotFragment :BaseFragment(), HomeContract.View{

    private var mTitle: String? = null

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun initView() {

    }

    override fun lazyLoad() {

    }

    override fun setHomeData(homeBean: HomeBean) {

    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {

    }

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

}