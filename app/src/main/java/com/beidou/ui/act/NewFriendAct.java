package com.beidou.ui.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.adapter.BaseAdapter;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;
import com.beidou.contact.NewFriendContact;
import com.beidou.domain.AddFriendBean;
import com.beidou.domain.NewFriendBean;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.presenter.NewFriendPresent;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContactManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lewis on 2018/6/8.
 */

public class NewFriendAct extends BaseActivity<NewFriendContact.NewFriendpresenter> implements NewFriendContact.NewFriendView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private BaseAdapter<NewFriendBean.ListBean> adapter;
    private List<NewFriendBean.ListBean> list=new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {

        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("新的朋友");
        recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BaseAdapter<>(R.layout.item_hy, list, new BaseAdapter.ConVert<NewFriendBean.ListBean>() {
            @Override
            public void convert(BaseViewHolder helper, final NewFriendBean.ListBean s) {
                helper.setText(R.id.tv_name,s.getOwnerMobie());
                String status = s.getStatus();
                TextView view = helper.getView(R.id.tv_status);
                view.setVisibility(View.VISIBLE);
                if (status.equals("0")){
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                  presenter.addFriend(aCache.getAsString("mobie"),s.getOwnerMobie(),"true",AppCof.ACCESS_KEY, Digests.getSign("addFriend"));
                            }
                        });
                }else {
                      view.setText("已添加");
                      view.setTextColor(getResources().getColor(R.color.black66));
                      view.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });

        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN );
        recycler.setAdapter(adapter);
        presenter.getNewFriend(aCache.getAsString("mobie"), AppCof.ACCESS_KEY,Digests.getSign("getNewFriendList"));
    }

    @Override
    protected int initLayout() {
        return R.layout.act_new_friend;
    }

    @Override
    public NewFriendContact.NewFriendpresenter initPresenter() {
        return new NewFriendPresent(this);
    }



    @Override
    public void setGetFriendData(NewFriendBean userModel) {
        if (userModel.getError()==0){
            list.clear();
            list.addAll(userModel.getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setAddFriendDara(AddFriendBean bean) {
          if (bean.getError()==0){
              toast("已通过");
              presenter.getNewFriend(aCache.getAsString("mobie"), AppCof.ACCESS_KEY,Digests.getSign("getNewFriendList"));
          }else {
              toast(bean.reason);
          }

    }
}
