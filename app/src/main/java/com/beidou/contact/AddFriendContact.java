package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.AddFriend;
import com.beidou.domain.GetUserBean;
import com.beidou.domain.UserBean;

public interface AddFriendContact {
   interface AddFriendView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setUserData(GetUserBean userModel);
         void setAddData(AddFriend bean);

    }

     interface AddFriendpresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getUser(String mobie,String accessKey, String sign);
        void sendFriend(String ownerMobie,String friendMobie,String accessKey, String sign);

    }
}