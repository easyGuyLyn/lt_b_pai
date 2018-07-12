package com.dawoo.lotterybox.adapter.ssc;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.BaseViewHolder;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.view.activity.lottery.BaseLotteryAActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by b on 18-2-20.
 */

public class GamePlayInputHolder extends BaseViewHolder {
    private Context mContext;
    @BindView(R.id.et_bet_input)
    EditText mEtBetInput;

    public GamePlayInputHolder(Context context , View itemView) {
        super(itemView);
        this.mContext = context;
        ButterKnife.bind(this, itemView);
    }


    public void onBindView(PlayTypeBean.PlayBean playBean,int position) {
//        mEtBetInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(playBean.getLayoutBeans().get(position).getSelectMin())});
        mEtBetInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String betNum = s.toString();
                int bets = 0; //注数
                StringBuffer betString = new StringBuffer();
                if (!TextUtils.isEmpty(betNum)){
                    String[] nums = betNum.split(";|\\s+|,");
                    for (int i = 0; i < nums.length ; i++){
                        String num = nums[i];
                        if (num.length() == playBean.getLayoutBeans().get(position).getSelectMin()){
                            boolean isAdd = true;
                            if ("组三单式".equals(playBean.getPlayTypeName())){
                                try{
                                    List<Integer> numbers = new ArrayList<>();
                                    for (int j = 0; j < num.length(); j++){
                                        String str = num.substring(j,j+1);
                                        int number = Integer.valueOf(str);
                                        numbers.add(number);
                                    }
                                    Collections.sort(numbers);
                                    boolean isDuo = false;
                                    int nowNum = 0;
                                    for (int j = 0; j < numbers.size(); j++) {
                                        if (j != 0) {
                                            if (nowNum == numbers.get(j)) {
                                                if (isDuo)
                                                    isDuo = false;
                                                else {
                                                    isDuo = true;
                                                }
                                            }
                                        }
                                        nowNum = numbers.get(j);
                                    }
                                    isAdd = isDuo;
                                }catch (Exception e){
                                    continue;
                                }
                            }
                            if ("组六单式".equals(playBean.getPlayTypeName())){
                                try{
                                    List<String> numbers = new ArrayList<>();
                                    for (int j = 0; j < num.length(); j++){
                                        String str = num.substring(j,j+1);
                                        if (!numbers.contains(str)){
                                            numbers.add(str);
                                        }
                                    }
                                    if (numbers.size() != playBean.getLayoutBeans().get(position).getSelectMin()){
                                        isAdd = false;
                                    }
                                }catch (Exception e){
                                    continue;
                                }
                            }
                            if ("混合组选".equals(playBean.getPlayTypeName())){
                                try{
                                    List<Integer> numbers = new ArrayList<>();
                                    for (int j = 0; j < num.length(); j++){
                                        String str = num.substring(j,j+1);
                                        int number = Integer.valueOf(str);
                                        numbers.add(number);
                                    }
                                    boolean isLeopard = true;
                                    int nowNum = 0;
                                    for (int j = 0; j < numbers.size(); j++) {
                                        if (j != 0) {
                                            if (nowNum != numbers.get(j)) {
                                                isLeopard = false;
                                            }
                                        }
                                        nowNum = numbers.get(j);
                                    }
                                    isAdd = !isLeopard;
                                }catch (Exception e){
                                    continue;
                                }
                            }
                            if (isAdd){
                                if (TextUtils.isEmpty(betString.toString())){
                                    betString.append(num);
                                    bets = 1;
                                }else {
                                    betString.append("|");
                                    betString.append(num);
                                    bets ++;
                                }
                            }
                        }
                    }

                    if (mContext instanceof BaseLotteryAActivity){
                        ((BaseLotteryAActivity)mContext).computBetInfoByInput(betString.toString(),bets);
                    }
                }
            }
        });
    }
}
