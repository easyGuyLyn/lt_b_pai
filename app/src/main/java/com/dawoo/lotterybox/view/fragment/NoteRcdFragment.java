package com.dawoo.lotterybox.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.record.CancleOrderFragment;
import com.dawoo.lotterybox.view.activity.record.CharseNumRecordFragment;
import com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment;
import com.dawoo.lotterybox.view.view.NoteRecordFragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dawoo.lotterybox.view.activity.BaseActivity.addStatusView;


/**
 * archar
 */
public class NoteRcdFragment extends BaseFragment {


    @BindView(R.id.btn_note_tab_his)
    Button mBtnNoteTabHis;
    @BindView(R.id.btn_note_tab_chaseNum)
    Button mBtnNoteTabChaseNum;
    @BindView(R.id.btn_note_tab_cancelOrder)
    Button mBtnNoteTabCancelOrder;
    Unbinder unbinder;


    private FragmentManager mFragmentManager;

    private final int TAB_HIS = 0;
    private final int TAB_CHARSE = 1;
    private final int TAB_ORDER = 2;


    public NoteRcdFragment() {
    }

    public static NoteRcdFragment newInstance() {
        NoteRcdFragment fragment = new NoteRcdFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_note_record, container, false);
        addStatusView(v, mContext);
        unbinder = ButterKnife.bind(this, v);
        initViews();
        initData();
        return v;
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        NoteRecordFragmentManager.getInstance().clear();
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initViews() {
    }

    /**
     * 初始化数据
     */
    void initData() {
        mFragmentManager = getFragmentManager();
    }

    @Override
    protected void loadData() {
        swithTab(TAB_HIS);
    }


    @OnClick({R.id.btn_note_tab_his, R.id.btn_note_tab_chaseNum, R.id.btn_note_tab_cancelOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_note_tab_his:
                swithTab(TAB_HIS);
                break;
            case R.id.btn_note_tab_chaseNum:
                swithTab(TAB_CHARSE);
                break;
            case R.id.btn_note_tab_cancelOrder:
                swithTab(TAB_ORDER);
                break;
            default:
        }
    }

    private void swithTab(int tab) {
        switch (tab) {
            case TAB_HIS:
                mBtnNoteTabHis.setSelected(true);
                mBtnNoteTabChaseNum.setSelected(false);
                mBtnNoteTabCancelOrder.setSelected(false);
                NoteRecordFragmentManager.getInstance().switchFragment(mFragmentManager, HistoryRepotFormFragment.class);
                break;
            case TAB_CHARSE:
                mBtnNoteTabHis.setSelected(false);
                mBtnNoteTabChaseNum.setSelected(true);
                mBtnNoteTabCancelOrder.setSelected(false);
                NoteRecordFragmentManager.getInstance().switchFragment(mFragmentManager, CharseNumRecordFragment.class);
                break;
            case TAB_ORDER:
                mBtnNoteTabHis.setSelected(false);
                mBtnNoteTabChaseNum.setSelected(false);
                mBtnNoteTabCancelOrder.setSelected(true);
                NoteRecordFragmentManager.getInstance().switchFragment(mFragmentManager, CancleOrderFragment.class);
                break;
            default:
        }
    }

}
