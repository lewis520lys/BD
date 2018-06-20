package com.beidou.presenter;

import com.beidou.base.BasePresenterImpl;
import com.beidou.contact.AddFriendContact;
import com.beidou.contact.LoginContact;
import com.beidou.domain.AddFriend;
import com.beidou.domain.GetUserBean;
import com.beidou.domain.UserBean;
import com.beidou.http.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class AddFriendPresent extends BasePresenterImpl<AddFriendContact.AddFriendView> implements AddFriendContact.AddFriendpresenter{
    public AddFriendPresent(AddFriendContact.AddFriendView view) {
        super(view);
    }




    @Override
    public void getUser(String mobie, String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("mobie",mobie);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .getUser(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetUserBean>() {
                    @Override
                    public void accept(@NonNull GetUserBean userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setUserData(userModel);
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
    public void sendFriend(String ownerMobie, String friendMobie, String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("ownerMobie",ownerMobie);
        map.put("friendMobie",friendMobie);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .senFriend(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddFriend>() {
                    @Override
                    public void accept(@NonNull AddFriend userModel) throws Exception {
                        view.dismissLoadingDialog();
                        view.setAddData(userModel);
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
