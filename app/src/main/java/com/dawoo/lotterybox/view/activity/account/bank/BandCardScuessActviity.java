package com.dawoo.lotterybox.view.activity.account.bank;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.DataCenter;
import com.dawoo.lotterybox.bean.MyBandCard;
import com.dawoo.lotterybox.mvp.presenter.BankCardPreserter;
import com.dawoo.lotterybox.mvp.view.IBandCardListIView;
import com.dawoo.lotterybox.util.ActivityUtil;
import com.dawoo.lotterybox.util.MSPropties;
import com.dawoo.lotterybox.util.ViewUtils;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jack
 * @date 18-2-20
 * 银行卡列表成功界面
 */
public class BandCardScuessActviity extends BaseActivity implements  IBandCardListIView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.card_iamge)
    ImageView cardIamge;
    @BindView(R.id.card_name)
    TextView cardName;
    @BindView(R.id.card_number)
    TextView cardNumber_tv;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.card_type)
    TextView cardType;
    @BindView(R.id.card_unbind)
    Button cardUnbind;
    @BindView(R.id.add_card)
    LinearLayout addCard;
    private CardView bandcardscuessActivity;


    private BankCardPreserter bankCardPreserter;


    @Override
    protected void createLayoutView() {
        setContentView(R.layout.activity_bankcardscuess);

        bandcardscuessActivity = findViewById(R.id.bandcardscuessActivity);
        bankCardPreserter = new BankCardPreserter<IBandCardListIView>(this, this);
        MSPropties.getBanklistStastus(bankCardPreserter);
        bandcardscuessActivity.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.my_card), true);

    }

    @Override
    protected void initData() {

        cardUnbind.setOnClickListener(v -> {
            ActivityUtil.gotoCustomerService();
        });

    }


    @Override
    public void bandCardList(Object o) {
        List<MyBandCard> myBandCard = (List<MyBandCard>) o;
        bandcardscuessActivity.setVisibility(View.VISIBLE);
        cardName.setText(myBandCard.get(0).getBankName());
        cardType.setText(myBandCard.get(0).getBankCode());
        userName.setText(myBandCard.get(0).getMasterName());
        cardNumber_tv.setText(myBandCard.get(0).getCardNumber());
        ViewUtils.showBankImage(this,cardIamge,myBandCard.get(0).getBankName());
        if (DataCenter.getInstance().getUser().isBindBankCard()){
            addCard.setVisibility(View.GONE);
        }else {
            addCard.setVisibility(View.VISIBLE);
        }

    }


}
