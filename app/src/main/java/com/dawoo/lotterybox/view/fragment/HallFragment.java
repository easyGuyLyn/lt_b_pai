package com.dawoo.lotterybox.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.dawoo.coretool.util.packageref.PackageInfoUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.adapter.hall.child.RecyclerAdapter;
import com.dawoo.lotterybox.adapter.hall.parent.ParentRecyclerAdapter;
import com.dawoo.lotterybox.bean.BannerBean;
import com.dawoo.lotterybox.bean.Bulletin;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.TypeAndLotteryBean;
import com.dawoo.lotterybox.mvp.presenter.HallPresenter;
import com.dawoo.lotterybox.mvp.view.IHallView;
import com.dawoo.lotterybox.mvp.view.ILotteryHallView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.view.activity.UserInforMationActivity;
import com.dawoo.lotterybox.view.activity.message.MessageMailActivity;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.LoadMoreFooterView;
import com.dawoo.lotterybox.view.view.swipetoloadlayout.RefreshHeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.dawoo.lotterybox.view.activity.BaseActivity.addStatusView;


public class HallFragment extends BaseFragment implements ILotteryHallView, IHallView, OnRefreshListener {

    @BindView(R.id.logined_ll)
    LinearLayout mLoginedLL;
    @BindView(R.id.tv_login_or_register)
    TextView mTvLoginOrRegister;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.iv_user_icon)
    CircleImageView mIvUserIcon;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.iv_activities)
    ImageView mIvActivities;
    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;


    private Unbinder mUnbinder;
    private ParentRecyclerAdapter mAdapter;
    private HallPresenter mHallPresenter;

    public HallFragment() {
    }

    public static HallFragment newInstance() {
        HallFragment fragment = new HallFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hall, container, false);
        addStatusView(v, mContext);
        mUnbinder = ButterKnife.bind(this, v);
        initViews();
        initData();
        return v;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void loadData() {
        if (mHallPresenter == null) {
            return;
        }
        mHallPresenter.getBanner();
        mHallPresenter.getBulletin();
        mHallPresenter.getTypeAndLottery();
    }

    public void initViews() {
        mSwipeToLoadLayout.setRefreshEnabled(true);
        mSwipeToLoadLayout.setLoadMoreEnabled(false);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeRefreshHeader.setAnimMode(RefreshHeaderView.MODE_DOG_EAT);
        mSwipeTarget.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeTarget.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ParentRecyclerAdapter(getActivity());
        mAdapter.setOnScrollListener(mOnScrollListener);
        mSwipeTarget.setAdapter(mAdapter);
    }

    private void initData() {
        mHallPresenter = new HallPresenter(mContext, this);
    }

    @Override
    public void onRefresh() {
        mHallPresenter.getBanner();
        mHallPresenter.getBulletin();
        mHallPresenter.getTypeAndLottery();
    }

    private void closeFresh() {
        if (mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
    }

    @OnClick({R.id.tv_login_or_register, R.id.iv_activities})
    public void onViewClicked(View view) {
        if (!DataCenter.getInstance().getUser().isLogin()) {
            ActivityUtil.startLoginActivity();
            return;
        }
        switch (view.getId()) {
            case R.id.tv_login_or_register:
                startActivity(new Intent(getActivity(), UserInforMationActivity.class));
                break;

            case R.id.iv_activities:
                MSPropties.startActivity(MessageMailActivity.class);
                break;
            default:
        }
    }

    RecyclerAdapter.OnScrollListener mOnScrollListener = new RecyclerAdapter.OnScrollListener() {
        @Override
        public void scrollTo(int pos) {
            mSwipeTarget.scrollToPosition(mAdapter.getItemCount() - 1);
        }
    };

    @Override
    public void onBanner(List<BannerBean> bannerBeans) {
        if (bannerBeans != null) {
            mAdapter.setBannerBeans(bannerBeans);
        }
        closeFresh();
    }

    @Override
    public void onBulletin(List<Bulletin> bulletins) {
        if (bulletins != null) {
            mAdapter.setBulletins(bulletins);
        }
        closeFresh();
    }

    @Override
    public void onTypeAndLottery(List<TypeAndLotteryBean> lotteryTypes) {
        if (lotteryTypes != null) {
            mAdapter.setLotteryTypes(lotteryTypes);
            for (TypeAndLotteryBean typeAndLotteryBean : lotteryTypes) {
                for (TypeAndLotteryBean.LotteriesBean lotteriesBean : typeAndLotteryBean.getLotteries()) {
                    DataCenter.getInstance().getmLotteryType().put(lotteriesBean.getCode(), lotteriesBean.getModel());
                }
            }
        }
        closeFresh();
    }

    /**
     * 加载帐户数据后回调
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_USER_INFO)})
    public void onUserInfoCallBack(String s) {
        // 当加载用户相关信息后显示用户信息
        mTvLoginOrRegister.setVisibility(View.GONE);
        mLoginedLL.setVisibility(View.VISIBLE);
        setHead();
        mTvUserName.setText(DataCenter.getInstance().getUser().getUsername());
    }

    /**
     * 登录成功后回调
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_LOGINED)})
    public void onLoginCallBack(String s) {
        // 登录成功先隐藏登录或注册tv
        mTvLoginOrRegister.setVisibility(View.GONE);
        mLoginedLL.setVisibility(View.GONE);
    }

    /**
     * 用户退出账户
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_LOGOUT)})
    public void logout(String s) {
        mTvLoginOrRegister.setVisibility(View.VISIBLE);
        mLoginedLL.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    void setHead() {
        String url = DataCenter.getInstance().getUser().getAvatarUrl();
        int headIcon = PackageInfoUtil.getResource(BoxApplication.getContext(), url);
        Glide.with(mContext).load(headIcon).into(mIvUserIcon);
    }

    /**
     * 修改头像
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_UPDATE_AVATER)})
    public void updateAvater(String uri) {
        Glide.with(this).load(PackageInfoUtil.getResource(BoxApplication.getContext(), uri)).into(mIvUserIcon);
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_NETWORK_EXCEPTION)})
    public void shrinkRefreshView(String s) {
        //  收起刷新
        if (null != mSwipeToLoadLayout && mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
            mSwipeToLoadLayout.setLoadingMore(false);
        }
    }
}
