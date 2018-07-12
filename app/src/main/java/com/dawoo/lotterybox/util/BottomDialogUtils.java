package com.dawoo.lotterybox.util;

import android.annotation.SuppressLint;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.aigestudio.datepicker.views.DatePicker;
import me.shaohui.bottomdialog.BottomDialog;


/**
 * Created by alex on 18-4-23.
 *
 * @author alex
 *         封装一下方便调用
 */

public class BottomDialogUtils {
    private static BottomDialogUtils instance;
    private FragmentActivity mActivity;
    private BottomSheetDialog dialog;
    private BottomDialog timePickDialog;

    private BottomDialogUtils(FragmentActivity context) {
        this.mActivity = context;
    }

    public static synchronized BottomDialogUtils with(FragmentActivity context) {
        if (instance == null) {
            synchronized (BottomDialogUtils.class) {
                if (instance == null) {
                    instance = new BottomDialogUtils(context);
                }
            }
        }
        return instance;
    }

    public void showSelectDialog(String titleName, boolean isAgent, SelectCallBack selectCallBack) {

        dialog = new BottomSheetDialog(mActivity);
        View view = mActivity.getLayoutInflater().inflate(R.layout.view_bottom_select, null);

        RelativeLayout rlAgentSelect = view.findViewById(R.id.rl_agent_select);
        RelativeLayout rlMemberSelect = view.findViewById(R.id.rl_memeber_select);
        ImageView ivAgentSelect = view.findViewById(R.id.iv_agent_select);
        ImageView ivMemberSelect = view.findViewById(R.id.iv_member_select);
        TextView tvTitleName = view.findViewById(R.id.tv_titleName);
        tvTitleName.setText(titleName);
        if (isAgent) {
            ivAgentSelect.setVisibility(View.VISIBLE);
            ivMemberSelect.setVisibility(View.GONE);
        } else {
            ivAgentSelect.setVisibility(View.GONE);
            ivMemberSelect.setVisibility(View.VISIBLE);
        }
        rlAgentSelect.setOnClickListener(v12 -> {
            selectCallBack.select("代理");
            dialog.dismiss();
        });

        rlMemberSelect.setOnClickListener(v1 -> {
            selectCallBack.select("普通会员");
            dialog.dismiss();
        });
        dialog.setContentView(view);
        dialog.show();


    }

    public void destory() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
        instance = null;
    }

    public interface SelectCallBack {
        void select(String select);
    }


    public void showTimePickDialog(TimeChooseCallback timeChooseCallback) {

        timePickDialog = BottomDialog.create(mActivity.getSupportFragmentManager())
                .setViewListener(new BottomDialog.ViewListener() {
                    @Override
                    public void bindView(View view) {
                        DatePicker datePicker = view.findViewById(R.id.dp_root);
                        datePicker.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH));
                        datePicker.setTodayDisplay(true);
                        datePicker.setOnDateSelectedListener(date -> {
                            if (date.size() == 2) {
                                //排序第一个为startDate,第二个为endData
                                Date startDate = DateTool.stringM2Date(date.get(0));
                                Date endDate = DateTool.stringM2Date(date.get(1));
                                assert startDate != null;
                                if (!startDate.before(endDate)){
                                    startDate=DateTool.stringM2Date(date.get(1));
                                    endDate=DateTool.stringM2Date(date.get(0));
                                }
                                String start = DateTool.convert2String(startDate, DateTool.FMT_DATE);
                                String end = DateTool.convert2String(endDate, DateTool.FMT_DATE);
                                timeChooseCallback.onTimeChoose(start, end);
                                timePickDialog.dismiss();
                            } else {
                                ToastUtils.showShort("请选择两个时间");
                            }

                        });
                    }
                })
                .setLayoutRes(R.layout.view_time_picker)
                .setDimAmount(0.1f)
                .setCancelOutside(true)
                .setTag("BottomDialog");

        timePickDialog.show();
    }

    public void destoryTimePick() {
        if (timePickDialog != null) {
            timePickDialog.dismiss();
        }
        timePickDialog = null;
        instance = null;
    }


    public interface TimeChooseCallback {
        void onTimeChoose(String startDate, String endDate);
    }

}
