package com.zfy.rxbestpractices.http.api;

import com.zfy.rxbestpractices.http.bean.WeChatBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author: fanyuzeng on 2018/3/1 14:34
 */
public interface WeixinApi {
    String HOST = "http://api.huceo.com/";
    /**
     * 微信数据
     *
     * @param key  秘钥
     * @param pageSize  请求个数
     * @param pageIndex 页数
     * @return
     */
    @GET("wxnew/")
    Flowable<WeChatBean> getWeiXin(@Query("key") String key, @Query("num") int pageSize, @Query("page") int pageIndex);
}
