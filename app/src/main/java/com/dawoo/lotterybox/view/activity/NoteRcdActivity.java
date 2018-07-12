package com.dawoo.lotterybox.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.view.activity.record.CharseNumRecordFragment;
import com.dawoo.lotterybox.view.activity.record.HistoryRepotFormFragment;
import com.dawoo.lotterybox.view.view.HeaderView;

import butterknife.BindView;

/**
 * Created by archar on 18-3-19.
 */

public class NoteRcdActivity extends BaseActivity {
    public static final String FRAGMENT_TAG = "fragment_tag";

    @BindView(R.id.head_view)
    HeaderView mHeadView;

    private String mFragmentClassName;
    private FragmentManager mFragmentManager;

    public static void goRcordActivity(Context context, String simpleName) {
        Intent intent = new Intent(context, NoteRcdActivity.class);
        intent.putExtra(FRAGMENT_TAG, simpleName);
        context.startActivity(intent);
    }

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_note_rcd);
    }

    @Override
    protected void initViews() {
        mFragmentManager = getSupportFragmentManager();
        mHeadView.setHeader(getString(R.string.my_bet), true);
        if (getIntent().getStringExtra(FRAGMENT_TAG) != null) {
            mFragmentClassName = getIntent().getStringExtra(FRAGMENT_TAG);
        }
        if (mFragmentClassName == null) return;
        if (mFragmentClassName.equals(HistoryRepotFormFragment.class.getSimpleName())) {
            mHeadView.setHeader(getString(R.string.my_bet), true);
        } else if (mFragmentClassName.equals(CharseNumRecordFragment.class.getSimpleName())) {
            mHeadView.setHeader(getString(R.string.my_chase_number), true);
        }
    }

    @Override
    protected void initData() {
        if (mFragmentClassName == null) return;
        if (mFragmentClassName.equals(HistoryRepotFormFragment.class.getSimpleName())) {
            addFragment(HistoryRepotFormFragment.class);
        } else if (mFragmentClassName.equals(CharseNumRecordFragment.class.getSimpleName())) {
            addFragment(CharseNumRecordFragment.class);
        }
    }

    private void addFragment(Class<? extends Fragment> clazz) {
        Fragment fragment = null;
        try {
            fragment = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, fragment);
        transaction.commitAllowingStateLoss();
    }

}
