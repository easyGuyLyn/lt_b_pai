package com.dawoo.lotterybox.view.fragment.mc;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.lotterybox.BuildConfig;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.team.base.BindLayout;
import com.dawoo.lotterybox.view.activity.team.base.OnMultiClickListener;
import com.dawoo.lotterybox.view.activity.team.base.SuperBaseFragment;
import com.dawoo.lotterybox.bean.OALinkBean;
import com.dawoo.lotterybox.bean.OALinkShowBean;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.IOALinklView;
import com.dawoo.lotterybox.util.BottomDialogUtils;
import com.dawoo.lotterybox.util.ViewUtils;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 18-4-21.
 *
 * @author alex
 */
@BindLayout(R.layout.fragment_link_open_account)
public class LinkOpenAccountFragment extends SuperBaseFragment implements IOALinklView {
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rl_link)
    RelativeLayout rlLink;
    @BindView(R.id.tv_open_account)
    TextView tvOpenAccount;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private UserPresenter<LinkOpenAccountFragment> linkPresenter;
    private String linkOA = BuildConfig.HOST_URL + "/#/register/?";
    private List<OALinkShowBean> datas;
    private LinkOpenAdapter linkOpenAdapter;

    public static LinkOpenAccountFragment newInstance() {

        Bundle args = new Bundle();

        LinkOpenAccountFragment fragment = new LinkOpenAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected void initView() {
        RxBus.get().register(this);
        ViewUtils.setLinearManager(getContext(), rvContent);
        rlLink.setOnClickListener(v -> BottomDialogUtils.with(getActivity())
                .showSelectDialog("请选择链接类型", "代理".equals(tvType.getText().toString()), select -> tvType.setText(select)));
    }

    @Override
    protected void initData() {
        linkPresenter = new UserPresenter<>(getActivity(), this);
        datas = new ArrayList<>();
        linkOpenAdapter = new LinkOpenAdapter();
        rvContent.setAdapter(linkOpenAdapter);
        tvOpenAccount.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                linkPresenter.getCreateOALink();
            }
        });

    }

    @Override
    protected void superLoadData() {

    }

    @Override
    public void onLinkResult(OALinkBean oaLinkBean) {
        OALinkShowBean oaLinkShowBean = new OALinkShowBean();
        if ("代理".equals(tvType.getText().toString())) {
            oaLinkShowBean.setType("代理");
            oaLinkShowBean.setData(linkOA + oaLinkBean.getAgent());
        } else {
            oaLinkShowBean.setType("普通会员");
            oaLinkShowBean.setData(linkOA + oaLinkBean.getMember());
        }
        linkOpenAdapter.addData(0,oaLinkShowBean);
        rvContent.smoothScrollToPosition(0);
    }


    class LinkOpenAdapter extends BaseQuickAdapter<OALinkShowBean, LinkOpenAdapter.LinkOpenHolder> {

        public LinkOpenAdapter() {
            super(R.layout.item_link_open_account);
        }

        @Override
        protected void convert(LinkOpenHolder helper, OALinkShowBean item) {
            helper.tvLine.setText(item.getData());
            helper.tvType.setText(item.getType());
            helper.tvDelete.setOnClickListener(new OnMultiClickListener() {
                @Override
                public void onMultiClick(View v) {
                    noticeDialog.setContentString("确定要删除这条链接么")
                            .show(alertDialog -> {
                                alertDialog.dismiss();
                                linkOpenAdapter.remove(helper.getPosition());
                            });
                }
            });
            helper.tvCopy.setOnClickListener(new OnMultiClickListener() {
                @Override
                public void onMultiClick(View v) {
                    copy(item.getData());
                    ToastUtils.showShort("已成功复制链接");
                }
            });


        }

        class LinkOpenHolder extends BaseViewHolder {
            @BindView(R.id.tv_line)
            TextView tvLine;
            @BindView(R.id.tv_account)
            TextView tvAccount;
            @BindView(R.id.tv_type)
            TextView tvType;
            @BindView(R.id.tv_copy)
            TextView tvCopy;
            @BindView(R.id.tv_delete)
            TextView tvDelete;

            public LinkOpenHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        BottomDialogUtils.with(getActivity()).destory();
        RxBus.get().unregister(this);
        linkPresenter.onDestory();
    }

    private void copy(String content) {
        LogUtils.e("link",content);
        ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());

    }
    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        ToastUtils.showShort(message);
    }
}
