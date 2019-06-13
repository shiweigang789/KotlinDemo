package com.lovely.pig.kotlindemo.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.BaseFragment
import com.lovely.pig.kotlindemo.mvp.contract.FollowContract
import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean
import com.lovely.pig.kotlindemo.mvp.presenter.FollowPresenter
import com.lovely.pig.kotlindemo.net.ErrorStatus
import com.lovely.pig.kotlindemo.showToast
import com.lovely.pig.kotlindemo.ui.adapter.FollowAdapter
import kotlinx.android.synthetic.main.layout_recyclerview.*

/**
 * 作者 swg
 * 时间 2019/6/13 15:57
 * 文件 KotlinDemo
 * 描述 关注
 */
class FollowFragment : BaseFragment(), FollowContract.View {

    private var mTitle: String? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private val mPresenter by lazy { FollowPresenter() }

    private val mFollowAdapter by lazy { activity?.let { FollowAdapter(it, itemList) } }

    /**
     * 是否加载更多
     */
    private var loadingMore = false


    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.layout_recyclerview

    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mFollowAdapter
        mLayoutStatusView = multipleStatusView
    }

    override fun lazyLoad() {
        mPresenter.requestFollowList()
    }

    override fun setFollowInfo(issue: HomeBean.Issue) {
        loadingMore = false
        itemList = issue.itemList
        mFollowAdapter?.addData(itemList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView.showContent()
    }

    companion object {
        fun getInstance(title: String): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}