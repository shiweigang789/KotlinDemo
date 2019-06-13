package com.lovely.pig.kotlindemo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.BaseFragment
import com.lovely.pig.kotlindemo.base.BaseFragmentAdapter
import com.lovely.pig.kotlindemo.utils.StatusBarUtil
import com.lovely.pig.kotlindemo.view.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * 作者 swg
 * 时间 2019/5/30 17:11
 * 文件 KotlinDemo
 * 描述 发现(和热门首页同样的布局）
 */
class DiscoveryFragment : BaseFragment() {

    private val tabList = ArrayList<String>()

    private val fragments = ArrayList<Fragment>()

    private var mTitle: String? = null

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun initView() {
        // 状态栏透明和间距处理
        activity?.let {
            StatusBarUtil.darkMode(it)
            StatusBarUtil.setPaddingSmart(it, toolbar)
        }

        tv_header_title.text = mTitle

        tabList.run {
            add("关注")
            add("分类")
        }

        fragments.run {
            add(FollowFragment.getInstance("关注"))
            add(CategoryFragment.getInstance("分类"))
        }

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragments, tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)

    }

    override fun lazyLoad() {

    }

    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

}