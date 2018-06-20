package com.beidou.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;


import com.beidou.utils.ACache;
import com.beidou.utils.StatusBarCompat;
import com.maning.mndialoglibrary.MProgressDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends BasePresenter> extends AutoLayoutActivity implements BaseView {
    protected P presenter;
    public Context context;
    private MProgressDialog pd;
    private Unbinder unbinder;
    protected ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        context = this;
        aCache = ACache.get(this);
        //StatusBarCompat.compat(this,Color.rgb(38,133,252));
        StatusBarCompat.fullScreen(this);
        setContentView(initLayout());
        BaseApplication.getInstance().addActivity(this);//将当前activity添加进入管理栈
        presenter = initPresenter();
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);


    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int initLayout();

    @Override
    protected void onDestroy() {
        BaseApplication.getInstance().removeActivity(this);//将当前activity移除管理栈
        if (presenter != null) {
            presenter.detach();//在presenter中解绑释放view
            presenter = null;
        }
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P initPresenter();


    @Override
    public void dismissLoadingDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    public void showLoadingDialog(String msg) {

        if (pd == null && context != null) {
        pd =  new MProgressDialog.Builder(context).build();
                //点击外部是否可以取消
//                .isCanceledOnTouchOutside(true)
//                //全屏背景窗体的颜色
//                .setBackgroundWindowColor(context.getResources().getColor(R.color.colorAccent))
//                //View背景的颜色
//                .setBackgroundViewColor(context.getResources().getColor(R.color.colorPrimaryDark))
//                //View背景的圆角
//                .setCornerRadius(20)
//                //View 边框的颜色
//                .setStrokeColor(context.getResources().getColor(R.color.colorAccent))
//                //View 边框的宽度
//                .setStrokeWidth(2)
//                //Progress 颜色
//                .setProgressColor(context.getResources().getColor(R.color.colorPrimary))
//                //Progress 宽度
//                .setProgressWidth(3)
//                //Progress 内圈颜色
//                .setProgressRimColor(Color.YELLOW)
//                //Progress 内圈宽度
//                .setProgressRimWidth(2)
//                //文字的颜色
//                .setTextColor(context.getResources().getColor(R.color.colorAccent))
//                //取消的监听
//                .setOnDialogDismissListener(new MProgressDialog.OnDialogDismissListener() {
//                    @Override
//                    public void dismiss() {
//
//                    }
//                })
//                .build();
                pd.show();
        }else {
            pd.show();
        }
    }
    /**
     * Activity之间的跳转方法
     *
     * @param
     * @param clazz
     * @param requestCode
     */
    public void jumpToClazz(Class clazz, int requestCode)
    {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * Activity之间的跳转方法
     *
     * @param
     * @param clazz
     * @param requestCode
     * @param bundle      要传递的数据
     */

    public void jumpToClazz(Class clazz, int requestCode, Bundle bundle)
    {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * Activity之间的跳转方法
     *
     * @param clazz
     */

    public void jumpToClazz(Class clazz)
    {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * Activity之间的跳转方法
     *
     * @param clazz
     */

    public void jumpToClazz(Class clazz, Boolean b)
    {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (b)
        {
            finish();
        }
    }

    /**
     * Activity之间的跳转方法
     *
     * @param clazz
     * @param bundle
     */
    public void jumpToClazz(Class clazz, Bundle bundle)
    {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    protected void toast(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}