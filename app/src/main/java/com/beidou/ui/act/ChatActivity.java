package com.beidou.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.beidou.R;
import com.beidou.ui.frg.MyChatFragment;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;

public class ChatActivity extends AppCompatActivity {

    private MyChatFragment myChatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //注册一个监听连接状态的listener
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("conversation");
//        bundle.getString(EaseConstant.EXTRA_USER_ID);
//        chatFragment=new EaseChatFragment();
//        chatFragment.setArguments(bundle);
        myChatFragment=new MyChatFragment();
        myChatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_chat_content, myChatFragment).commit();
    }
}
