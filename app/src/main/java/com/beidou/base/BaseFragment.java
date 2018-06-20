package com.beidou.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beidou.utils.ACache;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected T mPresenter;
    protected Context mContext;//activity的上下文对象
    protected Bundle mBundle;
    private Unbinder unbinder;
    protected View view;
    protected ACache aCache;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }

    }
    /**
     * 绑定activity
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
        //创建presenter
        mPresenter = initPresenter();
        aCache = ACache.get(mContext);
    }

    /**
     * 运行在onCreate之后
     * 生成view视图
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    protected abstract void init();

    /**
     * 运行在onCreateView之后
     * 加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常

    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detach();//在presenter中解绑释放view
            mPresenter = null;
        }
        super.onDestroyView();
        unbinder.unbind();
    }





    /**
     * 初始化Fragment应有的视图
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 创建prensenter
     *
     * @return <T extends BasePresenter> 必须是BasePresenter的子类
     */
    public abstract T initPresenter();

    @Override
    public Context getContext() {
        return mContext;
    }
    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    public void jumpToActivity(Class clazz, int requestCode) {
        Intent intent = new Intent(mContext, clazz);
        getActivity().startActivityForResult(intent, requestCode);
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    public void jumpToActivity(Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        getActivity().startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    public void jumpToActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }


    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    public void jumpToActivity(Class clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, requestCode);
    }


}

