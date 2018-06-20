package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.AddFriend;
import com.beidou.domain.AddFriendBean;
import com.beidou.domain.NewFriendBean;
import com.beidou.domain.UserBean;

public interface NewFriendContact {
   interface NewFriendView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setGetFriendData(NewFriendBean userModel);
         void  setAddFriendDara(AddFriendBean bean);
    }

     interface NewFriendpresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getNewFriend(String ownerMobie,String accessKey, String sign);
        void addFriend(String ownerMobie,String friendMobie,String isAgree,String accessKey, String sign);

    }
}