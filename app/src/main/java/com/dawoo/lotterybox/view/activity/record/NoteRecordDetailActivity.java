package com.dawoo.lotterybox.view.activity.record;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawoo.coretool.ToastUtil;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.record.NoteRecordHis;
import com.dawoo.lotterybox.mvp.presenter.RecordPresenter;
import com.dawoo.lotterybox.mvp.view.INoteRecordDetailView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.lottery.LotteryUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment.nowin;
import static com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment.pending;
import static com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment.revocation;
import static com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment.revoke_self;
import static com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment.revoke_sys;
import static com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment.wining;

/**
 * Created by archar on 18-2-20.
 */

public class NoteRecordDetailActivity extends BaseActivity implements INoteRecordDetailView {

    @BindView(R.id.head_view)
    HeaderView mHeadView;
    @BindView(R.id.iv_status_record)
    ImageView mIvStatusRecord;
    @BindView(R.id.tv_expect)
    TextView mTvExpect;
    @BindView(R.id.tv_play_code)
    TextView mTvPlayCode;
    @BindView(R.id.tv_lottery_code)
    TextView mTvLotteryCode;
    @BindView(R.id.tv_betNum)
    TextView mTvBetNum;
    @BindView(R.id.tv_openCode)
    TextView mTvOpenCode;
    @BindView(R.id.tv_betTime)
    TextView mTvBetTime;
    @BindView(R.id.tv_betCount_multiple)
    TextView mTvBetCountMultiple;
    @BindView(R.id.tv_note_idNum)
    TextView mTvNoteIdNum;
    @BindView(R.id.tv_payout)
    TextView mTvPayout;
    @BindView(R.id.tv_bonusModel)
    TextView mTvBonusModel;
    @BindView(R.id.tv_profit)
    TextView mTvProfit;
    @BindView(R.id.tv_betAmount)
    TextView mTvBetAmount;
    @BindView(R.id.tv_rebate)
    TextView mTvRebate;
    @BindView(R.id.tv_idNum)
    TextView mTvIdNum;
    @BindView(R.id.ll_availPrized)
    LinearLayout mLlAvailPrized;
    @BindView(R.id.tv_again_note)
    TextView mTvAgainNote;
    @BindView(R.id.tv_cancel_order)
    TextView mTvCancelOrder;

    private RecordPresenter mRecordPresenter;
    public static final String NOTE_ID = "note_id";
    public static final String ORIGN = "orign";
    public static String ORIGN_SELF = "self"; //个人注单详情
    public static String ORIGN_TEAM = "team"; //团队注单详情
    private int mId;//注单号
    private String code;

    public static void goNoteRecordDetailActivity(Context context, int id , String type){
        Intent intent = new Intent(context,NoteRecordDetailActivity.class);
        intent.putExtra(NOTE_ID,id);
        intent.putExtra(ORIGN,type);
        context.startActivity(intent);
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_record_note_detail_layout);
    }

    @Override
    protected void initViews() {
        mHeadView.setHeader(getString(R.string.note_detail), true);
        mId = getIntent().getIntExtra(NOTE_ID, -1);
    }

    @Override
    protected void initData() {
        mRecordPresenter = new RecordPresenter(this, this);
        mRecordPresenter.getOrderDetail(mId + "" , getIntent().getStringExtra(ORIGN));
    }


    @Override
    public void onRefreshResult(Object o) {
        NoteRecordHis noteRecordHis = (NoteRecordHis) o;
        code = noteRecordHis.getCode();
        if (noteRecordHis.getStatus().equals(pending)) {
            mIvStatusRecord.setImageResource(R.mipmap.img_status_weikaijiang);
            mTvAgainNote.setVisibility(View.VISIBLE);
        } else if (noteRecordHis.getStatus().equals(wining)) {
            mIvStatusRecord.setImageResource(R.mipmap.img_status_zhongjiang);
        } else if (noteRecordHis.getStatus().equals(nowin)) {
            mIvStatusRecord.setImageResource(R.mipmap.img_status_weizhongjiang);
        } else if (noteRecordHis.getStatus().equals(revoke_sys)) {
            mIvStatusRecord.setImageResource(R.mipmap.img_status_cd_system);
        } else if (noteRecordHis.getStatus().equals(revoke_self)) {
            mIvStatusRecord.setImageResource(R.mipmap.img_status_cd_zd);
        } else if (noteRecordHis.getStatus().equals(revocation)) {
            mIvStatusRecord.setImageResource(R.mipmap.img_status_cd_system);
        }
        mTvExpect.setText(getResources().getString(R.string.the_expect, noteRecordHis.getExpect()));
        mTvPlayCode.setText(LotteryUtil.getBetNameByCode(noteRecordHis.getBetCode() + ""));
        mTvLotteryCode.setText(LotteryUtil.getLotteryNameByCode(noteRecordHis.getCode() + ""));
        mTvBetNum.setText(LotteryUtil.getPlayName(noteRecordHis.getPlayCode() + "") + "@" + noteRecordHis.getBetNum());
        StringBuffer stringBuffer = new StringBuffer();
        if (noteRecordHis.getOpenCode() == null || noteRecordHis.getOpenCode().isEmpty()) {
            stringBuffer.append("暂未开奖");
        } else {
            String[] opencodes = noteRecordHis.getOpenCode().split(",");
            for (int i = 0; i < opencodes.length; i++) {
                if (i == 10) {
                    stringBuffer.append("\n" + opencodes[i] + " ");
                } else {
                    stringBuffer.append(opencodes[i] + " ");
                }
            }
        }
        mTvOpenCode.setText(stringBuffer);
        mTvBetTime.setText(DateTool.getTimeFromLong(DateTool.FMT_DATE_TIME, noteRecordHis.getBetTime()));
        mTvBetCountMultiple.setText(noteRecordHis.getBetCount() + "注," + noteRecordHis.getMultiple() + "倍");
        mTvNoteIdNum.setText(noteRecordHis.getId() + "");
        String playName = noteRecordHis.getPlayModel().equalsIgnoreCase("tradition") ? "传统" : "官方";
        mTvBonusModel.setText(noteRecordHis.getBonusModel() + "/" + playName);

        if (noteRecordHis.getStatus().equals(revoke_sys)
                || noteRecordHis.getStatus().equals(revoke_self)
                || noteRecordHis.getStatus().equals(revocation)) {
            mTvProfit.setText("--");
            mTvPayout.setText("--");
        } else {
            mTvProfit.setText(BalanceUtils.getScalsBalance(noteRecordHis.getProfit()));
            mTvPayout.setText(BalanceUtils.getScalsBalance(noteRecordHis.getPayout()));
        }
        mTvBetAmount.setText(BalanceUtils.getScalsBalance(noteRecordHis.getBetAmount()));
        mTvRebate.setText(BalanceUtils.getScalsBalance(noteRecordHis.getRebateAmount()));
        mTvIdNum.setText(noteRecordHis.getBillNo() + "");
    }


    @OnClick({R.id.tv_again_note, R.id.tv_cancel_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_again_note:
                if (code == null) {
                    ToastUtil.showToastShort(this, "参数丢失");
                    return;
                }
                ActivityUtil.startNoteActivity(code);
                break;
            case R.id.tv_cancel_order:
                break;
        }
    }
}
