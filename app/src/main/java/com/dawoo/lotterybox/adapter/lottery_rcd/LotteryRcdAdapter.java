//package com.dawoo.lotterybox.adapter.lottery_rcd;
//
//import android.content.Intent;
//import android.os.CountDownTimer;
//import android.view.View;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.dawoo.coretool.ToastUtil;
//import com.dawoo.coretool.util.date.DateTool;
//import com.dawoo.lotterybox.ConstantValue;
//import com.dawoo.lotterybox.R;
//import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
//import com.dawoo.lotterybox.util.lottery.K3Util;
//import com.dawoo.lotterybox.util.lottery.LHCUtil;
//import com.dawoo.lotterybox.util.lottery.LotteryUtil;
//import com.dawoo.lotterybox.util.lottery.SSCUtil;
//import com.dawoo.lotterybox.view.activity.record.RecentOpenRecActivity;
//import com.dawoo.lotterybox.view.view.Anticlockwise;
//import com.hwangjr.rxbus.RxBus;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 彩票开奖记录的适配器
// * Created by benson on 18-2-9.
// */
//
//public class LotteryRcdAdapter extends BaseMultiItemQuickAdapter {
//    // public static final int ITEM_TYPE_SSC = 1; //时时彩
//    public static final int ITEM_TYPE_SSC_CQSSC = 10; //重庆时时彩
//    public static final int ITEM_TYPE_SSC_XJSSC = 11; //新疆时时彩
//    public static final int ITEM_TYPE_SSC_FFSSC = 12; //分分时时彩时时彩
//    // public static final int ITEM_TYPE_PK10 = 2; //pk10
//    public static final int ITEM_TYPE_PK10_BJPK10 = 20; //北京pk10
//    public static final int ITEM_TYPE_PK10_XYFT = 21; //幸运飞艇pk10
//    public static final int ITEM_TYPE_PK10_JSPK10 = 22; //极速pk10
//    public static final int ITEM_TYPE_LHC = 30; //六合彩
//    // public static final int ITEM_TYPE_K3 = 4; //快三
//    public static final int ITEM_TYPE_K3_HBK3 = 40; //湖北快三
//    public static final int ITEM_TYPE_K3_GXK3 = 41; //广西快三
//    public static final int ITEM_TYPE_K3_JSK3 = 42; //江苏快三
//    public static final int ITEM_TYPE_K3_AHK3 = 43; //安徽快三
//    // public static final int ITEM_TYPE_SFC = 5; // (重庆幸运农场,广东快乐十分)
//    public static final int ITEM_TYPE_SFC_CQXYNC = 50; // 重庆幸运农场
//    public static final int ITEM_TYPE_SFC_GDKLSF = 51; // 广东快乐十分
//    // public static final int ITEM_TYPE_KENO = 6;//(keno,北京快乐8)
//    public static final int ITEM_TYPE_KENO_KENO = 60;// keno
//    public static final int ITEM_TYPE_KENO_BJKL8 = 61;// 北京快乐8
//    //  public static final int ITEM_TYPE_FC3D = 7;//(福彩3D,体彩排列3)
//    public static final int ITEM_TYPE_FC3D_FC3D = 70;// 福彩3D
//    public static final int ITEM_TYPE_FC3D_PL3 = 71;// 体彩排列3
//    public static final int ITEM_TYPE_XY28 = 80;//(幸运28)
//    private final long mInterval = 1000;
//    private Map<String, LotteryCountDownTimer> mCllector = new HashMap<>();
//
//    /**
//     * Same as QuickAdapter#QuickAdapter(Context,int) but with
//     * some initialization data.
//     *
//     * @param data A new list is created out of this one to avoid mutable list
//     */
//    public LotteryRcdAdapter(List data) {
//        super(data);
//        addItemType(ITEM_TYPE_SSC_CQSSC, R.layout.fragment_lottery_rcd_ssc);
//        addItemType(ITEM_TYPE_SSC_XJSSC, R.layout.fragment_lottery_rcd_ssc);
//        addItemType(ITEM_TYPE_SSC_FFSSC, R.layout.fragment_lottery_rcd_ssc);
//        addItemType(ITEM_TYPE_PK10_BJPK10, R.layout.fragment_lottery_rcd_pk10);
//        addItemType(ITEM_TYPE_PK10_XYFT, R.layout.fragment_lottery_rcd_pk10);
//        addItemType(ITEM_TYPE_PK10_JSPK10, R.layout.fragment_lottery_rcd_pk10);
//        addItemType(ITEM_TYPE_LHC, R.layout.fragment_lottery_rcd_lhc);
//        addItemType(ITEM_TYPE_K3_HBK3, R.layout.fragment_lottery_rcd_k3_hbk3);
//        addItemType(ITEM_TYPE_K3_GXK3, R.layout.fragment_lottery_rcd_k3_gxk3);
//        addItemType(ITEM_TYPE_K3_JSK3, R.layout.fragment_lottery_rcd_k3_jsk3);
//        addItemType(ITEM_TYPE_K3_AHK3, R.layout.fragment_lottery_rcd_k3_ahk3);
//        addItemType(ITEM_TYPE_SFC_CQXYNC, R.layout.fragment_lottery_rcd_sfc);
//        addItemType(ITEM_TYPE_SFC_GDKLSF, R.layout.fragment_lottery_rcd_sfc);
//        addItemType(ITEM_TYPE_KENO_KENO, R.layout.fragment_lottery_rcd_keno);
//        addItemType(ITEM_TYPE_KENO_BJKL8, R.layout.fragment_lottery_rcd_keno);
//        addItemType(ITEM_TYPE_FC3D_FC3D, R.layout.fragment_lottery_rcd_fc3d);
//        addItemType(ITEM_TYPE_FC3D_PL3, R.layout.fragment_lottery_rcd_fc3d);
//        addItemType(ITEM_TYPE_XY28, R.layout.fragment_lottery_rcd_xy28);
//    }
//
//
//    @Override
//    protected void convert(BaseViewHolder helper, Object item) {
//        if (item == null || !(item instanceof LotteryLastOpenAndOpening)) {
//            return;
//        }
//        LotteryLastOpenAndOpening rcd = (LotteryLastOpenAndOpening) item;
//
//        // 彩种名称
//        if (rcd.getLastCode() != null) {
//            helper.setText(R.id.ssc_name_tv, LotteryUtil.getLotteryNameByCode(rcd.getLastCode()));
//        } else {
//            helper.setText(R.id.ssc_name_tv, "");
//        }
//        // 这期期数
//        if (rcd.getLastExpect() != null) {
//            helper.setText(R.id.ssc_expect, mContext.getResources().getString(R.string.the_expect, rcd.getLastExpect()));
//        } else {
//            helper.setText(R.id.ssc_expect, "");
//        }
//        // 下期期数
//        helper.setText(R.id.lable_count_time, mContext.getResources().getString(R.string.the_expect_note_end, rcd.getOpeningExpect()));
//        // 点击进入最近开奖的页面
//        helper.itemView.setOnClickListener(v -> startRecentOpenRecActivity(rcd.getLastCode()));
//
//        switch (helper.getItemViewType()) {
//            case ITEM_TYPE_SSC_CQSSC:
//                setSSC_CQSSC(helper, rcd);
//                break;
//            case ITEM_TYPE_SSC_XJSSC:
//                setSSC_XJSSC(helper, rcd);
//                break;
//            case ITEM_TYPE_SSC_FFSSC:
//                setSSC_FFSSC(helper, rcd);
//                break;
//
//            case ITEM_TYPE_PK10_BJPK10:
//                setPk10_BJPK10(helper, rcd);
//                break;
//            case ITEM_TYPE_PK10_XYFT:
//                setPk10_XYFT(helper, rcd);
//                break;
//            case ITEM_TYPE_PK10_JSPK10:
//                setPk10_JSPK10(helper, rcd);
//                break;
//
//            case ITEM_TYPE_LHC:
//                setLHC(helper, rcd);
//                break;
//
//            case ITEM_TYPE_K3_HBK3:
//                setK3_HBK3(helper, rcd);
//                break;
//            case ITEM_TYPE_K3_GXK3:
//                setK3_GXK3(helper, rcd);
//                break;
//            case ITEM_TYPE_K3_JSK3:
//                setK3_JSK3(helper, rcd);
//                break;
//            case ITEM_TYPE_K3_AHK3:
//                setK3_AHK3(helper, rcd);
//                break;
//
//            case ITEM_TYPE_SFC_CQXYNC:
//                setSFC(helper, rcd);
//                break;
//            case ITEM_TYPE_SFC_GDKLSF:
//                setSFC(helper, rcd);
//                break;
//
//            case ITEM_TYPE_KENO_KENO:
//                setKeno(helper, rcd);
//                break;
//            case ITEM_TYPE_KENO_BJKL8:
//                setKeno(helper, rcd);
//                break;
//
//            case ITEM_TYPE_FC3D_FC3D:
//                setFC3D(helper, rcd);
//                break;
//            case ITEM_TYPE_FC3D_PL3:
//                setFC3D(helper, rcd);
//                break;
//
//            case ITEM_TYPE_XY28:
//                setXY28(helper, rcd);
//                break;
//
//        }
//    }
//
//    private void setXY28(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.xy28_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "xy28");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME));
//        if (rcd.getLastOpenCode() != null && 5 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setText(R.id.xy28_ball_one_tv, lastOpenCode[0]);
//            helper.setText(R.id.xy28_ball_two_tv, lastOpenCode[1]);
//            helper.setText(R.id.xy28_ball_three_tv, lastOpenCode[2]);
//
//        }
//    }
//
//    private void setFC3D(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.fc3d_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "fc3d");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 倒计时
//        //  setCountTimer(helper,rcd, helper.getView(R.id.fc3d_value_count_time), DateTool.FMT_TIME_3);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME));
//
//        if (rcd.getLastOpenCode() != null && 5 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setText(R.id.fc3d_ball_one_tv, lastOpenCode[0]);
//            helper.setText(R.id.fc3d_ball_two_tv, lastOpenCode[1]);
//            helper.setText(R.id.fc3d_ball_three_tv, lastOpenCode[2]);
//        }
//    }
//
//    private void setKeno(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.keno_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "keno");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.keno_xy28_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME));
//
//        if (rcd.getLastOpenCode() != null && 59 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setText(R.id.keno_xy28_ball_one_tv, lastOpenCode[0]);
//            helper.setText(R.id.keno_xy28_ball_two_tv, lastOpenCode[1]);
//            helper.setText(R.id.keno_xy28_ball_three_tv, lastOpenCode[2]);
//            helper.setText(R.id.keno_xy28_ball_four_tv, lastOpenCode[3]);
//            helper.setText(R.id.keno_xy28_ball_five_tv, lastOpenCode[4]);
//            helper.setText(R.id.keno_xy28_ball_six_tv, lastOpenCode[5]);
//            helper.setText(R.id.keno_xy28_ball_seven_tv, lastOpenCode[6]);
//            helper.setText(R.id.keno_xy28_ball_eight_tv, lastOpenCode[7]);
//            helper.setText(R.id.keno_xy28_ball_nine_tv, lastOpenCode[8]);
//            helper.setText(R.id.keno_xy28_ball_ten_tv, lastOpenCode[9]);
//            helper.setText(R.id.keno_xy28_ball_eleven_tv, lastOpenCode[10]);
//            helper.setText(R.id.keno_xy28_ball_twelve_tv, lastOpenCode[11]);
//            helper.setText(R.id.keno_xy28_ball_thirteen_tv, lastOpenCode[12]);
//            helper.setText(R.id.keno_xy28_ball_fourteen_tv, lastOpenCode[13]);
//            helper.setText(R.id.keno_xy28_ball_fifteen_tv, lastOpenCode[14]);
//            helper.setText(R.id.keno_xy28_ball_sixteen_tv, lastOpenCode[15]);
//            helper.setText(R.id.keno_xy28_ball_seventeen_tv, lastOpenCode[16]);
//            helper.setText(R.id.keno_xy28_ball_eighteen_tv, lastOpenCode[17]);
//            helper.setText(R.id.keno_xy28_ball_nineteen_tv, lastOpenCode[18]);
//            helper.setText(R.id.keno_xy28_ball_twenty_tv, lastOpenCode[19]);
//        }
//    }
//
//    private void setSFC(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.sfc_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "sfc");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 倒计时
//        //setCountTimer(helper, rcd, helper.getView(R.id.sfc_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME));
//
//        if (rcd.getLastOpenCode() != null && 23 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.sfc_ball_one_tv, getBgSfc(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.sfc_ball_two_tv, getBgSfc(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.sfc_ball_three_tv, getBgSfc(lastOpenCode[2]));
//            helper.setBackgroundRes(R.id.sfc_ball_four_tv, getBgSfc(lastOpenCode[3]));
//            helper.setBackgroundRes(R.id.sfc_ball_five_tv, getBgSfc(lastOpenCode[4]));
//            helper.setBackgroundRes(R.id.sfc_ball_six_tv, getBgSfc(lastOpenCode[5]));
//            helper.setBackgroundRes(R.id.sfc_ball_seven_tv, getBgSfc(lastOpenCode[6]));
//            helper.setBackgroundRes(R.id.sfc_ball_eight_tv, getBgSfc(lastOpenCode[7]));
//        }
//    }
//
//    private void setK3_HBK3(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.k3_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//               // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "k3");
//                }
//            }
//        });
//        anticlockwise.reStart();
//
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.k3_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//        if (rcd.getLastOpenCode() != null && 5 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.k3_ball_one_tv, getBgK3(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.k3_ball_tow_tv, getBgK3(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.k3_ball_three_tv, getBgK3(lastOpenCode[2]));
//
//            helper.setText(R.id.k3_value_sum_tv, "" + K3Util.getHeZhi(lastOpenCode));
//            helper.setText(R.id.k3_lable_group_tv, K3Util.getK3SanBuTongHao(lastOpenCode));
//        }
//    }
//    private void setK3_JSK3(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.k3_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//               // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "k3");
//                }
//            }
//        });
//        anticlockwise.reStart();
//
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.k3_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//        if (rcd.getLastOpenCode() != null && 5 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.k3_ball_one_tv, getBgK3(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.k3_ball_tow_tv, getBgK3(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.k3_ball_three_tv, getBgK3(lastOpenCode[2]));
//
//            helper.setText(R.id.k3_value_sum_tv, "" + K3Util.getHeZhi(lastOpenCode));
//            helper.setText(R.id.k3_lable_group_tv, K3Util.getK3SanBuTongHao(lastOpenCode));
//        }
//    }
//    private void setK3_GXK3(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.k3_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//               // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "k3");
//                }
//            }
//        });
//        anticlockwise.reStart();
//
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.k3_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//        if (rcd.getLastOpenCode() != null && 5 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.k3_ball_one_tv, getBgK3(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.k3_ball_tow_tv, getBgK3(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.k3_ball_three_tv, getBgK3(lastOpenCode[2]));
//
//            helper.setText(R.id.k3_value_sum_tv, "" + K3Util.getHeZhi(lastOpenCode));
//            helper.setText(R.id.k3_lable_group_tv, K3Util.getK3SanBuTongHao(lastOpenCode));
//        }
//    }
//    private void setK3_AHK3(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.k3_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//               // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "k3");
//                }
//            }
//        });
//        anticlockwise.reStart();
//
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.k3_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//        if (rcd.getLastOpenCode() != null && 5 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.k3_ball_one_tv, getBgK3(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.k3_ball_tow_tv, getBgK3(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.k3_ball_three_tv, getBgK3(lastOpenCode[2]));
//
//            helper.setText(R.id.k3_value_sum_tv, "" + K3Util.getHeZhi(lastOpenCode));
//            helper.setText(R.id.k3_lable_group_tv, K3Util.getK3SanBuTongHao(lastOpenCode));
//        }
//    }
//
//    private void setLHC(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.lhc_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//             // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "LHC");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.lhc_value_count_time), DateTool.FMT_TIME);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_DATE_TIME));
//
//        if (rcd.getLastOpenCode() != null && 20 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            if (lastOpenCode != null && lastOpenCode.length == 7) {
//                LHCUtil.initBall(helper.getView(R.id.lhc_ball_one_tv), helper.getView(R.id.lhc_animal_one_tv), lastOpenCode[0]);
//                LHCUtil.initBall(helper.getView(R.id.lhc_ball_two_tv), helper.getView(R.id.lhc_animal_two_tv), lastOpenCode[1]);
//                LHCUtil.initBall(helper.getView(R.id.lhc_ball_three_tv), helper.getView(R.id.lhc_animal_three_tv), lastOpenCode[2]);
//                LHCUtil.initBall(helper.getView(R.id.lhc_ball_four_tv), helper.getView(R.id.lhc_animal_four_tv), lastOpenCode[3]);
//                LHCUtil.initBall(helper.getView(R.id.lhc_ball_five_tv), helper.getView(R.id.lhc_animal_five_tv), lastOpenCode[4]);
//                LHCUtil.initBall(helper.getView(R.id.lhc_ball_six_tv), helper.getView(R.id.lhc_animal_six_tv), lastOpenCode[5]);
//                LHCUtil.initBall(helper.getView(R.id.plhc_ball_eight_tv), helper.getView(R.id.lhc_animal_eight_tv), lastOpenCode[6]);
//            }
//        }
//    }
//
//    private void setPk10_BJPK10(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.pk10_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "pk10");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.pk10_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//
//        if (rcd.getLastOpenCode() != null && 29 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.pk10_ball_one_tv, getBgPk10(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.pk10_ball_two_tv, getBgPk10(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.pk10_ball_three_tv, getBgPk10(lastOpenCode[2]));
//            helper.setBackgroundRes(R.id.pk10_ball_four_tv, getBgPk10(lastOpenCode[3]));
//            helper.setBackgroundRes(R.id.pk10_ball_five_tv, getBgPk10(lastOpenCode[4]));
//            helper.setBackgroundRes(R.id.pk10_ball_six_tv, getBgPk10(lastOpenCode[5]));
//            helper.setBackgroundRes(R.id.pk10_ball_seven_tv, getBgPk10(lastOpenCode[6]));
//            helper.setBackgroundRes(R.id.pk10_ball_eight_tv, getBgPk10(lastOpenCode[7]));
//            helper.setBackgroundRes(R.id.pk10_ball_nine_tv, getBgPk10(lastOpenCode[8]));
//            helper.setBackgroundRes(R.id.pk10_ball_ten_tv, getBgPk10(lastOpenCode[9]));
//        }
//    }
//    private void setPk10_XYFT(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.pk10_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "pk10");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.pk10_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//
//        if (rcd.getLastOpenCode() != null && 29 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.pk10_ball_one_tv, getBgPk10(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.pk10_ball_two_tv, getBgPk10(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.pk10_ball_three_tv, getBgPk10(lastOpenCode[2]));
//            helper.setBackgroundRes(R.id.pk10_ball_four_tv, getBgPk10(lastOpenCode[3]));
//            helper.setBackgroundRes(R.id.pk10_ball_five_tv, getBgPk10(lastOpenCode[4]));
//            helper.setBackgroundRes(R.id.pk10_ball_six_tv, getBgPk10(lastOpenCode[5]));
//            helper.setBackgroundRes(R.id.pk10_ball_seven_tv, getBgPk10(lastOpenCode[6]));
//            helper.setBackgroundRes(R.id.pk10_ball_eight_tv, getBgPk10(lastOpenCode[7]));
//            helper.setBackgroundRes(R.id.pk10_ball_nine_tv, getBgPk10(lastOpenCode[8]));
//            helper.setBackgroundRes(R.id.pk10_ball_ten_tv, getBgPk10(lastOpenCode[9]));
//        }
//    }
//    private void setPk10_JSPK10(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.pk10_value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "pk10");
//                }
//            }
//        });
//        anticlockwise.reStart();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.pk10_value_count_time), DateTool.FMT_TIME_2);
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//
//        if (rcd.getLastOpenCode() != null && 29 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setBackgroundRes(R.id.pk10_ball_one_tv, getBgPk10(lastOpenCode[0]));
//            helper.setBackgroundRes(R.id.pk10_ball_two_tv, getBgPk10(lastOpenCode[1]));
//            helper.setBackgroundRes(R.id.pk10_ball_three_tv, getBgPk10(lastOpenCode[2]));
//            helper.setBackgroundRes(R.id.pk10_ball_four_tv, getBgPk10(lastOpenCode[3]));
//            helper.setBackgroundRes(R.id.pk10_ball_five_tv, getBgPk10(lastOpenCode[4]));
//            helper.setBackgroundRes(R.id.pk10_ball_six_tv, getBgPk10(lastOpenCode[5]));
//            helper.setBackgroundRes(R.id.pk10_ball_seven_tv, getBgPk10(lastOpenCode[6]));
//            helper.setBackgroundRes(R.id.pk10_ball_eight_tv, getBgPk10(lastOpenCode[7]));
//            helper.setBackgroundRes(R.id.pk10_ball_nine_tv, getBgPk10(lastOpenCode[8]));
//            helper.setBackgroundRes(R.id.pk10_ball_ten_tv, getBgPk10(lastOpenCode[9]));
//        }
//    }
//
//    private void setSSC_CQSSC(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "ssc");
//                }
//            }
//        });
//        anticlockwise.start();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.value_count_time), DateTool.FMT_TIME_2);
//
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//
//        if (rcd.getLastOpenCode() != null && 9 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setText(R.id.ssc_ball_one_tv, lastOpenCode[0]);
//            helper.setText(R.id.ssc_ball_tow_tv, lastOpenCode[1]);
//            helper.setText(R.id.ssc_ball_three_tv, lastOpenCode[2]);
//            helper.setText(R.id.ssc_ball_four_tv, lastOpenCode[3]);
//            helper.setText(R.id.ssc_ball_five_tv, lastOpenCode[4]);
//
//            helper.setText(R.id.ssc_value_sum_tv, "" + SSCUtil.getHeZhi(lastOpenCode));
//
//
//            try {
//                int code3 = Integer.parseInt(lastOpenCode[2]);
//                int code4 = Integer.parseInt(lastOpenCode[3]);
//                int code5 = Integer.parseInt(lastOpenCode[4]);
//
//                if ("豹子".equals(SSCUtil.getZuLiuOrZUSan2(code3, code4, code5))) {
//                    helper.setGone(R.id.ssc_lable_group_tv, false);
//                } else {
//                    helper.setGone(R.id.ssc_lable_group_tv, true);
//                }
//                helper.setText(R.id.ssc_value_group_tv, SSCUtil.getZuLiuOrZUSan2(code3, code4, code5));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                ToastUtil.showToastShort(mContext, e.getMessage());
//            }
//        } else {
//            helper.setText(R.id.ssc_ball_one_tv, "");
//            helper.setText(R.id.ssc_ball_tow_tv, "");
//            helper.setText(R.id.ssc_ball_three_tv, "");
//            helper.setText(R.id.ssc_ball_four_tv, "");
//            helper.setText(R.id.ssc_ball_five_tv, "");
//            helper.setText(R.id.ssc_value_sum_tv, "");
//            helper.setGone(R.id.ssc_lable_group_tv, false);
//            helper.setText(R.id.ssc_value_group_tv, "");
//        }
//
//    }
//    private void setSSC_XJSSC(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "ssc");
//                }
//            }
//        });
//        anticlockwise.start();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.value_count_time), DateTool.FMT_TIME_2);
//
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//
//        if (rcd.getLastOpenCode() != null && 9 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setText(R.id.ssc_ball_one_tv, lastOpenCode[0]);
//            helper.setText(R.id.ssc_ball_tow_tv, lastOpenCode[1]);
//            helper.setText(R.id.ssc_ball_three_tv, lastOpenCode[2]);
//            helper.setText(R.id.ssc_ball_four_tv, lastOpenCode[3]);
//            helper.setText(R.id.ssc_ball_five_tv, lastOpenCode[4]);
//
//            helper.setText(R.id.ssc_value_sum_tv, "" + SSCUtil.getHeZhi(lastOpenCode));
//
//
//            try {
//                int code3 = Integer.parseInt(lastOpenCode[2]);
//                int code4 = Integer.parseInt(lastOpenCode[3]);
//                int code5 = Integer.parseInt(lastOpenCode[4]);
//
//                if ("豹子".equals(SSCUtil.getZuLiuOrZUSan2(code3, code4, code5))) {
//                    helper.setGone(R.id.ssc_lable_group_tv, false);
//                } else {
//                    helper.setGone(R.id.ssc_lable_group_tv, true);
//                }
//                helper.setText(R.id.ssc_value_group_tv, SSCUtil.getZuLiuOrZUSan2(code3, code4, code5));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                ToastUtil.showToastShort(mContext, e.getMessage());
//            }
//        } else {
//            helper.setText(R.id.ssc_ball_one_tv, "");
//            helper.setText(R.id.ssc_ball_tow_tv, "");
//            helper.setText(R.id.ssc_ball_three_tv, "");
//            helper.setText(R.id.ssc_ball_four_tv, "");
//            helper.setText(R.id.ssc_ball_five_tv, "");
//            helper.setText(R.id.ssc_value_sum_tv, "");
//            helper.setGone(R.id.ssc_lable_group_tv, false);
//            helper.setText(R.id.ssc_value_group_tv, "");
//        }
//
//    }
//    private void setSSC_FFSSC(BaseViewHolder helper, LotteryLastOpenAndOpening rcd) {
//        //开始
//        final boolean[] isFengpan = {true};
//        Anticlockwise anticlockwise = helper.getView(R.id.value_count_time);
//        anticlockwise.initTime(rcd.getLeftTime());
//        anticlockwise.setOnTimeCompleteListener(new Anticlockwise.OnTimeCompleteListener() {
//            @Override
//            public void onTimeComplete() {
//                // 进行封盘倒计时
//                if (isFengpan[0]) {
//                    isFengpan[0] = false;
//                    anticlockwise.reStart(rcd.getLeftOpenTime());
//                } else {
//                    // 封盘倒计时完了拉取数据
//                    RxBus.get().post(ConstantValue.EVENT_LOTTERY_RCD_DATA_REFRESH, "ssc");
//                }
//            }
//        });
//        anticlockwise.start();
//        // 倒计时
//        // setCountTimer(helper, rcd, helper.getView(R.id.value_count_time), DateTool.FMT_TIME_2);
//
//        // 这期开奖时间
//        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getLastOpenTime()), DateTool.FMT_TIME_3));
//
//        if (rcd.getLastOpenCode() != null && 9 == rcd.getLastOpenCode().length()) {
//            String[] lastOpenCode = rcd.getLastOpenCode().split(",");
//            helper.setText(R.id.ssc_ball_one_tv, lastOpenCode[0]);
//            helper.setText(R.id.ssc_ball_tow_tv, lastOpenCode[1]);
//            helper.setText(R.id.ssc_ball_three_tv, lastOpenCode[2]);
//            helper.setText(R.id.ssc_ball_four_tv, lastOpenCode[3]);
//            helper.setText(R.id.ssc_ball_five_tv, lastOpenCode[4]);
//
//            helper.setText(R.id.ssc_value_sum_tv, "" + SSCUtil.getHeZhi(lastOpenCode));
//
//
//            try {
//                int code3 = Integer.parseInt(lastOpenCode[2]);
//                int code4 = Integer.parseInt(lastOpenCode[3]);
//                int code5 = Integer.parseInt(lastOpenCode[4]);
//
//                if ("豹子".equals(SSCUtil.getZuLiuOrZUSan2(code3, code4, code5))) {
//                    helper.setGone(R.id.ssc_lable_group_tv, false);
//                } else {
//                    helper.setGone(R.id.ssc_lable_group_tv, true);
//                }
//                helper.setText(R.id.ssc_value_group_tv, SSCUtil.getZuLiuOrZUSan2(code3, code4, code5));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                ToastUtil.showToastShort(mContext, e.getMessage());
//            }
//        } else {
//            helper.setText(R.id.ssc_ball_one_tv, "");
//            helper.setText(R.id.ssc_ball_tow_tv, "");
//            helper.setText(R.id.ssc_ball_three_tv, "");
//            helper.setText(R.id.ssc_ball_four_tv, "");
//            helper.setText(R.id.ssc_ball_five_tv, "");
//            helper.setText(R.id.ssc_value_sum_tv, "");
//            helper.setGone(R.id.ssc_lable_group_tv, false);
//            helper.setText(R.id.ssc_value_group_tv, "");
//        }
//
//    }
//
//    /**
//     * 进入最近的开奖记录activity
//     *
//     * @param lotteryCode
//     */
//    void startRecentOpenRecActivity(String lotteryCode) {
//        Intent intent = new Intent(mContext, RecentOpenRecActivity.class);
//        intent.putExtra(RecentOpenRecActivity.LOTTERY_CODE, lotteryCode);
//        mContext.startActivity(intent);
//    }
//
//
//    int getBgPk10(String code) {
//        switch (code) {
//            case "01":
//                return R.mipmap.car_1;
//            case "02":
//                return R.mipmap.car_2;
//            case "03":
//                return R.mipmap.car_3;
//            case "04":
//                return R.mipmap.car_4;
//            case "05":
//                return R.mipmap.car_5;
//            case "06":
//                return R.mipmap.car_6;
//            case "07":
//                return R.mipmap.car_7;
//            case "08":
//                return R.mipmap.car_8;
//            case "09":
//                return R.mipmap.car_9;
//            case "10":
//                return R.mipmap.car_10;
//            default:
//                return 0;
//        }
//    }
//
//    int getBgLHC(String code) {
//        switch (LHCUtil.getBallColor(code)) {
//            case "red":
//                return R.drawable.shape_ball_red;
//            case "blue":
//                return R.drawable.shape_ball_blue;
//            case "green":
//                return R.drawable.shape_ball_green;
//        }
//        return 0;
//    }
//
//    int getBgK3(String code) {
//        switch (code) {
//            case "1":
//                return R.mipmap.fast1;
//            case "2":
//                return R.mipmap.fast2;
//            case "3":
//                return R.mipmap.fast3;
//            case "4":
//                return R.mipmap.fast4;
//            case "5":
//                return R.mipmap.fast5;
//            case "6":
//                return R.mipmap.fast6;
//            default:
//                return 0;
//        }
//    }
//
//    int getBgSfc(String code) {
//        switch (code) {
//            case "01":
//                return R.mipmap.sfc1;
//            case "02":
//                return R.mipmap.sfc2;
//            case "03":
//                return R.mipmap.sfc3;
//            case "04":
//                return R.mipmap.sfc4;
//            case "05":
//                return R.mipmap.sfc5;
//            case "06":
//                return R.mipmap.sfc6;
//            case "07":
//                return R.mipmap.sfc7;
//            case "08":
//                return R.mipmap.sfc8;
//            case "09":
//                return R.mipmap.sfc9;
//            case "10":
//                return R.mipmap.sfc10;
//            case "11":
//                return R.mipmap.sfc11;
//            case "12":
//                return R.mipmap.sfc12;
//            case "13":
//                return R.mipmap.sfc13;
//            case "14":
//                return R.mipmap.sfc14;
//            case "15":
//                return R.mipmap.sfc15;
//            case "16":
//                return R.mipmap.sfc16;
//            case "17":
//                return R.mipmap.sfc17;
//            case "18":
//                return R.mipmap.sfc18;
//            case "19":
//                return R.mipmap.sfc19;
//            case "20":
//                return R.mipmap.sfc20;
//            default:
//                return 0;
//        }
//    }
//
//
//    /**
//     * 设置倒计时
//     *
//     * @param rcd
//     */
//    private void setCountTimer(BaseViewHolder helper, LotteryLastOpenAndOpening rcd, TextView textView, String formate) {
//        LotteryCountDownTimer timer = mCllector.get(rcd.getLastCode());
//        if (timer == null) {
//            timer = new LotteryCountDownTimer(rcd.getLeftTime(), mInterval, textView, formate);
//            mCllector.put(rcd.getLastCode(), timer);
//            timer.start();
//        }
//
//    }
//
//
//    /**
//     * 倒计时类
//     */
//    static class LotteryCountDownTimer extends CountDownTimer {
//        private final TextView textView;
//        private final String formate;
//        public long mCurrentLeftTime;
//
//        public LotteryCountDownTimer(long millisInFuture, long countDownInterval, TextView textView, String formate) {
//            super(millisInFuture, countDownInterval);
//            this.textView = textView;
//            this.formate = formate;
//        }
//
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            mCurrentLeftTime = millisUntilFinished;
//            if (textView != null) {
//                textView.setText(DateTool.convert2String(new Date(millisUntilFinished), formate));
//            }
//        }
//
//        @Override
//        public void onFinish() {
//            if (textView != null) {
//                if (DateTool.FMT_TIME_2.equals(formate)) {
//                    textView.setText("00:00");
//                } else if (DateTool.FMT_TIME_3.equals(formate)) {
//                    textView.setText("00:00:00");
//                } else {
//                    textView.setText("00:00");
//                }
//            }
//        }
//
//        public long getCurrentLeftTime() {
//            return mCurrentLeftTime;
//        }
//    }
//
//
//    /**
//     * 页面关闭清除timer
//     */
//    public void cancleTimer() {
//        if (mCllector == null) {
//            return;
//        }
//        for (int i = 0; i < mCllector.size(); i++) {
//            if (mCllector.get(i) != null) {
//                mCllector.get(i).cancel();
//            }
//        }
//    }
//
//
//    static class TimerViewHolder extends BaseViewHolder {
//        LotteryCountDownTimer mCountDownTimer;
//
//        public TimerViewHolder(View view, long millisInFuture, long countDownInterval, TextView textView, String formate) {
//            super(view);
//        }
//    }
//
//
//}
