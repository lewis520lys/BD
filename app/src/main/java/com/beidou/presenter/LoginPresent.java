package com.beidou.presenter;

import com.beidou.base.BasePresenterImpl;
import com.beidou.contact.LoginContact;
import com.beidou.domain.RegBean;
import com.beidou.domain.UserBean;
import com.beidou.http.RetrofitFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class LoginPresent extends BasePresenterImpl<LoginContact.LoginView> implements LoginContact.Loginpresenter{
    public LoginPresent(LoginContact.LoginView view) {
        super(view);
    }


    @Override
    public void login(String mobie, String password, String accessKey, String sign) {


        Map<String,String> map=new HashMap<>();
        map.put("mobie",mobie);
        map.put("password",password);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .login(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(@NonNull UserBean userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setData(userModel);
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
