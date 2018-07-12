package com.dawoo.lotterybox.view.fragment.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.mvp.presenter.MessagePresenter;
import com.dawoo.lotterybox.mvp.view.IMessageBaseView;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.dawoo.lotterybox.view.view.popu.CustomPopupWindow;
import com.hwangjr.rxbus.RxBus;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.fragment.message.MailBoxFragment.REFRSH_Fragment;
import static com.dawoo.lotterybox.view.fragment.message.MailBoxFragment.REFRSH_Fragment_DATA;

/**
 * Created by archar on 18-4-27.
 */

public class SendMsgFragment extends BaseFragment implements IMessageBaseView {

    Unbinder unbinder;
    @BindView(R.id.tv_receiver)
    TextView mTvReceiver;
    @BindView(R.id.Et_title)
    EditText mEtTitle;
    @BindView(R.id.et_sendMsg)
    EditText mEtSendMsg;
    @BindView(R.id.tv_num_tip)
    TextView mTvNumTip;

    private MessagePresenter mMessagePresenter;
    private String[] mSendTypeArrays = BoxApplication.getContext().getResources().getStringArray(R.array.send_msg_type);
    private int mChooseTypeIndex = 0;
    private CustomPopupWindow mTypePopupWindow;
    private List<String> mTypeList = new ArrayList<>();

    public SendMsgFragment() {
    }

    public static SendMsgFragment newInstance() {
        SendMsgFragment fragment = new SendMsgFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_msg_send, container, false);
        unbinder = ButterKnife.bind(this, v);
        initView();
        initDate();
        initListener();
        return v;
    }

    private void initView() {
        mMessagePresenter = new MessagePresenter(mContext, this);

    }

    private void initDate() {
        mTvReceiver.setText(mSendTypeArrays[0]);
        mTypeList = Arrays.asList(mSendTypeArrays);
        mTypePopupWindow = new CustomPopupWindow(getActivity(), new TypeChooseAdapter(R.layout.custom_popup_list_item_view, mTypeList));
    }

    private void initListener() {
        mEtSendMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvNumTip.setText(mEtSendMsg.getText().toString().length() + "/200字");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        mMessagePresenter.onDestory();
        mTypePopupWindow.dissMissPopWindow();
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_receiver, R.id.b_send_right_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_receiver:
                mTypePopupWindow.doTogglePopupWindow(view);
                break;
            case R.id.b_send_right_now:
                if (checkContent()) {
                    mMessagePresenter.sendMsg(mChooseTypeIndex + "", mEtSendMsg.getText().toString(), mEtTitle.getText().toString());
                }
                break;
        }
    }


    public class TypeChooseAdapter extends BaseQuickAdapter {

        public TypeChooseAdapter(int layoutResId, @Nullable List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            helper.setText(R.id.item_tv, String.valueOf(item));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mChooseTypeIndex = helper.getAdapterPosition();
                    mTvReceiver.setText(mSendTypeArrays[mChooseTypeIndex]);
                    mTypePopupWindow.dissMissPopWindow();
                }
            });
        }
    }


    private boolean checkContent() {
        if (TextUtils.isEmpty(mEtTitle.getText().toString())) {
            SingleToast.showMsg(getString(R.string.must_input_title));
            return false;
        }

        if (TextUtils.isEmpty(mEtSendMsg.getText().toString())) {
            SingleToast.showMsg(getString(R.string.must_input_content));
            return false;
        }

        if (mEtSendMsg.getText().toString().length() > 200) {
            SingleToast.showMsg(getString(R.string.content_toolong));
            return false;
        }
        return true;
    }

    @Override
    public void onRefreshResult(Object o) {
        BaseHttpResult baseHttpResult = (BaseHttpResult) o;
        if (baseHttpResult.getError() == 0) {
            SingleToast.showMsg(getString(R.string.action_success));
            mEtTitle.setText("");
            mTvReceiver.setText(mSendTypeArrays[0]);
            mEtSendMsg.setText("");
            mTvNumTip.setText("0/200字");
            RxBus.get().post(REFRSH_Fragment_DATA, "1");
        } else {
            SingleToast.showMsg(getString(R.string.action_faliure));
        }
    }

    @Override
    public void onLoadMoreResult(Object o) {

    }
}
