package com.beidou.ui.act;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.base.BaseActivity;
import com.beidou.base.BasePresenter;
import com.beidou.http.AppCof;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lewis on 2018/6/14.
 */

public class ComWebAct extends BaseActivity {
//    @BindView(R.id.iv_back)
//    ImageView ivBack;
//    @BindView(R.id.tv_title)
//    TextView tvTitle;
    @BindView(R.id.container)
    LinearLayout container;
    private AgentWeb mAgentWeb;

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) container, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);

    }

    @Override
    protected int initLayout() {
        return R.layout.act_comweb;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
