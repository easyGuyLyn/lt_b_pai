package com.dawoo.lotterybox.adapter.hall.child.adapterset;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * 轮播配置初始化
 * Created by benson on 17-12-25.
 */

public class BannerConfigInit {
    private Banner mBanner;

    public BannerConfigInit(Banner banner, List images, List titles, OnBannerListener listener) {
        mBanner = banner;
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.setOnBannerListener(listener);
        //banner设置方法全部调用完毕时最后调用
        //banner.start();
    }

    public void start() {
        if (mBanner != null) {
            mBanner.start();
        }
    }
}
