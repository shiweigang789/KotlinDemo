package com.lovely.pig.kotlindemo.mvp.model

import com.lovely.pig.kotlindemo.mvp.model.bean.TabInfoBean
import com.lovely.pig.kotlindemo.net.RetrofitManager
import com.lovely.pig.kotlindemo.utils.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/30.
 * desc: 热门 Model
 */
class HotTabModel {

    /**
     * 获取 TabInfo
     */
    fun getTabInfo(): Observable<TabInfoBean> {

        return RetrofitManager.service.getRankList()
                .compose(SchedulerUtils.ioToMain())
    }

}
