package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.LHCRecordBean;
import com.dawoo.lotterybox.util.lottery.LHCUtil;

/**
 * Created by rain on 18-4-25.
 */

public class HistoryTextLayout extends RelativeLayout {
    TextView item_left_tv;

    public HistoryTextLayout(Context context) {
        super(context);
        initView();
    }

    public HistoryTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HistoryTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView() {
        inflate(getContext(), R.layout.adapter_simple_text_layout, this);
        item_left_tv = findViewById(R.id.item_left_tv);
    }

    public void setData(LHCRecordBean bean, int position) {
        item_left_tv.setBackgroundColor(Color.TRANSPARENT);

        if (bean.getCode() != null) {
            item_left_tv.setText(bean.getCode());
            item_left_tv.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.color_gray_333333));
            if (!bean.isAnimall()) {
                if(bean.getCode().equalsIgnoreCase("red")){
                    item_left_tv.setTextColor(Color.RED);
                    item_left_tv.setText("红");
                    return;
                }
                if(bean.getCode().equalsIgnoreCase("blue")){
                    item_left_tv.setTextColor(Color.BLUE);
                    item_left_tv.setText("蓝");
                    return;
                }
                if(bean.getCode().equalsIgnoreCase("green")){
                    item_left_tv.setTextColor(Color.GREEN);
                    item_left_tv.setText("绿");
                    return;
                }
                item_left_tv.setBackgroundResource(R.drawable.shape_ball_yellow);
                String textColor = LHCUtil.getBallColor(bean.getCode());

                if (textColor.equalsIgnoreCase("red")) {
                    item_left_tv.setTextColor(Color.parseColor("#cd0056"));
                } else if (textColor.equalsIgnoreCase("blue")) {
                    item_left_tv.setTextColor(Color.parseColor("#0056CD"));
                } else if (textColor.equalsIgnoreCase("green")) {
                    item_left_tv.setTextColor(Color.parseColor("#33CD66"));
                }

            }
        } else {
            item_left_tv.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.color_gray_999999));
            if (bean.isAnimall() && position == 7) {
                item_left_tv.setTextColor(Color.RED);
            }
            item_left_tv.setText(bean.getLeaveOut() + "");
        }
    }
}
