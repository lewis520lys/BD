package com.beidou.http;




import com.beidou.domain.AddFriend;
import com.beidou.domain.AddFriendBean;
import com.beidou.domain.AreaModel;
import com.beidou.domain.CountryModel;
import com.beidou.domain.ForgetPwdBean;
import com.beidou.domain.FriendBean;
import com.beidou.domain.GetUserBean;
import com.beidou.domain.JiHuoBean;
import com.beidou.domain.NewFriendBean;
import com.beidou.domain.ResultBean;
import com.beidou.domain.RegBean;
import com.beidou.domain.UpdateNickBean;
import com.beidou.domain.UserBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * 测试接口service-post相关
 * Created by WZG on 2016/12/19.
 */

public interface RetrofitService {

    @FormUrlEncoded
    @POST("register")
    Observable<RegBean> register(@FieldMap Map<String, String> fields);


    @FormUrlEncoded
    @POST("validateCode")
    Observable<ResultBean> getCode(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("getAreaMsg")
    Observable<AreaModel> getArea(@FieldMap Map<String, String> fields);
    @FormUrlEncoded
    @POST("getCountryMsg")
    Observable<CountryModel> getCountry(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("login")
    Observable<UserBean> login(@FieldMap Map<String, String> fields);


    @FormUrlEncoded
    @POST("getUser")
    Observable<GetUserBean> getUser(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("sendAddFriendInvitation")
    Observable<AddFriend> senFriend(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("addFriend")
    Observable<AddFriendBean> addFriend(@FieldMap Map<String, String> fields);
    @FormUrlEncoded
    @POST("getNewFriendList")
    Observable<NewFriendBean> getNewFriendList(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("getFriends")
    Observable<FriendBean> getFriends(@FieldMap Map<String, String> fields);
    @FormUrlEncoded
    @POST("resetPassword")
    Observable<ForgetPwdBean> forgetPwd(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("activateUser")
    Observable<JiHuoBean> jihuo(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("updateUserNickname")
    Observable<UpdateNickBean> updateNickName(@FieldMap Map<String, String> map);
}
