package com.beidou.presenter;

import com.beidou.base.BasePresenterImpl;
import com.beidou.contact.ContactContact;
import com.beidou.contact.LoginContact;
import com.beidou.domain.FriendBean;
import com.beidou.domain.UserBean;
import com.beidou.http.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ContactPresent extends BasePresenterImpl<ContactContact.ContactView> implements ContactContact.Contactpresenter{
    public ContactPresent(ContactContact.ContactView view) {
        super(view);
    }




    @Override
    public void getFriends(String ownerMobie, String accessKey, String sign) {
        Map<String,String> map=new HashMap<>();
        map.put("ownerMobie",ownerMobie);
        map.put("accessKey",accessKey);
        map.put("sign",sign);
        RetrofitFactory.getInstance()
                .getFriends(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);//请求加入管理
                        view.showLoadingDialog("");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendBean>() {
                    @Override
                    public void accept(@NonNull FriendBean userModel) throws Exception {
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
