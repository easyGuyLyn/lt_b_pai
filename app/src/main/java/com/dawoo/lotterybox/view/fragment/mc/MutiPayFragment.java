package com.dawoo.lotterybox.view.fragment.mc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.util.LogUtils;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.Deposit.CallBackUrlBean;
import com.dawoo.lotterybox.bean.Deposit.OnlineResulltBean;
import com.dawoo.lotterybox.bean.Deposit.ParentDepositBean;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;
import com.dawoo.lotterybox.mvp.presenter.DepositPresenter;
import com.dawoo.lotterybox.mvp.view.IDepositView;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.util.DepositUtil;
import com.dawoo.lotterybox.util.NetUtil;
import com.dawoo.lotterybox.view.activity.account.deposit.SelectRechargeMoneyActivity;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 18-4-8.
 *
 * @author alex
 */

@SuppressLint("ValidFragment")
public class MutiPayFragment extends BaseFragment implements IDepositView {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerview_lsit)
    RecyclerView Mrecyclerview;
    private PayAdadpter payAdapter;
    private List<ParentDepositBean> payTypeBeanlist = new ArrayList<>();
    DepositPresenter mPresenter;
    private boolean isCompany;

    public MutiPayFragment(boolean isCompany) {
        super();
        this.isCompany = isCompany;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muti_pay, container, false);
        ButterKnife.bind(this, rootView);
        payAdapter = new PayAdadpter(R.layout.payactivity_list_item);
        payAdapter.setEmptyView(inflater.inflate(R.layout.empty_view, container, false));
        initView();
        return rootView;
    }

    void initView() {
        mPresenter = new DepositPresenter(mContext, this);
        payAdapter.setNewData(payTypeBeanlist);
        Mrecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Mrecyclerview.setAdapter(payAdapter);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getDepositWay(isCompany);
            }
        });
    }

    @Override
    protected void loadData() {
        mPresenter.getDepositWay(isCompany);

    }

    @Override
    public void getDepositWay(List<ParentDepositBean> beans) {
        refreshLayout.finishRefresh();
        if (!beans.isEmpty()) {
            payTypeBeanlist.clear();
            payTypeBeanlist.addAll(beans);
            payAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getBankDepositDetail(List<PayDetailBean> beans) {

    }

    @Override
    public void getFee(Object o) {

    }

    @Override
    public void postResult(OnlineResulltBean o) {

    }

    @Override
    public void callBackOrder(CallBackUrlBean o) {

    }

    @Override
    public void getSales(List<SaleBean> saleBeans) {

    }

    @Override
    public void getSaleMoney(Object o) {

    }

    @Override
    public void postCompany(BaseHttpResult baseHttpResult) {

    }

    class PayAdadpter extends BaseQuickAdapter {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.icon_company);
        public PayAdadpter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            final ParentDepositBean payTypeBean = (ParentDepositBean) item;
            Glide.with(getActivity()).load(Uri.parse(NetUtil.handleUrl(DataCenter.getInstance().getDomain(), payTypeBean.getPicUrl()))).apply(options).into((ImageView) helper.getView(R.id.pay_image));
            helper.setText(R.id.pay_name, payTypeBean.getPayTran());
            helper.setText(R.id.pay_details, "跳转到" + payTypeBean.getPayTran()+ "支付快速到账");
            helper.setOnClickListener(R.id.pay_rl, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), SelectRechargeMoneyActivity.class).putExtra("item", (Serializable) item));
                }
            });

        }
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void shrinkRefreshView(String s) {
        LogUtils.d(s);
        //  收起刷新
        refreshLayout.finishRefresh();
    }
}
