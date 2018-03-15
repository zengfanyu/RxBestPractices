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
         * 获取数据成功调用，展示数据
         *
         * @param data
         */
        void showNGData(List<NGBean> data);

        /**
         * 获取数据失败时调用
         */
        void getNGDataFailed(String msg);

    }

    interface Presenter extends IBasePresenter<View> {
        /**
         * 获取数据
         *
         * @param url
         * @return
         */
        void getData(String url);
    }
}
