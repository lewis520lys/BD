package com.beidou.contact;


import com.beidou.base.BasePresenter;
import com.beidou.base.BaseView;
import com.beidou.domain.AreaModel;
import com.beidou.domain.CountryModel;
import com.beidou.domain.RegBean;
import com.beidou.domain.ResultBean;

public interface RegOneContact {
   interface RegOneView extends BaseView {
        /**
         * 设置数据
         *
         * @param
         */
         void setAreaData(AreaModel userModel);
         void setCountryData(CountryModel userModel);

    }

     interface RegOnepresenter extends BasePresenter {
        /**
         * 获取数据
         */

        void getAreaData(String accessKey, String sign);
        void getCountryData(String area,String accessKey, String sign);
    }
}