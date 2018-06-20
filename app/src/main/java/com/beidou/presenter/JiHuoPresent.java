package com.beidou.presenter;

import com.beidou.base.BasePresenterImpl;
import com.beidou.contact.JiHuoContact;
import com.beidou.domain.JiHuoBean;
import com.beidou.domain.ResultBean;
import com.beidou.http.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class JiHuoPresent extends BasePresenterImpl<JiHuoContact.JiHuoView> implements JiHuoContact.JiHuopresenter{
    public JiHuoPresent(JiHuoContact.JiHuoView view) {
        super(view);
    }

    @Override
    public void JiHuo(String ownerMobie, String othersMobie, String code, String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("ownerMobie",ownerMobie);
        map.put("othersMobie",othersMobie);
        map.put("code",code);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .jihuo(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JiHuoBean>() {
                    @Override
                    public void accept(@NonNull JiHuoBean userModel) throws Exception {
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

    @Override
    public void getSmsCode(String mobie, String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("mobie",mobie);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
                RetrofitFactory.getInstance()
                .getCode(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultBean>() {
                    @Override
                    public void accept(@NonNull ResultBean userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setYzm(userModel);
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
