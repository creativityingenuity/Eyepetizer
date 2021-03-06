package com.practice.eyepetizer.mvp.contract

import com.practice.eyepetizer.mvp.base.IBaseView
import com.practice.eyepetizer.mvp.base.IPresenter
import com.practice.eyepetizer.mvp.model.bean.HomeBean


/**
 * desc: 契约类
 */
interface FollowContract {

    interface View : IBaseView {
        /**
         * 设置关注信息数据
         */
        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg: String, errorCode: Int)
    }


    interface Presenter : IPresenter<View> {
        /**
         * 获取List
         */
        fun requestFollowList()

        /**
         * 加载更多
         */
        fun loadMoreData()
    }
}