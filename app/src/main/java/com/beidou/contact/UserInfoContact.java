package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.UpdateNickBean;

public interface UserInfoContact {
   interface UserInfoView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
      void setNickData(UpdateNickBean bean);

    }

     interface UserInfopresenter extends BasePresenter {
        /**
         * 获取数据
         */
        void updateUserNickname(String mobie, String nickName, String accessKey, String sign);

    }
}