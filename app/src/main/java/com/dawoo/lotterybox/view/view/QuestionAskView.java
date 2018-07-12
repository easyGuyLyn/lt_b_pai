package com.dawoo.lotterybox.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dawoo.lotterybox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by rain on 18-3-7.
 */

public class QuestionAskView extends RelativeLayout{
    @BindView(R.id.question_ask_tv)
    TextView askTV;
    public QuestionAskView(Context context) {
        super(context);
        initView(context);
    }

    public QuestionAskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QuestionAskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    void initView(Context context){
        View askView = LayoutInflater.from(context).inflate(R.layout.layout_question_ask,this);
        ButterKnife.bind(this,askView);
        //addView(askView);
    }
    public void setAskTV(String content,OnClickListener onClickListener){
        askTV.setText(content);
        if(onClickListener==null){return;}
       this.setOnClickListener(onClickListener);
    }

}
