package com.lovely.pig.kotlindemo.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.BaseFragment
import com.lovely.pig.kotlindemo.mvp.contract.CategoryContract
import com.lovely.pig.kotlindemo.mvp.model.bean.CategoryBean
import com.lovely.pig.kotlindemo.mvp.presenter.CategoryPresenter
import com.lovely.pig.kotlindemo.net.ErrorStatus
import com.lovely.pig.kotlindemo.showToast
import com.lovely.pig.kotlindemo.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * 作者 swg
 * 时间 2019/6/13 16:21
 * 文件 KotlinDemo
 * 描述 分类
 */
class CategoryFragment : BaseFragment(), CategoryContract.View {

    private val mPresenter by lazy { CategoryPresenter() }

    private val mAdapter by lazy { activity?.let { CategoryAdapter(it, mCategoryList, R.layout.item_category) } }

    private var mTitle: String? = null
    private var mCategoryList = ArrayList<CategoryBean>()

    override fun getLayoutId(): Int = R.layout.fragment_category

    override fun initView() {
        mPresenter.attachView(this)


        mLayoutStatusView = multipleStatusView

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(activity,2)
    }

    override fun lazyLoad() {
        //获取分类信息
        mPresenter.getCategoryData()
    }

    override fun showCategory(categoryList: ArrayList<CategoryBean>) {
        mCategoryList = categoryList
        mAdapter?.setData(mCategoryList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()
        } else {
            multipleStatusView?.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView?.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView?.showContent()
    }

    /**
     * 伴生对象
     */
    companion object {
        fun getInstance(title: String): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

}