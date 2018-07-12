package com.dawoo.lotterybox.util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.TypeBean;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 18-5-2.
 *
 * @author alex
 */

public class PullTabHelper {

    private PopupWindow popupWindow;
    private ChooseTypeCallBack chooseTypeCallBack;
    private ImageView ivToggle;
    public PullTabHelper(Context context, List<TypeBean>list, ImageView ivToggle, ChooseTypeCallBack chooseTypeCallBack) {
        initPop(context,list);
        this.ivToggle=ivToggle;
        this.chooseTypeCallBack=chooseTypeCallBack;
    }

    private void initPop(Context context, List<TypeBean>list) {
        popupWindow = new PopupWindow(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_type_choose, null);
        RecyclerView recyclerView = inflate.findViewById(R.id.rv_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        ShowTypeAdapter showTypeAdapter = new ShowTypeAdapter();
        recyclerView.setAdapter(showTypeAdapter);
        showTypeAdapter.setNewData(list);
        showTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
            toggleAnimation();
            popupWindow.dismiss();
            chooseTypeCallBack.choose(list.get(position).getType(),list.get(position).getItem());
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelect(false);
            }
            list.get(position).setSelect(true);
            showTypeAdapter.setNewData(list);
        });
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(false);
        popupWindow.setBackgroundDrawable(null);
    }


    class ShowTypeAdapter extends BaseQuickAdapter<TypeBean, ShowTypeAdapter.ShowTypeHolder> {

        public ShowTypeAdapter() {
            super(R.layout.item_type_choose);
        }

        @Override
        protected void convert(ShowTypeHolder helper, TypeBean item) {
            helper.tvCodeName.setText(item.getMessage());
            if (item.isSelect()){
                helper.tvCodeName.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                helper.tvCodeName.setBackground(mContext.getResources().getDrawable(R.drawable.shape_code_item_select));
            }else {
                helper.tvCodeName.setTextColor(mContext.getResources().getColor(R.color.color_gray_333333));
                helper.tvCodeName.setBackground(mContext.getResources().getDrawable(R.drawable.shape_code_item));
            }
        }

        class ShowTypeHolder extends BaseViewHolder {
           @BindView(R.id.tv_code_name)
            TextView tvCodeName;
            public ShowTypeHolder(View view) {
                super(view);
                ButterKnife.bind(this,view);
            }
        }

    }
    public void toggleShow(View view){
        toggleAnimation();
        if (!popupWindow.isShowing()){
            popupWindow.showAsDropDown(view,0,5);
        }else {
            popupWindow.dismiss();
        }
    }

    public interface ChooseTypeCallBack{
        void choose(String type,String item);
    }

    private void toggleAnimation(){
        ObjectAnimator.ofFloat(ivToggle, "rotation", ivToggle.getRotation() - 180).start();
    }
    public boolean isShowing(){
        return popupWindow.isShowing();
    }

    public void turnOffPop(){
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
            toggleAnimation();
        }
    }
}
