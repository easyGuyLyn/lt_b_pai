package com.dawoo.lotterybox.view.activity.account.bank;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.Bank;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jack
 * @date 18-2-19
 * 银行卡列表
 */

public class BankCardList extends BaseActivity {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private BankCardListAdapter bankCardListAdapter;
    private ArrayList<Bank> datas;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_bank_list);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.bank_list), true);
        datas = getIntent().getParcelableArrayListExtra("datas");

    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bankCardListAdapter = new BankCardListAdapter();
        bankCardListAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        recyclerView.setAdapter(bankCardListAdapter);
        bankCardListAdapter.setNewData(datas);
        bankCardListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BankCardList.this.setResult(1, new Intent()
                        .putExtra("position", position));
                finish();
            }
        });
    }


    class BankCardListAdapter extends BaseQuickAdapter<Bank, BankCardListAdapter.BankCardHolder> {

        public BankCardListAdapter() {
            super(R.layout.item_bank_list);
        }

        @Override
        protected void convert(BankCardHolder helper, Bank item) {
            helper.tvBindCard.setText(item.getBankShortName());
            ViewUtils.showBankImage(mContext, helper.ivBindCard, item.getBankShortName());


        }

        class BankCardHolder extends BaseViewHolder {
            @BindView(R.id.iv_bandcard)
            ImageView ivBindCard;
            @BindView(R.id.bank_item)
            TextView tvBindCard;

            public BankCardHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

    }
}
