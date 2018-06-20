package com.beidou;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import com.beidou.base.BaseActivity;
import com.beidou.base.BaseFragment;
import com.beidou.base.BasePresenter;
import com.beidou.huanxin.utils.APPConfig;
import com.beidou.huanxin.utils.SharedPreferencesUtils;
import com.beidou.ui.act.LoginActivity;
import com.beidou.ui.frg.AdvisoryFragment;
import com.beidou.ui.frg.HomeFragment;
import com.beidou.ui.frg.MyFragment;
import com.beidou.ui.frg.StorehouseFragment;
import com.beidou.utils.permission.DefaultRationale;
import com.beidou.utils.permission.PermissionSetting;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    private HomeFragment homeFragment;
    private StorehouseFragment storehouseFragment;
    private AdvisoryFragment advisoryFragment;
    private MyFragment myFragment;
    private FragmentManager fManager;
    private BaseFragment currentFragment;
    private Handler mHandler;
    private String mobie;
    private String pwd;

    @Override
    protected void initView(Bundle savedInstanceState) {

//        // 判断sdk是否登录成功过，并没有退出和被踢，否则跳转到登陆界面
//        if (!EMClient.getInstance().isLoggedInBefore()) {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }
        fManager =getSupportFragmentManager();
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) fManager.findFragmentByTag("homeFragment");
            storehouseFragment = (StorehouseFragment) fManager.findFragmentByTag("storehouseFragment");
            advisoryFragment = (AdvisoryFragment) fManager.findFragmentByTag("advisoryFragment");
            myFragment = (MyFragment) fManager.findFragmentByTag("myFragment");
        }
        InitNavigationBar();
        homeFragment=new HomeFragment();
        showFragment(homeFragment,"homeFragment");
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {

                    case 2:
                        //Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show();
                        SharedPreferencesUtils.setParam(context, APPConfig.USER_HEAD_IMG, "http://img6.itiexue.net/1314/13143390.jpg");
                        break;
                    case 3:
                        Toast.makeText(context, "环信登录失败" + msg.obj.toString(), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        mobie = aCache.getAsString("mobie");
        pwd = aCache.getAsString("hxpwd");
        login();
    }

    private void login() {

        EMClient.getInstance().login(mobie, pwd, new EMCallBack() {
            @Override
            public void onSuccess() {

//                SharedPreferencesUtils.setParam(context, APPConfig.USER_NAME, "leiws");
//                SharedPreferencesUtils.setParam(context, APPConfig.PASS_WORD, "123456");
                Message msg = new Message();
                msg.what = 2;
                mHandler.sendMessage(msg);

                Log.i("huanxin", "登录成功");


            }

            @Override
            public void onError(int i, String s) {
                Message msg = new Message();
                msg.what = 3;
                msg.obj = s;
                mHandler.sendMessage(msg);
                Log.e("huanxin", s);
                Log.i("huanxin", "登录失败");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
    private void InitNavigationBar() {
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.hy1, R.string.hy).setInActiveColor(Color.rgb(51,51,51)).setInactiveIconResource(R.mipmap.hy).setActiveColor(Color.rgb(47,137,255)))
                .addItem(new BottomNavigationItem(R.mipmap.wb1, R.string.wb).setInActiveColor(Color.rgb(51,51,51)).setInactiveIconResource(R.mipmap.wb).setActiveColor(Color.rgb(47,137,255)))
                .addItem(new BottomNavigationItem(R.mipmap.sq1, R.string.sq).setInActiveColor(Color.rgb(51,51,51)).setInactiveIconResource(R.mipmap.sq).setActiveColor(Color.rgb(47,137,255)))
                .addItem(new BottomNavigationItem(R.mipmap.wd1, R.string.wd).setInActiveColor(Color.rgb(51,51,51)).setInactiveIconResource(R.mipmap.wd).setActiveColor(Color.rgb(47,137,255)))
                .setFirstSelectedPosition(0)
                .initialise();
    }


    @Override
    public void onTabSelected(int position) {

        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                showFragment(homeFragment,"homeFragment");

                break;
            case 1:
                if (storehouseFragment == null) {
                    storehouseFragment = new StorehouseFragment();
                }
                showFragment(storehouseFragment,"storehouseFragment");
                break;
            case 2:
                if (advisoryFragment == null) {
                    advisoryFragment = new AdvisoryFragment();
                }
                showFragment(advisoryFragment,"advisoryFragment");
                break;

                case 3:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                }
                showFragment(myFragment,"myFragment");
                break;
            default:
                break;
        }
        // 事务提交

    }
            int i=0;
            private void showFragment(BaseFragment fragment,String tag) {
               if (currentFragment!=fragment) {
                     FragmentTransaction transaction = fManager.beginTransaction();
                     if (i!=0){
                         transaction.hide(currentFragment);
                     }
                     currentFragment = fragment;
                     if (!fragment.isAdded()) {

                              transaction.add(R.id.fragment_container, fragment,tag).show(fragment).commit();
                        } else {
                               transaction.show(fragment).commit();
                           }
                           i++;
                  }
            }
    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().logout(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
              if (currentFragment==storehouseFragment){
                  if ( storehouseFragment.mAgentWeb.handleKeyEvent(keyCode, event)) {
                      return true;
                  }

              }
        return super.onKeyDown(keyCode, event);
    }
}
