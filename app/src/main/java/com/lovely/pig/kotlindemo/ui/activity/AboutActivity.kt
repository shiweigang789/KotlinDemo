package com.lovely.pig.kotlindemo.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import com.lovely.pig.kotlindemo.MyApplication
import com.lovely.pig.kotlindemo.R
import com.lovely.pig.kotlindemo.base.BaseActivity
import com.lovely.pig.kotlindemo.utils.StatusBarUtil
import com.lovely.pig.kotlindemo.utils.getVerName
import kotlinx.android.synthetic.main.activity_about.*

/**
 * 作者 swg
 * 时间 2019/6/18 16:50
 * 文件 KotlinDemo
 * 描述 关于
 */
class AboutActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_about

    @SuppressLint("SetTextI18n")
    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        tv_version_name.text ="v${getVerName(MyApplication.context)}"
        //返回
        toolbar.setNavigationOnClickListener { finish() }
        //访问 GitHub
        relayout_gitHub.setOnClickListener {
            val uri = Uri.parse("https://github.com/git-xuhao/KotlinMvp")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun initData() {

    }

    override fun start() {

    }


}