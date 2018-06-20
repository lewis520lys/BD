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
import com.beidou.contact.ForgetPwdContact;
import com.beidou.domain.ForgetPwdBean;
import com.beidou.domain.ResultBean;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.presenter.ForgetPwdPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPwdAct extends BaseActivity<ForgetPwdContact.ForgetPwdpresenter> implements ForgetPwdContact.ForgetPwdView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.tv_yzm)
    TextView tvYzm;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_two_pwd)
    EditText etTwoPwd;
    private TimeCount time;
    private String mobie;

    @Override
    protected void initView(Bundle savedInstanceState) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("忘记密码");
        time = new TimeCount(60000, 1000);
        Bundle bundle = getIntent().getExtras();
        mobie=bundle.getString("mobile");
    }

    @Override
    protected int initLayout() {
        return R.layout.act_forget_pwd;
    }

    @Override
    public ForgetPwdContact.ForgetPwdpresenter initPresenter() {
        return new ForgetPwdPresent(this);
    }



    @OnClick({R.id.iv_back, R.id.tv_yzm, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_yzm:

                presenter.getSmsCode(mobie, AppCof.ACCESS_KEY, Digests.getSign("validateCode"));
                time.start();
                break;
            case R.id.bt_login:

                String pwd = etPwd.getText().toString();
                String twppwd = etTwoPwd.getText().toString();
                String yzm = etYzm.getText().toString();

                if (TextUtils.isEmpty(yzm)){
                    toast("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(pwd)){
                toast("请输入密码");
                return;
            }
                if (TextUtils.isEmpty(twppwd)){
                    toast("请再次输入密码");
                    return;
                }
                if (!pwd.equals(twppwd)){
                    toast("两次密码不一致");
                    return;
                }
                presenter.forgetPwd(mobie,pwd,yzm,AppCof.ACCESS_KEY,Digests.getSign("resetPassword"));
                break;
        }
    }

    @Override
    public void setData(ForgetPwdBean userModel) {
        if (userModel.getError()==0){
            toast("修改成功");
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
