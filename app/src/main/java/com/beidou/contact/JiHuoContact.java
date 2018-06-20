package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.JiHuoBean;
import com.beidou.domain.ResultBean;

public interface JiHuoContact {
   interface JiHuoView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setData(JiHuoBean userModel);
         void setYzm(ResultBean bean);
    }

     interface JiHuopresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void JiHuo(String ownerMobie, String othersMobie, String code, String accessKey, String sign);
        void getSmsCode(String mobie, String accessKey, String sign);
    }
}