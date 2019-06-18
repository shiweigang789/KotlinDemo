package com.lovely.pig.kotlindemo.mvp.presenter

import com.lovely.pig.kotlindemo.base.BasePresenter
import com.lovely.pig.kotlindemo.mvp.contract.HotTabContract
import com.lovely.pig.kotlindemo.mvp.model.HotTabModel
import com.lovely.pig.kotlindemo.net.ExceptionHandle

/**
 * Created by xuhao on 2017/11/30.
 * desc: 获取 TabInfo Presenter
 */
class HotTabPresenter: BasePresenter<HotTabContract.View>(),HotTabContract.Presenter {

    private val hotTabModel by lazy { HotTabModel() }


    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = hotTabModel.getTabInfo()
                .subscribe({
                    tabInfo->
                    mRootView?.setTabInfo(tabInfo)
                },{
                    throwable->
                    //处理异常
                    mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                })
        addSubscription(disposable)
    }
}