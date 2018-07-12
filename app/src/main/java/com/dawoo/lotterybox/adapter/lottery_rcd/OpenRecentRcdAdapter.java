package com.dawoo.lotterybox.adapter.lottery_rcd;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.HandicapWithOpening;
import com.dawoo.lotterybox.util.lottery.K3Util;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.SSCUtil;

import java.util.Date;
import java.util.List;

/**
 * 彩票最近开奖记录
 * Created by benson on 18-2-19.
 */

public class OpenRecentRcdAdapter extends BaseMultiItemQuickAdapter {
    // public static final int ITEM_TYPE_SSC = 1; //时时彩
    public static final int ITEM_TYPE_SSC_CQSSC = 10; //重庆时时彩
    public static final int ITEM_TYPE_SSC_XJSSC = 11; //新疆时时彩
    public static final int ITEM_TYPE_SSC_FFSSC = 12; //分分时时彩时时彩
    // public static final int ITEM_TYPE_PK10 = 2; //pk10
    public static final int ITEM_TYPE_PK10_BJPK10 = 20; //北京pk10
    public static final int ITEM_TYPE_PK10_XYFT = 21; //幸运飞艇pk10
    public static final int ITEM_TYPE_PK10_JSPK10 = 22; //极速pk10
    public static final int ITEM_TYPE_LHC = 30; //六合彩
    // public static final int ITEM_TYPE_K3 = 4; //快三
    public static final int ITEM_TYPE_K3_HBK3 = 40; //湖北快三
    public static final int ITEM_TYPE_K3_GXK3 = 41; //广西快三
    public static final int ITEM_TYPE_K3_JSK3 = 42; //江苏快三
    public static final int ITEM_TYPE_K3_AHK3 = 43; //安徽快三
    // public static final int ITEM_TYPE_SFC = 5; // (重庆幸运农场,广东快乐十分)
    public static final int ITEM_TYPE_SFC_CQXYNC = 50; // 重庆幸运农场
    public static final int ITEM_TYPE_SFC_GDKLSF = 51; // 广东快乐十分
    // public static final int ITEM_TYPE_KENO = 6;//(keno,北京快乐8)
    public static final int ITEM_TYPE_KENO_KENO = 60;// keno
    public static final int ITEM_TYPE_KENO_BJKL8 = 61;// 北京快乐8
    //  public static final int ITEM_TYPE_FC3D = 7;//(福彩3D,体彩排列3)
    public static final int ITEM_TYPE_FC3D_FC3D = 70;// 福彩3D
    public static final int ITEM_TYPE_FC3D_PL3 = 71;// 体彩排列3
    public static final int ITEM_TYPE_XY28 = 80;//(幸运28)

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OpenRecentRcdAdapter(List data) {
        super(data);
        addItemType(ITEM_TYPE_SSC_CQSSC, R.layout.fragment_lottery_rcd_item_ssc);
        addItemType(ITEM_TYPE_SSC_XJSSC, R.layout.fragment_lottery_rcd_item_ssc);
        addItemType(ITEM_TYPE_SSC_FFSSC, R.layout.fragment_lottery_rcd_item_ssc);
        addItemType(ITEM_TYPE_PK10_BJPK10, R.layout.fragment_lottery_rcd_item_pk10);
        addItemType(ITEM_TYPE_PK10_XYFT, R.layout.fragment_lottery_rcd_item_pk10);
        addItemType(ITEM_TYPE_PK10_JSPK10, R.layout.fragment_lottery_rcd_item_pk10);
        addItemType(ITEM_TYPE_LHC, R.layout.fragment_lottery_rcd_item_lhc);
        addItemType(ITEM_TYPE_K3_HBK3, R.layout.fragment_lottery_rcd_item_k3);
        addItemType(ITEM_TYPE_K3_GXK3, R.layout.fragment_lottery_rcd_item_k3);
        addItemType(ITEM_TYPE_K3_JSK3, R.layout.fragment_lottery_rcd_item_k3);
        addItemType(ITEM_TYPE_K3_AHK3, R.layout.fragment_lottery_rcd_item_k3);
        addItemType(ITEM_TYPE_SFC_CQXYNC, R.layout.fragment_lottery_rcd_item_sfc);
        addItemType(ITEM_TYPE_SFC_GDKLSF, R.layout.fragment_lottery_rcd_item_sfc);
        addItemType(ITEM_TYPE_KENO_KENO, R.layout.fragment_lottery_rcd_item_keno);
        addItemType(ITEM_TYPE_KENO_BJKL8, R.layout.fragment_lottery_rcd_item_keno);
        addItemType(ITEM_TYPE_FC3D_FC3D, R.layout.fragment_lottery_rcd_item_fc3d);
        addItemType(ITEM_TYPE_FC3D_PL3, R.layout.fragment_lottery_rcd_item_fc3d);
        addItemType(ITEM_TYPE_XY28, R.layout.fragment_lottery_rcd_item_xy28);
    }


    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        if (item == null || !(item instanceof HandicapWithOpening)) {
            return;
        }
        HandicapWithOpening rcd = (HandicapWithOpening) item;

        // 这期期数
        if (rcd.getExpect() != null) {
            helper.setText(R.id.ssc_expect, mContext.getResources().getString(R.string.the_expect, rcd.getExpect()));
        } else {
            helper.setText(R.id.ssc_expect, "");
        }
        // 下期期数
        // helper.setText(R.id.lable_count_time, mContext.getResources().getString(R.string.the_expect_note_end, rcd.getOpeningExpect()));

        switch (helper.getItemViewType()) {
            case ITEM_TYPE_SSC_CQSSC:
                setSSC(helper, rcd);
                break;
            case ITEM_TYPE_SSC_XJSSC:
                setSSC(helper, rcd);
                break;
            case ITEM_TYPE_SSC_FFSSC:
                setSSC(helper, rcd);
                break;

            case ITEM_TYPE_PK10_BJPK10:
                setPk10(helper, rcd);
                break;
            case ITEM_TYPE_PK10_XYFT:
                setPk10(helper, rcd);
                break;
            case ITEM_TYPE_PK10_JSPK10:
                setPk10(helper, rcd);
                break;

            case ITEM_TYPE_LHC:
                setLHC(helper, rcd);
                break;

            case ITEM_TYPE_K3_HBK3:
                setK3(helper, rcd);
                break;
            case ITEM_TYPE_K3_GXK3:
                setK3(helper, rcd);
                break;
            case ITEM_TYPE_K3_JSK3:
                setK3(helper, rcd);
                break;
            case ITEM_TYPE_K3_AHK3:
                setK3(helper, rcd);
                break;

            case ITEM_TYPE_SFC_CQXYNC:
                setSFC(helper, rcd);
                break;
            case ITEM_TYPE_SFC_GDKLSF:
                setSFC(helper, rcd);
                break;

            case ITEM_TYPE_KENO_KENO:
                setKeno(helper, rcd);
                break;
            case ITEM_TYPE_KENO_BJKL8:
                setKeno(helper, rcd);
                break;

            case ITEM_TYPE_FC3D_FC3D:
                setFC3D(helper, rcd);
                break;
            case ITEM_TYPE_FC3D_PL3:
                setFC3D(helper, rcd);
                break;

            case ITEM_TYPE_XY28:
                setXY28(helper, rcd);
                break;

        }
    }

    private void setXY28(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));
        if (rcd.getOpenCode() != null && 5 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");
            helper.setText(R.id.xy28_ball_one_tv, OpenCode[0]);
            helper.setText(R.id.xy28_ball_two_tv, OpenCode[1]);
            helper.setText(R.id.xy28_ball_three_tv, OpenCode[2]);
            helper.setText(R.id.xy28_ball_fout_tv, String.valueOf(getXy28Sum(OpenCode)));


            if (0 == helper.getAdapterPosition()) {
                helper.setBackgroundRes(R.id.xy28_ball_one_tv, R.drawable.shape_ball_red);
                helper.setBackgroundRes(R.id.xy28_ball_two_tv, R.drawable.shape_ball_red);
                helper.setBackgroundRes(R.id.xy28_ball_three_tv, R.drawable.shape_ball_red);
                helper.setBackgroundRes(R.id.xy28_ball_fout_tv, R.drawable.shape_ball_red);
                helper.setTextColor(R.id.xy28_ball_one_tv, ContextCompat.getColor(mContext, R.color.white));
                helper.setTextColor(R.id.xy28_ball_two_tv, ContextCompat.getColor(mContext, R.color.white));
                helper.setTextColor(R.id.xy28_ball_three_tv, ContextCompat.getColor(mContext, R.color.white));
                helper.setTextColor(R.id.xy28_ball_fout_tv, ContextCompat.getColor(mContext, R.color.white));
            } else {
                helper.setBackgroundRes(R.id.xy28_ball_one_tv, 0);
                helper.setBackgroundRes(R.id.xy28_ball_two_tv, 0);
                helper.setBackgroundRes(R.id.xy28_ball_three_tv, 0);
                helper.setBackgroundRes(R.id.xy28_ball_fout_tv, 0);
                helper.setTextColor(R.id.xy28_ball_one_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.xy28_ball_two_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.xy28_ball_three_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.xy28_ball_fout_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
            }
        }
    }

    private void setFC3D(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));

        if (rcd.getOpenCode() != null && 5 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");
            helper.setText(R.id.fc3d_ball_one_tv, OpenCode[0]);
            helper.setText(R.id.fc3d_ball_two_tv, OpenCode[1]);
            helper.setText(R.id.fc3d_ball_three_tv, OpenCode[2]);
        }

        if (0 == helper.getAdapterPosition()) {
            helper.setBackgroundRes(R.id.fc3d_ball_one_tv, R.drawable.shape_ball_red);
            helper.setBackgroundRes(R.id.fc3d_ball_two_tv, R.drawable.shape_ball_red);
            helper.setBackgroundRes(R.id.fc3d_ball_three_tv, R.drawable.shape_ball_red);
            helper.setTextColor(R.id.fc3d_ball_one_tv, ContextCompat.getColor(mContext, R.color.white));
            helper.setTextColor(R.id.fc3d_ball_two_tv, ContextCompat.getColor(mContext, R.color.white));
            helper.setTextColor(R.id.fc3d_ball_three_tv, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundRes(R.id.fc3d_ball_one_tv, 0);
            helper.setBackgroundRes(R.id.fc3d_ball_two_tv, 0);
            helper.setBackgroundRes(R.id.fc3d_ball_three_tv, 0);
            helper.setTextColor(R.id.fc3d_ball_one_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
            helper.setTextColor(R.id.fc3d_ball_two_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
            helper.setTextColor(R.id.fc3d_ball_three_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
        }

    }

    private void setKeno(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));

        if (rcd.getOpenCode() != null && 59 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");
            helper.setText(R.id.keno_xy28_ball_one_tv, OpenCode[0]);
            helper.setText(R.id.keno_xy28_ball_two_tv, OpenCode[1]);
            helper.setText(R.id.keno_xy28_ball_three_tv, OpenCode[2]);
            helper.setText(R.id.keno_xy28_ball_four_tv, OpenCode[3]);
            helper.setText(R.id.keno_xy28_ball_five_tv, OpenCode[4]);
            helper.setText(R.id.keno_xy28_ball_six_tv, OpenCode[5]);
            helper.setText(R.id.keno_xy28_ball_seven_tv, OpenCode[6]);
            helper.setText(R.id.keno_xy28_ball_eight_tv, OpenCode[7]);
            helper.setText(R.id.keno_xy28_ball_nine_tv, OpenCode[8]);
            helper.setText(R.id.keno_xy28_ball_ten_tv, OpenCode[9]);
            helper.setText(R.id.keno_xy28_ball_eleven_tv, OpenCode[10]);
            helper.setText(R.id.keno_xy28_ball_twelve_tv, OpenCode[11]);
            helper.setText(R.id.keno_xy28_ball_thirteen_tv, OpenCode[12]);
            helper.setText(R.id.keno_xy28_ball_fourteen_tv, OpenCode[13]);
            helper.setText(R.id.keno_xy28_ball_fifteen_tv, OpenCode[14]);
            helper.setText(R.id.keno_xy28_ball_sixteen_tv, OpenCode[15]);
            helper.setText(R.id.keno_xy28_ball_seventeen_tv, OpenCode[16]);
            helper.setText(R.id.keno_xy28_ball_eighteen_tv, OpenCode[17]);
            helper.setText(R.id.keno_xy28_ball_nineteen_tv, OpenCode[18]);
            helper.setText(R.id.keno_xy28_ball_twenty_tv, OpenCode[19]);


            helper.setText(R.id.single_big_small, getKenoSingleBigSmall(OpenCode));
            helper.setText(R.id.couple_big_small, getKenoCoupleBigSmall(OpenCode));
            helper.setText(R.id.five_go, getKenoFiveGo(OpenCode));
            helper.setText(R.id.sum_value, mContext.getResources().getString(R.string.rcd_keno_sum_value, String.valueOf(getKenoSumValue(OpenCode))));
        }
    }

    private void setSFC(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));

        if (rcd.getOpenCode() != null && 23 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");

            if (0 == helper.getAdapterPosition()) {
                helper.setBackgroundRes(R.id.sfc_ball_one_tv, getBgSfc(OpenCode[0]));
                helper.setBackgroundRes(R.id.sfc_ball_two_tv, getBgSfc(OpenCode[1]));
                helper.setBackgroundRes(R.id.sfc_ball_three_tv, getBgSfc(OpenCode[2]));
                helper.setBackgroundRes(R.id.sfc_ball_four_tv, getBgSfc(OpenCode[3]));
                helper.setBackgroundRes(R.id.sfc_ball_five_tv, getBgSfc(OpenCode[4]));
                helper.setBackgroundRes(R.id.sfc_ball_six_tv, getBgSfc(OpenCode[5]));
                helper.setBackgroundRes(R.id.sfc_ball_seven_tv, getBgSfc(OpenCode[6]));
                helper.setBackgroundRes(R.id.sfc_ball_eight_tv, getBgSfc(OpenCode[7]));

                helper.setText(R.id.sfc_ball_one_tv, "");
                helper.setText(R.id.sfc_ball_two_tv, "");
                helper.setText(R.id.sfc_ball_three_tv, "");
                helper.setText(R.id.sfc_ball_four_tv, "");
                helper.setText(R.id.sfc_ball_five_tv, "");
                helper.setText(R.id.sfc_ball_six_tv, "");
                helper.setText(R.id.sfc_ball_seven_tv, "");
                helper.setText(R.id.sfc_ball_eight_tv, "");

                helper.setTextColor(R.id.sfc_ball_one_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.sfc_ball_two_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.sfc_ball_three_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.sfc_ball_four_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.sfc_ball_five_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.sfc_ball_six_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.sfc_ball_seven_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.sfc_ball_eight_tv, ContextCompat.getColor(mContext, R.color.transparent));
            } else {
                helper.setBackgroundRes(R.id.sfc_ball_one_tv, 0);
                helper.setBackgroundRes(R.id.sfc_ball_two_tv, 0);
                helper.setBackgroundRes(R.id.sfc_ball_three_tv, 0);
                helper.setBackgroundRes(R.id.sfc_ball_four_tv, 0);
                helper.setBackgroundRes(R.id.sfc_ball_five_tv, 0);
                helper.setBackgroundRes(R.id.sfc_ball_six_tv, 0);
                helper.setBackgroundRes(R.id.sfc_ball_seven_tv, 0);
                helper.setBackgroundRes(R.id.sfc_ball_eight_tv, 0);

                helper.setText(R.id.sfc_ball_one_tv, OpenCode[0]);
                helper.setText(R.id.sfc_ball_two_tv, OpenCode[1]);
                helper.setText(R.id.sfc_ball_three_tv, OpenCode[2]);
                helper.setText(R.id.sfc_ball_four_tv, OpenCode[3]);
                helper.setText(R.id.sfc_ball_five_tv, OpenCode[4]);
                helper.setText(R.id.sfc_ball_six_tv, OpenCode[5]);
                helper.setText(R.id.sfc_ball_seven_tv, OpenCode[6]);
                helper.setText(R.id.sfc_ball_eight_tv, OpenCode[7]);

                helper.setTextColor(R.id.sfc_ball_one_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.sfc_ball_two_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.sfc_ball_three_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.sfc_ball_four_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.sfc_ball_five_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.sfc_ball_six_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.sfc_ball_seven_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.sfc_ball_eight_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
            }
        }
    }

    private void setK3(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));
        if (rcd.getOpenCode() != null && 5 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");

            if (0 == helper.getAdapterPosition()) {
                helper.setBackgroundRes(R.id.k3_ball_one_tv, getBgK3(OpenCode[0]));
                helper.setBackgroundRes(R.id.k3_ball_tow_tv, getBgK3(OpenCode[1]));
                helper.setBackgroundRes(R.id.k3_ball_three_tv, getBgK3(OpenCode[2]));

                helper.setText(R.id.k3_ball_one_tv, "");
                helper.setText(R.id.k3_ball_tow_tv, "");
                helper.setText(R.id.k3_ball_three_tv, "");
                helper.setTextColor(R.id.k3_ball_one_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.k3_ball_tow_tv, ContextCompat.getColor(mContext, R.color.transparent));
                helper.setTextColor(R.id.k3_ball_three_tv, ContextCompat.getColor(mContext, R.color.transparent));
            } else {
                helper.setBackgroundRes(R.id.k3_ball_one_tv, 0);
                helper.setBackgroundRes(R.id.k3_ball_tow_tv, 0);
                helper.setBackgroundRes(R.id.k3_ball_three_tv, 0);
                helper.setText(R.id.k3_ball_one_tv, OpenCode[0]);
                helper.setText(R.id.k3_ball_tow_tv, OpenCode[1]);
                helper.setText(R.id.k3_ball_three_tv, OpenCode[2]);
                helper.setTextColor(R.id.k3_ball_one_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.k3_ball_tow_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.k3_ball_three_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
            }
            helper.setText(R.id.k3_value_sum_tv, "" + K3Util.getHeZhi(OpenCode));
            helper.setText(R.id.k3_lable_group_tv, K3Util.getK3SanBuTongHao(OpenCode));
        }
    }

    private void setLHC(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));
        if (rcd.getOpenCode() != null && 20 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");
            if (OpenCode != null && OpenCode.length == 7) {
                LHCUtil.initBallText(helper.getView(R.id.lhc_ball_one_tv), helper.getView(R.id.lhc_animal_one_tv), OpenCode[0]);
                LHCUtil.initBallText(helper.getView(R.id.lhc_ball_two_tv), helper.getView(R.id.lhc_animal_two_tv), OpenCode[1]);
                LHCUtil.initBallText(helper.getView(R.id.lhc_ball_three_tv), helper.getView(R.id.lhc_animal_three_tv), OpenCode[2]);
                LHCUtil.initBallText(helper.getView(R.id.lhc_ball_four_tv), helper.getView(R.id.lhc_animal_four_tv), OpenCode[3]);
                LHCUtil.initBallText(helper.getView(R.id.lhc_ball_five_tv), helper.getView(R.id.lhc_animal_five_tv), OpenCode[4]);
                LHCUtil.initBallText(helper.getView(R.id.lhc_ball_six_tv), helper.getView(R.id.lhc_animal_six_tv), OpenCode[5]);
                LHCUtil.initBallText(helper.getView(R.id.plhc_ball_eight_tv), helper.getView(R.id.lhc_animal_eight_tv), OpenCode[6]);

                if (0 == helper.getAdapterPosition()) {
                    helper.setBackgroundRes(R.id.lhc_ball_one_tv, LHCUtil.getBallbg(OpenCode[0]));
                    helper.setBackgroundRes(R.id.lhc_ball_two_tv, LHCUtil.getBallbg(OpenCode[1]));
                    helper.setBackgroundRes(R.id.lhc_ball_three_tv, LHCUtil.getBallbg(OpenCode[2]));
                    helper.setBackgroundRes(R.id.lhc_ball_four_tv, LHCUtil.getBallbg(OpenCode[3]));
                    helper.setBackgroundRes(R.id.lhc_ball_five_tv, LHCUtil.getBallbg(OpenCode[4]));
                    helper.setBackgroundRes(R.id.lhc_ball_six_tv, LHCUtil.getBallbg(OpenCode[5]));
                    helper.setBackgroundRes(R.id.plhc_ball_eight_tv, LHCUtil.getBallbg(OpenCode[6]));

                    helper.setTextColor(R.id.lhc_ball_one_tv, ContextCompat.getColor(mContext, R.color.black));
                    helper.setTextColor(R.id.lhc_ball_two_tv, ContextCompat.getColor(mContext, R.color.black));
                    helper.setTextColor(R.id.lhc_ball_three_tv, ContextCompat.getColor(mContext, R.color.black));
                    helper.setTextColor(R.id.lhc_ball_four_tv, ContextCompat.getColor(mContext, R.color.black));
                    helper.setTextColor(R.id.lhc_ball_five_tv, ContextCompat.getColor(mContext, R.color.black));
                    helper.setTextColor(R.id.lhc_ball_six_tv, ContextCompat.getColor(mContext, R.color.black));
                    helper.setTextColor(R.id.plhc_ball_eight_tv, ContextCompat.getColor(mContext, R.color.black));

                    helper.setVisible(R.id.t_small, true);
                    helper.setVisible(R.id.t_big, false);
                } else {
                    helper.setBackgroundRes(R.id.lhc_ball_one_tv, 0);
                    helper.setBackgroundRes(R.id.lhc_ball_two_tv, 0);
                    helper.setBackgroundRes(R.id.lhc_ball_three_tv, 0);
                    helper.setBackgroundRes(R.id.lhc_ball_four_tv, 0);
                    helper.setBackgroundRes(R.id.lhc_ball_five_tv, 0);
                    helper.setBackgroundRes(R.id.lhc_ball_six_tv, 0);
                    helper.setBackgroundRes(R.id.plhc_ball_eight_tv, 0);

                    helper.setTextColor(R.id.lhc_ball_one_tv, getLHCColorRes(LHCUtil.getBallColor(Integer.parseInt(OpenCode[0]))));
                    helper.setTextColor(R.id.lhc_ball_two_tv, getLHCColorRes(LHCUtil.getBallColor(Integer.parseInt(OpenCode[1]))));
                    helper.setTextColor(R.id.lhc_ball_three_tv, getLHCColorRes(LHCUtil.getBallColor(Integer.parseInt(OpenCode[2]))));
                    helper.setTextColor(R.id.lhc_ball_four_tv, getLHCColorRes(LHCUtil.getBallColor(Integer.parseInt(OpenCode[3]))));
                    helper.setTextColor(R.id.lhc_ball_five_tv, getLHCColorRes(LHCUtil.getBallColor(Integer.parseInt(OpenCode[4]))));
                    helper.setTextColor(R.id.lhc_ball_six_tv, getLHCColorRes(LHCUtil.getBallColor(Integer.parseInt(OpenCode[5]))));
                    helper.setTextColor(R.id.plhc_ball_eight_tv, getLHCColorRes(LHCUtil.getBallColor(Integer.parseInt(OpenCode[6]))));

                    helper.setVisible(R.id.t_small, false);
                    helper.setVisible(R.id.t_big, true);
                }
            }
        }
    }


    private void setPk10(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));

        if (rcd.getOpenCode() != null && 29 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");
            helper.setBackgroundRes(R.id.pk10_ball_one_tv, getBgPk10(OpenCode[0]));
            helper.setBackgroundRes(R.id.pk10_ball_two_tv, getBgPk10(OpenCode[1]));
            helper.setBackgroundRes(R.id.pk10_ball_three_tv, getBgPk10(OpenCode[2]));
            helper.setBackgroundRes(R.id.pk10_ball_four_tv, getBgPk10(OpenCode[3]));
            helper.setBackgroundRes(R.id.pk10_ball_five_tv, getBgPk10(OpenCode[4]));
            helper.setBackgroundRes(R.id.pk10_ball_six_tv, getBgPk10(OpenCode[5]));
            helper.setBackgroundRes(R.id.pk10_ball_seven_tv, getBgPk10(OpenCode[6]));
            helper.setBackgroundRes(R.id.pk10_ball_eight_tv, getBgPk10(OpenCode[7]));
            helper.setBackgroundRes(R.id.pk10_ball_nine_tv, getBgPk10(OpenCode[8]));
            helper.setBackgroundRes(R.id.pk10_ball_ten_tv, getBgPk10(OpenCode[9]));
        }
    }

    private void setSSC(BaseViewHolder helper, HandicapWithOpening rcd) {
        // 这期开奖时间
        helper.setText(R.id.ssc_time, DateTool.convert2String(new Date(rcd.getOpenTime()), DateTool.FMT_DATE_TIME));

        if (rcd.getOpenCode() != null && 9 == rcd.getOpenCode().length()) {
            String[] OpenCode = rcd.getOpenCode().split(",");
            helper.setText(R.id.ssc_ball_one_tv, OpenCode[0]);
            helper.setText(R.id.ssc_ball_tow_tv, OpenCode[1]);
            helper.setText(R.id.ssc_ball_three_tv, OpenCode[2]);
            helper.setText(R.id.ssc_ball_four_tv, OpenCode[3]);
            helper.setText(R.id.ssc_ball_five_tv, OpenCode[4]);

            helper.setText(R.id.ssc_value_sum_tv, "" + SSCUtil.getHeZhi(OpenCode));

            if (0 == helper.getAdapterPosition()) {
                helper.setBackgroundRes(R.id.ssc_ball_one_tv, R.drawable.shape_ball_red);
                helper.setBackgroundRes(R.id.ssc_ball_tow_tv, R.drawable.shape_ball_red);
                helper.setBackgroundRes(R.id.ssc_ball_three_tv, R.drawable.shape_ball_red);
                helper.setBackgroundRes(R.id.ssc_ball_four_tv, R.drawable.shape_ball_red);
                helper.setBackgroundRes(R.id.ssc_ball_five_tv, R.drawable.shape_ball_red);
                helper.setTextColor(R.id.ssc_ball_one_tv, ContextCompat.getColor(mContext, R.color.white));
                helper.setTextColor(R.id.ssc_ball_tow_tv, ContextCompat.getColor(mContext, R.color.white));
                helper.setTextColor(R.id.ssc_ball_three_tv, ContextCompat.getColor(mContext, R.color.white));
                helper.setTextColor(R.id.ssc_ball_four_tv, ContextCompat.getColor(mContext, R.color.white));
                helper.setTextColor(R.id.ssc_ball_five_tv, ContextCompat.getColor(mContext, R.color.white));
            } else {
                helper.setBackgroundRes(R.id.ssc_ball_one_tv, 0);
                helper.setBackgroundRes(R.id.ssc_ball_tow_tv, 0);
                helper.setBackgroundRes(R.id.ssc_ball_three_tv, 0);
                helper.setBackgroundRes(R.id.ssc_ball_four_tv, 0);
                helper.setBackgroundRes(R.id.ssc_ball_five_tv, 0);
                helper.setTextColor(R.id.ssc_ball_one_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.ssc_ball_tow_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.ssc_ball_three_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.ssc_ball_four_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
                helper.setTextColor(R.id.ssc_ball_five_tv, ContextCompat.getColor(mContext, R.color.color_open_lottery_ball));
            }


            try {
                int code1 = Integer.parseInt(OpenCode[0]);
                int code2 = Integer.parseInt(OpenCode[1]);
                int code3 = Integer.parseInt(OpenCode[2]);
                int code4 = Integer.parseInt(OpenCode[3]);
                int code5 = Integer.parseInt(OpenCode[4]);
                if ("豹子".equals(SSCUtil.getZuLiuOrZUSan2(code3, code4, code5))) {
                    helper.setGone(R.id.ssc_lable_group_tv, false);
                    helper.setTextColor(R.id.ssc_lable_group_tv, ContextCompat.getColor(mContext, R.color.history_item_green));
                    helper.setTextColor(R.id.ssc_value_group_tv, ContextCompat.getColor(mContext, R.color.history_item_green));
                } else if ("六".equals(SSCUtil.getZuLiuOrZUSan2(code3, code4, code5))) {
                    helper.setGone(R.id.ssc_lable_group_tv, true);
                    helper.setTextColor(R.id.ssc_lable_group_tv, ContextCompat.getColor(mContext, R.color.color_gray_666666));
                    helper.setTextColor(R.id.ssc_value_group_tv, ContextCompat.getColor(mContext, R.color.color_gray_666666));

                } else if ("三".equals(SSCUtil.getZuLiuOrZUSan2(code3, code4, code5))) {
                    helper.setGone(R.id.ssc_lable_group_tv, true);
                    helper.setTextColor(R.id.ssc_lable_group_tv, ContextCompat.getColor(mContext, R.color.custom_one));
                    helper.setTextColor(R.id.ssc_value_group_tv, ContextCompat.getColor(mContext, R.color.custom_one));
                }


                helper.setText(R.id.ssc_value_group_tv, SSCUtil.getZuLiuOrZUSan2(code3, code4, code5));
            } catch (NumberFormatException e) {
                ToastUtil.showToastShort(mContext, e.getMessage());
            }

        } else {
            helper.setText(R.id.ssc_ball_one_tv, "");
            helper.setText(R.id.ssc_ball_tow_tv, "");
            helper.setText(R.id.ssc_ball_three_tv, "");
            helper.setText(R.id.ssc_ball_four_tv, "");
            helper.setText(R.id.ssc_ball_five_tv, "");
            helper.setText(R.id.ssc_value_sum_tv, "");
            helper.setGone(R.id.ssc_lable_group_tv, false);
            helper.setText(R.id.ssc_value_group_tv, "");
        }

    }


    int getBgPk10(String code) {
        switch (code) {
            case "01":
                return R.mipmap.car_1;
            case "02":
                return R.mipmap.car_2;
            case "03":
                return R.mipmap.car_3;
            case "04":
                return R.mipmap.car_4;
            case "05":
                return R.mipmap.car_5;
            case "06":
                return R.mipmap.car_6;
            case "07":
                return R.mipmap.car_7;
            case "08":
                return R.mipmap.car_8;
            case "09":
                return R.mipmap.car_9;
            case "10":
                return R.mipmap.car_10;
            default:
                return 0;
        }
    }

    int getBgLHC(String code) {
        switch (LHCUtil.getBallColor(code)) {
            case "red":
                return R.drawable.shape_ball_red;
            case "blue":
                return R.drawable.shape_ball_blue;
            case "green":
                return R.drawable.shape_ball_green;
        }
        return 0;
    }

    int getBgK3(String code) {
        switch (code) {
            case "1":
                return R.mipmap.fast1;
            case "2":
                return R.mipmap.fast2;
            case "3":
                return R.mipmap.fast3;
            case "4":
                return R.mipmap.fast4;
            case "5":
                return R.mipmap.fast5;
            case "6":
                return R.mipmap.fast6;
            default:
                return 0;
        }
    }

    int getBgSfc(String code) {
        switch (code) {
            case "01":
                return R.mipmap.sfc1;
            case "02":
                return R.mipmap.sfc2;
            case "03":
                return R.mipmap.sfc3;
            case "04":
                return R.mipmap.sfc4;
            case "05":
                return R.mipmap.sfc5;
            case "06":
                return R.mipmap.sfc6;
            case "07":
                return R.mipmap.sfc7;
            case "08":
                return R.mipmap.sfc8;
            case "09":
                return R.mipmap.sfc9;
            case "10":
                return R.mipmap.sfc10;
            case "11":
                return R.mipmap.sfc11;
            case "12":
                return R.mipmap.sfc12;
            case "13":
                return R.mipmap.sfc13;
            case "14":
                return R.mipmap.sfc14;
            case "15":
                return R.mipmap.sfc15;
            case "16":
                return R.mipmap.sfc16;
            case "17":
                return R.mipmap.sfc17;
            case "18":
                return R.mipmap.sfc18;
            case "19":
                return R.mipmap.sfc19;
            case "20":
                return R.mipmap.sfc20;
            default:
                return 0;
        }
    }

    String getKenoSingleBigSmall(String[] openCode) {
        int sum = getKenoSumValue(openCode);
        if (sum > 810) {
            return "大";
        } else if (sum < 810) {
            return "小";
        }
        return "和";
    }

    String getKenoCoupleBigSmall(String[] openCode) {
        int sum = getKenoSumValue(openCode);
        String couple;

        if (sum % 2 == 0) {
            couple = "双";
        } else {
            couple = "单";
        }

        if (sum > 810) {
            return "大" + couple;
        } else if (sum < 810) {
            return "小" + couple;
        }
        return "和" + couple;
    }

    String getKenoFiveGo(String[] openCode) {
        int sum = getKenoSumValue(openCode);
        if (sum >= 210 && sum <= 695) {
            return "金";
        } else if (sum >= 696 && sum <= 763) {
            return "木";
        } else if (sum >= 764 && sum <= 855) {
            return "水";
        } else if (sum >= 856 && sum <= 923) {
            return "火";
        } else if (sum >= 924 && sum <= 1410) {
            return "土";
        }

        return "";
    }

    int getKenoSumValue(String[] openCode) {
        int sum = 0;
        for (String i : openCode) {
            int a = Integer.parseInt(i);
            sum = sum + a;
        }

        return sum;
    }

    int getXy28Sum(String[] openCode) {
        int sum = 0;
        for (String i : openCode) {
            int a = Integer.parseInt(i);
            sum = sum + a;
        }

        return sum;
    }

    int getLHCColorRes(String color) {

        if ("red".equals(color)) {
            return Color.parseColor("#B71A2E");
        } else if ("green".equals(color)) {
            return Color.parseColor("#26B365");

        } else if ("blue".equals(color)) {
            return Color.parseColor("#1A71B5");

        }
        return Color.parseColor("#1A71B5");
    }
}
