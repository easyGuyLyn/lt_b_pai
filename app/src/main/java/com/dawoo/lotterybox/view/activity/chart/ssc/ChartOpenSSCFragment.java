package com.dawoo.lotterybox.view.activity.chart.ssc;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.lottery.Handicap;
import com.dawoo.lotterybox.bean.playtype.PlayTypeBean;
import com.dawoo.lotterybox.util.lottery.SSCUtil;
import com.dawoo.lotterybox.view.fragment.BaseFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 时时彩的开奖fragment
 * 主要显示 期数，开奖号，
 */
public class ChartOpenSSCFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Unbinder unbinder;
    @BindView(R.id.left_title_tv)
    TextView mLeftTitleTv;
    @BindView(R.id.left_recycler_view)
    RecyclerView mLeftRecyclerView;
    @BindView(R.id.tv_item_right_1)
    TextView mTvItemRight1;
    @BindView(R.id.tv_item_right_2)
    TextView mTvItemRight2;
    @BindView(R.id.tv_item_right_3)
    TextView mTvItemRight3;
    @BindView(R.id.tv_item_right_4)
    TextView mTvItemRight4;
    @BindView(R.id.right_recycler_view)
    RecyclerView mRightRecyclerView;
    @BindView(R.id.view_1)
    View mView1;
    @BindView(R.id.view_2)
    View mView2;
    @BindView(R.id.view_3)
    View mView3;
    @BindView(R.id.view_4)
    View mView4;
    private LeftAdapter mLeftAdapter;
    private RightAdapter mRightAdapter;
    ArrayList<Handicap> mRencentList;
    PlayTypeBean.PlayBean mPlayBean;
    private View mLeftFooterView;
    private View mRightFooterView;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRencentList = getArguments().getParcelableArrayList(ARG_PARAM1);
            mPlayBean = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLeftFooterView = null;
        mRightFooterView = null;
        RxBus.get().unregister(this);
        unbinder.unbind();
    }


    public static ChartOpenSSCFragment newInstance(ArrayList<Handicap> rencentList, PlayTypeBean.PlayBean playBean) {
        ChartOpenSSCFragment fragment = new ChartOpenSSCFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, rencentList);
        args.putParcelable(ARG_PARAM2, playBean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_open_ssc, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        initData();
        return view;
    }

    private void initViews() {
        mLeftAdapter = new LeftAdapter(R.layout.list_item_chart_open_ssc_left_view);
        mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLeftRecyclerView.setAdapter(mLeftAdapter);


        mRightAdapter = new RightAdapter(R.layout.list_item_chart_open_ssc_right_view);
        mRightRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRightRecyclerView.setAdapter(mRightAdapter);

        syncScroll(mLeftRecyclerView, mRightRecyclerView);
        setScroll();
    }

    private void setScroll() {
        RxBus.get().register(this);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRencentList != null) {
                    mLeftRecyclerView.scrollToPosition(mRencentList.size() - 1);
                    mRightRecyclerView.scrollToPosition(mRencentList.size() - 1);
                }
            }
        }, 200);
    }

    private void syncScroll(RecyclerView leftList, RecyclerView rightList) {
        leftList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    rightList.scrollBy(dx, dy);
                }
            }
        });

        rightList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    leftList.scrollBy(dx, dy);
                }
            }
        });
    }

    private void initData() {
        mPlayBean = ((ChartSSCActivity) mContext).getPlayBean();
        getHowtoPlay();
        mLeftAdapter.setNewData(mRencentList);
        mRightAdapter.setNewData(mRencentList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void loadData() {

    }


    /**
     * 根据玩法初始化
     * title lable
     * 开奖号码
     * 以及其他
     */
    void getHowtoPlay() {
        mLeftTitleTv.setText("期号");
        mTvItemRight1.setText("开奖号码");
        mTvItemRight2.setText("十位");
        mTvItemRight3.setText("个位");
        mTvItemRight4.setText("后三");

        if (mPlayBean != null && mPlayBean.getScheme().contains("前三")) {
            mTvItemRight4.setText("前三");
        } else {
            mTvItemRight4.setText("后三");
        }
    }

    class LeftAdapter extends BaseQuickAdapter {

        public LeftAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item != null && item instanceof Handicap) {
                Handicap bean = (Handicap) item;
                setItemBg(helper);
                if (TextUtils.isEmpty(bean.getExpect())) {
                    return;
                }
                if (bean.getExpect().length() - 3 > 0) {
                    helper.setText(R.id.item_left_tv, bean.getExpect().substring(bean.getExpect().length() - 3) + "期");
                } else {
                    helper.setText(R.id.item_left_tv, bean.getExpect() + "期");
                }

            }
        }
    }


    class RightAdapter extends BaseQuickAdapter {

        public RightAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            if (item != null && item instanceof Handicap) {
                setItemBg(helper);

                Handicap bean = (Handicap) item;
                String openCode = bean.getOpenCode();
                if (openCode == null) {
                    return;
                }
                String[] arr = openCode.split(",");
                try {
                    int code1 = Integer.parseInt(arr[0]);
                    int code2 = Integer.parseInt(arr[1]);
                    int code3 = Integer.parseInt(arr[2]);
                    int code4 = Integer.parseInt(arr[3]);
                    int code5 = Integer.parseInt(arr[4]);

                    if (mPlayBean == null) {
                        return;
                    }

                    String scheme = mPlayBean.getScheme();
                    String openCodeString = openCode.replace(",", " ");
                    if (scheme.contains("前三")) {
                        SpannableString spannableString = new SpannableString(openCodeString);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#CC192F"));
                        spannableString.setSpan(colorSpan, 0, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        // 开奖
                        helper.setText(R.id.tv_item_right_1, spannableString);

                    } else if (scheme.contains("后三")) {
                        SpannableString spannableString = new SpannableString(openCodeString);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#CC192F"));
                        spannableString.setSpan(colorSpan, 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        helper.setText(R.id.tv_item_right_1, spannableString);

                    } else if (scheme.contains("前二")) {
                        SpannableString spannableString = new SpannableString(openCodeString);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#CC192F"));
                        spannableString.setSpan(colorSpan, 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        helper.setText(R.id.tv_item_right_1, spannableString);

                    } else if (scheme.contains("五星")) {
                        SpannableString spannableString = new SpannableString(openCodeString);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#CC192F"));
                        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        helper.setText(R.id.tv_item_right_1, spannableString);

                    } else if (scheme.contains("四星")) {
                        SpannableString spannableString = new SpannableString(openCodeString);
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#CC192F"));
                        spannableString.setSpan(colorSpan, 1, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        helper.setText(R.id.tv_item_right_1, spannableString);
                    } else {
                        helper.setText(R.id.tv_item_right_1, openCodeString);
                    }


                    // 十位
                    helper.setText(R.id.tv_item_right_2, SSCUtil.getDaXiaoDanShuang(code4));
                    // 个位
                    helper.setText(R.id.tv_item_right_3, SSCUtil.getDaXiaoDanShuang(code5));
                    // 后三 或 前三 中三


                    if (mPlayBean.getScheme().contains("前三")) {
                        helper.setText(R.id.tv_item_right_4, SSCUtil.getZuLiuOrZUSan(code1, code2, code3));
                    } else {
                        helper.setText(R.id.tv_item_right_4, SSCUtil.getZuLiuOrZUSan(code3, code4, code5));
                    }


                } catch (NumberFormatException e) {
                    ToastUtil.showToastShort(mContext, e.getMessage());
                }
            }
        }
    }


    /**
     * 设置item背景颜色
     *
     * @param helper
     */
    void setItemBg(BaseViewHolder helper) {
        int position = helper.getAdapterPosition();
        if (position % 2 == 0) {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color_chart_item_bg));
        }
    }


    /**
     * 控制开奖footer
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_OPENING_LOTTERY)})
    public void toggleOpenFooter(String s) {
        if (mRencentList == null) {
            return;
        }
        if ("opening".equals(s)) {
            if (mLeftFooterView == null) {
                mLeftFooterView = View.inflate(mContext, R.layout.list_item_chart_left_footer_view, null);
            }
            if (mRightFooterView == null) {
                mRightFooterView = View.inflate(mContext, R.layout.list_item_chart_right_footer_view, null);
            }
            mLeftAdapter.addFooterView(mLeftFooterView);
            mRightAdapter.addFooterView(mRightFooterView);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mRencentList != null) {
                        mLeftRecyclerView.scrollToPosition(mRencentList.size());
                        mRightRecyclerView.scrollToPosition(mRencentList.size());
                    }
                }
            }, 200);
        } else {
            mLeftAdapter.removeFooterView(mLeftFooterView);
            mRightAdapter.removeFooterView(mRightFooterView);

            mLeftAdapter.notifyDataSetChanged();
            mRightAdapter.notifyDataSetChanged();
        }
    }

}
