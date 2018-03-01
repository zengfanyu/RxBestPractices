package com.zfy.rxbestpractices.weixin;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.http.bean.WeixinBean;
import com.zfy.rxbestpractices.util.ImageLoader;
import com.zfy.rxbestpractices.util.NetworkUtil;

/**
 * @author: fanyuzeng on 2018/3/1 15:36
 */
public class WeixinAdapter extends BaseQuickAdapter<WeixinBean.NewslistBean, BaseViewHolder> {

    public WeixinAdapter(int item_view_res) {
        super(item_view_res);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeixinBean.NewslistBean item) {
        helper.setText(R.id.id_author, item.getDescription());
        helper.setText(R.id.id_title,item.getTitle());
        if (NetworkUtil.isNetworkConnected(mContext)) {
            ImageLoader.loadAll(mContext, item.getPicUrl(), (ImageView) helper.getView(R.id.id_image));
        } else {
            ImageLoader.loadDefault(mContext, (ImageView) helper.getView(R.id.id_image));
        }
    }
}
