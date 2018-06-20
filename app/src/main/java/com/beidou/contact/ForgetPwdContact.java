package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;

import com.beidou.domain.ForgetPwdBean;
import com.beidou.domain.ResultBean;

public interface ForgetPwdContact {
   interface ForgetPwdView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setData(ForgetPwdBean userModel);
         void setYzm(ResultBean bean);
    }

     interface ForgetPwdpresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void forgetPwd(String mobie, String password, String code, String accessKey, String sign);
        void getSmsCode(String mobie, String accessKey, String sign);
    }
}