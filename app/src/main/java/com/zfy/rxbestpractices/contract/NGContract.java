package com.zfy.rxbestpractices.contract;

import com.zfy.rxbestpractices.base.IBasePresenter;
import com.zfy.rxbestpractices.base.IBaseView;
import com.zfy.rxbestpractices.http.bean.NGBean;

import java.util.List;

/**
 * @author: fanyuzeng on 2018/3/13 14:38
 */
public interface NGContract {

    interface View extends IBaseView {
        /**
         * @param data
         */
        void showNGData(List<NGBean> data);

        /**
         *
         */
        void getNGDataFailed(String msg);

    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * @param url
         * @return
         */
        void getData(String url);
    }
}
