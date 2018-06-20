package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.UserBean;

public interface LoginContact {
   interface LoginView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setData(UserBean userModel);

    }

     interface Loginpresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void login(String mobie, String password, String accessKey, String sign);

    }
}