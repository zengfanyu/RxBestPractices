package com.zfy.rxbestpractices.contract;

import com.zfy.rxbestpractices.base.IBasePresenter;
import com.zfy.rxbestpractices.base.IBaseView;
import com.zfy.rxbestpractices.http.bean.WeChatBean;

/**
 * WeChatFragment 的契约类，聚合 View 层和 Presenter 层接口
 *
 * @author: fanyuzeng on 2018/3/1 14:38
 */
public interface WeChatContract {

    interface View extends IBaseView {
        /**
         * View层展示数据的接口
         *
         * @param result
         */
        void showWeCahtData(WeChatBean result);

        /**
         * View 层展示错误信息的接口
         *
         * @param msg
         */
        void getDataFail(String msg);

    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * Presenter 层获取数据的接口
         *
         * @param pageSize  分页大小
         * @param pageIndex 分页索引
         */
        void getWeChatData(int pageSize, int pageIndex);
    }
}
