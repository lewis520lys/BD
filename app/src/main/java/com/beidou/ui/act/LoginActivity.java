package com.beidou.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beidou.MainActivity;
import com.beidou.Manifest;
import com.beidou.R;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;
import com.beidou.contact.LoginContact;
import com.beidou.domain.UserBean;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.huanxin.utils.APPConfig;
import com.beidou.huanxin.utils.SharedPreferencesUtils;
import com.beidou.presenter.LoginPresent;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContact.Loginpresenter> implements LoginContact.LoginView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    private Handler mHandler;
    private String mobie;
    private String pwd;

    @Override
    protected void initView(Bundle savedInstanceState) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("登录");


    }

    @Override
    protected int initLayout() {
        return R.layout.act_login;
    }

    @Override
    public LoginContact.Loginpresenter initPresenter() {
        return new LoginPresent(this);
    }




    @OnClick({R.id.iv_back, R.id.tv_reg, R.id.forget_pwd, R.id.bt_login})
    public void onViewClicked(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_reg:

                bundle.putInt("type",0);
                jumpToClazz(RegOneAct.class,bundle);
                break;
            case R.id.forget_pwd:
                bundle.putInt("type",1);
                jumpToClazz(RegOneAct.class,bundle);
                break;
            case R.id.bt_login:

                mobie = etPhone.getText().toString();
                pwd = etPwd.getText().toString();
                presenter.login(mobie, pwd, AppCof.ACCESS_KEY, Digests.getSign("login"));


                break;
        }
    }

    @Override
    public void setData(UserBean userModel) {
        if (userModel.getError()==0){
            toast("登录成功");


            aCache.put("mobie",userModel.getLogName());
            aCache.put("hxpwd",userModel.getPassword());
            //aCache.put("userId",userModel.getUserId());
            aCache.put("address",userModel.getAddress());
            aCache.put("islogin","true");
            jumpToClazz(MainActivity.class);
        }else {
            toast(userModel.getReason());
        }
    }
}
