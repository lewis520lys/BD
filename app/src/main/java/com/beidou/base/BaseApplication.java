package com.beidou.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.beidou.MainActivity;
import com.beidou.huanxin.Constant;
import com.beidou.huanxin.HxEaseuiHelper;
import com.beidou.ui.act.LoginActivity;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGCMListenerService;

import com.hyphenate.easeui.EaseUI;
;
import com.hyphenate.util.EMLog;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import cn.jpush.android.api.JPushInterface;


import static com.beidou.utils.photo.PhotoUtils.initPicker;


public class BaseApplication extends Application {
    /*
    * 初始化TAG
    * */
    private  static String TAG=BaseApplication.class.getName();

    /*Activity堆*/
    private Stack<Activity> activityStack = new Stack<Activity>();
    // 提供一个单件
    private static BaseApplication application;
    private EaseUI easeUI;
    private SharedPreferences sharedPreferences;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //Avoiding the 64K Limit

    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        application=this;
        printAppParameter();
        initPicker();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        initHuanXin();

    }

    private void initHuanXin() {
        HxEaseuiHelper.getInstance().init(this.getApplicationContext());
        //设置全局监听
        setGlobalListeners();
    }
    EMConnectionListener connectionListener;

    /**
     * 设置一个全局的监听
     */
    protected void setGlobalListeners(){


        // create the global connection listener
        connectionListener = new EMConnectionListener() {
            @Override
            public void onDisconnected(int error) {
                EMLog.d("global listener", "onDisconnect" + error);
                if (error == EMError.USER_REMOVED) {// 显示帐号已经被移除
                    onUserException(Constant.ACCOUNT_REMOVED);
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {// 显示帐号在其他设备登录
                    onUserException(Constant.ACCOUNT_CONFLICT);
                    EMClient.getInstance().logout(true);//退出登录
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(),"退出成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else if (error == EMError.SERVER_SERVICE_RESTRICTED) {//
                    onUserException(Constant.ACCOUNT_FORBIDDEN);
                }
            }

            @Override
            public void onConnected() {
                // in case group and contact were already synced, we supposed to notify sdk we are ready to receive the events

            }
        };

        //register connection listener
        EMClient.getInstance().addConnectionListener(connectionListener);
    }

    /**
     * user met some exception: conflict, removed or forbidden
     */
    protected void onUserException(String exception){
        EMLog.e(EMGCMListenerService.TAG, "onUserException: " + exception);
        Looper.prepare();
        Toast.makeText(getApplicationContext(),exception,Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getBaseContext(), UserQrCodeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(exception, true);
//        this.startActivity(intent);
    }


    public static Context getContext()
    {
        return application;
    }

    public static BaseApplication getInstance()
    {
        return application;
    }
    /*打印出一些app的参数*/
    private void printAppParameter(){
       // LogUtils.d(TAG, "OS : "+ Build.VERSION.RELEASE + " ( " + Build.VERSION.SDK_INT + " )");
        //DeviceMgr.ScrSize realSize = DeviceMgr.getScreenRealSize(this);
        //LogUtils.d(TAG, "Screen Size: " + realSize.w + " X " + realSize.h);

    }

    public void addActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(curAT);
    }

    public void removeActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(curAT);
    }

    //获取最后一个Activity
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //返回寨内Activity的总数
    public int howManyActivities() {
        return activityStack.size();
    }

    //关闭所有Activity
    public void finishAllActivities() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    //关闭所有Activity
    public void finishAActivities() {
        for (int i = 0, size = activityStack.size()-1; i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }
}