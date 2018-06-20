package com.beidou.ui.act;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.adapter.BaseAdapter;
import com.beidou.base.BaseActivity;
import com.beidou.contact.RegOneContact;
import com.beidou.domain.AreaModel;
import com.beidou.domain.CountryModel;
import com.beidou.http.AppCof;
import com.beidou.http.Digests;
import com.beidou.presenter.RegOnePresent;
import com.beidou.ui.weiget.CustomPopWindow;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegOneAct extends BaseActivity<RegOneContact.RegOnepresenter> implements RegOneContact.RegOneView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_quhao)
    TextView tvQuhao;
    @BindView(R.id.root)
    LinearLayout root;
    private List<CountryModel.CountryMsgBean.CountryBean> countryMsgs = new ArrayList<>();
    private List<AreaModel.AreaMsgBean> areaMsgs = new ArrayList<>();
    private CustomPopWindow mCustomPopWindow;
    private RecyclerView recycler1;
    private RecyclerView recycler2;
    private BaseAdapter<CountryModel.CountryMsgBean.CountryBean> adapter2;
    private BaseAdapter<AreaModel.AreaMsgBean> adapter1;
    private int type;


    @Override
    protected void initView(Bundle savedInstanceState) {
        type = getIntent().getExtras().getInt("type");
        WindowManager wm1 = this.getWindowManager();
        int width = wm1.getDefaultDisplay().getWidth();
        int height = wm1.getDefaultDisplay().getHeight();
        ivBack.setVisibility(View.VISIBLE);
        if (type==0){
            tvTitle.setText("注册");
        }else {
            tvTitle.setText("忘记密码");
        }

        View contentView = LayoutInflater.from(context).inflate(R.layout.popu_quhao_seclct, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(context)
                .setView(contentView)
                .size(width, height / 2)
                .create();
        presenter.getAreaData(AppCof.ACCESS_KEY, Digests.getSign("getAreaMsg"));
        presenter.getCountryData("亚洲",AppCof.ACCESS_KEY, Digests.getSign("getCountryMsg"));
    }

    private void handleLogic(View contentView) {
        recycler1 = contentView.findViewById(R.id.recycler1);
        recycler2 = contentView.findViewById(R.id.recycler2);
        recycler1.setLayoutManager(new LinearLayoutManager(context));
        adapter1 = new BaseAdapter<>(R.layout.item_country, areaMsgs, new BaseAdapter.ConVert<AreaModel.AreaMsgBean>() {
            @Override
            public void convert(BaseViewHolder helper, AreaModel.AreaMsgBean s) {
                helper.setText(R.id.tv, s.getArea());
            }
        });

        adapter1.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recycler1.setAdapter(adapter1);
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.getCountryData(areaMsgs.get(position).getArea(),AppCof.ACCESS_KEY, Digests.getSign("getCountryMsg"));

            }
        });

        recycler2.setLayoutManager(new LinearLayoutManager(context));
        adapter2 = new BaseAdapter<>(R.layout.item_country, countryMsgs, new BaseAdapter.ConVert<CountryModel.CountryMsgBean.CountryBean>() {
            @Override
            public void convert(BaseViewHolder helper, CountryModel.CountryMsgBean.CountryBean s) {
                helper.setText(R.id.tv, s.getName());
            }
        });

        adapter2.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recycler2.setAdapter(adapter2);
        adapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                tvQuhao.setText(countryMsgs.get(position).getCode());
                mCustomPopWindow.dissmiss();
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.act_one_reg;
    }

    @Override
    public RegOneContact.RegOnepresenter initPresenter() {
        return new RegOnePresent(this);
    }


    @OnClick({R.id.iv_back, R.id.bt_next, R.id.iv_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_select:
                mCustomPopWindow.showAtLocation(root, Gravity.BOTTOM,0,0);
                break;
            case R.id.bt_next:
                String mobile = etPhone.getText().toString();
                if (TextUtils.isEmpty(mobile)) {
                    toast("请输入手机号");
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("mobile", mobile);

                if (type==0){
                    jumpToClazz(RegAct.class, bundle);
                }else {
                    jumpToClazz(ForgetPwdAct.class, bundle);
                }
                break;
        }
    }


    @Override
    public void setAreaData(AreaModel userModel) {
        if (userModel.getError() == 0) {
            areaMsgs.addAll(userModel.getAreaMsg());
            adapter1.notifyDataSetChanged();
        }

    }

    @Override
    public void setCountryData(CountryModel userModel) {
        if (userModel.getError() == 0) {
            countryMsgs.clear();
            countryMsgs.addAll(userModel.getCountryMsg().getCountry());
            adapter2.notifyDataSetChanged();
        }

    }


}
