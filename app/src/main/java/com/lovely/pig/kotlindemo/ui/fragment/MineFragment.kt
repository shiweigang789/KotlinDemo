package com.lovely.pig.kotlindemo.ui.fragment

import android.os.Bundle
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.BaseFragment

/**
 * 作者 swg
 * 时间 2019/5/30 17:14
 * 文件 KotlinDemo
 * 描述
 */
class MineFragment : BaseFragment() {

    private var mTitle:String? =null

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {

    }

    override fun lazyLoad() {

    }

    companion object {
        fun getInstance(title:String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}