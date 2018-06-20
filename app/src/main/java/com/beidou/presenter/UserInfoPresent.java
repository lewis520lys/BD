package com.beidou.presenter;

import com.beidou.base.BasePresenterImpl;
import com.beidou.contact.UserInfoContact;
import com.beidou.domain.UpdateNickBean;
import com.beidou.http.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class UserInfoPresent extends BasePresenterImpl<UserInfoContact.UserInfoView> implements UserInfoContact.UserInfopresenter{
    public UserInfoPresent(UserInfoContact.UserInfoView view) {
        super(view);
    }




    @Override
    public void updateUserNickname(String mobie, String nickName, String accessKey, String sign) {

        Map<String,String> map=new HashMap<>();
        map.put("mobie",mobie);
        map.put("nickName",nickName);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .updateNickName(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdateNickBean>() {
                    @Override
                    public void accept(@NonNull UpdateNickBean userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setNickData(userModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        view.dismissLoadingDialog();
//                        ExceptionHelper.handleException(throwable);

                    }
                });
    }
}
