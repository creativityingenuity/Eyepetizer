package com.practice.eyepetizer.mvp.contract

import com.practice.eyepetizer.mvp.base.IBaseView
import com.practice.eyepetizer.mvp.base.IPresenter
import com.practice.eyepetizer.mvp.model.bean.HomeBean


/**
 * desc: 分类详情契约类
 */
interface CategoryDetailContract {

    interface View: IBaseView {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)




    }

    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}