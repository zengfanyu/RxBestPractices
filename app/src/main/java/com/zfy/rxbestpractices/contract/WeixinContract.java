package com.zfy.rxbestpractices.contract;

import com.zfy.rxbestpractices.base.IBasePresenter;
import com.zfy.rxbestpractices.base.IBaseView;
import com.zfy.rxbestpractices.http.bean.WeixinBean;

/**
 * @author: fanyuzeng on 2018/3/1 14:38
 */
public interface WeixinContract {

    interface View extends IBaseView {

        void showWeixinData(WeixinBean result);

        void getDataFail();

    }

    interface Presenter extends IBasePresenter<View> {

        void getWeixinData(int pageSize, int pageIndex);
    }
}
