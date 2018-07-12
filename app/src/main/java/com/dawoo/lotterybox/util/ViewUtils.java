package com.dawoo.lotterybox.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.R;

import java.lang.reflect.Field;

/**
 * Created by alex on 18-4-2.
 *
 * @author alex
 */

public final class ViewUtils {

    public static void setLinearManager(Context context, RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public static void showBankImage(Context context, ImageView imageView, String message) {
        if ("中国银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_zhongguo_bank)).into(imageView);

        } else if ("中国工商银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_gongshang_bank)).into(imageView);

        } else if ("中国农业银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_nongye_bank)).into(imageView);

        } else if ("交通银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_jiaotong_bank)).into(imageView);

        } else if ("广发银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_guangfa_bank)).into(imageView);

        } else if ("招商银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_zhaoshao_bank)).into(imageView);

        } else if ("中国邮政储蓄银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_youzheng_bank)).into(imageView);

        } else if ("中国民生银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_mingsheng_bank)).into(imageView);

        } else if ("中信银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_zhongxing_bank)).into(imageView);

        } else if ("中国光大银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_guangda_bank)).into(imageView);

        } else if ("渤海银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ic_bohai_bank)).into(imageView);

        }else if ("中国建设银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.ccb)).into(imageView);

        }else if ("上海浦东发展银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.spdb)).into(imageView);

        }else if (message.contains("安银行")){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.spabank)).into(imageView);

        }else if ("兴业银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.cib)).into(imageView);

        }else if ("北京银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.bjbank)).into(imageView);

        }else if ("广东发展银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.gdb)).into(imageView);

        }else if ("江苏银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.jsbank)).into(imageView);

        }else if ("华夏银行".equals(message)){
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.hxbank)).into(imageView);

        }else if ("东亚银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.hkbea)).into(imageView);

        }else if ("宁波银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.nbbank)).into(imageView);

        }else if ("浙商银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.czbank)).into(imageView);

        }else if ("杭州银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.hzcb)).into(imageView);

        }else if ("广州银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.gcb)).into(imageView);

        }else if ("恒生银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.hangseng)).into(imageView);

        }else if ("台州银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.tzb)).into(imageView);

        }else if ("上海银行".equals(message)) {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.shbank)).into(imageView);

        }else {
            Glide.with(context).load(context.getResources().getDrawable(R.mipmap.other_bank)).into(imageView);
           // imageView.setVisibility(View.INVISIBLE);
        }
    }

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

    public static void reflexTabLayout(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = DensityUtil.dp2px(tabLayout.getContext(), 20);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static void noSpaceEditText(EditText et_content){
        et_content.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (s.toString().contains(" ")) {
                    String[] str = s.toString().split(" ");
                    String str1 = "";
                    for (int i = 0; i < str.length; i++) {
                        str1 += str[i];
                    }
                    et_content.setText(str1);
                    et_content.setSelection(start);

                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
