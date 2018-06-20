package com.beidou.ui.act;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;
import com.beidou.contact.JiHuoContact;
import com.beidou.domain.JiHuoBean;
import com.beidou.domain.ResultBean;
import com.beidou.http.AppCof;
import com.beidou.presenter.JiHuoPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lewis on 2018/6/8.
 */

public class JiHuoFriendAct extends BaseActivity<JiHuoContact.JiHuopresenter> implements JiHuoContact.JiHuoView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yzm)
    TextView tvYzm;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    private TimeCount time;
    private String onwmobie;

    @Override
    protected void initView(Bundle savedInstanceState) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("好友激活");
        onwmobie = aCache.getAsString("mobie");
        time = new TimeCount(60000, 1000);
    }

    @Override
    protected int initLayout() {
        return R.layout.act_jijuo;
    }

    @Override
    public JiHuoContact.JiHuopresenter initPresenter() {
        return new JiHuoPresent(this);
    }



    @OnClick({R.id.iv_back, R.id.bt_comit,R.id.tv_yzm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_comit:
                String mobie = etPhone.getText().toString();

                String yzm = etCode.getText().toString();
                if (TextUtils.isEmpty(mobie)){
                    toast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(yzm)){
                    toast("请输入验证码");
                    return;
                }
                presenter.JiHuo(onwmobie,mobie,yzm, AppCof.ACCESS_KEY,"activateUser");
                break;
                case R.id.tv_yzm:
                    String mobie1= etPhone.getText().toString();
                    if (TextUtils.isEmpty(mobie1)){
                        toast("请输入手机号");
                        return;
                    }
                    presenter.getSmsCode(mobie1, AppCof.ACCESS_KEY,"validateCode");
                    time.start();
                break;
        }
    }

    @Override
    public void setData(JiHuoBean userModel) {
        if (userModel.error==0){toast("激活成功");}else {
            toast(userModel.reason);
        }
    }

    @Override
    public void setYzm(ResultBean bean) {
        if (bean.error==0){
            toast("发送成功");
        }else {
            toast(bean.reason);
        }
    }

    public class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            tvYzm.setText("获取验证码");
            tvYzm.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            tvYzm.setClickable(false);//防止重复点击
            tvYzm.setText(millisUntilFinished / 1000 + "s");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        time.cancel();
    }
}
