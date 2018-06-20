package com.beidou.presenter;

import com.beidou.base.BasePresenterImpl;
import com.beidou.contact.RegOneContact;
import com.beidou.domain.AreaModel;
import com.beidou.domain.CountryModel;
import com.beidou.domain.UserBean;
import com.beidou.http.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RegOnePresent extends BasePresenterImpl<RegOneContact.RegOneView> implements RegOneContact.RegOnepresenter{
    public RegOnePresent(RegOneContact.RegOneView view) {
        super(view);
    }




    @Override
    public void getAreaData(String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();

        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .getArea(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AreaModel>() {
                    @Override
                    public void accept(@NonNull AreaModel userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setAreaData(userModel);
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
    public void getCountryData(String area,String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("area",area);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .getCountry(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CountryModel>() {
                    @Override
                    public void accept(@NonNull CountryModel userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setCountryData(userModel);
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
