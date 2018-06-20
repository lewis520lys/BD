package com.beidou.ui.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beidou.R;
import com.beidou.base.BaseFragment;
import com.beidou.base.BasePresenter;
import com.beidou.http.AppCof;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StorehouseFragment extends BaseFragment {
    @BindView(R.id.root)
    LinearLayout root;
    public AgentWeb mAgentWeb;


    @Override
    protected void init() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) root, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(AppCof.WABAO+aCache.getAsString("address"));

    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_storehourse, null);
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
//    o
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
