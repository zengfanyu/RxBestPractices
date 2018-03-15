package com.zfy.rxbestpractices.ui.wechat;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zfy.rxbestpractices.R;
import com.zfy.rxbestpractices.http.bean.WeChatBean;
import com.zfy.rxbestpractices.util.ImageLoader;
import com.zfy.rxbestpractices.util.NetworkUtil;

import java.util.List;

/**
 * 微信精选页面 RecyclerView 的 Adapter
 *
 * @author: fanyuzeng on 2018/3/1 15:36
 */
public class WeCahtAdapter extends BaseMultiItemQuickAdapter<WeChatBean.NewslistBean, BaseViewHolder> {

    public WeCahtAdapter(List<WeChatBean.NewslistBean> data) {
        super(data);
        addItemType(WeChatBean.NewslistBean.ITEM_TYPE_BIG,R.layout.list_item_card_big);
        addItemType(WeChatBean.NewslistBean.ITEM_TYPE_SMALL,R.layout.list_item_card_small);

    }

    @Override
    protected void convert(BaseViewHolder helper, WeChatBean.NewslistBean item) {
        helper.setText(R.id.id_author, item.getDescription());
        helper.setText(R.id.id_title, item.getTitle());
        if (NetworkUtil.isNetworkConnected(mContext)) {
            ImageLoader.loadAll(mContext, item.getPicUrl(), (ImageView) helper.getView(R.id.id_image));
        } else {
            ImageLoader.loadDefault(mContext, (ImageView) helper.getView(R.id.id_image));
        }
    }
}
