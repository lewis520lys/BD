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
import com.beidou.contact.RegContact;
import com.beidou.domain.ResultBean;
import com.beidou.domain.RegBean;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.presenter.RegPresent;

import butterknife.BindView;
import butterknife.OnClick;

public class RegAct extends BaseActivity<RegContact.Regpresenter> implements RegContact.RegView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.tv_yzm)
    TextView tvYzm;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    private String mobile;
    private TimeCount time;

    @Override
    protected void initView(Bundle savedInstanceState) {
        time = new TimeCount(60000, 1000);
        Bundle bundle = getIntent().getExtras();
        mobile = bundle.getString("mobile");
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("注册");
    }

    @Override
    protected int initLayout() {
        return R.layout.act_reg;
    }

    @Override
    public RegContact.Regpresenter initPresenter() {
        return new RegPresent(this);
    }

    @Override
    public void setData(RegBean userModel) {
        if (userModel.getError()==0){
            toast("注册成功");

            jumpToClazz(LoginActivity.class);
        }else {
            toast(userModel.getReason());
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



    @OnClick({R.id.iv_back, R.id.tv_yzm, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_yzm:
                presenter.getSmsCode(mobile, AppCof.ACCESS_KEY, Digests.getSign("validateCode"));
                time.start();
                break;
            case R.id.bt_login:
                String code = etYzm.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(code)){
                    toast("请输入验证码");
                    return;
                }if (TextUtils.isEmpty(pwd)){
                    toast("请输入密码");
                    return;
                }
                presenter.register(mobile,pwd,code,AppCof.ACCESS_KEY,Digests.getSign("register"));
                break;
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
