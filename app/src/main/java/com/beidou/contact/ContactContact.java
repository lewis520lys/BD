package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.FriendBean;
import com.beidou.domain.UserBean;

public interface ContactContact {
   interface ContactView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setData(FriendBean userModel);

    }

     interface Contactpresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getFriends(String ownerMobie,String accessKey, String sign);

    }
}