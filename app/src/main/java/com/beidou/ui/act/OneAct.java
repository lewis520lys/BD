package com.beidou.ui.act;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import com.beidou.MainActivity;
import com.beidou.R;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;
import com.beidou.utils.permission.DefaultRationale;
import com.beidou.utils.permission.PermissionSetting;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

/**
 * Created by lewis on 2018/6/7.
 */

public class OneAct extends BaseActivity {
    private Rationale mRationale;
    private PermissionSetting mSetting;
    private String[] permissios={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void initView(Bundle savedInstanceState) {
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);

        requestPermission(permissios);

    }
    private void requestPermission(String[]... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        toast(R.string.successfully);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        toast(R.string.failure);
                        if (AndPermission.hasAlwaysDeniedPermission(OneAct.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                })
                .start();
    }
    protected void toast(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected int initLayout() {
        return R.layout.act_one;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @OnClick({R.id.bt_login, R.id.bt_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                jumpToClazz(LoginActivity.class);
                break;
            case R.id.bt_reg:
                Bundle bundle=new Bundle();
                bundle.putInt("type",0);
                jumpToClazz(RegOneAct.class,bundle);
                break;
        }
    }
}
