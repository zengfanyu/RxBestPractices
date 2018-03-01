package com.zfy.rxbestpractices.config;

import com.zfy.rxbestpractices.util.Common;

import java.io.File;

/**
 * @author: fanyuzeng on 2018/3/1 13:50
 */
public class Constants {


    public static final String DATA_PATH = Common.mAppContext.getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String NET_CACHE_PATH = DATA_PATH + "/netcache";
    public static final String LOG_CACHE_PATH = DATA_PATH + "/log";
    public static final String LOG_CACHE_FILE_NAME = "message_log.txt";

    /**
     * 微信精选key<p>
     * https://www.tianapi.com/#wxnew
     */
    public static final String WEIXIN_API_KEY = "a2873ef1d3dc60e2e17ee98a0537fb8f";
}
