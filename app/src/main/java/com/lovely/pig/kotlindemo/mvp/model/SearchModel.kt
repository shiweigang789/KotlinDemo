package com.lovely.pig.kotlindemo.mvp.model

import com.lovely.pig.kotlindemo.mvp.model.bean.HomeBean
import com.lovely.pig.kotlindemo.net.RetrofitManager
import com.lovely.pig.kotlindemo.utils.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/30.
 * desc: 搜索 Model
 */
class SearchModel {

    /**
     * 请求热门关键词的数据
     */
    fun requestHotWordData(): Observable<ArrayList<String>> {

        return RetrofitManager.service.getHotWord()
                .compose(SchedulerUtils.ioToMain())
    }


    /**
     * 搜索关键词返回的结果
     */
    fun getSearchResult(words: String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getSearchData(words)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多数据
     */
    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }

}
