package com.dawoo.lotterybox.adapter;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.util.DepositUtil;
import com.dawoo.lotterybox.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 18-5-2.
 */

public class SelectPayWayAdapter extends BaseAdapter {
    private List<PayDetailBean> mDatas = new ArrayList<>();
    private int selectIndex;
    RequestOptions options = new RequestOptions().placeholder(R.mipmap.icon_company);

    public SelectPayWayAdapter(List<PayDetailBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_select_pay_way, parent, false);
            holder = new ViewHolder();
            holder.itemView = convertView.findViewById(R.id.card_view);
            holder.selectView = convertView.findViewById(R.id.iv_select);
            holder.way_iv = convertView.findViewById(R.id.way_iv);
            holder.way_tv = convertView.findViewById(R.id.way_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PayDetailBean bean = mDatas.get(position);
        if (!TextUtils.isEmpty(bean.getCustomBankName())) {
            holder.way_tv.setText(bean.getCustomBankName());
        } else if(!TextUtils.isEmpty(bean.getPayName())) {
            holder.way_tv.setText(bean.getPayName());
        }else{
            holder.way_tv.setText(bean.getPayTran()+"");
        }

        if (position == selectIndex) {
            holder.selectView.setVisibility(View.VISIBLE);
            holder.itemView.setSelected(true);
        } else {
            holder.selectView.setVisibility(View.GONE);
            holder.itemView.setSelected(false);
        }
        if (bean.getPicUrl() == null || bean.getPicUrl().isEmpty()) {
            holder.way_iv.setImageResource(R.mipmap.icon_company);
        } else {
            Glide.with(parent.getContext())
                    .load(Uri.parse(NetUtil.handleUrl(DataCenter.getInstance().getDomain(), bean.getPicUrl())))
                    .apply(options).into(holder.way_iv);
        }
        return convertView;
    }

    class ViewHolder {
        RelativeLayout itemView;
        TextView selectView;
        ImageView way_iv;
        TextView way_tv;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    public int getSelectIndex() {
        return selectIndex;
    }

}
