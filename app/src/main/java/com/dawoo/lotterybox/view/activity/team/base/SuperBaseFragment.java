package com.dawoo.lotterybox.view.activity.team.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.NoticeDialog;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.EditDailyWageDialog;

/**
 * Created by alex on 18-4-20.
 *
 * @author alex
 *         小小封装一下
 */

public abstract class SuperBaseFragment extends BaseFragment {

    protected View emptyView;
    protected NoticeDialog noticeDialog;
    protected EditDailyWageDialog editDailyWageDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = AlexBind.bindFragment(this, inflater, container);
        emptyView = inflater.inflate(R.layout.empty_view, container, false);
        noticeDialog= new NoticeDialog(getActivity());
        editDailyWageDialog = new EditDailyWageDialog(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();

    }


    @Override
    protected void loadData() {
        new Handler().postDelayed(this::superLoadData,400);

    }


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void superLoadData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        noticeDialog.destory();
    }


}
