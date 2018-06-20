package com.beidou.ui.act;

import android.os.Bundle;
import android.os.Handler;

import com.beidou.MainActivity;
import com.beidou.R;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;

/**
 * Created by lewis on 2018/6/19.
 */

public class LauchAct extends BaseActivity {

    private String islogin;

    @Override
    protected void initView(Bundle savedInstanceState) {
        islogin = aCache.getAsString("islogin");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("true".equals(islogin)){
                    jumpToClazz(MainActivity.class);
                    finish();
                }else {
                    jumpToClazz(OneAct.class);
                    finish();
                }
            }
        },2000);

    }

    @Override
    protected int initLayout() {
        return R.layout.act_lauch;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
