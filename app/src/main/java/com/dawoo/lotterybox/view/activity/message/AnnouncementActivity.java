package com.dawoo.lotterybox.view.activity.message;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.fragment.message.AnnouncementFragment;
import com.dawoo.lotterybox.view.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author archar
 */

public class AnnouncementActivity extends BaseActivity {
    @BindView(R.id.hend_back)
    ImageView hendBack;
    @BindView(R.id.message_receive)
    Button messageAnnouncement;
    @BindView(R.id.message_station_letter)
    Button messageStationLetter;
    @BindView(R.id.share_friend_iv)
    ImageView shareFriendIv;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    @BindView(R.id.message_send_email)
    Button messageStationEmail;
    public static final int messageAnnouncement_index = 0;
    public static final int messageStationLetter_index = 1;
    public static final int messageEmailBox_index = 2;
    private List<Fragment> fragments = new ArrayList<>();

    private MessageViewPageAdapter messageViewPageAdapter;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_messagecenter);
    }

    @Override
    protected void initViews() {
        //公告的界面
        fragments.add(new AnnouncementFragment());
//        //站内信
//        fragments.add(new SiteMsgFragment());
//        //信箱
//        fragments.add(new MailBoxFragment());
        messageViewPageAdapter = new MessageViewPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(messageViewPageAdapter);
        switchTab(messageAnnouncement_index);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.hend_back, R.id.message_receive, R.id.message_station_letter, R.id.share_friend_iv, R.id.view_pager, R.id.message_send_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hend_back:
                finish();
                break;
            case R.id.message_receive:
                switchTab(messageAnnouncement_index);
                break;
            case R.id.message_station_letter:
                switchTab(messageStationLetter_index);
                break;
            case R.id.message_send_email:
                switchTab(messageEmailBox_index);
                break;
            case R.id.share_friend_iv:
                ActivityUtil.gotoCustomerService();
                break;
            default:
        }
    }


    private class MessageViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();

        public MessageViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //   super.destroyItem(container, position, object);
        }
    }

    /**
     * 状态选择
     *
     * @param tabIndex
     */
    void switchTab(int tabIndex) {
        switch (tabIndex) {
            case messageAnnouncement_index:
                messageAnnouncement.setTextColor(getResources().getColor(R.color.colorAccent));
                messageStationLetter.setTextColor(getResources().getColor(R.color.white));
                messageStationEmail.setTextColor(getResources().getColor(R.color.white));
                messageAnnouncement.setSelected(true);
                messageStationLetter.setSelected(false);
                messageStationEmail.setSelected(false);
                viewPager.setCurrentItem(messageAnnouncement_index);
                break;
            case messageStationLetter_index:
                messageAnnouncement.setTextColor(getResources().getColor(R.color.white));
                messageStationLetter.setTextColor(getResources().getColor(R.color.colorAccent));
                messageStationEmail.setTextColor(getResources().getColor(R.color.white));
                messageAnnouncement.setSelected(false);
                messageStationLetter.setSelected(true);
                messageStationEmail.setSelected(false);
                viewPager.setCurrentItem(messageStationLetter_index);
                break;

            case messageEmailBox_index:
                messageAnnouncement.setTextColor(getResources().getColor(R.color.white));
                messageStationLetter.setTextColor(getResources().getColor(R.color.white));
                messageStationEmail.setTextColor(getResources().getColor(R.color.colorAccent));
                messageAnnouncement.setSelected(false);
                messageStationLetter.setSelected(false);
                messageStationEmail.setSelected(true);
                viewPager.setCurrentItem(messageEmailBox_index);
                break;
            default:
        }
    }
}
