package com.dawoo.lotterybox.view.activity.account.deposit;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dawoo.coretool.ToastUtil;
import com.dawoo.lotterybox.BuildConfig;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.Deposit.CallBackUrlBean;
import com.dawoo.lotterybox.bean.Deposit.OnlineResulltBean;
import com.dawoo.lotterybox.bean.Deposit.ParentDepositBean;
import com.dawoo.lotterybox.bean.Deposit.PayAccountCompanyThirdType;
import com.dawoo.lotterybox.bean.Deposit.PayDetailBean;
import com.dawoo.lotterybox.bean.Deposit.SaleBean;
import com.dawoo.lotterybox.mvp.presenter.DepositPresenter;
import com.dawoo.lotterybox.mvp.view.IDepositView;
import com.dawoo.lotterybox.net.BaseHttpResult;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.DepositUtil;
import com.dawoo.lotterybox.util.FileTool;
import com.dawoo.lotterybox.util.NetUtil;
import com.dawoo.lotterybox.util.PermissionUtil;
import com.dawoo.lotterybox.util.SingleToast;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.dawoo.lotterybox.view.view.dialog.ShowFeeDialog;
import com.dawoo.lotterybox.view.view.dialog.ShowSuccessDialog;

import org.apache.http.util.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rain on 18-5-2.
 */

public class PostDepositActivity extends BaseActivity implements IDepositView {
    @BindView(R.id.head_view)
    HeaderView headerView;

    /**
     * 银行转账
     */
    @BindView(R.id.bank_card_rl)
    RelativeLayout bankLayout;
    @BindView(R.id.bank_iv)
    ImageView bankIv;
    @BindView(R.id.bank_name)
    TextView bankNameTv;
    @BindView(R.id.bank_user_name)
    TextView bankUserTv;
    @BindView(R.id.bank_account)
    TextView bankAccountTv;
    @BindView(R.id.open_bank)
    TextView bankOpenTv;

    /**
     * 第三方
     */
    @BindView(R.id.other_rl)
    RelativeLayout otherLayout;
    @BindView(R.id.way_iv)
    ImageView otherIv;
    @BindView(R.id.other_name)
    TextView otherNameTv;
    @BindView(R.id.account_tv)
    TextView otherAccountTv;
    @BindView(R.id.account_user)
    TextView otherUserTv;

    /**
     * 二维码
     */
    @BindView(R.id.pay_image_rl)
    LinearLayout qrCodeLayout;
    @BindView(R.id.pay_image_iv)
    ImageView qrCodeIv;
    @BindView(R.id.start_pay_tv)
    TextView appTv;
    @BindView(R.id.save_pic_tv)
    TextView savePicTv;
    /**
     * 输入
     */
    @BindView(R.id.first_name)
    TextView firstTv;
    @BindView(R.id.first_et)
    EditText firstEd;
    @BindView(R.id.sec_name)
    TextView secTv;
    @BindView(R.id.sec_et)
    EditText secEd;
    @BindView(R.id.notice_tv)
    TextView noticeTv;
    @BindView(R.id.sec_layout)
    RelativeLayout secLayout;
    @BindView(R.id.first_layout)
    RelativeLayout firstLayout;

    private int saleId;
    private PayDetailBean bean;
    private double depositAmount;
    private ShowFeeDialog mFeeDialog;
    private ShowSuccessDialog mSuccessDialog;
    DepositPresenter mPresenter;

    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_withdraw_post);
        bean = (PayDetailBean) getIntent().getSerializableExtra("item");
        depositAmount = getIntent().getDoubleExtra("depositAmount", 0);
        saleId = getIntent().getIntExtra("saleId", 0);
        if (bean == null) {
            SingleToast.showMsg("参数丢失");
            finish();
            return;
        }
    }

    @Override
    protected void initViews() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.icon_company);
        if (!TextUtils.isEmpty(bean.getItem()) && bean.getItem().equalsIgnoreCase("online_bank_deposit")) {
            otherLayout.setVisibility(View.GONE);
            bankLayout.setVisibility(View.VISIBLE);
            bankUserTv.setText(bean.getFullName());
            bankOpenTv.setText("开户行：" + bean.getOpenAcountName());
            bankAccountTv.setText("账户信息：" + bean.getAccount());
            headerView.setHeader("转账到银行卡", true);
            if (!TextUtils.isEmpty(bean.getCustomBankName())) {
                bankNameTv.setText(bean.getCustomBankName());
            } else if (!TextUtils.isEmpty(bean.getPayName())) {
                bankNameTv.setText(bean.getPayName());
            } else {
                bankNameTv.setText(bean.getPayTran() + "");
            }
            if (!TextUtils.isEmpty(bean.getPicUrl())) {
                bankIv.setImageResource(R.mipmap.icon_company);
            } else {
                Glide.with(this).load(Uri.parse(NetUtil.handleUrl(DataCenter.getInstance().getDomain(), bean.getPicUrl())))
                        .apply(options).into(bankIv);
            }
        } else {
            otherLayout.setVisibility(View.VISIBLE);
            bankLayout.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(bean.getCustomBankName())) {
                otherNameTv.setText(bean.getCustomBankName());
            } else if (!TextUtils.isEmpty(bean.getPayName())) {
                otherNameTv.setText(bean.getPayName());
            } else {
                otherNameTv.setText(bean.getPayTran() + "");
            }

            otherAccountTv.setText(bean.getAccount());
            otherUserTv.setText("姓名:" + bean.getFullName());
            headerView.setHeader(bean.getCustomBankName(), true);
            if (!TextUtils.isEmpty(bean.getPicUrl())) {
                otherIv.setImageResource(R.mipmap.icon_company);
            } else {
                Glide.with(this).load(Uri.parse(NetUtil.handleUrl(DataCenter.getInstance().getDomain(), bean.getPicUrl())))
                        .apply(options).into(otherIv);
            }
        }

        if (bean.getQrCodeUrl().isEmpty()) {
            qrCodeLayout.setVisibility(View.GONE);
        } else {
            Glide.with(this).load(NetUtil.handleUrl(DataCenter.getInstance().getImgDomain(), bean.getQrCodeUrl())).into(qrCodeIv);
            if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.ALIPAY.getCode())) {
                appTv.setText("打开支付宝App");
                appTv.setVisibility(View.VISIBLE);
            } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.WECHAT.getCode())) {
                appTv.setText("打开微信App");
                appTv.setVisibility(View.VISIBLE);
            } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.UNIOP_PAY.getCode())) {
                appTv.setText("打开QQ钱包App");
                appTv.setVisibility(View.VISIBLE);
            }
        }


        String notice = DepositUtil.getNoteByCode(bean.getBankCode(), bean.isRandomAmount(), bean.getType(), false);
        SpannableStringBuilder noticeBuffer = new SpannableStringBuilder("温馨提示：\n" + notice.replaceAll("<br>", "\n"));
        SpannableString colorSpan = new SpannableString("点击联系在线客服");
        colorSpan.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorPrimary));
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }

            @Override
            public void onClick(View widget) {
                if (SPUtils.getInstance().getString("cslink", "").isEmpty()) {
                    ActivityUtil.gotoCustomerService();
                    return;
                }
                ActivityUtil.startWebView(SPUtils.getInstance().getString("cslink", ""), ConstantValue.WEBVIEW_TYPE_THIRD_ORDINARY);
            }
        }, 0, colorSpan.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        noticeBuffer.append(colorSpan);
        noticeTv.setText(noticeBuffer);
        noticeTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void initData() {
        mPresenter = new DepositPresenter(this, this);
        if (bean.getAccountType() != null && bean.getAccountType().equalsIgnoreCase("1")) {
            secLayout.setVisibility(View.GONE);
            firstTv.setText("存款人账号");
            secEd.setHint("请输入存款人账户");

        } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.WECHAT.getCode())) {
            firstTv.setText("您的微信账号");
            firstEd.setHint("请输入您的微信账号");
        } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.ALIPAY.getCode())) {
            firstTv.setText("您的支付宝账号");
            firstEd.setHint("请输入您的支付宝账号");
        } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.ONE_CODE_PAY.getCode())) {
            firstLayout.setVisibility(View.GONE);
        } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.BD_WWALLET.getCode())) {
            firstTv.setText("您的百度钱包账号");
            firstEd.setHint("请输入您的百度钱包账号");
        } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.UNIOP_PAY.getCode())) {
            firstTv.setText("您的QQ钱包账号");
            firstEd.setHint("请输入您的QQ钱包账号");
        } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.JD_WALLET.getCode())) {
            firstTv.setText("您的京东钱包账号");
            firstEd.setHint("请输入您的京东钱包账号");
        }


    }

    @OnClick({R.id.tv_copy_account, R.id.tv_copy_name, R.id.save_pic_tv, R.id.start_pay_tv, R.id.disport_bt})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.disport_bt:
                if (firstLayout.getVisibility() == View.VISIBLE) {
                    if (firstEd.getText().toString().trim().isEmpty()) {
                        SingleToast.showMsg(firstEd.getHint().toString());
                        return;
                    }
                }
                if (secLayout.getVisibility() == View.VISIBLE) {
                    String sec = secEd.getText().toString().trim();
                    if (!TextUtils.isEmpty(sec) && sec.length() != 5) {
                        SingleToast.showMsg(secEd.getHint().toString());
                        return;
                    }
                }
                mPresenter.getFee(bean.getId(), depositAmount);
                break;
            case R.id.tv_copy_account:
                copy(bean.getAccount());
                break;
            case R.id.tv_copy_name:
                copy(bean.getFullName());
                break;

            case R.id.save_pic_tv:
                if (qrCodeIv.getDrawable() == null) {
                    ToastUtil.showToastShort(this, getString(R.string.deposit_uncatch_image_toast));
                    return;
                }
                qrCodeIv.setDrawingCacheEnabled(true);
                if (!PermissionUtil.checkPermission(PostDepositActivity.this, ConstantValue.PERMISSIONS_STORAGE_WRITE, 1)) {
                    return;
                }
                Bitmap bitmap = qrCodeIv.getDrawingCache();
                if (bitmap == null) {
                    ToastUtil.showToastShort(this, getString(R.string.deposit_uncatch_image_toast));
                    return;
                }
                FileTool.saveImageToGallery(this, bitmap);
                qrCodeIv.destroyDrawingCache();
                break;
            case R.id.start_pay_tv:
                if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.ALIPAY.getCode())) {
                    ActivityUtil.startOtherapp(ConstantValue.packageali);
                } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.WECHAT.getCode())) {
                    ActivityUtil.startOtherapp(ConstantValue.packagewechat);
                } else if (bean.getBankCode().equalsIgnoreCase(PayAccountCompanyThirdType.UNIOP_PAY.getCode())) {
                    ActivityUtil.startOtherapp(ConstantValue.packageQQ);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                savePicTv.callOnClick();
            } else {
                // Permission Denied
                ToastUtil.showToastShort(PostDepositActivity.this, "您没有授权存储权限，请在设置中打开授权");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 复制功能
     *
     * @param content
     */

    public void copy(String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        ToastUtil.showToastShort(this, "成功复制到剪切板");
    }


    @Override
    public void getDepositWay(List<ParentDepositBean> beans) {

    }

    @Override
    public void getBankDepositDetail(List<PayDetailBean> bean) {

    }

    @Override
    public void getFee(Object o) {
        if (o == null) {
            return;
        }
        double feeAmount;
        try {
            feeAmount = Double.parseDouble(o.toString());
        } catch (Exception e) {
            feeAmount = 0.00;
        }

        if (mFeeDialog == null) {
            mFeeDialog = new ShowFeeDialog(this, R.style.CustomDialogStyle);
            mFeeDialog.setSureClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.postCompanyPay(bean.getId(), depositAmount, saleId, secEd.getText().toString().trim(), firstEd.getText().toString().trim());
                }
            });
        }
        mFeeDialog.setText(depositAmount, feeAmount);
        mFeeDialog.show();
    }

    @Override
    public void postResult(OnlineResulltBean o) {

    }

    @Override
    public void callBackOrder(CallBackUrlBean o) {

    }

    @Override
    public void getSales(List<SaleBean> saleBeans) {

    }

    @Override
    public void getSaleMoney(Object o) {

    }

    @Override
    public void postCompany(BaseHttpResult baseHttpResult) {
        if (baseHttpResult == null) {
            return;
        }
        if (baseHttpResult.getError() != 0) {
            SingleToast.showMsg(TextUtils.isEmpty(baseHttpResult.getMessage()) ? "返回失败" : baseHttpResult.getMessage());
        } else {
            mFeeDialog.dismiss();
            if (mSuccessDialog == null) {
                mSuccessDialog = new ShowSuccessDialog(this, R.style.CustomDialogStyle);
                mSuccessDialog.setSureClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSuccessDialog.dismiss();
                        setResult(ConstantValue.RESULE_SUCCESS);
                        finish();
                    }
                });
            }
            mSuccessDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }
}
