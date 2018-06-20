package com.beidou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beidou.R;
import com.taobao.library.BaseBannerAdapter;
import com.taobao.library.VerticalBannerView;

import java.util.List;

public class SampleAdapter extends BaseBannerAdapter<String> {

    private List<String> mDatas;

    public SampleAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tg,null);
    }

    @Override
    public void setItem(final View view, final String data) {
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(data);
        //你可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比如打开url
                Toast.makeText(view.getContext(),data,Toast.LENGTH_SHORT).show();
            }
        });
    }
}