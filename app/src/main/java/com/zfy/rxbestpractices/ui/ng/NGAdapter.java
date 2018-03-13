package com.zfy.rxbestpractices.ui.ng;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.http.bean.NGBean;
import com.zfy.rxbestpractices.util.ImageLoader;
import com.zfy.rxbestpractices.util.NetworkUtil;

/**
 * @author: fanyuzeng on 2018/3/13 16:00
 */
public class NGAdapter extends BaseQuickAdapter<NGBean, BaseViewHolder> {

    public NGAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NGBean item) {
        helper.setText(R.id.id_ng_title, item.getTitle())
                .setText(R.id.id_ng_content, item.getContent())
                .addOnClickListener(R.id.id_ng_title)
                .addOnClickListener(R.id.id_ng_content)
                .addOnClickListener(R.id.id_ng_img);
        if (NetworkUtil.isNetworkConnected(mContext)) {
            ImageLoader.loadAll(mContext, item.getImgUrl(), helper.getView(R.id.id_ng_img));
        } else {
            ImageLoader.loadDefault(mContext, helper.getView(R.id.id_ng_img));
        }
    }
}
