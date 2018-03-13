package com.zfy.rxbestpractices.presenter;

import android.content.Context;

import com.zfy.rxbestpractices.base.BaseRxPresenter;
import com.zfy.rxbestpractices.base.BaseSubscriber;
import com.zfy.rxbestpractices.contract.NGContract;
import com.zfy.rxbestpractices.http.bean.NGBean;
import com.zfy.rxbestpractices.util.LogUtil;
import com.zfy.rxbestpractices.util.StringUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: fanyuzeng on 2018/3/13 15:23
 */
public class NGPresenter extends BaseRxPresenter<NGContract.View> implements NGContract.Presenter {
    private Context mContext;
    private List<NGBean> mDatas;
    public static final int DATA_SIZE = 10;

    @Inject
    public NGPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void getData(String url) {
        addSubscribe(

                Flowable
                        .create((FlowableOnSubscribe<Document>) e -> {
                            Document document = Jsoup.connect(url).get();
                            e.onNext(document);
                            e.onComplete();
                        }, BackpressureStrategy.ERROR)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .doOnNext(document -> {
                            Element contents = document.getElementById("ajaxBox");
                            Elements list = contents.getElementsByClass("ajax_list");
                            mDatas = new ArrayList<>();
                            for (int i = 0; i < DATA_SIZE && i < list.size(); i++) {
                                Element element = list.get(i);
                                NGBean nG = new NGBean();
                                Element imageA = element.select("dd").first().select("a").first();

                                nG.setUrl(StringUtil.salfString(imageA.attr("href")));
                                nG.setImgUrl(StringUtil.salfString(imageA.select("img").first().attr("src")));
                                nG.setTitle(StringUtil.salfString(imageA.select("img").first().attr("alt")));
                                nG.setContent(StringUtil.salfString(element.getElementsByClass("ajax_dd_text").first().ownText()));

                                mDatas.add(nG);
                                LogUtil.d(TAG, "ngbean:" + nG.toString());

                            }
                            // TODO(ZFY): 2018/3/13 此处使用 SharedPreference 存入封面图
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new BaseSubscriber<Document>(mContext, mView) {
                            @Override
                            public void onNext(Document document) {
                                LogUtil.d(TAG, "onNext");
                                if (mDatas != null && mDatas.size() > 0) {
                                    mView.showNGData(mDatas);
                                } else {
                                    mView.showErrorTip("no data to show");
                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                mView.getNGDataFailed(t.getMessage());
                            }
                        })
        );
    }

}
