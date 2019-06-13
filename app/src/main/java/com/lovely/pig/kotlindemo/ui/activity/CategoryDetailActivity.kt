package com.lovely.pig.kotlindemo.ui.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import com.lovely.pig.kotlindemo.Constants
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.BaseActivity
import com.lovely.pig.kotlindemo.glide.GlideApp
import com.lovely.pig.kotlindemo.mvp.contract.CategoryDetailContract
import com.lovely.pig.kotlindemo.mvp.model.bean.CategoryBean
import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean
import com.lovely.pig.kotlindemo.mvp.presenter.CategoryDetailPresenter
import com.lovely.pig.kotlindemo.ui.adapter.CategoryDetailAdapter
import com.lovely.pig.kotlindemo.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_category_detail.*

/**
 * 作者 swg
 * 时间 2019/6/13 16:45
 * 文件 KotlinDemo
 * 描述 分类详情
 */
class CategoryDetailActivity : BaseActivity(), CategoryDetailContract.View {

    private val mPresenter by lazy { CategoryDetailPresenter() }

    private val mAdapter by lazy { CategoryDetailAdapter(this, itemList, R.layout.item_category_detail) }

    private var categoryData: CategoryBean? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    init {
        mPresenter.attachView(this)
    }

    /**
     * 是否加载更多
     */
    private var loadingMore = false

    override fun layoutId(): Int = R.layout.activity_category_detail

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // 加载headerImage
        GlideApp.with(this)
                .load(categoryData?.headerImage)
                .placeholder(R.color.color_darker_gray)
                .into(imageView)

        tv_category_desc.text ="#${categoryData?.description}#"

        collapsing_toolbar_layout.title = categoryData?.name
        collapsing_toolbar_layout.setExpandedTitleColor(Color.WHITE) //设置还没收缩时状态下字体颜色
        collapsing_toolbar_layout.setCollapsedTitleTextColor(Color.BLACK) //设置收缩后Toolbar上字体的颜色

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)
    }

    override fun initData() {
        categoryData = intent.getSerializableExtra(Constants.BUNDLE_CATEGORY_DATA) as CategoryBean?
    }

    override fun start() {
        //获取当前分类列表
        categoryData?.id?.let { mPresenter.getCategoryDetailList(it) }
    }

    override fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>) {
        loadingMore = false
        mAdapter.addData(itemList)
    }

    override fun showError(errorMsg: String) {
        multipleStatusView.showError()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}