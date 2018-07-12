package com.dawoo.lotterybox.util.lottery.initdata;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.BetOrdersListBean;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.LotteryOddBean;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.bean.playtype.ParentPlayTypeBean;
import com.dawoo.lotterybox.bean.playtype.PlayChooseBean;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import cn.aigestudio.datepicker.entities.DPInfo;

/**
 * Created by rain on 18-3-7.
 */

public class LHCDataUtils {
    private static final String CODE = "hklhc";
    private static List<LotteryOddBean> mAllLotteryOdds = new ArrayList<>();
    private static List<Handicap> mHandicaps = new ArrayList<>();
    static final String COLOR_RED = "red", COLOR_BLUE = "blue", COLOR_GREEN = "green";
    public static Map<String, List<LotteryOddBean>> listMaps = new HashMap<>();
    public static int hot = 0;

    public static void setmAllLotteryOdds(List<LotteryOddBean> mAllLotteryOdds) {
        LHCDataUtils.mAllLotteryOdds = mAllLotteryOdds;
        listMaps.clear();
    }

    public static List<Handicap> getmHandicaps() {
        return mHandicaps;
    }

    public static void setmHandicaps(List<Handicap> mHandicaps) {
        LHCDataUtils.mHandicaps = mHandicaps;
        listMaps.clear();
    }

    public static List<LotteryOddBean> getOddersBYCode(String code) {
        if (listMaps.get(code) == null || listMaps.get(code).isEmpty()) {
            return getmAllLotteryOdds();
        } else {
            Log.e("what_time", code);
            return listMaps.get(code);
        }
    }

    public static List<LotteryOddBean> getmAllLotteryOdds() {
        return mAllLotteryOdds;
    }

    public static void setmAllLotteryOdds(Collection<LotteryOddBean> mAllLotteryOdds) {
        if (!LHCDataUtils.mAllLotteryOdds.isEmpty()) {
            LHCDataUtils.mAllLotteryOdds.clear();
        }
        LHCDataUtils.mAllLotteryOdds.addAll(mAllLotteryOdds);
    }

    //初始化 左边导航选择项的数据源
    public static List<ParentPlayTypeBean> initChooseData() {
        List<ParentPlayTypeBean> sscbPlayChhooseBeanList = new ArrayList<>();
        List<String> stringList = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_parent_play_type_name));

        for (int i = 0; i < stringList.size(); i++) {
            ParentPlayTypeBean sscbPlayChhooseBean = new ParentPlayTypeBean();
            if (i == 0) {
                sscbPlayChhooseBean.setChoose(true);
            }
            sscbPlayChhooseBean.setName(stringList.get(i));
            sscbPlayChhooseBeanList.add(sscbPlayChhooseBean);
        }
        return sscbPlayChhooseBeanList;
    }

    //刷新 左边导航选择项的数据源
    public static void refreshChooseData(List<ParentPlayTypeBean> list, int position) {
        for (int i = 0; i < list.size(); i++) {
            if (i == position) {
                list.get(i).setChoose(true);
            } else {
                list.get(i).setChoose(false);
            }
        }
    }

    /**
     * 初始特码数据源
     *
     * @return
     */
    public static Map<String, List<PlayDetailBean>> getZmPlays(String code) {
        List<LotteryOddBean> mList = new ArrayList<>();
        Map<String, List<PlayDetailBean>> listMap = new HashMap<>();
        List<PlayDetailBean> bollsPlays = new ArrayList<>();
        List<PlayDetailBean> wordPlays = new ArrayList<>();
        List<PlayDetailBean> colorPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(code)) {
            if (bean.getBetCode().equalsIgnoreCase(code) &&
                    !bean.getPlayCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_tm_sx))
                    ) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                if (Pattern.compile("[0-9]{2}").matcher(playDetailBean.getNum()).matches()) {
                    playDetailBean.setLr(String.valueOf(getHotNum(0, 6, bean.getBetNum(), false)));
                    playDetailBean.setYl(String.valueOf(getBollMissNum(0, 6, bean.getBetNum(), false)));
                    bollsPlays.add(playDetailBean);
                    mList.add(bean);
                } else {
                    if (playDetailBean.getNum().contains("波")) {
                        playDetailBean.setLr(String.valueOf(getColorHotNum(6, bean.getBetNum())));
                        playDetailBean.setYl(String.valueOf(getColorMissNum(6, bean.getBetNum())));
                        mList.add(bean);
                        colorPlays.add(playDetailBean);
                    } else {
                        mList.add(bean);
                        playDetailBean.setLr(getStringTypeNum(1, 6, bean.getBetNum()));
                        playDetailBean.setYl(getStringTypeNum(2, 6, bean.getBetNum()));
                        wordPlays.add(playDetailBean);
                    }
                }
                if (bollsPlays.size() + colorPlays.size() + wordPlays.size() == 66) {
                    break;
                }
            }
        }

        Collections.sort(bollsPlays, new numLenthComparator());
        Collections.sort(wordPlays, new numLenthComparator());
        Collections.sort(colorPlays, new numLenthComparator());
        wordPlays.addAll(colorPlays);
        listMap.put("boll", bollsPlays);
        listMap.put("word", wordPlays);
        listMaps.put(code, mList);
        return listMap;
    }

    /**
     * 初始化正码数据源
     *
     * @return
     */
    public static Map<String, List<PlayDetailBean>> getPositiveDatas() {
        List<LotteryOddBean> mList = new ArrayList<>();
        Map<String, List<PlayDetailBean>> listMap = new HashMap<>();
        List<PlayDetailBean> bollsPlays = new ArrayList<>();
        List<PlayDetailBean> wordPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(BoxApplication.getContext().getResources().getString(R.string.lhc_zm))) {
            if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_zm))) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                playDetailBean.setLr(String.valueOf(getHotNum(0, 5, bean.getBetNum(), true)));
                playDetailBean.setYl(String.valueOf(getBollMissNum(0, 5, bean.getBetNum(), true)));
                bollsPlays.add(playDetailBean);
                mList.add(bean);
            } else if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_sum))) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                playDetailBean.setLr(String.valueOf(getSumNum(1, bean.getBetNum())));
                playDetailBean.setYl(String.valueOf(getSumNum(2, bean.getBetNum())));
                wordPlays.add(playDetailBean);
                mList.add(bean);
            }
        }


        Collections.sort(bollsPlays, new numLenthComparator());
        Collections.sort(wordPlays, new numLenthComparator());
        listMap.put("boll", bollsPlays);
        listMap.put("word", wordPlays);
        listMaps.put(BoxApplication.getContext().getResources().getString(R.string.lhc_zm), mList);
        return listMap;
    }

    /**
     * 根据码数获取正码特数据源
     *
     * @return
     */
    public static Map<String, List<PlayDetailBean>> getDatasByPositive(String name) {
        List<LotteryOddBean> mList = new ArrayList<>();
        Map<String, List<PlayDetailBean>> listMap = new HashMap<>();
        List<PlayDetailBean> bollsPlays = new ArrayList<>();
        List<PlayDetailBean> wordPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(name)) {
            if (bean.getBetCode().equalsIgnoreCase(name)) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                int index = 0;
                if (name.contains("first")) {
                    index = 0;
                } else if (name.contains("sec")) {
                    index = 1;
                } else if (name.contains("third")) {
                    index = 2;
                } else if (name.contains("four")) {
                    index = 3;
                } else if (name.contains("fifth")) {
                    index = 4;
                } else if (name.contains("six")) {
                    index = 5;
                }
                if (bean.getPlayCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_zmt_digital))) {
                    playDetailBean.setLr(String.valueOf(getHotNum(0, index, bean.getBetNum(), false)));
                    playDetailBean.setYl(String.valueOf(getBollMissNum(0, index, bean.getBetNum(), false)));
                    mList.add(bean);
                    bollsPlays.add(playDetailBean);
                } else {
                    if (bean.getBetCode().contains("波")) {
                        playDetailBean.setLr(String.valueOf(getColorHotNum(index, bean.getBetNum())));
                        playDetailBean.setYl(String.valueOf(getColorMissNum(index, bean.getBetNum())));
                    } else {
                        playDetailBean.setLr(getStringTypeNum(1, index, bean.getBetNum()));
                        playDetailBean.setYl(getStringTypeNum(2, index, bean.getBetNum()));
                    }
                    mList.add(bean);
                    wordPlays.add(playDetailBean);
                }

                if (wordPlays.size() >= 13 && bollsPlays.size() == 49) {
                    break;
                }

            }
        }
        Collections.sort(bollsPlays, new numLenthComparator());
        Collections.sort(wordPlays, new numLenthComparator());
        listMap.put("boll", bollsPlays);
        listMap.put("word", wordPlays);
        listMaps.put(name, mList);
        return listMap;
    }

    /**
     * 初始化正码一到6
     *
     * @return
     */
    public static List<PlayDetailBean> getOneToSixDatas() {
        List<LotteryOddBean> mList = new ArrayList<>();
        List<PlayDetailBean> mDatas = new ArrayList<>();
        List<PlayDetailBean> mbeans1 = new ArrayList<>();
        List<PlayDetailBean> mbeans2 = new ArrayList<>();
        List<PlayDetailBean> mbeans3 = new ArrayList<>();
        List<PlayDetailBean> mbeans4 = new ArrayList<>();
        List<PlayDetailBean> mbeans5 = new ArrayList<>();
        List<PlayDetailBean> mbeans6 = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode("onetosix")) {
            if (!bean.getPlayCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_zmt_digital))) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                playDetailBean.setBetCode(bean.getBetCode());
                if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lch_bcode_z1t))) {
                    playDetailBean.setKind("1");
                    playDetailBean.setLr(getStringTypeNum(1, 0, bean.getBetNum()));
                    playDetailBean.setYl(getStringTypeNum(2, 0, bean.getBetNum()));
                    mbeans1.add(playDetailBean);
                } else if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lch_bcode_z2t))) {
                    playDetailBean.setLr(getStringTypeNum(1, 1, bean.getBetNum()));
                    playDetailBean.setYl(getStringTypeNum(2, 1, bean.getBetNum()));
                    playDetailBean.setKind("2");
                    mbeans2.add(playDetailBean);
                } else if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lch_bcode_z3t))) {
                    playDetailBean.setLr(getStringTypeNum(1, 2, bean.getBetNum()));
                    playDetailBean.setYl(getStringTypeNum(2, 2, bean.getBetNum()));
                    playDetailBean.setKind("3");
                    mbeans3.add(playDetailBean);
                } else if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lch_bcode_z4t))) {
                    playDetailBean.setKind("4");
                    playDetailBean.setLr(getStringTypeNum(1, 3, bean.getBetNum()));
                    playDetailBean.setYl(getStringTypeNum(2, 3, bean.getBetNum()));
                    mbeans4.add(playDetailBean);
                } else if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lch_bcode_z5t))) {
                    playDetailBean.setKind("5");
                    playDetailBean.setLr(getStringTypeNum(1, 4, bean.getBetNum()));
                    playDetailBean.setYl(getStringTypeNum(2, 4, bean.getBetNum()));
                    mbeans5.add(playDetailBean);
                } else if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lch_bcode_z6t))) {
                    playDetailBean.setKind("6");
                    playDetailBean.setLr(getStringTypeNum(1, 5, bean.getBetNum()));
                    playDetailBean.setYl(getStringTypeNum(2, 5, bean.getBetNum()));
                    mbeans6.add(playDetailBean);
                }
                mList.add(bean);
            }
        }
        Collections.sort(mbeans1, new numLenthComparator());
        Collections.sort(mbeans2, new numLenthComparator());
        Collections.sort(mbeans3, new numLenthComparator());
        Collections.sort(mbeans4, new numLenthComparator());
        Collections.sort(mbeans5, new numLenthComparator());
        Collections.sort(mbeans6, new numLenthComparator());
        PlayDetailBean ask0 = new PlayDetailBean();
        ask0.setAskview(true);
        ask0.setNum("正码一");
        ask0.setCode("positive_one_big_small");

        PlayDetailBean ask1 = new PlayDetailBean();
        ask1.setAskview(true);
        ask1.setNum("正码二");
        ask1.setCode("positive_two_big_small");

        PlayDetailBean ask2 = new PlayDetailBean();
        ask2.setAskview(true);
        ask2.setNum("正码三");
        ask2.setCode("positive_three_big_small");
        PlayDetailBean ask3 = new PlayDetailBean();
        ask3.setAskview(true);
        ask3.setNum("正码四");
        ask3.setCode("positive_four_big_small");
        PlayDetailBean ask4 = new PlayDetailBean();
        ask4.setAskview(true);
        ask4.setNum("正码五");
        ask4.setCode("positive_five_big_small");
        PlayDetailBean ask5 = new PlayDetailBean();
        ask5.setAskview(true);
        ask5.setNum("正码六");
        ask5.setCode("positive_six_big_small");
        mDatas.add(ask0);
        mDatas.addAll(mbeans1);
        mDatas.add(ask1);
        mDatas.addAll(mbeans2);
        mDatas.add(ask2);
        mDatas.addAll(mbeans3);
        mDatas.add(ask3);
        mDatas.addAll(mbeans4);
        mDatas.add(ask4);
        mDatas.addAll(mbeans5);
        mDatas.add(ask5);
        mDatas.addAll(mbeans6);
        listMaps.put("onetosix", mList);
        return mDatas;
    }

    /**
     * 获取半波数据源
     *
     * @return
     */
    public static Map<String, List<PlayDetailBean>> getHalfColor() {
        List<LotteryOddBean> mlist = new ArrayList<>();

        Map<String, List<PlayDetailBean>> listMap = new HashMap<>();

        List<PlayDetailBean> wordPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(BoxApplication.getContext().getResources().getString(R.string.lhc_half))) {
            if (bean.getBetCode().equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_half))) {
                if (bean.getPlayCode().length() > 3) {
                    PlayDetailBean playDetailBean = new PlayDetailBean();
                    playDetailBean.setType(bean.getBetCode());
                    playDetailBean.setBetCode(bean.getBetCode());
                    playDetailBean.setChildType(bean.getPlayCode());
                    playDetailBean.setNum(bean.getBetNum());
                    playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                    playDetailBean.setKind(LHCUtil.getBollsByName(bean.getBetNum()));
                    playDetailBean.setLr(getHalfColorNum(1, bean.getBetNum()));
                    playDetailBean.setYl(getHalfColorNum(2, bean.getBetNum()));
                    wordPlays.add(playDetailBean);
                    mlist.add(bean);
                }
            }
        }
        Collections.sort(wordPlays, new numLenthComparator());
        int index = wordPlays.size() / 3;
        listMap.put("red", wordPlays.subList(0, index));
        listMap.put("green", wordPlays.subList(index, 2 * index));
        listMap.put("blue", wordPlays.subList(2 * index, wordPlays.size()));
        listMaps.put(BoxApplication.getContext().getResources().getString(R.string.lhc_half), mlist);
        return listMap;
    }

    /**
     * 根据球位获取生肖数据源
     *
     * @return
     */
    public static List<PlayDetailBean> getAnimationDatasByPosition(String position) {
        List<LotteryOddBean> mList = new ArrayList<>();
        Map<String, PlayDetailBean> maps = new LinkedHashMap<>();
        maps.put("鼠", null);
        maps.put("牛", null);
        maps.put("虎", null);
        maps.put("兔", null);
        maps.put("龙", null);
        maps.put("蛇", null);
        maps.put("马", null);
        maps.put("羊", null);
        maps.put("猴", null);
        maps.put("鸡", null);
        maps.put("狗", null);
        maps.put("猪", null);
        for (LotteryOddBean bean : getOddersBYCode(position + "1")) {
            if (position.contains("special")) {
                if (bean.getPlayCode().equalsIgnoreCase("special_zodiac")) {
                    PlayDetailBean playDetailBean = new PlayDetailBean();
                    playDetailBean.setType(bean.getBetCode());
                    playDetailBean.setBetCode(bean.getBetCode());
                    playDetailBean.setChildType(bean.getPlayCode());
                    playDetailBean.setNum(bean.getBetNum());
                    playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                    playDetailBean.setKind(LHCUtil.getCodesByShenxiao(bean.getBetNum()));
                    playDetailBean.setLr(getAnimalNum(1, 6, bean.getBetNum()));
                    playDetailBean.setYl(getAnimalNum(2, 6, bean.getBetNum()));
                    maps.put(bean.getBetNum(), playDetailBean);
                    mList.add(bean);
                }
            } else if (bean.getBetCode().equalsIgnoreCase(position)) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                playDetailBean.setKind(LHCUtil.getCodesByShenxiao(bean.getBetNum()));
                if (position.contains("one")) {
                    playDetailBean.setLr(getAnimalNum(1, 0, bean.getBetNum()));
                    playDetailBean.setYl(getAnimalNum(2, 0, bean.getBetNum()));
                } else {
                    playDetailBean.setLr(getAnimalNum(1, 6, bean.getBetNum()));
                    playDetailBean.setYl(getAnimalNum(2, 6, bean.getBetNum()));
                }
                mList.add(bean);
                maps.put(bean.getBetNum(), playDetailBean);
            }
        }

        List<PlayDetailBean> wordPlays = new ArrayList<>();
        wordPlays.addAll(maps.values());
        listMaps.put(position + "1", mList);
        return wordPlays;
    }

    /**
     * 根据betCode获取球数据源（尾数连）
     *
     * @param bet
     * @return
     */
    public static List<PlayDetailBean> getMantissaEnenByBet(String bet) {
        List<LotteryOddBean> mList = new ArrayList<>();
        List<PlayDetailBean> bollPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(bet)) {
            if (bean.getBetCode().equalsIgnoreCase(bet)) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                playDetailBean.setKind(LHCUtil.getCodesBynum(bean.getBetNum()));
                playDetailBean.setLr(getMantissaNum(1, bean.getBetNum()));
                playDetailBean.setYl(getMantissaNum(2, bean.getBetNum()));
                bollPlays.add(playDetailBean);
                mList.add(bean);
            }
            if (bollPlays.size() == 10) {
                break;
            }
        }
        Collections.sort(bollPlays, new numLenthComparator());
        listMaps.put(bet, mList);
        return bollPlays;
    }

    /**
     * 根据betCode获取球数据源（连码）
     *
     * @param bet
     * @return
     */
    public static List<PlayDetailBean> getBollsByBet(String bet) {
        List<LotteryOddBean> mList = new ArrayList<>();
        List<PlayDetailBean> bollPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(bet)) {
            if (bean.getBetCode().equalsIgnoreCase(bet)) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                bollPlays.add(playDetailBean);
                mList.add(bean);
            }
            if (bollPlays.size() >= 2) {
                break;
            }
        }
        Collections.sort(bollPlays, new numLenthComparator());
        listMaps.put(bet, mList);
        return getBollsinitOdd(bollPlays, true);
    }

    private static List<PlayDetailBean> getBollsinitOdd(List<PlayDetailBean> oddsList, boolean isMatch) {
        if (oddsList == null || oddsList.isEmpty()) {
            return new ArrayList<>();
        }
        PlayDetailBean bean = oddsList.get(0);
        List<PlayDetailBean> bollPlays = new ArrayList<>();
        for (int i = 01; i < 50; i++) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(bean.getType());
            playDetailBean.setChildType(bean.getChildType());
            playDetailBean.setBetCode(bean.getBetCode());
            if (i < 10) {
                playDetailBean.setNum("0" + String.valueOf(i));
            } else {
                playDetailBean.setNum(String.valueOf(i));
            }
            if (oddsList.size() == 2) {
                playDetailBean.setOdd(oddsList.get(1).getOdd() + "/" + bean.getOdd());
            } else {
                playDetailBean.setOdd(bean.getOdd());
            }
            int maxIndex;
            if (isMatch) {
                maxIndex = 5;
            } else {
                maxIndex = 6;
            }
            playDetailBean.setLr(String.valueOf(getHotNum(0, maxIndex, String.valueOf(i), true)));
            playDetailBean.setYl(String.valueOf(getBollMissNum(0, maxIndex, String.valueOf(i), true)));
            bollPlays.add(playDetailBean);
        }
        return bollPlays;
    }


    /**
     * 根据type获取连肖数据源
     *
     * @param code
     * @return
     */
    public static List<PlayDetailBean> getAnimalEvenDatasByCode(String code) {
        List<LotteryOddBean> mList = new ArrayList<>();
        Map<String, PlayDetailBean> maps = new LinkedHashMap<>();
        maps.put("鼠", null);
        maps.put("牛", null);
        maps.put("虎", null);
        maps.put("兔", null);
        maps.put("龙", null);
        maps.put("蛇", null);
        maps.put("马", null);
        maps.put("羊", null);
        maps.put("猴", null);
        maps.put("鸡", null);
        maps.put("狗", null);
        maps.put("猪", null);
        List<PlayDetailBean> bollPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(code)) {
            if (bean.getBetCode().equalsIgnoreCase(code)) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                playDetailBean.setKind(LHCUtil.getCodesByShenxiao(bean.getBetNum()));
                playDetailBean.setLr(getAnimalNum(1, bean.getBetNum()));
                playDetailBean.setYl(getAnimalNum(2, bean.getBetNum()));
                maps.put(bean.getBetNum(), playDetailBean);
                mList.add(bean);
            }

        }
        bollPlays.addAll(maps.values());
        listMaps.put(code, mList);
        return bollPlays;
    }

    private static List<PlayDetailBean> getAnimalInitOdd(List<PlayDetailBean> oddsList) {
        Map<String, PlayDetailBean> maps = new LinkedHashMap<>();
        maps.put("鼠", null);
        maps.put("牛", null);
        maps.put("虎", null);
        maps.put("兔", null);
        maps.put("龙", null);
        maps.put("蛇", null);
        maps.put("马", null);
        maps.put("羊", null);
        maps.put("猴", null);
        maps.put("鸡", null);
        maps.put("狗", null);
        maps.put("猪", null);
        String[] animalNames = BoxApplication.getContext().getResources().getStringArray(R.array.sheng_xiao_array);
        List<PlayDetailBean> animaLPlays = new ArrayList<>();
        PlayDetailBean bean = oddsList.get(0);
        for (String animal : animalNames) {
            PlayDetailBean playDetailBean = new PlayDetailBean();
            playDetailBean.setType(bean.getType());
            playDetailBean.setChildType(bean.getChildType());
            playDetailBean.setNum(animal);
            playDetailBean.setBetCode(bean.getBetCode());
            playDetailBean.setKind(LHCUtil.getCodesByShenxiao(animal));
            playDetailBean.setOdd(String.valueOf(bean.getOdd()));
            playDetailBean.setLr(getAnimalNum(1, 6, animal));
            playDetailBean.setYl(getAnimalNum(2, 6, animal));
            maps.put(animal, playDetailBean);

        }
        animaLPlays.addAll(maps.values());
        return animaLPlays;
    }


    /**
     * 根据type获取合肖数据源
     *
     * @param code
     * @return
     */
    public static List<PlayDetailBean> getAnimalDatasByCode(String code) {
        List<LotteryOddBean> mlist = new ArrayList<>();
        List<PlayDetailBean> bollPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(code)) {
            if (bean.getBetCode().equalsIgnoreCase(code)) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                playDetailBean.setKind(LHCUtil.getCodesByShenxiao(bean.getBetNum()));
                bollPlays.add(playDetailBean);
                mlist.add(bean);
            }
            if (bollPlays.size() >= 2) {
                break;
            }
        }
        Collections.sort(bollPlays, new oddLenthComparator());
        listMaps.put(code, mlist);
        return getAnimalInitOdd(bollPlays);
    }

    /**
     * 根据type获取全不中数据源
     *
     * @param code
     * @return
     */
    public static List<PlayDetailBean> getUnMatchDatasByCode(String code) {
        List<LotteryOddBean> mList = new ArrayList<>();
        List<PlayDetailBean> bollPlays = new ArrayList<>();
        for (LotteryOddBean bean : getOddersBYCode(code)) {
            if (bean.getBetCode().equalsIgnoreCase(code)) {
                PlayDetailBean playDetailBean = new PlayDetailBean();
                playDetailBean.setType(bean.getBetCode());
                playDetailBean.setChildType(bean.getPlayCode());
                playDetailBean.setNum(bean.getBetNum());
                playDetailBean.setBetCode(bean.getBetCode());
                playDetailBean.setOdd(String.valueOf(bean.getOdd()));
                bollPlays.add(playDetailBean);
                mList.add(bean);
                break;
            }

        }
        listMaps.put(code, mList);
        return getBollsinitOdd(bollPlays, false);
    }

    // 自定义比较器：num
    static class numLenthComparator implements Comparator {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            PlayDetailBean p1 = (PlayDetailBean) object1; // 强制转换
            PlayDetailBean p2 = (PlayDetailBean) object2;
            if (p1.getNum().length() == p2.getNum().length()) {
                return p1.getNum().compareTo(p2.getNum());
            } else {
                if (p1.getNum().length() > p2.getNum().length()) {
                    return 1;
                } else {
                    return -1;
                }
            }

        }
    }

    // 自定义比较器：odd
    static class oddLenthComparator implements Comparator {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            PlayDetailBean p1 = (PlayDetailBean) object1; // 强制转换
            PlayDetailBean p2 = (PlayDetailBean) object2;
            return p1.getOdd().compareTo(p2.getOdd());

        }
    }


    /**
     * 合肖头部数据源
     *
     * @return
     */
    public static List<PlayChooseBean> getAnimalAddTypes() {
        String[] typeNames = BoxApplication.getContext().getResources().getStringArray(R.array.lhc_add_animal_names);
        String[] typeCodes = BoxApplication.getContext().getResources().getStringArray(R.array.lhc_add_animal_codes);
        List<PlayChooseBean> typeList = new ArrayList<>();
        for (int i = 0; i < typeNames.length; i++) {

            PlayChooseBean bean = new PlayChooseBean();
            bean.setBetName(typeNames[i]);
            bean.setBerCode(typeCodes[i]);
            if (i == 0) {
                bean.setSelected(true);
            }
            typeList.add(bean);
        }
        return typeList;
    }

    public static List<PlayChooseBean> getTmTypes() {
        List<PlayChooseBean> typeList = new ArrayList<>();
        String[] names = BoxApplication.getContext().getResources().getStringArray(R.array.lhc_tm_play_name);
        String[] bCodes = BoxApplication.getContext().getResources().getStringArray(R.array.lhc_tm_play_code);
        for (int i = 0; i < names.length; i++) {
            PlayChooseBean chooseBean = new PlayChooseBean();
            if (i == 0) {
                chooseBean.setSelected(true);
            }
            chooseBean.setBerCode(bCodes[i]);
            chooseBean.setBetName(names[i]);
            typeList.add(chooseBean);
        }
        return typeList;
    }

    /**
     * 获取正马特头部信息
     *
     * @return
     */
    public static List<PlayChooseBean> getTypes() {
        List<PlayChooseBean> typeList = new ArrayList<>();
        String[] names = BoxApplication.getContext().getResources().getStringArray(R.array.lhc_zmt_type_name);
        for (int i = 0; i < names.length; i++) {
            PlayChooseBean chooseBean = new PlayChooseBean();
            if (i == 0) {
                chooseBean.setSelected(true);
            }
            chooseBean.setBetName(names[i]);
            typeList.add(chooseBean);
        }
        return typeList;
    }


    /**
     * 获取尾数连头部信息
     *
     * @return
     */
    public static List<PlayChooseBean> getMantissaEvenTypes() {
        List<PlayChooseBean> typeList = new ArrayList<>();
        List<String> names = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_even_mantissa_names));
        List<String> bCodes = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_even_mantissa_codes));
        for (int i = 0; i < names.size(); i++) {
            PlayChooseBean chooseBean = new PlayChooseBean();
            if (i == 0) {
                chooseBean.setSelected(true);
            }
            chooseBean.setBerCode(bCodes.get(i));
            chooseBean.setBetName(names.get(i));
            typeList.add(chooseBean);
        }
        return typeList;
    }


    /**
     * 获取连肖头部数据源
     *
     * @return
     */
    public static List<PlayChooseBean> getAnimaleEvenTypes() {
        List<PlayChooseBean> typeList = new ArrayList<>();
        List<String> names = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_even_animal_names));
        List<String> bCodes = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_even_animal_codes));
        for (int i = 0; i < names.size(); i++) {
            PlayChooseBean chooseBean = new PlayChooseBean();
            if (i == 0) {
                chooseBean.setSelected(true);
            }
            chooseBean.setBerCode(bCodes.get(i));
            chooseBean.setBetName(names.get(i));
            typeList.add(chooseBean);
        }
        return typeList;
    }

    /**
     * 获取连码头部数据源
     *
     * @return
     */
    public static List<PlayChooseBean> getContinuTypes() {
        List<PlayChooseBean> typeList = new ArrayList<>();
        List<String> names = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_con_match_types));
        List<String> bCodes = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_con_match_type_codes));
        for (int i = 0; i < names.size(); i++) {
            PlayChooseBean chooseBean = new PlayChooseBean();
            if (i == 0) {
                chooseBean.setSelected(true);
            }
            chooseBean.setBetName(names.get(i));
            chooseBean.setBerCode(bCodes.get(i));
            chooseBean.setBerCode(bCodes.get(i));
            typeList.add(chooseBean);
        }
        return typeList;
    }


    /**
     * 全不中头部数据源
     *
     * @return
     */
    public static List<PlayChooseBean> getUnMatchTypes() {
        String[] typeNames = BoxApplication.getContext().getResources().getStringArray(R.array.lhc_un_match_names);
        String[] typeCodes = BoxApplication.getContext().getResources().getStringArray(R.array.lhc_un_match_codes);
        List<PlayChooseBean> typeList = new ArrayList<>();
        for (int i = 0; i < typeNames.length; i++) {
            PlayChooseBean bean = new PlayChooseBean();
            bean.setBetName(typeNames[i]);
            bean.setBerCode(typeCodes[i]);
            if (i == 0) {
                bean.setSelected(true);
            }
            typeList.add(bean);
        }
        return typeList;
    }

    /**
     * 修改头部选中信息
     *
     * @param typeList
     * @param position
     * @return
     */
    public static List<PlayChooseBean> changeTypeSelected(List<PlayChooseBean> typeList, int position) {
        for (int i = 0; i < typeList.size(); i++) {
            if (position == i) {
                typeList.get(i).setSelected(true);
            } else {
                typeList.get(i).setSelected(false);
            }
        }
        return typeList;
    }

    /**
     * 清除选中状态
     *
     * @param mList
     * @return
     */
    public static void clearSelected(List<PlayDetailBean> mList) {
        if (!mList.isEmpty()) {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setSelected(false);
            }
        }
    }

    /**
     * 获取波色玩法分类
     *
     * @return
     */
    public static List<PlayChooseBean> getColorTypes() {
        List<PlayChooseBean> colorPlays = new ArrayList<>();
        List<String> words = Arrays.asList(BoxApplication.getContext().getResources().getStringArray(R.array.lhc_color_plays_name));
        for (String word : words) {
            PlayChooseBean bean = new PlayChooseBean();
            bean.setBetName(word);
            colorPlays.add(bean);
        }
        return colorPlays;
    }

    public static  void changeUIList(Activity mActivity,String parentType,List<PlayDetailBean> mPlays,
                                             TextView mTvTzCountMoney, TextView mTvTzPrizedMoney, TextView mTvInputNote){
        double inputCount;//每注投的钱
        if (TextUtils.isEmpty(mTvInputNote.getText().toString())) {
            inputCount = 0;
        } else {
            inputCount = Double.parseDouble(mTvInputNote.getText().toString());
        }

        if (mPlays.size() == 0) {
            mTvTzCountMoney.setText(R.string.zs_default);
            mTvTzPrizedMoney.setText(R.string.ys_default);
            return ;
        }
        Collections.sort(mPlays, new Comparator<PlayDetailBean>() {
            @Override
            public int compare(PlayDetailBean o1, PlayDetailBean o2) {
                return -o1.getOdd().compareTo(o2.getOdd());
            }
        });

        int minNum=1;
        double maxOdds = 0.00;
        if (parentType.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_play_type0))) {
            minNum=1;
            maxOdds=inputCount*Double.parseDouble(mPlays.get(0).getOdd());
        } else if (parentType.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_play_type1))) {
            String play = mPlays.get(0).getBetCode();
            if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code0))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code1))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code1))) {
                minNum=3;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code3))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code4))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code0))) {
               minNum=2;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code5))) {
                minNum=4;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code3))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code0))) {
                minNum=5;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code4))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code1))) {
                minNum=6;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code5))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code2))) {
                minNum=7;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code6))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code3))) {
                minNum=8;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code7))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code4))) {
                minNum=9;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code8))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code5))) {
                minNum=10;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code9))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code6))) {
               minNum=11;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code7))) {
               minNum=12;
            }
            if (mPlays.get(0).getOdd().contains("/")) {
                String[] odds = mPlays.get(0).getOdd().split("/");
                if (Double.parseDouble(odds[0]) > Double.parseDouble(odds[1])) {
                    maxOdds = inputCount * Double.valueOf(odds[0]);
                } else {
                    maxOdds = inputCount * Double.valueOf(odds[1]);
                }
            } else {
                maxOdds = inputCount * Double.valueOf(mPlays.get(0).getOdd());
            }
        } else if (parentType.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_play_type2))) {
            String play = mPlays.get(0).getBetCode();
            if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code0))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code0))) {
                minNum = 2;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code1))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code1))) {
                minNum = 3;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code2))) {
                minNum = 4;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code3))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code3))) {
                minNum = 5;
            }
            maxOdds = inputCount* Double.valueOf(mPlays.get(0).getOdd());
        }

        int divisor = 0;
        int divisor2 = 0;
        long num1 = 0;
        long num2 = 0;
        for (int i = 0; i < minNum; i++) {
            if (i == 0) {
                divisor = mPlays.size();
                divisor2 = minNum;
                num1 = divisor;
                num2 = divisor2;
            } else {
                num1 *= (divisor - i);
                num2 *= (divisor2 - i);
            }
        }

        long tzCount = num1 / num2;//投注数量
        Object[] str1 = {tzCount + "", BalanceUtils.getScalsBalance(inputCount * tzCount)};
        double finalMaxOdds = maxOdds;
        mTvTzCountMoney.setText(BoxApplication.getContext().getResources().getString(R.string.sum_note, str1));
        mTvTzPrizedMoney.setText(BoxApplication.getContext().getResources().getString(R.string.max_prized, BalanceUtils.getScalsBalance(finalMaxOdds)));

    }
    /**
     * 六合彩玩法算法
     *
     * @param parentType       玩法父节点
     * @param mPlays           选中列表
     * @param mTvInputNote     生成备注信息TV
     * @return
     */
    public static synchronized List<BetOrdersListBean> changeUIGetPostList(Activity mActivity, String parentType, List<PlayDetailBean> mPlays,
                                                                       TextView mTvInputNote) {
        List<BetOrdersListBean> postPlayBeans = new ArrayList<>();//生成的注单清单
        double inputCount;//每注投的钱
        if (TextUtils.isEmpty(mTvInputNote.getText().toString())) {
            inputCount = 0;
        } else {
            inputCount = Double.parseDouble(mTvInputNote.getText().toString());
        }

        if (mPlays.size() == 0) {
            return postPlayBeans;
        }
        if (parentType.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_play_type0))) {
            for (PlayDetailBean bean : mPlays) {
                BetOrdersListBean postPlayBean = new BetOrdersListBean();
                postPlayBean.setBetAmount(String.valueOf(inputCount));
                postPlayBean.setOdd(bean.getOdd());
                postPlayBean.setBetNum(bean.getNum());
                postPlayBean.setPlayCode(bean.getChildType());
                postPlayBean.setBetCode(bean.getBetCode());
                postPlayBean.setOdd(bean.getOdd());
                postPlayBeans.add(postPlayBean);
            }
        } else if (parentType.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_play_type1))) {
            String play = mPlays.get(0).getBetCode();
            List<String> numlist = new ArrayList<>();
            if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code0))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code1))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code1))) {
                numlist = combine(mPlays, 3).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code3))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code4))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code0))) {
                numlist = combine(mPlays, 2).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_con_match_code5))) {
                numlist = combine(mPlays, 4).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code3))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code0))) {
                numlist = combine(mPlays, 5).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code4))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code1))) {
                numlist = combine(mPlays, 6).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code5))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code2))) {
                numlist = combine(mPlays, 7).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code6))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code3))) {
                numlist = combine(mPlays, 8).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code7))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code4))) {
                numlist = combine(mPlays, 9).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code8))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code5))) {
                numlist = combine(mPlays, 10).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_add_animal_code9))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code6))) {
                numlist = combine(mPlays, 11).get("num");
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getResources().getString(R.string.lhc_un_match_code7))) {
                numlist = combine(mPlays, 12).get("num");
            }
            for (String num : numlist) {
                BetOrdersListBean postPlayBean = new BetOrdersListBean();
                postPlayBean.setBetAmount(inputCount + "");
                postPlayBean.setBetNum(num);
                postPlayBean.setPlayCode(mPlays.get(0).getPlayCode() == null ? mPlays.get(0).getChildType() : mPlays.get(0).getPlayCode());
                postPlayBean.setBetCode(mPlays.get(0).getBetCode());
                postPlayBeans.add(postPlayBean);
                if (mPlays.get(0).getOdd().contains("/")) {
                    String[] odds = mPlays.get(0).getOdd().split("/");
                    if (Double.parseDouble(odds[0]) > Double.parseDouble(odds[1])) {
                        postPlayBean.setOdd(odds[1]);
                    } else {
                        postPlayBean.setOdd(odds[0]);
                    }
                } else {
                    postPlayBean.setOdd(mPlays.get(0).getOdd());
                }

            }
        } else if (parentType.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_play_type2))) {
            String play = mPlays.get(0).getBetCode();
            int minNum = 1;
            if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code0))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code0))) {
                minNum = 2;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code1))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code1))) {
                minNum = 3;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code2))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code2))) {
                minNum = 4;
            } else if (play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_animal_con_code3))
                    || play.equalsIgnoreCase(BoxApplication.getContext().getString(R.string.lhc_mantissa_even_code3))) {
                minNum = 5;
            }
            Map<String, List<String>> listMap = combine(mPlays, minNum);
            List<String> nums = listMap.get("num");
            List<String> odds = listMap.get("odd");
            for (int i = 0; i < nums.size(); i++) {
                BetOrdersListBean postPlayBean = new BetOrdersListBean();
                postPlayBean.setBetAmount(inputCount + "");
                postPlayBean.setBetNum(nums.get(i));
                postPlayBean.setPlayCode(mPlays.get(0).getPlayCode() == null ? mPlays.get(0).getChildType() : mPlays.get(0).getPlayCode());
                postPlayBean.setBetCode(mPlays.get(0).getBetCode());
                postPlayBean.setOdd(odds.get(i));
                postPlayBeans.add(postPlayBean);

            }
        }

        return postPlayBeans;
    }


    /**
     * 实现的算法
     *
     * @param a   数据数组
     * @param num M选N中 N的个数
     * @return 反回组合num list
     */
    private static synchronized Map<String, List<String>> combine(List<PlayDetailBean> a, int num) {

            Map<String, List<String>> listMap = new HashMap<>();
            List<String> numsList = new ArrayList<>();
            List<String> odds = new ArrayList<>();
            StringBuffer sb = new StringBuffer();//连肖和尾数连去最小odd
            String[] b = new String[a.size()];
            for (int i = 0; i < b.length; i++) {
                if (i < num) {
                    b[i] = "1";
                } else
                    b[i] = "0";
            }

            int point = 0;
            int nextPoint = 0;
            int count = 0;
            int sum = 0;
            String temp = "1";

            while (true) {
                // 判断是否全部移位完毕
                double odd = 0;
                for (int i = b.length - 1; i >= b.length - num; i--) {
                    if (b[i].equals("1"))
                        sum += 1;
                }
                // 根据移位生成数据
                for (int i = 0; i < b.length; i++) {
                    if (b[i].equals("1")) {
                        point = i;
                        sb.append(a.get(point).getNum());
                        double thisOdd;
                        if (a.get(point).getOdd().contains("/")) {
                            String[] duobleOdds = a.get(point).getOdd().split("/");
                            if (Double.parseDouble(duobleOdds[0]) > Double.parseDouble(duobleOdds[1])) {
                                thisOdd = Double.parseDouble(duobleOdds[0]);
                            } else {
                                thisOdd = Double.parseDouble(duobleOdds[1]);
                            }

                        } else {
                            thisOdd = Double.valueOf(a.get(point).getOdd());
                        }
                        if (odd == 0 || odd > thisOdd) {
                            odd = thisOdd;
                        }

                        count++;
                        if (count == num)
                            break;
                        sb.append(",");
                    }
                }
                // 往返回值列表添加数据
                numsList.add(sb.toString());
                odds.add(String.valueOf(odd));
// 当数组的最后num位全部为1 退出
                if (sum == num) {
                    break;
                }
                sum = 0;

// 修改从左往右第一个10变成01
                for (int i = 0; i < b.length - 1; i++) {
                    if (b[i].equals("1") && b[i + 1].equals("0")) {
                        point = i;
                        nextPoint = i + 1;
                        b[point] = "0";
                        b[nextPoint] = "1";
                        break;
                    }
                }
                // 将 i-point个元素的1往前移动 0往后移动
                for (int i = 0; i < point - 1; i++)
                    for (int j = i; j < point - 1; j++) {
                        if (b[i].equals("0")) {
                            temp = b[i];
                            b[i] = b[j + 1];
                            b[j + 1] = temp;
                        }
                    }
                // 清空 StringBuffer
                sb.setLength(0);
                count = 0;
            }
            listMap.put("num", numsList);
            listMap.put("odd", odds);
            return listMap;
    }

    /**
     * 单个球的遗漏数据
     *
     * @param index 几号球
     * @return
     */
    private static int getBollMissNum(int startIndex, int index, String codeName, boolean isCollection) {
        int num = 0;
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return num;
        }

        for (Handicap handicap : mHandicaps) {
            String openCode[] = handicap.getOpenCode().split(",");
            if (openCode == null || openCode.length <= index) {
                num++;
                continue;
            } else {
                if (isCollection) {
                    List<String> collections = Arrays.asList(openCode).subList(startIndex, index);
                    if (collections.contains(codeName)) {
                        break;
                    }
                } else {
                    String indexCode = openCode[index];
                    if (indexCode.length() == 1) {
                        indexCode = "0" + indexCode;
                    }
                    if (indexCode.equalsIgnoreCase(codeName)) {
                        break;
                    }
                }
            }
            num++;
        }
        return num;
    }

    /**
     * 获取冷热数据
     *
     * @return
     */
    private static int getHotNum(int startIndex, int index, String codeName, boolean isCollection) {
        int num = 0;
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return num;
        }

        for (Handicap handicap : mHandicaps) {
            String openCode[] = handicap.getOpenCode().split(",");
            if (openCode == null || openCode.length <= index) {
                continue;
            } else {
                if (isCollection) {
                    List<String> collections = Arrays.asList(openCode).subList(startIndex, index);
                    if (collections.contains(codeName)) {
                        num++;
                    }
                } else {
                    String indexCode = openCode[index];
                    if (indexCode.length() == 1) {
                        indexCode = "0" + indexCode;
                    }
                    if (indexCode.equalsIgnoreCase(codeName)) {
                        num++;
                    }
                }
            }

        }
        return num;
    }

    /**
     * 获取颜色遗漏数据
     *
     * @param index
     * @param codeName
     * @return
     */
    private static int getColorMissNum(int index, String codeName) {
        int num = 0;
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return num;
        }

        for (Handicap handicap : mHandicaps) {
            String openCode[] = handicap.getOpenCode().split(",");
            if (openCode == null || openCode.length <= index) {
                num++;
                continue;
            } else {
                String indexCode = openCode[index];
                String color = LHCUtil.getBallColor(indexCode);
                if (openCode == null || openCode.length <= index) {
                    continue;
                } else {

                    if (codeName.contains("红") && COLOR_RED.equalsIgnoreCase(color)) {
                        num++;
                    } else if (codeName.contains("绿") && COLOR_GREEN.equalsIgnoreCase(color)) {
                        num++;
                    } else if (codeName.contains("蓝") && COLOR_BLUE.equalsIgnoreCase(color)) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    /**
     * 获取颜色冷热数据
     *
     * @param index
     * @param codeName
     * @return
     */
    private static int getColorHotNum(int index, String codeName) {
        int num = 0;
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return num;
        }

        for (Handicap handicap : mHandicaps) {
            String openCode[] = handicap.getOpenCode().split(",");
            if (openCode == null || openCode.length <= index) {
                num++;
                continue;
            } else {
                String indexCode = openCode[index];
                String color = LHCUtil.getBallColor(indexCode);
                if (codeName.contains("红") && COLOR_RED.equalsIgnoreCase(color)) {
                    break;
                } else if (codeName.contains("绿") && COLOR_GREEN.equalsIgnoreCase(color)) {
                    break;
                } else if (codeName.contains("蓝") && COLOR_BLUE.equalsIgnoreCase(color)) {
                    break;
                }
            }
            num++;
        }
        return num;
    }

    /**
     * sum总分
     *
     * @param code
     * @return
     */
    private static String getSumNum(int hotOrLess, String code) {

        int num = 0;
        for (Handicap handicap : mHandicaps) {
            if (handicap.getOpenCode().isEmpty()) {
                return "0";
            } else {
                String[] openCodes = handicap.getOpenCode().split(",");
                int count = 0;
                for (String easyCode : openCodes) {
                    count += Integer.valueOf(easyCode);
                }
                if (hotOrLess == 1) {
                    if (code.contains("单") && count % 2 == 1) {
                        num++;
                    } else if (code.contains("双") && count % 2 == 0) {
                        num++;
                    } else if (code.contains("大") && count >= 175) {
                        num++;
                    } else if (code.contains("小") && count < 175) {
                        num++;
                    }
                } else if (hotOrLess == 2) {
                    if (code.contains("单") && count % 2 == 1) {
                        break;
                    } else if (code.contains("双") && count % 2 == 0) {
                        break;
                    } else if (code.contains("大") && count >= 175) {
                        break;
                    } else if (code.contains("小") && count < 175) {
                        break;
                    }
                }
                if (hotOrLess == 2) {
                    num++;
                }
            }
        }
        return String.valueOf(num);

    }

    /**
     * 获取文字类型的冷热和遗漏数据
     *
     * @param index
     * @param betNum
     * @return
     */
    private static String getStringTypeNum(int hotOrLess, int index, String betNum) {
        if (mHandicaps.isEmpty()) {
            return "";
        } else {
            int num = 0;
            for (Handicap handicap : mHandicaps) {
                String[] openCodes = handicap.getOpenCode().split(",");
                if (index < openCodes.length) {
                    String openCode = openCodes[index];
                    if (betNum.equalsIgnoreCase("单")) {
                        if (betNum.equalsIgnoreCase(LHCUtil.getSingleOrDouble(openCode))) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }
                    } else if (betNum.equalsIgnoreCase("双")) {
                        if (betNum.equalsIgnoreCase(LHCUtil.getSingleOrDouble(openCode))) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }
                    } else if (betNum.equalsIgnoreCase("大")) {
                        if (betNum.equalsIgnoreCase(LHCUtil.getBigOrSmall(openCode))) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }

                    } else if (betNum.equalsIgnoreCase("小")) {
                        if (betNum.equalsIgnoreCase(LHCUtil.getBigOrSmall(openCode))) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }
                    } else if (betNum.equalsIgnoreCase("合单")) {
                        if ("单".equalsIgnoreCase(LHCUtil.getBigOrSmall(LHCUtil.getAddNum(openCode)))) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }

                    } else if (betNum.equalsIgnoreCase("合双")) {
                        if ("双".equalsIgnoreCase(LHCUtil.getBigOrSmall(LHCUtil.getAddNum(openCode)))) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }
                    } else if (betNum.equalsIgnoreCase("大单")) {
                        int op = Integer.valueOf(openCode);
                        if (op > 24 && op % 2 == 1) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }

                    } else if (betNum.equalsIgnoreCase("大双")) {
                        int op = Integer.valueOf(openCode);
                        if (op > 24 && op % 2 == 0) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }
                    } else if (betNum.equalsIgnoreCase("小单")) {
                        int op = Integer.valueOf(openCode);
                        if (op < 25 && op % 2 == 1) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }

                    } else if (betNum.equalsIgnoreCase("小双")) {
                        int op = Integer.valueOf(openCode);
                        if (op < 25 && op % 2 == 0) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }
                    } else if (betNum.equalsIgnoreCase("尾大")) {
                        int op = Integer.valueOf(LHCUtil.getLastNum(openCode));
                        if (op > 24) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }

                    } else if (betNum.equalsIgnoreCase("尾小")) {
                        int op = Integer.valueOf(LHCUtil.getLastNum(openCode));
                        if (op < 24) {
                            if (hotOrLess == 1) {
                                num++;
                            } else {
                                break;
                            }
                        }
                    }
                }
                if (hotOrLess == 2) {
                    num++;
                }
            }
            return String.valueOf(num);
        }
    }

    /**
     * 半波冷热遗漏数据
     *
     * @param betNum
     * @return
     */
    private static String getHalfColorNum(int hotOrLess, String betNum) {
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return "";
        }
        int num = 0;
        String[] allcodes = LHCUtil.getBollsByName(betNum).split(",");
        lablea:
        for (Handicap handicap : mHandicaps) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length == 7) {
                String tCode = openCodes[6];
                lableb:
                for (String string : allcodes) {
                    if (string.equalsIgnoreCase(tCode)) {
                        if (hotOrLess == 1) {
                            num++;
                        } else {
                            break lablea;
                        }
                        break lableb;
                    }
                }

            }
            if (hotOrLess == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    /**
     * 获取生肖的冷热和遗漏数据
     *
     * @param index
     * @param betNum
     * @return
     */
    private static String getAnimalNum(int hotOrLess, int index, String betNum) {
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return "";
        }
        int num = 0;
        lablea:
        for (Handicap handicap : mHandicaps) {
            String[] openCodes = handicap.getOpenCode().split(",");
            if (openCodes.length == 7) {
                String indexCode = LHCUtil.getShengXiao(openCodes[index]);
                if (betNum.equalsIgnoreCase(indexCode)) {
                    if (hotOrLess == 1) {
                        num++;
                    } else {
                        break lablea;
                    }
                }
            }
        }
        if (hotOrLess == 2) {
            num++;
        }
        return String.valueOf(num);
    }

    /**
     * 获取连肖的冷热和遗漏数据
     *
     * @param
     * @return
     */
    private static String getAnimalNum(int hotOrLess, String betNum) {
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return "";
        }
        int num = 0;
        lablea:
        for (Handicap handicap : mHandicaps) {
            String[] openCodes = handicap.getOpenCode().split(",");
            lableb:
            for (int i = 0; i < 7; i++) {
                String animal = LHCUtil.getShengXiao(openCodes[i]);
                if (animal.equalsIgnoreCase(betNum)) {
                    if (hotOrLess == 1) {
                        num++;
                    } else {
                        break lablea;
                    }
                    break lableb;
                }
            }
            if (hotOrLess == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }

    private static String getMantissaNum(int hotOrLess, String betNum) {
        if (mHandicaps == null || mHandicaps.isEmpty()) {
            return "";
        }
        int num = 0;
        lablea:
        for (Handicap handicap : mHandicaps) {
            String[] openCodes = handicap.getOpenCode().split(",");
            lableb:
            for (String string : openCodes) {
                String oneCode = string.substring(string.length() - 1);
                if (oneCode.equalsIgnoreCase(betNum)) {
                    if (hotOrLess == 1) {
                        num++;
                    } else {
                        break lablea;
                    }
                    break lableb;
                }

            }
            if (hotOrLess == 2) {
                num++;
            }
        }
        return String.valueOf(num);
    }
}

