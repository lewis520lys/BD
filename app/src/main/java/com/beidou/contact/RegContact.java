package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.ResultBean;
import com.beidou.domain.RegBean;

public interface RegContact {
   interface RegView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setData(RegBean userModel);
         void setYzm(ResultBean bean);
    }

     interface Regpresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void register(String mobie, String password, String code, String accessKey,String sign);
        void getSmsCode(String mobie,String accessKey,String sign);
    }
}