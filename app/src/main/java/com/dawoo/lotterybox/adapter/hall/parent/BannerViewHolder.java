package com.dawoo.lotterybox.adapter.hall.parent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.hall.child.BaseViewHolder;
import com.dawoo.lotterybox.adapter.hall.child.adapterset.BannerConfigInit;
import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.NetUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 18-4-12.
 * 轮播图
 */

public class BannerViewHolder extends BaseViewHolder {

    Banner mBanner;

    public BannerViewHolder(Context context ,View itemView) {
        super(itemView);
        mBanner = itemView.findViewById(R.id.banner);
    }

    public void bindView(List<BannerBean> bannerBeans){
        List images = new ArrayList();
        List titles = new ArrayList();
        List<String> Links = new ArrayList();

        for (int i = 0; i < bannerBeans.size(); i++) {
            images.add(NetUtil.handleUrl(DataCenter.getInstance().getImgDomain(), bannerBeans.get(i).getCover()));
            titles.add(bannerBeans.get(i).getName());
            Links.add(bannerBeans.get(i).getLink());
        }
        BannerConfigInit init = new BannerConfigInit(mBanner, images, titles, position ->
                ActivityUtil.startWebView(Links.get(position), ConstantValue.WEBVIEW_TYPE_ORDINARY));

        init.start();
    }
}
