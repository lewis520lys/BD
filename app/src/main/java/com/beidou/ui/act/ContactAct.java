package com.beidou.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.adapter.BaseAdapter;
import com.beidou.base.BaseActivity;
import com.beidou.contact.ContactContact;
import com.beidou.domain.FriendBean;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.presenter.ContactPresent;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.easeui.EaseConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lewis on 2018/6/8.
 */

public class ContactAct extends BaseActivity<ContactContact.Contactpresenter> implements ContactContact.ContactView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private BaseAdapter<String> adapter;
    private List<String> list = new ArrayList<>();
    private String mobie;

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvTitle.setText("联系人");
        ivBack.setVisibility(View.VISIBLE);
        mobie = aCache.getAsString("mobie");
        recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BaseAdapter<>(R.layout.item_hy, list, new BaseAdapter.ConVert<String>() {
            @Override
            public void convert(BaseViewHolder helper, String s) {
                helper.setText(R.id.tv_name, s);
            }
        });

        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, ChatActivity.class);
                //传入参数
                Bundle args = new Bundle();
                args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                args.putString(EaseConstant.EXTRA_USER_ID, list.get(position));
                intent.putExtra("conversation", args);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getFriends(mobie, AppCof.ACCESS_KEY, Digests.getSign("getFriends"));
    }

    @Override
    protected int initLayout() {
        return R.layout.act_contact;
    }

    @Override
    public ContactContact.Contactpresenter initPresenter() {
        return new ContactPresent(this);
    }

    @Override
    public void setData(FriendBean userModel) {
        if (userModel.getError() == 0) {
            if (userModel.getRet().getData() != null && userModel.getRet().getData().size() > 0) {
                list.clear();
                list.addAll(userModel.getRet().getData());
                adapter.notifyDataSetChanged();
            }
        } else {
            toast(userModel.getReason());
        }
    }






    @OnClick({R.id.ll_newriend, R.id.ll_kf,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_newriend:
                jumpToClazz(NewFriendAct.class);
                break;
            case R.id.ll_kf:

                break;
                case R.id.iv_back:
                    finish();
                break;
        }
    }
}
