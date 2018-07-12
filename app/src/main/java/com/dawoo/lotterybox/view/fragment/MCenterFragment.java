package com.dawoo.lotterybox.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dawoo.coretool.util.packageref.PackageInfoUtil;
import com.dawoo.lotterybox.BoxApplication;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.mvp.presenter.HallPresenter;
import com.dawoo.lotterybox.util.BalanceUtils;
import com.dawoo.lotterybox.util.NumberFormaterUtils;
import com.dawoo.lotterybox.util.SPConfig;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.MyBandCard;
import com.dawoo.lotterybox.bean.SettingBean;
import com.dawoo.lotterybox.bean.User;
import com.dawoo.lotterybox.mvp.presenter.UserPresenter;
import com.dawoo.lotterybox.mvp.view.IMCenterFragmentView;
import com.dawoo.lotterybox.net.rx.DefaultCallback;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.view.activity.account.bank.BindCardActivity;
import com.dawoo.lotterybox.view.activity.setting.FeedBackNextActivity;
import com.dawoo.lotterybox.view.activity.account.deposit.PayParentActivity;
import com.dawoo.lotterybox.view.activity.message.MessageMailActivity;
import com.dawoo.lotterybox.view.activity.UserInforMationActivity;
import com.dawoo.lotterybox.view.activity.account.withdraw.WithdrawalsActivity;
import com.dawoo.lotterybox.view.activity.team.TeamManagementActivity;
import com.dawoo.lotterybox.view.fragment.bill_details.BillingDetailsActivity;
import com.dawoo.lotterybox.view.activity.GameRecordActivity;
import com.dawoo.lotterybox.view.activity.message.AnnouncementActivity;
import com.dawoo.lotterybox.view.activity.account.RechargeRecordActivity;
import com.dawoo.lotterybox.view.activity.SecurityCenterActivity;
import com.dawoo.lotterybox.view.activity.LoginActivity;
import com.dawoo.lotterybox.view.activity.ShareGiftsActivity;
import com.dawoo.lotterybox.view.activity.account.WalletManagement;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;


import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.dawoo.lotterybox.view.activity.BaseActivity.addStatusView;

public class MCenterFragment extends BaseFragment implements IMCenterFragmentView {

    @BindView(R.id.service_iv)
    RelativeLayout serviceIv;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.setting_iv)
    RelativeLayout settingIv;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.profile_image_Fl)
    FrameLayout profileImageFl;
    @BindView(R.id.mine_account)
    TextView mine_account;
    @BindView(R.id.mine_name_id)
    TextView mine_name_id;
    @BindView(R.id.mine_name)
    TextView mine_name;
    @BindView(R.id.total_assets_lable)
    TextView totalAssetsLable;
    @BindView(R.id.total_assets_value)
    TextView totalAssetsValue;
    @BindView(R.id.purse_balance_value)
    TextView purseBalanceValue;
    @BindView(R.id.purse_balance_lable)
    TextView purseBalanceLable;
    @BindView(R.id.asset_ll)
    LinearLayout assetLl;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.mine_user_lv)
    TextView mineUserLv;
    @BindView(R.id.recharge_ll)
    LinearLayout rechargeLl;
    @BindView(R.id.point)
    View point;
    @BindView(R.id.withdraw_label_tv)
    TextView withdrawLabelTv;
    @BindView(R.id.withdraw_progress_tv)
    TextView withdrawProgressTv;
    @BindView(R.id.drawcash_rl)
    RelativeLayout drawcashRl;
    @BindView(R.id.mine_game_record_bootom_iv_view)
    View mineGameRecordBootomIvView;
    @BindView(R.id.mine_game_record_bootom_iv)
    ImageView mineGameRecordBootomIv;
    @BindView(R.id.mine_game_record_bootom_tv)
    TextView mineGameRecordBootomTv;
    @BindView(R.id.mine_game_record_bootom)
    TextView mineGameRecordBootom;
    @BindView(R.id.mine_game_record)
    RelativeLayout mineGameRecord;
    @BindView(R.id.mine_security_center_bootom_view)
    View mineSecurityCenterBootomView;
    @BindView(R.id.mine_security_center_bootom_iv)
    ImageView mineSecurityCenterBootomIv;
    @BindView(R.id.mine_security_center_bootom_tv)
    TextView mineSecurityCenterBootomTv;
    @BindView(R.id.mine_security_center_bootom)
    TextView mineSecurityCenterBootom;
    @BindView(R.id.textView)
    ImageView textView;
    @BindView(R.id.mine_security_center)
    RelativeLayout mineSecurityCenter;
    @BindView(R.id.point_views)
    View pointViews;
    @BindView(R.id.mine_account_details_bootom_iv)
    ImageView mineAccountDetailsBootomIv;
    @BindView(R.id.mine_account_details_bootom_tv)
    TextView mineAccountDetailsBootomTv;
    @BindView(R.id.mine_account_details_bootom)
    TextView mineAccountDetailsBootom;
    @BindView(R.id.mine_account_details)
    RelativeLayout mineAccountDetails;
    @BindView(R.id.mine_recharge_record_view)
    View mineRechargeRecordView;
    @BindView(R.id.mine_recharge_record_iv)
    ImageView mineRechargeRecordIv;
    @BindView(R.id.mine_recharge_record_tv)
    TextView mineRechargeRecordTv;
    @BindView(R.id.mine_recharge_record_bootom)
    TextView mineRechargeRecordBootom;
    @BindView(R.id.mine_recharge_record)
    RelativeLayout mineRechargeRecord;
    @BindView(R.id.mine_recommend_friends_view)
    View mineRecommendFriendsView;
    @BindView(R.id.mine_account_details_bootom_iv_s)
    ImageView mineAccountDetailsBootomIvS;
    @BindView(R.id.mine_account_details_bootom_tv_s)
    TextView mineAccountDetailsBootomTvS;
    @BindView(R.id.mine_account_details_bootom_s)
    TextView mineAccountDetailsBootomS;
    @BindView(R.id.mine_recommend_friends)
    RelativeLayout mineRecommendFriends;
    @BindView(R.id.mine_wallet_management_s)
    View mineWalletManagementS;
    @BindView(R.id.mine_recharge_record_iv_s)
    ImageView mineRechargeRecordIvS;
    @BindView(R.id.mine_recharge_record_tv_s)
    TextView mineRechargeRecordTvS;
    @BindView(R.id.mine_recharge_record_bootom_s)
    TextView mineRechargeRecordBootomS;
    @BindView(R.id.mine_wallet_management)
    RelativeLayout mineWalletManagement;
    @BindView(R.id.mine_recommend_friends_views)
    View mineRecommendFriendsViews;
    @BindView(R.id.mine_account_details_bootom_iv_ss)
    ImageView mineAccountDetailsBootomIvSs;
    @BindView(R.id.mine_account_details_bootom_tv_ss)
    TextView mineAccountDetailsBootomTvSs;
    @BindView(R.id.mine_account_details_bootom_ss)
    TextView mineAccountDetailsBootomSs;
    @BindView(R.id.mine_message_center)
    RelativeLayout mineMessageCenter;
    @BindView(R.id.mine_wallet_management_sss)
    View mineWalletManagementSss;
    @BindView(R.id.mine_feedback_bootom_ss)
    ImageView mineFeedbackBootomSs;
    @BindView(R.id.mine_feedback_bootom_s)
    TextView mineFeedbackBootomS;
    @BindView(R.id.mine_feedback_bootom)
    TextView mineFeedbackBootom;
    @BindView(R.id.mine_feedback)
    RelativeLayout mineFeedback;
    @BindView(R.id.mine_share_gifts_view)
    View mineShareGiftsView;
    @BindView(R.id.mine_share_gifts_bootom_tv_iv)
    ImageView mineShareGiftsBootomTvIv;
    @BindView(R.id.mine_share_gifts_bootom_tv)
    TextView mineShareGiftsBootomTv;
    @BindView(R.id.mine_share_gifts_bootom_s)
    TextView mineShareGiftsBootomS;
    @BindView(R.id.mine_share_gifts)
    RelativeLayout mineShareGifts;
    @BindView(R.id.mine_contact_custom_service_bootom_view)
    View mineContactCustomServiceBootomView;
    @BindView(R.id.mine_contact_custom_service_bootom_iv)
    ImageView mineContactCustomServiceBootomIv;
    @BindView(R.id.mine_contact_custom_service_bootom_tv)
    TextView mineContactCustomServiceBootomTv;
    @BindView(R.id.mine_contact_custom_service_bootom)
    TextView mineContactCustomServiceBootom;
    @BindView(R.id.mine_contact_custom_service)
    RelativeLayout mineContactCustomService;
    @BindView(R.id.money_prb)
    ProgressBar moneyPrb;
    @BindView(R.id.first_punch_text)
    TextView firstPunchText;
    Unbinder unbinder;
    private User user;
    private UserPresenter mUserPresenter;
    private HallPresenter<IMCenterFragmentView> mHallPresenter;
    private String mCSLink;

    public MCenterFragment() {

    }

    public static MCenterFragment newInstance() {
        return new MCenterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_center, container, false);
        addStatusView(v, mContext);
        unbinder = ButterKnife.bind(this, v);
        initViews();
        initData();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //解决由于内存不足导致的显示错误
        if (ConstantValue.PLAYER_AGENT.equals(user.getPlayerType())) {
            //代理
            mineUserLv.setText("代理");
            mineRecommendFriends.setVisibility(View.VISIBLE);
        } else if (ConstantValue.PLAYER_MEMBER.equals(user.getPlayerType())) {
            //一般会员
            mineUserLv.setText("普通会员");
            mineRecommendFriends.setVisibility(View.GONE);
        } else {
            mineUserLv.setText("- -");
        }

    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        mUserPresenter.onDestory();
        mHallPresenter.onDestory();
        unbinder.unbind();
        super.onDestroyView();
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

    public void initViews() {
        user = DataCenter.getInstance().getUser();
        changeViewState();
    }

    @SuppressLint("SetTextI18n")
    private void changeViewState() {
        if (!user.isLogin()) {
            mine_account.setVisibility(View.GONE);
            mine_name_id.setVisibility(View.GONE);
            mine_name.setVisibility(View.GONE);
            mineUserLv.setVisibility(View.GONE);
            assetLl.setVisibility(View.GONE);
            firstPunchText.setVisibility(View.GONE);
            tvLogin.setVisibility(View.VISIBLE);
            mineRecommendFriends.setVisibility(View.GONE);
            profileImage.setImageDrawable(getResources().getDrawable(R.mipmap.ic_avater_default));
        } else {
            mine_account.setVisibility(View.VISIBLE);
            mine_name_id.setVisibility(View.VISIBLE);
            mine_name.setVisibility(View.VISIBLE);
            mineUserLv.setVisibility(View.VISIBLE);
            assetLl.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
            firstPunchText.setVisibility(View.GONE);
            mine_account.setText("账户:" + MSPropties.getUserDate("username"));
            mine_name_id.setText("ID:" + MSPropties.getUserDate("userid"));
            mine_name.setText("昵称:" + MSPropties.getUserDate("nickName"));
            purseBalanceValue.setText(NumberFormaterUtils.formaterS2S(user.getBalance()));
        }
    }


    private void initData() {
        mUserPresenter = new UserPresenter<IMCenterFragmentView>(getActivity(), this);
        mHallPresenter = new HallPresenter<IMCenterFragmentView>(getActivity(), this);
        mHallPresenter.getCSLink();
    }

    @OnClick({R.id.service_iv, R.id.tv_login, R.id.mine_game_record, R.id.mine_security_center, R.id.mine_account_details,
            R.id.mine_recharge_record, R.id.mine_recommend_friends, R.id.mine_wallet_management, R.id.mine_feedback,
            R.id.mine_share_gifts, R.id.asset_ll, R.id.recharge_ll, R.id.first_punch_text, R.id.profile_image, R.id.setting_iv,
            R.id.mine_message_center, R.id.mail_mail, R.id.mine_contact_custom_service, R.id.drawcash_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.service_iv:
                ActivityUtil.gotoCustomerService();
                return;
            case R.id.mine_message_center:
                MSPropties.startActivity(AnnouncementActivity.class);
                return;
            case R.id.mine_contact_custom_service:
                ActivityUtil.gotoCustomerService();
                return;
            case R.id.drawcash_rl:
                if (ActivityUtil.isLogin()) {
                    //先请求判断是否需要绑定银行卡
                    if (DataCenter.getInstance().getUser().isBindBankCard()) {
                        MSPropties.startActivity(WithdrawalsActivity.class);
                    } else {
                        MSPropties.startActivity(BindCardActivity.class);
                    }
                }
                return;
            default:
        }

        if (!DataCenter.getInstance().getUser().isLogin()) {
            ActivityUtil.startLoginActivity();
            return;
        }
        switch (view.getId()) {
            case R.id.setting_iv:
                MSPropties.startActivity(UserInforMationActivity.class);
                break;
            case R.id.tv_login:
                MSPropties.startActivity(LoginActivity.class);
                break;
            case R.id.mine_game_record:
                MSPropties.startActivity(GameRecordActivity.class);
                break;
            case R.id.mine_security_center:
                MSPropties.startActivity(SecurityCenterActivity.class);
                break;
            case R.id.mine_account_details:
                MSPropties.startActivity(BillingDetailsActivity.class);
                break;
            case R.id.mine_recharge_record:
                MSPropties.startActivity(RechargeRecordActivity.class);
                break;
            case R.id.mine_recommend_friends:
                ActivityUtils.startActivity(TeamManagementActivity.class);
                break;
            case R.id.mine_wallet_management:
                MSPropties.startActivity(WalletManagement.class);
                break;
            case R.id.mine_feedback:
                ActivityUtils.startActivity(FeedBackNextActivity.class);
                break;
            case R.id.mine_share_gifts:
                MSPropties.startActivity(ShareGiftsActivity.class);
                break;
            case R.id.mail_mail:
                MSPropties.startActivity(MessageMailActivity.class);
                break;
            case R.id.asset_ll:
                mUserPresenter.getUerInfoWithoutDialog(new DefaultCallback() {
                    @Override
                    public void start() {
                        purseBalanceValue.setVisibility(View.GONE);
                        moneyPrb.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void complete() {

                    }
                });
                break;
            case R.id.recharge_ll:
                MSPropties.startActivity(PayParentActivity.class);
                break;
            case R.id.first_punch_text:
                break;
            case R.id.profile_image:
                break;
            default:
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onInfoResult(Object o) {
        if (o instanceof User) {
            user = (User) o;
            mUserPresenter.setUserWithoutToken(user);
            user = DataCenter.getInstance().getUser();
            mine_account.setVisibility(View.VISIBLE);
            mine_name_id.setVisibility(View.VISIBLE);
            mine_name.setVisibility(View.VISIBLE);
            mineUserLv.setVisibility(View.VISIBLE);
            assetLl.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
            firstPunchText.setVisibility(View.GONE);
            mine_account.setText("账户:" + user.getUsername());
            mine_name_id.setText("ID:" + user.getUserId());
            mine_name.setText("昵称:" + user.getNickname());
            purseBalanceValue.setText(NumberFormaterUtils.formaterS2S(user.getBalance()));
            purseBalanceValue.setVisibility(View.VISIBLE);
            moneyPrb.setVisibility(View.GONE);
            if (ConstantValue.PLAYER_AGENT.equals(user.getPlayerType())) {
                //代理
                mineUserLv.setText("代理");
                mineRecommendFriends.setVisibility(View.VISIBLE);
            } else {
                //一般会员
                mineUserLv.setText("普通会员");
                mineRecommendFriends.setVisibility(View.GONE);
            }
            setHead();
            RxBus.get().post(ConstantValue.EVENT_TYPE_USER_INFO, "mcfragment_user_info");
        } else if (o instanceof SettingBean) {
            //系统设置保存本地
            SettingBean settingBean = (SettingBean) o;
            SettingBean.AwardPushBean award_push = settingBean.getAward_push();
            SPUtils.getInstance().put(SPConfig.AWARD_PUSH, award_push.getParamValue());
            SettingBean.OpenPushBean open_push = settingBean.getOpen_push();
            SPUtils.getInstance().put(SPConfig.OPEN_PUSH, open_push.getParamValue());
            SettingBean.AnimationBean animation = settingBean.getAnimation();
            SPUtils.getInstance().put(SPConfig.ANIMARION, animation.getParamValue());
            SettingBean.GestureBean gesture = settingBean.getGesture();
            SPUtils.getInstance().put(SPConfig.GESTURE, gesture.getParamValue());
            SettingBean.ShakeBean shake = settingBean.getShake();
            SPUtils.getInstance().put(SPConfig.SHAKE, shake.getParamValue());
            SettingBean.VoiceBean voice = settingBean.getVoice();
            SPUtils.getInstance().put(SPConfig.VOICE, voice.getParamValue());
            SettingBean.DeviceBean device = settingBean.getDevice();
            SPUtils.getInstance().put(SPConfig.DEVICE, device.getParamValue());
        } else {
            //银行卡
            List<MyBandCard> datas = (List<MyBandCard>) o;
            if (datas.size() > 0) {
                DataCenter.getInstance().getUser().setBindBankCard(true);
            } else {
                DataCenter.getInstance().getUser().setBindBankCard(false);
            }
        }
    }

    @Override
    public void onCSLinkResult(String link) {
        if (!TextUtils.isEmpty(link)) {
            mCSLink = link;
            SPUtils.getInstance().put("cslink", mCSLink);
        }
    }

    void setHead() {
        String url = DataCenter.getInstance().getUser().getAvatarUrl();
        int headIcon = PackageInfoUtil.getResource(BoxApplication.getContext(), url);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(getResources().getDrawable(R.drawable.ic_default_img));
        Glide.with(mContext).load(headIcon).apply(requestOptions).into(profileImage);
    }

    /**
     * 登录成功后，回调加载账户
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_LOGINED)})
    public void loginedCallBack(String s) {
        //拉去用户信息，包括系统设置参数预定
        mUserPresenter.getUserInfoWithSystemSetting();
    }

    /**
     * 用户退出账户
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_TYPE_LOGOUT)})
    public void logout(String s) {
        changeViewState();
    }

    /**
     * 用户绑卡成功
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_BIND_CARD_SUCCEED)})
    public void bindCardSuccess(String s) {
        DataCenter.getInstance().getUser().setBindBankCard(true);
        DataCenter.getInstance().getUser().setRealName(s);
        ToastUtils.showShort("绑定银行卡成功");
    }

    /**
     * 修改昵称成功
     */
    @SuppressLint("SetTextI18n")
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_UPDATE_NICKNAME)})
    public void updateNickName(String nickName) {
        mine_name.setText("昵称:" + nickName);
    }

    /**
     * 修改头像
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_UPDATE_AVATER)})
    public void updateAvater(String uri) {
        Glide.with(this).load(PackageInfoUtil.getResource(BoxApplication.getContext(), uri)).into(profileImage);
    }

    /**
     * 设置了手势密码
     */
    @Subscribe(tags = {@Tag(ConstantValue.EVENT_SET_SS_CODE)})
    public void updateSS(Uri uri) {
        DataCenter.getInstance().getUser().setHasSecPwd(true);
    }


}