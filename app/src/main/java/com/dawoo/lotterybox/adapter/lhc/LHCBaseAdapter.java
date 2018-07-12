package com.dawoo.lotterybox.adapter.lhc;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.playtype.PlayDetailBean;
import com.dawoo.lotterybox.util.lottery.LHCUtil;
import com.dawoo.lotterybox.util.lottery.initdata.LHCDataUtils;
import com.dawoo.lotterybox.util.lottery.initdata.Lottery_B_DataUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rain on 18-4-23.
 */

public class LHCBaseAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    final int ASKTYPE = 0;
    final int BALLTYPETWO = 1;
    final int BALLTYPETHREE = 2;
    final int WORDTYPETHREE = 3;
    final int WORDTYPETWO = 4;
    private List<PlayDetailBean> mDatas = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ASKTYPE) {
            return new ASKViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_ask, parent, false));
        } else if(viewType==BALLTYPETHREE||viewType==BALLTYPETWO){
            return new BOLLViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_lhc_bo_item, parent, false));
        }else{
            return new WordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_lhc_play_item, parent, false));

        }
    }
    public PlayDetailBean getItem(int position){
        return mDatas.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if (holder instanceof ASKViewHolder)
            ((ASKViewHolder) holder).bindView(mDatas.get(position));
        else if (holder instanceof WordViewHolder)
            ((WordViewHolder) holder).bindView(mDatas.get(position));
        else if (holder instanceof BOLLViewHolder)
            ((BOLLViewHolder) holder).bindView(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position).isAskview()) {
            return ASKTYPE;
        } else {
            if (Lottery_B_DataUtils.isNum(mDatas.get(position).getNum())) {
                int num = Integer.parseInt(mDatas.get(position).getNum());
                if (num < 46) {
                    return BALLTYPETHREE;
                }else {
                    return BALLTYPETWO;
                }
            } else if (mDatas.get(position).getNum().contains("波")) {
                return WORDTYPETHREE;
            }else {
                return WORDTYPETWO;
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);

            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case ASKTYPE:
                            return 6;
                        case BALLTYPETWO:
                        case WORDTYPETWO:
                            return 3;
                        default:
                            return 2;
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取position
            onItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    class ASKViewHolder extends RecyclerView.ViewHolder  {
        TextView view;

        public ASKViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.question_ask_tv);
            if(onItemClickListener!=null){
             itemView.setOnClickListener(LHCBaseAdapter.this);
            }
        }

        public void bindView(PlayDetailBean bean) {
            view.setText(bean.getNum());
        }


    }

    class BOLLViewHolder extends RecyclerView.ViewHolder {
        TextView numView;
        TextView changeView;
        TextView oddView;

        public BOLLViewHolder(View itemView) {
            super(itemView);
            numView = itemView.findViewById(R.id.lhc_bo_tv);
            oddView = itemView.findViewById(R.id.odds_tv);
            changeView = itemView.findViewById(R.id.tv_change);
            if(onItemClickListener!=null){
                itemView.setOnClickListener(LHCBaseAdapter.this);
            }
        }

        public void bindView(PlayDetailBean bean) {
            numView.setText(bean.getNum());
            oddView.setText(bean.getOdd());
            if (LHCDataUtils.hot == 0) {
                changeView.setText("");
            } else if (LHCDataUtils.hot == 1) {
                changeView.setText(bean.getLr());
            } else {
                changeView.setText(bean.getYl());
            }
            numView.setBackground(BoxApplication.getContext().getResources().getDrawable(LHCUtil.getBallbg(bean.getNum())));
            if (bean.isSelected()) {
                oddView.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.text_note_record_yellow));
            } else {
                oddView.setTextColor(BoxApplication.getContext().getResources().getColor(R.color.color_gray_333333));
            }
            itemView.setSelected(bean.isSelected());
        }
    }
    class WordViewHolder extends RecyclerView.ViewHolder{
        TextView numView;
        TextView changeView;
        TextView oddView;
        public WordViewHolder(View itemView) {
            super(itemView);
            numView = itemView.findViewById(R.id.lhc_play_tv);
            oddView = itemView.findViewById(R.id.odds_tv);
            changeView = itemView.findViewById(R.id.tv_change);
            if(onItemClickListener!=null){
                itemView.setOnClickListener(LHCBaseAdapter.this);
            }
        }
        public void bindView(PlayDetailBean bean) {
            numView.setText(bean.getNum());
            oddView.setText(bean.getOdd());
            if (LHCDataUtils.hot == 0) {
                changeView.setText("");
            } else if (LHCDataUtils.hot == 1) {
                changeView.setText(bean.getLr());
            } else {
                changeView.setText(bean.getYl());
            }
            if (bean.isSelected()) {
                numView.setTextColor( BoxApplication.getContext().getResources().getColor(R.color.text_note_record_yellow));
                oddView.setTextColor( BoxApplication.getContext().getResources().getColor(R.color.text_note_record_yellow));
            } else {
                numView.setTextColor( BoxApplication.getContext().getResources().getColor(R.color.black));
                oddView.setTextColor( BoxApplication.getContext().getResources().getColor(R.color.color_gray_333333));
            }
            itemView.setSelected(bean.isSelected());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setmDatas(List<PlayDetailBean> mDatas){
        this.mDatas = mDatas;
    }
}
