package com.dawoo.lotterybox.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dawoo.lotterybox.R;


/**
 * Created by jack
 * 选择金额的界面
 */

public class SelectRechangeMoneyAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mMoneyType;
    private int location;

    public SelectRechangeMoneyAdapter(Context context, String[] moneyType) {
        this.mContext = context;
        this.mMoneyType = moneyType;
    }

    @Override
    public int getCount() {
        return mMoneyType.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewSelectMoney holder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_select_money, null);
            holder = new ViewSelectMoney();
            holder.money = convertView.findViewById(R.id.money);
            convertView.setTag(holder);

        } else {
            holder = (ViewSelectMoney) convertView.getTag();
        }

        holder.money.setText(mMoneyType[position]);

        return convertView;
    }

    public class ViewSelectMoney {
        public TextView money;
    }
}
