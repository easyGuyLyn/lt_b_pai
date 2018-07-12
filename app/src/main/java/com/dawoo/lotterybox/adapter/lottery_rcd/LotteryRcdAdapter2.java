//package com.dawoo.lotterybox.adapter.lottery_rcd;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import com.dawoo.lotterybox.R;
//import com.dawoo.lotterybox.bean.lottery.LotteryLastOpenAndOpening;
//import com.dawoo.lotterybox.bean.lottery.lotteryenum.LotteryEnum;
//import com.dawoo.lotterybox.util.lottery.LotteryUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 彩票开奖记录的适配器
// * Created by benson on 18-2-9.
// */
//
//public class LotteryRcdAdapter2 extends RecyclerView.Adapter<LotteryRcdAdapter2.LotteryRcdViewHolder> {
//    Context mContext;
//    List<LotteryLastOpenAndOpening> mList = new ArrayList<>();
//
//    public LotteryRcdAdapter2(Context context, List<LotteryLastOpenAndOpening> list) {
//        mContext = context;
//        mList = list;
//    }
//
//    @NonNull
//    @Override
//    public LotteryRcdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout view = new LinearLayout(mContext);
//        view.setLayoutParams(lp);
//        view.setRotation(LinearLayout.VERTICAL);
//        LotteryLastOpenAndOpening itemData;
//        for (int i = 0; i < mList.size(); i++) {
//            itemData = mList.get(i);
//            // 时时彩
//            if (LotteryEnum.CQSSC.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_ssc_cqssc, null));
//            } else if (LotteryEnum.XJSSC.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_ssc_xjssc, null));
//            } else if (LotteryEnum.FFSSC.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_ssc_ffssc, null));
//            }
//            // pk10
//            else if (LotteryEnum.BJPK10.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_pk10_bjpk10, null));
//            } else if (LotteryEnum.XYFT.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_pk10_xyft, null));
//            } else if (LotteryEnum.JSPK10.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_pk10_jspk10, null));
//            }
//            //LHC
//            else if (LotteryEnum.HKLHC.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_lhc, null));
//            }
//            // k3
//            else if (LotteryEnum.HBK3.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_hbk3, null));
//            } else if (LotteryEnum.GXK3.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_gxk3, null));
//            } else if (LotteryEnum.JSK3.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_jsk3, null));
//            } else if (LotteryEnum.AHK3.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_ahk3, null));
//            }
//            // sfc 重庆幸运农场
//
//            else if (LotteryEnum.CQXYNC.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_sfc_cqxync, null));
//            }
//            // sfc 广东快乐十分
//            else if (LotteryEnum.GDKL10.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_sfc_gdklsf, null));
//            }
//            // keno 北京快乐8
//            else if (LotteryEnum.BJKL8.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_bjkl8, null));
//            }
//            // 幸运28
//            else if (LotteryEnum.XY28.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_xy28, null));
//            }
//            // 福彩3D
//            else if (LotteryEnum.FC3D.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_fc3d_fc3d, null));
//            }
//            // 体彩排列3
//            else if (LotteryEnum.TCPL3.getCode().equals(itemData.getLastCode())) {
//                view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_fc3d_pl3, null));
//            }
//        }
//
//        return new LotteryRcdViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull LotteryRcdViewHolder holder, int position) {
//        holder.onBindView();
//    }
//
//    @Override
//    public int getItemCount() {
//        if(0 == mList.size()) {
//            return 0;
//        } else {
//            return 1;
//        }
//    }
//
//    class LotteryRcdViewHolder extends RecyclerView.ViewHolder {
//
//        public LotteryRcdViewHolder(View itemView) {
//            super(itemView);
//            itemView.findViewById(R.id.)
//        }
//
//        public void onBindView() {
//            LotteryLastOpenAndOpening rcd = mList.get(getAdapterPosition());
//            if(rcd == null) {
//                return;
//            }
//
//            LotteryLastOpenAndOpening itemData;
//            for (int i = 0; i < mList.size(); i++) {
//                itemData = mList.get(i);
//                // 时时彩
//                if (LotteryEnum.CQSSC.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_ssc_cqssc, null));
//                    setCQSSCData();
//                } else if (LotteryEnum.XJSSC.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_ssc_xjssc, null));
//                } else if (LotteryEnum.FFSSC.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_ssc_ffssc, null));
//                }
//                // pk10
//                else if (LotteryEnum.BJPK10.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_pk10_bjpk10, null));
//                } else if (LotteryEnum.XYFT.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_pk10_xyft, null));
//                } else if (LotteryEnum.JSPK10.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_pk10_jspk10, null));
//                }
//                //LHC
//                else if (LotteryEnum.HKLHC.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_lhc, null));
//                }
//                // k3
//                else if (LotteryEnum.HBK3.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_hbk3, null));
//                } else if (LotteryEnum.GXK3.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_gxk3, null));
//                } else if (LotteryEnum.JSK3.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_jsk3, null));
//                } else if (LotteryEnum.AHK3.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_k3_ahk3, null));
//                }
//                // sfc 重庆幸运农场
//
//                else if (LotteryEnum.CQXYNC.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_sfc_cqxync, null));
//                }
//                // sfc 广东快乐十分
//                else if (LotteryEnum.GDKL10.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_sfc_gdklsf, null));
//                }
//                // keno 北京快乐8
//                else if (LotteryEnum.BJKL8.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_bjkl8, null));
//                }
//                // 幸运28
//                else if (LotteryEnum.XY28.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_xy28, null));
//                }
//                // 福彩3D
//                else if (LotteryEnum.FC3D.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_fc3d_fc3d, null));
//                }
//                // 体彩排列3
//                else if (LotteryEnum.TCPL3.getCode().equals(itemData.getLastCode())) {
//                    view.addView(inflater.inflate(R.layout.fragment_lottery_rcd_fc3d_pl3, null));
//                }
//            }
//
//        }
//    }
//
//    private void setCQSSCData() {
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
//    }
//}
