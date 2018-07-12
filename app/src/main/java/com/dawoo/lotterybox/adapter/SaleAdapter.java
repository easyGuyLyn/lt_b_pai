package com.dawoo.lotterybox.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.lhc.LHCBaseAdapter;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 18-5-4.
 */

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleHolder> {
    private List<SaleBean> mDatas = new ArrayList<>();
    private int selectedIndex = 0;
    private OnItemClickLinstener onItemClickLInstener;

    public void setSelectedIndex(int selectedIndex) {
        if (this.selectedIndex == selectedIndex) {
            return;
        }
        this.selectedIndex = selectedIndex;
        notifyDataSetChanged();
    }

    /**
     * 获取选中的saleID
     * @return
     */
    public int getSaleId() {
        if(mDatas.isEmpty()){
            return 0;
        }
        return mDatas.get(selectedIndex).getId();
    }

    @NonNull
    @Override
    public SaleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sale_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SaleHolder holder, int position) {
        holder.itemView.setTag(position);
        if (position == 0) {
            holder.topLine.setVisibility(View.GONE);
        } else {
            holder.topLine.setVisibility(View.VISIBLE);
        }
        if (selectedIndex == position) {
            holder.selectIV.setChecked(true);
        } else {
            holder.selectIV.setChecked(false);
        }
        holder.saleName.setText(mDatas.get(position).getActivityName());

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setmDatas(List<SaleBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    class SaleHolder extends RecyclerView.ViewHolder {
        CheckBox selectIV;
        TextView saleName;
        View topLine;

        public SaleHolder(View itemView) {
            super(itemView);
            selectIV = itemView.findViewById(R.id.select_iv);
            saleName = itemView.findViewById(R.id.sale_name);
            topLine = itemView.findViewById(R.id.top_line);
            selectIV.setFocusable(false);
            selectIV.setFocusableInTouchMode(false);
            selectIV.setClickable(false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLInstener != null)
                        onItemClickLInstener.onItemClick((Integer) v.getTag());
                }
            });
        }
    }

    public interface OnItemClickLinstener {
        void onItemClick(int position);
    }

    public void setOnItemClickLInstener(OnItemClickLinstener onItemClickLInstener) {
        this.onItemClickLInstener = onItemClickLInstener;
    }
}
