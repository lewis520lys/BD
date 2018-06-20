package com.beidou.presenter;

import com.beidou.base.BasePresenterImpl;
import com.beidou.contact.NewFriendContact;
import com.beidou.domain.AddFriendBean;
import com.beidou.domain.NewFriendBean;
import com.beidou.domain.UserBean;
import com.beidou.http.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class NewFriendPresent extends BasePresenterImpl<NewFriendContact.NewFriendView> implements NewFriendContact.NewFriendpresenter{
    public NewFriendPresent(NewFriendContact.NewFriendView view) {
        super(view);
    }



    @Override
    public void getNewFriend(String ownerMobie, String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("ownerMobie",ownerMobie);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .getNewFriendList(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewFriendBean>() {
                    @Override
                    public void accept(@NonNull NewFriendBean userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setGetFriendData(userModel);
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
    public void addFriend(String ownerMobie, String friendMobie, String isAgree, String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("ownerMobie",ownerMobie);
        map.put("friendMobie",friendMobie);
        map.put("isAgree",isAgree);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .addFriend(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddFriendBean>() {
                    @Override
                    public void accept(@NonNull AddFriendBean userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setAddFriendDara(userModel);
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
