package com.beidou.ui.frg;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beidou.R;
import com.beidou.base.BaseFragment;
import com.beidou.base.BasePresenter;
import com.beidou.domain.AddFriend;
import com.beidou.ui.act.AddFriendAct;
import com.beidou.ui.act.ChatActivity;
import com.beidou.ui.act.ContactAct;
import com.beidou.ui.act.JiHuoFriendAct;
import com.beidou.ui.act.NewFriendAct;
import com.beidou.ui.weiget.CustomPopWindow;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.contanier)
    LinearLayout contanier;
    @BindView(R.id.head)
    LinearLayout head;
    private EaseConversationListFragment easeConversationListFragment;
    private CustomPopWindow mCustomPopWindow;

    @Override
    protected void init() {
        tvTitle.setText(R.string.hy);
        rl.setVisibility(View.VISIBLE);
        iv1.setVisibility(View.VISIBLE);
        iv1.setImageResource(R.mipmap.jhicon);
        iv2.setVisibility(View.VISIBLE);
        iv2.setImageResource(R.mipmap.leibicon);
        iv3.setVisibility(View.VISIBLE);
        iv3.setImageResource(R.mipmap.sousou);
        easeConversationListFragment=new EaseConversationListFragment();
        easeConversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent=new Intent(mContext,ChatActivity.class);
                //传入参数
                Bundle args=new Bundle();
                args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                args.putString(EaseConstant.EXTRA_USER_ID,conversation.conversationId());
                intent.putExtra("conversation",args);
                startActivity(intent);
            }
        });


        getChildFragmentManager().beginTransaction().add(R.id.contanier,easeConversationListFragment).commit();

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popu_add_fredent,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(contentView)
                .size(400,450)

                .create();
    }
    EMMessageListener messageListener=new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //接收到新的消息
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    private void refreshUIWithMessage() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                // refresh conversation list
                if (easeConversationListFragment != null) {
                    easeConversationListFragment.refresh();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
    }
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_home, null);
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



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv1:
                mCustomPopWindow.showAsDropDown(head,0,0,Gravity.RIGHT);
                break;
            case R.id.iv2:
                jumpToActivity(ContactAct.class);
                break;
            case R.id.iv3:
                jumpToActivity(AddFriendAct.class);
                break;
        }
    }

    private void handleLogic(View contentView) {
        contentView.findViewById(R.id.ll_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                jumpToActivity(AddFriendAct.class);
            }
        });
        contentView.findViewById(R.id.ll_jh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                jumpToActivity(JiHuoFriendAct.class);
            }
        });;
        contentView.findViewById(R.id.ll_ss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });;
    }


}
