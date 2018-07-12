package com.dawoo.lotterybox.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.DateResources;
import com.dawoo.lotterybox.bean.MyBandCard;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.mvp.presenter.BankCardPreserter;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.view.activity.account.bank.BandCardScuessActviity;
import com.dawoo.lotterybox.view.activity.account.bank.BindCardActivity;
import com.guoqi.iosdialog.IOSDialog;

import java.util.List;


/**
 * @author jack
 * @date 18-2-8
 * 里面的数据之后都要改
 */

public class MSPropties {

    /**
     * 退出登录
     *
     * @param userPresenter
     * @param context
     */
    public static void signOut(UserPresenter userPresenter, Context context) {
        final IOSDialog alertDialog = new IOSDialog(context);
        alertDialog.builder();
        alertDialog.setCancelable(true);
        alertDialog.setTitle("温馨提示");
        alertDialog.setMsg("是否确定退出账号?");
        alertDialog.setPositiveButton("确定", v -> {
            userPresenter.signOut();
            alertDialog.dismiss();
        });
        alertDialog.setNegativeButton("取消", v -> alertDialog.dismiss());
        alertDialog.show();
    }

    /**
     * 展示消息
     *
     * @param context
     * @param msg
     */
    public static void showMeg(Context context, String msg) {
        ToastUtil.showToastShort(context, msg);
    }

    /**
     * 刷新　加载设置
     *
     * @param context
     * @param mSwipeToLoadLayout
     */
    public static void setSwipeToLoadLayout(Context context, SwipeToLoadLayout mSwipeToLoadLayout) {
        mSwipeToLoadLayout.setOnRefreshListener((OnRefreshListener) context);
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(true);
        mSwipeToLoadLayout.setOnLoadMoreListener((OnLoadMoreListener) context);
    }

    public static List<String> getTabLayout(List<String> tabIndicators) {
        for (int i = 0; i < DateResources.tabtitles.length; i++) {
            tabIndicators.add(DateResources.tabtitles[i]);

        }
        return tabIndicators;
    }

    public static List<String> getTablaout(List<String> tabIndicators) {
        for (int i = 0; i < DateResources.recharagerecord.length; i++) {
            tabIndicators.add(DateResources.recharagerecord[i]);

        }
        return tabIndicators;
    }

    /**
     * 获取是否有绑定银行卡
     *
     * @param bankCardPreserter
     */
    public static void getBanklistStastus(BankCardPreserter bankCardPreserter) {
        bankCardPreserter.bankCardList();
    }

    /**
     * 根据返回的银行卡进行页面跳转
     *
     * @param o
     * @param context
     */
    public static void getMyBankStatus(Object o, Context context) {
        List<MyBandCard> myBandCard = (List<MyBandCard>) o;
        if (myBandCard.size() == 0) {
            //无卡　
            DataCenter.getInstance().getUser().setBindBankCard(false);
            MSPropties.startActivity(BindCardActivity.class);
        } else {
            DataCenter.getInstance().getUser().setBindBankCard(false);
            MSPropties.startActivity(BandCardScuessActviity.class);
            context.startActivity(new Intent(context, BandCardScuessActviity.class)
                    .putExtra("bankCode", myBandCard.get(0).getBankCode())
                    .putExtra("bankName", myBandCard.get(0).getBankName())
                    .putExtra("cardNumber", myBandCard.get(0).getCardNumber())
                    .putExtra("masterName", myBandCard.get(0).getMasterName()));
        }

    }

    /**
     * 根据传进来的数据返回用户【对应的数据
     *
     * @param valuename
     * @return
     */
    public static String getUserDate(String valuename) {
        User user = DataCenter.getInstance().getUser();
        String userName = user.getUsername();
        long userId = user.getUserId();
        String nickName = user.getNickname();
        String Balance = user.getBalance();
        if (valuename.equals("username")) {
            return userName;
        } else if (valuename.equals("nickName")) {
            return nickName;
        } else if (valuename.equals("balance")) {
            return Balance;
        } else {
            return String.valueOf(userId);
        }

    }

    /**
     * 页面的　跳转
     *
     * @param cla
     */
    public static void startActivity(Class<?> cla) {
        BoxApplication.getContext().startActivity(new Intent().setClass(BoxApplication.getContext(), cla).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 判断获得的数据是否为空
     *
     * @param view
     * @param msg
     */
    public static void EdTexisEmpty(EditText view, String msg) {
        if (TextUtils.isEmpty(view.getText().toString().trim())) {
            MSPropties.showMeg(BoxApplication.getContext(), msg);
            return;
        }
    }


    /**
     * 取出字符串中除去后一位的元,转换成double类型
     *
     * @param str
     * @return
     */
    public static Double getDoubleNumber(String str) {
        return Double.parseDouble(str.substring(0, str.length() - 1));
    }


}
