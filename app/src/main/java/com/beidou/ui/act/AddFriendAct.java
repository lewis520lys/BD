package com.beidou.ui.act;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;
import com.beidou.contact.AddFriendContact;
import com.beidou.domain.AddFriend;
import com.beidou.domain.GetUserBean;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.presenter.AddFriendPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Created by lewis on 2018/6/7.
 */

public class AddFriendAct extends BaseActivity<AddFriendContact.AddFriendpresenter> implements AddFriendContact.AddFriendView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;  @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.ll)
    LinearLayout ll;
    private String mobie;
    private String friendMobie;

    @Override
    protected void initView(Bundle savedInstanceState) {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("加好友");
        mobie = aCache.getAsString("mobie");
    }

    @Override
    protected int initLayout() {
        return R.layout.act_addfriend;
    }

    @Override
    public AddFriendContact.AddFriendpresenter initPresenter() {
        return new AddFriendPresent(this);
    }


    @OnClick({R.id.iv_back, R.id.bt_add, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_add:

                presenter.sendFriend(mobie,friendMobie,AppCof.ACCESS_KEY, Digests.getSign("sendAddFriendInvitation"));
                break;
                case R.id.iv_search:
                    String mobie = et_phone.getText().toString();
                    if (TextUtils.isEmpty(mobie)){
                        toast("请输入对方手机号");
                        return;
                    }
                    presenter.getUser(mobie, AppCof.ACCESS_KEY, Digests.getSign("getUser"));
                break;
        }
    }





    @Override
    public void setUserData(GetUserBean userModel) {
        if (userModel.getError()==0&&userModel.getRet().getCount()>0){
            ll.setVisibility(View.VISIBLE);
            tvName.setText(userModel.getRet().getEntities().get(0).getUsername());
            friendMobie=userModel.getRet().getEntities().get(0).getUsername();
        }else {
            toast("账号不存在");
        }
    }

    @Override
    public void setAddData(AddFriend bean) {
        if (bean.getError()==0){
            toast("已发送好友请求");
        }else {
            toast(bean.getReason());
        }
    }
}