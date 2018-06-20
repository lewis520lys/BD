package com.beidou.ui.act;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;
import com.beidou.contact.UserInfoContact;
import com.beidou.domain.UpdateNickBean;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.presenter.UserInfoPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lewis on 2018/6/14.
 */

public class UserInfoAct extends BaseActivity<UserInfoContact.UserInfopresenter> implements UserInfoContact.UserInfoView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_mobie)
    TextView tvMobie;
    private String mobie;

    @Override
    protected void initView(Bundle savedInstanceState) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("个人信息");
        mobie = aCache.getAsString("mobie");
    }

    @Override
    protected int initLayout() {
        return R.layout.act_userinfo;
    }

    @Override
    public UserInfoContact.UserInfopresenter initPresenter() {
        return new UserInfoPresent(this);
    }



    @OnClick({R.id.rl_head, R.id.tv_nick, R.id.ll_erweima, R.id.rl_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_head:
                break;
            case R.id.tv_nick:
                View inflate = View.inflate(context, R.layout.dialog_upnick, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(inflate);
                final AlertDialog dialog = builder.create();
                final EditText etnick = inflate.findViewById(R.id.et_nick);
                inflate.findViewById(R.id.bt_comit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = etnick.getText().toString();
                        if (TextUtils.isEmpty(s)){
                            toast("请输入昵称");
                            return;
                        }
                        presenter.updateUserNickname(mobie,s, AppCof.ACCESS_KEY, Digests.getSign("updateUserNickname"));
                        dialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;
            case R.id.ll_erweima:
                break;
            case R.id.rl_location:
                break;
        }
    }

    @Override
    public void setNickData(UpdateNickBean bean) {
        if (bean.getError()==0){
            toast("修改成功");
        }else {
            toast(bean.reason);
        }
    }
}
