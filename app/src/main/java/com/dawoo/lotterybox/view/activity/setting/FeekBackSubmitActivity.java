package com.dawoo.lotterybox.view.activity.setting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dawoo.coretool.util.date.DateTool;
import com.dawoo.lotterybox.ConstantValue;
import com.dawoo.lotterybox.R;
import com.dawoo.lotterybox.bean.TypeBean;
import com.dawoo.lotterybox.mvp.presenter.SettingPresenter;
import com.dawoo.lotterybox.mvp.view.IFeedBackView;
import com.dawoo.lotterybox.view.activity.BaseActivity;
import com.dawoo.lotterybox.view.view.HeaderView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jack
 * 意见反馈带单选按钮的
 */


public class FeekBackSubmitActivity extends BaseActivity implements OnDateSetListener, IFeedBackView {
    @BindView(R.id.head_view)
    HeaderView headView;
    @BindView(R.id.time_btn)
    TextView timeBtn;
    @BindView(R.id.input_text)
    EditText inputText;
    @BindView(R.id.please_input_detailed_information)
    EditText pleaseInputDetailedInformation;
    @BindView(R.id.submint_feek)
    Button submintFeek;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Context context;
    private String type;
    private String module="";
    private String faultTime;
    private String Platform="Android";
    private String[] typeText =
            {"游戏大厅", "游戏界面", "开奖记录", "购彩记录", "账号信息"};
    private String[] typeValue =
            {"hall", "game_ui", "award_record", "buy_record", "account"};
    private SettingPresenter<FeekBackSubmitActivity> settingPresenter;
    private List<TypeBean> data;

    @Override
    protected void createLayoutView() {
        context = this;
        setContentView(R.layout.activity_feekback_submit);
        RxBus.get().register(this);
    }

    @Override
    protected void initViews() {
        headView.setHeader(getResources().getString(R.string.feekback_next), true);
        type = getIntent().getStringExtra("type");
    }

    @Override
    protected void initData() {
        settingPresenter = new SettingPresenter<>(this, this);
        setDateTime(System.currentTimeMillis());
        data = new ArrayList<>();
        for (int i = 0; i < typeText.length; i++) {
            TypeBean typeBean = new TypeBean();
            typeBean.setType(typeValue[i]);
            typeBean.setMessage(typeText[i]);
            data.add(typeBean);
        }
        MultipleChoiceAdapter adapter = new MultipleChoiceAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position, id) -> {
            for (TypeBean t : data) {
                t.setSelect(false);
            }
            data.get(position).setSelect(true);
            module=data.get(position).getType();
            adapter.setNewData(data);
        });


    }

    @OnClick({R.id.time_btn, R.id.input_text, R.id.please_input_detailed_information, R.id.submint_feek})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.time_btn:
                TimePickerDialog dialogMonthDayHourMin = new TimePickerDialog.Builder()
                        .setType(Type.MONTH_DAY_HOUR_MIN)
                        .setCallBack(this)
                        .setTitleStringId(getResources().getString(R.string.please_select_time))
                        .setToolBarTextColor(getResources().getColor(R.color.colorAccent))
                        .build();
                dialogMonthDayHourMin.show(getSupportFragmentManager(), "MONTH_DAY_HOUR_MIN");
                break;
            case R.id.input_text:
                break;
            case R.id.please_input_detailed_information:
                break;
            case R.id.submint_feek:
                //提交
                if ("".equals(module)){
                    ToastUtils.showShort("请选择一个错误功能");
                    return;
                }
                String content = inputText.getText().toString().trim();
                String contact = pleaseInputDetailedInformation.getText().toString().trim();
                settingPresenter.getFeedback(type,module,faultTime,content,contact,Platform);
                break;
            default:
        }
    }


    /**
     * 年月日时分的回调
     *
     * @param timePickerView
     * @param millseconds
     */
    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        setDateTime(millseconds);
    }

    /**
     * 设置刚进来时的时间
     */
    private void setDateTime(long dateTime) {
        String timeFromLong = DateTool.getTimeFromLong(DateTool.FMT_DATE_TIME, dateTime);
        faultTime=timeFromLong;
        timeBtn.setText(timeFromLong);
    }

    @Override
    public void feedBackResult(Object o) {
        ToastUtils.showShort("提交成功,我们会尽快解决");
        finish();

    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, long id);
    }

    public class InternalViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public CheckBox checkBox;

        public InternalViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.type_name);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }

    private class MultipleChoiceAdapter extends RecyclerView.Adapter<InternalViewHolder> {
        private final LayoutInflater layoutInflater;
        private List<TypeBean> data;
        private OnItemClickListener onItemClickListener;

        public MultipleChoiceAdapter(Context context, List<TypeBean> data) {
            layoutInflater = LayoutInflater.from(context);
            this.data = data;

        }

        public String getItem(int position) {
            return data.get(position).getMessage();
        }

        @Override
        public InternalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new InternalViewHolder(layoutInflater.inflate(R.layout.item_more_select, parent, false));
        }

        @Override
        public void onBindViewHolder(InternalViewHolder holder, final int position) {
            holder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position, getItemId(position));
                }
            });
            holder.checkBox.setChecked(data.get(position).isSelect());
            holder.textView.setText(getItem(position));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public void setOnItemClickListener(@NonNull OnItemClickListener listener) {
            onItemClickListener = listener;
        }

        public void setNewData(List<TypeBean> data) {
            this.data = data;
            notifyDataSetChanged();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        settingPresenter.onDestory();
    }

    String msg = "";

    @Subscribe(tags = @Tag(ConstantValue.EVENT_TYPE_NETWORK_RETURN))
    public void onReturnError(String message) {
        if (msg.equals(message)) {
        } else {
            msg = message;
            ToastUtils.showShort(message);

        }

    }
}
