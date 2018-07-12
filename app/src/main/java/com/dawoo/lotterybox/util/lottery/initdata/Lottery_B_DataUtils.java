package com.dawoo.lotterybox.util.lottery.initdata;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dawoo.coretool.util.activity.DensityUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.ChooseMoney;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.bean.playtype.PostPlayBean;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.SingleToast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.dawoo.lotterybox.view.fragment.keno.XYBDataUtils.getCount;
import static com.dawoo.lotterybox.view.fragment.keno.XYBDataUtils.getEvenCount;

/**
 * Created by archar on 18-2-27.
 * <p>
 * 彩种 B 盘的数据源 :
 * 时时彩B
 * 快3B
 * PK10B
 * 幸运28
 * 北京快乐8
 */

public class Lottery_B_DataUtils {


    private static Resources getRec() {
        return BoxApplication.getContext().getResources();
    }

    private synchronized static Map<String, LotteryOddBean> getOddMap() {
        return BLotteryOddFactory.getInstance().getStringLotteryOddBeanMap();
    }

    /**
     * 根据彩种初始化玩法名称列表数据
     *
     * @param lotteryCode
     * @return
     */
    public static List<PlayChooseBean> getPlayTypeNameList(String lotteryCode) {
        if (lotteryCode.equals(LotteryEnum.XJSSC.getCode())
                || lotteryCode.equals(LotteryEnum.TJSSC.getCode())
                || lotteryCode.equals(LotteryEnum.FFSSC.getCode())
                || lotteryCode.equals(LotteryEnum.EFSSC.getCode())
                || lotteryCode.equals(LotteryEnum.SFSSC.getCode())
                || lotteryCode.equals(LotteryEnum.WFSSC.getCode())
                || lotteryCode.equals(LotteryEnum.CQSSC.getCode())) {

            return initSSCBChooseData();

        } else if (lotteryCode.equals(LotteryEnum.BJPK10.getCode())
                || lotteryCode.equals(LotteryEnum.JSPK10.getCode())) {
            return initPK10BChooseData();

        } else if (lotteryCode.equals(LotteryEnum.JSK3.getCode())
                || lotteryCode.equals(LotteryEnum.AHK3.getCode())
                || lotteryCode.equals(LotteryEnum.HBK3.getCode())
                || lotteryCode.equals(LotteryEnum.GXK3.getCode())) {

            return initJSK3BChooseData();

        } else if (lotteryCode.equals(LotteryEnum.XY28.getCode())) {

            return initXy28ChooseData();

        } else if (lotteryCode.equals(LotteryEnum.BJKL8.getCode())) {

            return initBjkl8ChooseData();

        } else if (lotteryCode.equals(LotteryEnum.FC3D.getCode())
                || lotteryCode.equals(LotteryEnum.TCPL3.getCode())) {

            return initQTBChooseData();
        } else if (lotteryCode.equals(LotteryEnum.CQXYNC.getCode())
                || lotteryCode.equals(LotteryEnum.GDKL10.getCode())) {
            return initCQXYNCChooseDate();
        }

        return new ArrayList<>();
    }


    //初始化 重庆时时彩B 左边导航选择项的数据源
    public static List<PlayChooseBean> initSSCBChooseData() {
        List<PlayChooseBean> playChooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(getRec().getStringArray(R.array.cqssc_b_playType));

        for (int i = 0; i < stringList.size(); i++) {
            PlayChooseBean playChooseBean = new PlayChooseBean();
            if (i == 0) {
                playChooseBean.setSelected(true);
            }
            playChooseBean.setBetName(stringList.get(i));
            playChooseBeanList.add(playChooseBean);
        }
        return playChooseBeanList;
    }

    //初始化 pk10b 左边导航选择项的数据源
    public static List<PlayChooseBean> initPK10BChooseData() {
        List<PlayChooseBean> playChooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(getRec().getStringArray(R.array.pk10_b_playType));

        for (int i = 0; i < stringList.size(); i++) {
            PlayChooseBean playChooseBean = new PlayChooseBean();
            if (i == 0) {
                playChooseBean.setSelected(true);
            }
            playChooseBean.setBetName(stringList.get(i));
            playChooseBeanList.add(playChooseBean);
        }
        return playChooseBeanList;
    }

    //初始化 jsk3b 左边导航选择项的数据源
    public static List<PlayChooseBean> initJSK3BChooseData() {
        List<PlayChooseBean> playChooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(getRec().getStringArray(R.array.jsk3_b_playType));

        for (int i = 0; i < stringList.size(); i++) {
            PlayChooseBean playChooseBean = new PlayChooseBean();
            if (i == 0) {
                playChooseBean.setSelected(true);
            }
            playChooseBean.setBetName(stringList.get(i));
            playChooseBeanList.add(playChooseBean);
        }
        return playChooseBeanList;
    }

    //初始化 xy28 左边导航选择项的数据源
    public static List<PlayChooseBean> initXy28ChooseData() {
        List<PlayChooseBean> playChooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(getRec().getStringArray(R.array.xy28_playType));

        for (int i = 0; i < stringList.size(); i++) {
            PlayChooseBean playChooseBean = new PlayChooseBean();
            if (i == 0) {
                playChooseBean.setSelected(true);
            }
            playChooseBean.setBetName(stringList.get(i));
            playChooseBeanList.add(playChooseBean);
        }
        return playChooseBeanList;
    }

    //初始化 bjkl8 左边导航选择项的数据源
    public static List<PlayChooseBean> initBjkl8ChooseData() {
        List<PlayChooseBean> playChooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(getRec().getStringArray(R.array.bjkl8_playType));

        for (int i = 0; i < stringList.size(); i++) {
            PlayChooseBean playChooseBean = new PlayChooseBean();
            if (i == 0) {
                playChooseBean.setSelected(true);
            }
            playChooseBean.setBetName(stringList.get(i));
            playChooseBeanList.add(playChooseBean);
        }
        return playChooseBeanList;
    }

    //初始化 qtB 左边导航选择项的数据源
    public static List<PlayChooseBean> initQTBChooseData() {
        List<PlayChooseBean> playChooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(getRec().getStringArray(R.array.QTB_playType));

        for (int i = 0; i < stringList.size(); i++) {
            PlayChooseBean playChooseBean = new PlayChooseBean();
            if (i == 0) {
                playChooseBean.setSelected(true);
            }
            playChooseBean.setBetName(stringList.get(i));
            playChooseBeanList.add(playChooseBean);
        }
        return playChooseBeanList;
    }

    //初始化重庆幸运农场
    public static List<PlayChooseBean> initCQXYNCChooseDate() {
        List<PlayChooseBean> playChooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.CQXYNC_playType));

        for (int i = 0; i < stringList.size(); i++) {
            PlayChooseBean playChooseBean = new PlayChooseBean();
            if (i == 0) {
                playChooseBean.setSelected(true);
            }
            playChooseBean.setBetName(stringList.get(i));
            playChooseBeanList.add(playChooseBean);
        }
        return playChooseBeanList;
    }


    //刷新 左边导航选择项的数据源
    public static void refreshChooseData(List<PlayChooseBean> list, int position) {
        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.get(i).setSelected(true);
            } else {
                list.get(i).setSelected(false);
            }
        }
    }


    //初始化 底部选择钱的数量的数据源
    public static List<ChooseMoney> initBottomChooseMoneyData() {
        List<ChooseMoney> list = new ArrayList<>();
        int[] countArray = new int[]{1, 5, 10, 50, 100, 500, 1000, 5000};
        String baseDrawableStr = "niuniu_chouma";
        for (int i = 1; i < 9; i++) {
            ChooseMoney chooseMoney = new ChooseMoney();
            chooseMoney.setCount(countArray[i - 1]);
            chooseMoney.setDrawableId(baseDrawableStr + i);
            list.add(chooseMoney);
        }
        return list;
    }

    /**
     * 刷新投注view UI;
     * <p>
     * 并且更新 注数和可赚金额;
     * <p>
     * 生成清单;
     */
    @SuppressLint("StringFormatMatches")
    public static List<PostPlayBean> updateTouZhuViewUI(List<PlayDetailBean> mPlayDetailBeans,
                                                        TextView mTvTzCountMoney, TextView mTvTzPrizedMoney, TextView mTvInputNote) {
        List<PostPlayBean> postPlayBeans = new ArrayList<>();//生成的注单清单

        long inputCount;//每注投的钱
        if (TextUtils.isEmpty(mTvInputNote.getText().toString())) {
            inputCount = 0;
        } else {
            inputCount = Long.parseLong(mTvInputNote.getText().toString());
        }
        if (mPlayDetailBeans.size() == 0) {
            mTvTzCountMoney.setText(R.string.zs_default);
            mTvTzPrizedMoney.setText(R.string.ys_default);
            return postPlayBeans;
        }

        String playType = mPlayDetailBeans.get(0).getType();

        if (playType.equals(getRec().getString(R.string.RZDW))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            String childType1 = kind.substring(0, 1);
            String childType2 = kind.substring(1, 2);
            List<PlayDetailBean> list1 = new ArrayList<>();
            List<PlayDetailBean> list2 = new ArrayList<>();
            for (PlayDetailBean playDetailBean : mPlayDetailBeans) {
                if (playDetailBean.getChildType().equals(childType1)) {
                    list1.add(playDetailBean);
                } else if (playDetailBean.getChildType().equals(childType2)) {
                    list2.add(playDetailBean);
                }
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int y = 0; y < list2.size(); y++) {
                    PostPlayBean postPlayBean = new PostPlayBean();
                    postPlayBean.setType(playType);
                    postPlayBean.setMoney(inputCount + "");
                    postPlayBean.setOdd(list1.get(0).getOdd());
                    postPlayBean.setKind(list1.get(0).getKind().substring(0, 1) + "," + list1.get(0).getKind().substring(1, 2));
                    postPlayBean.setPostNum(list1.get(i).getNum() + "" + list2.get(y).getNum());
                    postPlayBean.setCode(list1.get(0).getCode());
                    postPlayBean.setPlayCode(list1.get(0).getPlayCode());
                    postPlayBean.setBetCode(list1.get(0).getBetCode());
                    postPlayBeans.add(postPlayBean);
                }
            }
            int tzCount = postPlayBeans.size();//投注数量
            Object[] str1 = {tzCount + "", BalanceUtils.getScalsBalance(inputCount * tzCount) + ""};
            mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
            if (postPlayBeans.size() > 0) {
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount * 1))));
            } else {
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(mPlayDetailBeans.get(0).getOdd()) * inputCount * tzCount))));
            }
        } else if (playType.equals(getRec().getString(R.string.SZDW))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            String childType1 = kind.substring(0, 1);
            String childType2 = kind.substring(1, 2);
            String childType3 = kind.substring(2, 3);
            List<PlayDetailBean> list1 = new ArrayList<>();
            List<PlayDetailBean> list2 = new ArrayList<>();
            List<PlayDetailBean> list3 = new ArrayList<>();
            for (PlayDetailBean playDetailBean : mPlayDetailBeans) {
                if (playDetailBean.getChildType().equals(childType1)) {
                    list1.add(playDetailBean);
                } else if (playDetailBean.getChildType().equals(childType2)) {
                    list2.add(playDetailBean);
                } else if (playDetailBean.getChildType().equals(childType3)) {
                    list3.add(playDetailBean);
                }
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int y = 0; y < list2.size(); y++) {
                    for (int z = 0; z < list3.size(); z++) {
                        PostPlayBean postPlayBean = new PostPlayBean();
                        postPlayBean.setType(playType);
                        postPlayBean.setMoney(inputCount + "");
                        postPlayBean.setOdd(list1.get(0).getOdd());
                        postPlayBean.setKind(list1.get(0).getKind().substring(0, 1) + "," + list1.get(0).getKind().substring(1, 2) + "," + list1.get(0).getKind().substring(2, 3));
                        postPlayBean.setPostNum(list1.get(i).getNum() + "" + list2.get(y).getNum() + "" + list3.get(z).getNum());
                        postPlayBean.setCode(list1.get(0).getCode());
                        postPlayBean.setPlayCode(list1.get(0).getPlayCode());
                        postPlayBean.setBetCode(list1.get(0).getBetCode());
                        postPlayBeans.add(postPlayBean);
                    }
                }
            }
            int tzCount = postPlayBeans.size();//投注数量
            Object[] str1 = {tzCount + "", BalanceUtils.getScalsBalance((inputCount * tzCount)) + ""};
            mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
            if (postPlayBeans.size() > 0) {
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount * 1))));
            } else {
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(mPlayDetailBeans.get(0).getOdd()) * inputCount * tzCount))));
            }

        } else if (playType.equals(getRec().getString(R.string.ZXS))) {

            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());
            if (mPlayDetailBeans.size() == 5
                    || mPlayDetailBeans.size() == 6
                    || mPlayDetailBeans.size() == 7
                    || mPlayDetailBeans.size() == 8
                    || mPlayDetailBeans.size() == 9
                    || mPlayDetailBeans.size() == 10) {
                LotteryOddBean lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":" + mPlayDetailBeans.size());
                if (lotteryOddBean != null) {
                    postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                } else {
                    if (mPlayDetailBeans.size() == 5) {
                        postPlayBean.setOdd("6.7");
                    } else if (mPlayDetailBeans.size() == 6) {
                        postPlayBean.setOdd("4.47");
                    } else if (mPlayDetailBeans.size() == 7) {
                        postPlayBean.setOdd("3.19");
                    } else if (mPlayDetailBeans.size() == 8) {
                        postPlayBean.setOdd("2.39");
                    } else if (mPlayDetailBeans.size() == 9) {
                        postPlayBean.setOdd("1.86");
                    } else if (mPlayDetailBeans.size() == 10) {
                        postPlayBean.setOdd("1.49");
                    }
                }
                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }

        } else if (playType.equals(getRec().getString(R.string.ZXL))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());

            if (mPlayDetailBeans.size() == 4
                    || mPlayDetailBeans.size() == 5
                    || mPlayDetailBeans.size() == 6
                    || mPlayDetailBeans.size() == 7
                    || mPlayDetailBeans.size() == 8) {
                LotteryOddBean lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":" + mPlayDetailBeans.size());
                if (lotteryOddBean != null) {
                    postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                } else {
                    if (mPlayDetailBeans.size() == 4) {
                        postPlayBean.setOdd("24.4");
                    } else if (mPlayDetailBeans.size() == 5) {
                        postPlayBean.setOdd("9.76");
                    } else if (mPlayDetailBeans.size() == 6) {
                        postPlayBean.setOdd("4.88");
                    } else if (mPlayDetailBeans.size() == 7) {
                        postPlayBean.setOdd("2.79");
                    } else if (mPlayDetailBeans.size() == 8) {
                        postPlayBean.setOdd("1.74");
                    }
                }
                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }

        } else if (playType.equals(getRec().getString(R.string.TMBS))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());
            if (mPlayDetailBeans.size() == 3) {
                postPlayBean.setOdd(mPlayDetailBeans.get(0).getOdd());
                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }

        } else if (playType.equals(getRec().getString(R.string.x5))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());
            if (mPlayDetailBeans.size() == 5) {
                if (mPlayDetailBeans.get(0).getOdd().isEmpty()) {
                    postPlayBean.setOdd("21");
                    LotteryOddBean lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":选五-中5");
                    if (lotteryOddBean != null) {
                        postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                    }

                } else {
                    postPlayBean.setOdd(mPlayDetailBeans.get(0).getOdd() + "");
                }
                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }
        } else if (playType.equals(getRec().getString(R.string.x4))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());
            if (mPlayDetailBeans.size() == 4) {
                if (mPlayDetailBeans.get(0).getOdd().isEmpty()) {
                    postPlayBean.setOdd("21");
                    LotteryOddBean lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":选四-中4");
                    if (lotteryOddBean != null) {
                        postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                    }

                } else {
                    postPlayBean.setOdd(mPlayDetailBeans.get(0).getOdd() + "");
                }
                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }
        } else if (playType.equals(getRec().getString(R.string.x3))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());
            if (mPlayDetailBeans.size() == 3) {
                if (mPlayDetailBeans.get(0).getOdd().isEmpty()) {
                    postPlayBean.setOdd("21");
                    LotteryOddBean lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":选三-中3");
                    if (lotteryOddBean != null) {
                        postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                    }
                } else {
                    postPlayBean.setOdd(mPlayDetailBeans.get(0).getOdd() + "");
                }
                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }
        } else if (playType.equals(getRec().getString(R.string.x2))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());
            if (mPlayDetailBeans.size() == 2) {
                if (mPlayDetailBeans.get(0).getOdd().isEmpty()) {
                    postPlayBean.setOdd("21");
                    LotteryOddBean lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":中2");
                    if (lotteryOddBean != null) {
                        postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                    }

                } else {
                    postPlayBean.setOdd(mPlayDetailBeans.get(0).getOdd() + "");
                }
                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }
        } else if (playType.equals(getRec().getString(R.string.x1))) {
            String kind = mPlayDetailBeans.get(0).getKind();
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum() + ",");
                }
            }
            postPlayBean.setMoney(inputCount + "");
            postPlayBean.setKind(kind);
            postPlayBean.setType(playType);
            postPlayBean.setPostNum(stringBuilder.toString());
            postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
            postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
            postPlayBean.setBetCode(mPlayDetailBeans.get(0).getBetCode());
            if (mPlayDetailBeans.size() == 1) {
                if (mPlayDetailBeans.get(0).getOdd().isEmpty()) {
                    postPlayBean.setOdd("3.3");
                    LotteryOddBean lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":中1");
                    if (lotteryOddBean != null) {
                        postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                    }
                } else {
                    postPlayBean.setOdd(mPlayDetailBeans.get(0).getOdd());
                }

                postPlayBeans.add(postPlayBean);
            } else {
                postPlayBean.setOdd("0");
            }

            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }
        } else if (playType.equals(getRec().getString(R.string.zh_rzzh))) {
            //组合
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            PlayDetailBean ball1 = null;
            PlayDetailBean ball2 = null;
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                }

                if (mPlayDetailBeans.get(i).getKind().equals(getRec().getString(R.string.zh_dyq))) {
                    ball1 = mPlayDetailBeans.get(i);
                }
                if (mPlayDetailBeans.get(i).getKind().equals(getRec().getString(R.string.zh_drq))) {
                    ball2 = mPlayDetailBeans.get(i);
                }
            }

            if (ball1 != null && ball2 != null && mPlayDetailBeans.size() == 2) {
                postPlayBean.setMoney(inputCount + "");
                postPlayBean.setPostNum(stringBuilder.toString());
                postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
                postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
                LotteryOddBean lotteryOddBean;
                if (ball1.getNum().equals(ball2.getNum())) {
                    postPlayBean.setBetCode(getRec().getString(R.string.pl3_two_same_二同));
                    lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":" + "二同");
                } else {
                    postPlayBean.setBetCode(getRec().getString(R.string.pl3_two_different_二不同));
                    lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":" + "二不同");
                }
                if (lotteryOddBean != null) {
                    postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                    postPlayBeans.add(postPlayBean);
                }
            }
            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }

        } else if (playType.equals(getRec().getString(R.string.zh_szzh))) {
            PostPlayBean postPlayBean = new PostPlayBean();
            StringBuilder stringBuilder = new StringBuilder();
            PlayDetailBean ball1 = null;
            PlayDetailBean ball2 = null;
            PlayDetailBean ball3 = null;
            for (int i = 0; i < mPlayDetailBeans.size(); i++) {
                if (i == mPlayDetailBeans.size() - 1) {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                } else {
                    stringBuilder.append(mPlayDetailBeans.get(i).getNum());
                }

                if (mPlayDetailBeans.get(i).getKind().equals(getRec().getString(R.string.zh_dyq))) {
                    ball1 = mPlayDetailBeans.get(i);
                }
                if (mPlayDetailBeans.get(i).getKind().equals(getRec().getString(R.string.zh_drq))) {
                    ball2 = mPlayDetailBeans.get(i);
                }
                if (mPlayDetailBeans.get(i).getKind().equals(getRec().getString(R.string.zh_dsq))) {
                    ball3 = mPlayDetailBeans.get(i);
                }
            }

            if (ball1 != null && ball2 != null && ball3 != null && mPlayDetailBeans.size() == 3) {
                postPlayBean.setMoney(inputCount + "");
                postPlayBean.setPostNum(stringBuilder.toString());
                postPlayBean.setCode(mPlayDetailBeans.get(0).getCode());
                postPlayBean.setPlayCode(mPlayDetailBeans.get(0).getPlayCode());
                LotteryOddBean lotteryOddBean;
                if (ball1.getNum().equals(ball2.getNum()) && ball1.getNum().equals(ball3.getNum())) {
                    postPlayBean.setBetCode(getRec().getString(R.string.pl3_three_same_三同));
                    lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":" + "三同");
                } else if (ball1.getNum().equals(ball2.getNum())
                        || ball1.getNum().equals(ball3.getNum())
                        || ball2.getNum().equals(ball3.getNum())) {
                    postPlayBean.setBetCode(getRec().getString(R.string.pl3_three_group3_组三));
                    lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":" + "组三");
                } else {
                    postPlayBean.setBetCode(getRec().getString(R.string.pl3_three_group6_组六));
                    lotteryOddBean = getOddMap().get(postPlayBean.getBetCode() + ":" + "组六");
                }
                if (lotteryOddBean != null) {
                    postPlayBean.setOdd(lotteryOddBean.getOdd() + "");
                    postPlayBeans.add(postPlayBean);
                }
            }
            if (postPlayBeans.size() > 0) {
                Object[] str1 = {"1", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((Double.parseDouble(postPlayBeans.get(0).getOdd()) * inputCount))));
            } else {
                Object[] str1 = {"0", BalanceUtils.getScalsBalance(inputCount) + ""};
                mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
                mTvTzPrizedMoney.setText(getRec().getString(R.string.ys_default));
            }

        } else {
            int tzCount = mPlayDetailBeans.size();//投注数量
            double odd = 0.00;
            for (PlayDetailBean playDetailBean : mPlayDetailBeans) {
                //  odd = odd + Double.parseDouble(playDetailBean.getOdd());
                if (Double.parseDouble(playDetailBean.getOdd()) > odd) {
                    odd = Double.parseDouble(playDetailBean.getOdd());
                }
                PostPlayBean postPlayBean = new PostPlayBean();
                postPlayBean.setOdd(playDetailBean.getOdd());
                postPlayBean.setMoney(inputCount + "");
                postPlayBean.setType(playType);
                postPlayBean.setKind(mPlayDetailBeans.get(0).getKind());
                postPlayBean.setPostNum(playDetailBean.getNum());
                postPlayBean.setCode(playDetailBean.getCode());
                postPlayBean.setPlayCode(playDetailBean.getPlayCode());
                postPlayBean.setBetCode(playDetailBean.getBetCode());
                postPlayBeans.add(postPlayBean);
            }
            Object[] str1 = {tzCount + "", BalanceUtils.getScalsBalance(inputCount * tzCount) + ""};
            mTvTzCountMoney.setText(getRec().getString(R.string.sum_note, str1));
            mTvTzPrizedMoney.setText(getRec().getString(R.string.max_prized, BalanceUtils.getScalsBalance((odd * inputCount))));
        }

        return postPlayBeans;
    }

    /**
     * 检验要提交的注单数据源
     */
    public static Boolean checkPostBeans(String playType, List<PostPlayBean> postPlayBeans) {
        if (postPlayBeans.size() == 0) {
            if (playType.equals(getRec().getString(R.string.ZXS)) || playType.equals(getRec().getString(R.string.zusan))) {
                SingleToast.showMsg(getRec().getString(R.string.min_5));
                return false;
            } else if (playType.equals(getRec().getString(R.string.ZXL)) || playType.equals(getRec().getString(R.string.zuliu))) {
                SingleToast.showMsg(getRec().getString(R.string.min_4_8));
                return false;
            } else if (playType.equals(getRec().getString(R.string.RZDW))) {
                SingleToast.showMsg(getRec().getString(R.string.wei_2));
                return false;
            } else if (playType.equals(getRec().getString(R.string.SZDW))) {
                SingleToast.showMsg(getRec().getString(R.string.wei_3));
                return false;
            } else if (playType.equals(R.string.dingwei)) {
                SingleToast.showMsg(getRec().getString(R.string.wei_n));
                return false;
            } else if (playType.equals(getRec().getString(R.string.TMBS))) {
                SingleToast.showMsg(getRec().getString(R.string.sure_3));
                return false;
            } else if (playType.equals(getRec().getString(R.string.x5))) {
                SingleToast.showMsg(getRec().getString(R.string.sure_5));
                return false;
            } else if (playType.equals(getRec().getString(R.string.x4))) {
                SingleToast.showMsg(getRec().getString(R.string.sure_4));
                return false;
            } else if (playType.equals(getRec().getString(R.string.x3))) {
                SingleToast.showMsg(getRec().getString(R.string.sure_3));
                return false;
            } else if (playType.equals(getRec().getString(R.string.x2))) {
                SingleToast.showMsg(getRec().getString(R.string.sure_2));
                return false;
            } else if (playType.equals(getRec().getString(R.string.x1))) {
                SingleToast.showMsg(getRec().getString(R.string.sure_1));
                return false;
            } else if (playType.equals(getRec().getString(R.string.zuhe))) {
                SingleToast.showMsg(getRec().getString(R.string.wei_just_one));
                return false;
            } else {
                SingleToast.showMsg(getRec().getString(R.string.min_1));
                return false;
            }
        }
        return true;
    }


    /**
     * 获得时时彩B 五星形态
     */

    public static String getFiveStarStatus(String openCode) {
        String sum = null;
        String bigSmall = null;
        String ds = null;
        String lhh = null;


        String code = openCode.replace(",", "");
        char[] chars = code.toCharArray();
        sum = (Integer.parseInt(chars[0] + "")
                + Integer.parseInt(chars[1] + "")
                + Integer.parseInt(chars[2] + "")
                + Integer.parseInt(chars[3] + "")
                + Integer.parseInt(chars[4] + "")) + "";

        if (Integer.parseInt(sum) > 22) {
            bigSmall = getRec().getString(R.string.big);
        } else {
            bigSmall = getRec().getString(R.string.small);
        }

        if (Integer.parseInt(sum) % 2 == 0) {
            ds = getRec().getString(R.string.double_);
        } else {
            ds = getRec().getString(R.string.single);
        }

        if (Integer.parseInt(chars[0] + "") > Integer.parseInt(chars[4] + "")) {
            lhh = getRec().getString(R.string.dragon);
        } else if (Integer.parseInt(chars[0] + "") == Integer.parseInt(chars[4] + "")) {
            lhh = getRec().getString(R.string.he);
        } else {
            lhh = getRec().getString(R.string.tiger);
        }

        return sum + " " + bigSmall + " " + ds + " " + lhh;

    }

    /**
     * pk10 龙湖双小计算
     */

    public static String getPK10StarStatus(String[] nums) {
        StringBuffer strNum = new StringBuffer();
        int num = Integer.valueOf(nums[0]) + Integer.valueOf(nums[1]);
        strNum.append(num + ",");
        strNum.append((num % 2) == 0 ? getRec().getString(R.string.double_) + "," : getRec().getString(R.string.single) + ",");
        strNum.append(num < 12 ? getRec().getString(R.string.small) + "," : getRec().getString(R.string.big) + ",");
        strNum.append(Integer.valueOf(nums[0]) > Integer.valueOf(nums[9]) ? getRec().getString(R.string.dragon) + "," : getRec().getString(R.string.tiger) + ",");
        strNum.append(Integer.valueOf(nums[1]) > Integer.valueOf(nums[8]) ? getRec().getString(R.string.dragon) + "," : getRec().getString(R.string.tiger) + ",");
        strNum.append(Integer.valueOf(nums[2]) > Integer.valueOf(nums[7]) ? getRec().getString(R.string.dragon) + "," : getRec().getString(R.string.tiger) + ",");
        strNum.append(Integer.valueOf(nums[3]) > Integer.valueOf(nums[6]) ? getRec().getString(R.string.dragon) + "," : getRec().getString(R.string.tiger) + ",");
        strNum.append(Integer.valueOf(nums[4]) > Integer.valueOf(nums[5]) ? getRec().getString(R.string.dragon) : getRec().getString(R.string.tiger));
        return strNum.toString();
    }


    /**
     * 获得排列三B 三星形态
     */

    public static String getSanStarStatus(String openCode) {
        String sum;
        String bigSmall;
        String ds;
        String lhh;


        String[] codes = openCode.split(",");
        sum = (Integer.parseInt(codes[0])
                + Integer.parseInt(codes[1])
                + Integer.parseInt(codes[2])) + "";

        if (Integer.parseInt(sum) > 13) {
            bigSmall = getRec().getString(R.string.big);
        } else {
            bigSmall = getRec().getString(R.string.small);
        }

        if (Integer.parseInt(sum) % 2 == 0) {
            ds = getRec().getString(R.string.double_);
        } else {
            ds = getRec().getString(R.string.single);
        }

        int a = Integer.parseInt(codes[0]);
        int b = Integer.parseInt(codes[1]);
        int c = Integer.parseInt(codes[2]);

        if (a != b & a != c & b != c) {
            lhh = "组六";
        } else if (a == b && a == c) {
            lhh = "豹子";
        } else {
            lhh = "组三";
        }

        return sum + " " + bigSmall + " " + ds + " " + lhh;

    }


    /**
     * 清除选中状态
     *
     * @param mList
     * @return
     */
    public static List<PlayDetailBean> clearSelected(List<PlayDetailBean> mList) {
        if (mList == null) return null;
        if (!mList.isEmpty()) {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setSelected(false);
            }
        }
        return mList;
    }

    public static boolean isNum(String betNum) {
        return Pattern.compile("[0-9]{1,2}").matcher(betNum).matches();
    }

    /**
     * 是否是质数
     *
     * @param n
     * @return
     */
    public static boolean isPrime(long n) {
        if (n > 2 && (n & 1) == 0)
            return false;
        /* 运用试除法:
         * 1.只有奇数需要被测试
         * 2.测试范围从2与根号{n},反之亦然 */
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    /**
     * 北京快乐8的 合数单双金木水火土
     */
    public static String getBjkl8RewardStatus(String openCode) {
        if (TextUtils.isEmpty(openCode)) return "";
        int he = 0;
        String sb = "";
        String doubleorSingle = "";
        String sx = "";
        String oh = "";
        String jmsht = "";

        List<String> openCodes = Arrays.asList(openCode.split(","));
        for (String code : openCodes) {
            he = he + Integer.parseInt(code);
        }

        if (he >= 810) {
            sb = "大";
        } else {
            sb = "小";
        }

        if (he % 2 == 0) {
            doubleorSingle = "双";
        } else {
            doubleorSingle = "单";
        }

        if (Integer.parseInt(openCodes.get(10)) <= 40) {
            sx = "上";
        } else if (Integer.parseInt(openCodes.get(9)) >= 41) {
            sx = "中";
        } else if (Integer.parseInt(openCodes.get(9)) < 41 && Integer.parseInt(openCodes.get(10)) > 40) {
            sx = "下";
        }

        if (getEvenCount(openCodes) == 10) {
            oh = "和";
        } else if (getEvenCount(openCodes) > 10) {
            oh = "偶";
        } else {
            oh = "奇";
        }

        if (getCount(openCodes) < 696) {
            jmsht = "金";
        } else if (getCount(openCodes) > 695 && getCount(openCodes) < 764) {
            jmsht = "木";
        } else if (getCount(openCodes) > 763 && getCount(openCodes) < 856) {
            jmsht = "水";
        } else if (getCount(openCodes) > 855 && getCount(openCodes) < 924) {
            jmsht = "火";
        } else if (getCount(openCodes) > 925) {
            jmsht = "土";
        }

        return "和: " + he + " " + sb + " " + doubleorSingle + " " + sx + " " + oh + " " + jmsht;
    }


    /**
     * 美化布局 动态设置列表的列数
     */
    public static void setSpanCountByData(int dataSize, Context context, int lastPosition, View itemView) {
        if (dataSize % 2 != 0) {
            if (lastPosition == dataSize - 1) {
                StaggeredGridLayoutManager.LayoutParams layoutParams =
                        new StaggeredGridLayoutManager.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                DensityUtil.dp2px(context, 70));
                layoutParams.setFullSpan(true);
                itemView.setLayoutParams(layoutParams);
            }
        }
    }

}
