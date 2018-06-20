package com.beidou.ui.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.base.BaseApplication;
import com.beidou.base.BaseFragment;
import com.beidou.base.BasePresenter;
import com.beidou.http.AppCof;
import com.beidou.huanxin.utils.SharedPreferencesUtils;
import com.beidou.ui.act.ComWebAct;
import com.beidou.ui.act.LoginActivity;
import com.beidou.ui.act.UserInfoAct;
import com.beidou.ui.act.XiTongAct;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_phone)
    TextView tvPhone;



    @Override
    protected void init() {
        tvTitle.setText("我的");
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_my, null);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }


    @OnClick({R.id.iv_head, R.id.iv_erweima, R.id.ll_qb, R.id.ll_wz, R.id.ll_store, R.id.ll_sc, R.id.ll_aq, R.id.ll_xt,R.id.ll_yqhy, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                jumpToActivity(UserInfoAct.class);
                break;
            case R.id.iv_erweima:
                break;
            case R.id.ll_qb:
                Bundle bundle = new Bundle();
                bundle.putString("url", AppCof.QIANBAO + aCache.getAsString("address"));
                jumpToActivity(ComWebAct.class, bundle);
                break;
            case R.id.ll_wz:
                break;
            case R.id.ll_sc:
                break;
            case R.id.ll_store:
                break;

            case R.id.ll_aq:
                break;
                case R.id.ll_yqhy:
                break;
                case R.id.bt_login:
                    BaseApplication.getInstance().finishAllActivities();
                    aCache.clear();

                    jumpToActivity(LoginActivity.class);
                break;
            case R.id.ll_xt:
                jumpToActivity(XiTongAct.class);
                break;
        }
    }



}
