package com.hazz.kotlinmvp.mvp.model


import com.practice.eyepetizer.mvp.model.bean.HomeBean
import com.practice.eyepetizer.net.RetrofitManager
import com.practice.eyepetizer.net.scheduler.SchedulerUtils
import io.reactivex.Observable
import java.util.*

/**
 * desc: 分类详情的 Model
 */
class CategoryDetailModel {

    /**
     * 获取分类下的 List 数据
     */
    fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getCategoryDetailList(id)
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
