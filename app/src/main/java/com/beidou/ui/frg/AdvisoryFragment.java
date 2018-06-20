package com.beidou.ui.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.beidou.R;
import com.beidou.adapter.BaseAdapter;
import com.beidou.adapter.SampleAdapter;
import com.beidou.base.BaseFragment;
import com.beidou.base.BasePresenter;
import com.beidou.http.AppCof;
import com.beidou.ui.act.ComWebAct;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taobao.library.VerticalBannerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdvisoryFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    Banner banner;

    RecyclerView recycler1;

    VerticalBannerView vbanner;
    private List<ToolBean>  tools=new ArrayList<>();
    private List<String>  tgList=new ArrayList<>();
    private ArrayList list;
    private BaseAdapter<String> adapter;
    private int mNextRequestPage;
    private BaseAdapter<ToolBean> adapter1;

    @Override
    protected void init() {
        list = new ArrayList();
        list.add("asdiajs");
        list.add("sss");
        list.add("dasd");

        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        adapter = new BaseAdapter<>(R.layout.item_lt, list, new BaseAdapter.ConVert<String>() {
            @Override
            public void convert(BaseViewHolder helper, String s) {

            }
        });
        View headView = View.inflate(mContext, R.layout.layout_lthead, null);
        initHeadView(headView);
        adapter.addHeaderView(headView);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        // adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN );
        recycler.setAdapter(adapter);
        initRefreshLayout();
        swipeLayout.setRefreshing(true);
        refresh();
    }

    private void initHeadView(View headView) {
        banner=headView.findViewById(R.id.banner);
        vbanner=headView.findViewById(R.id.vbanner);
        recycler1=headView.findViewById(R.id.recycler1);
        tools.add(new ToolBean(R.mipmap.zix,"咨询"));
        tools.add(new ToolBean(R.mipmap.lunt,"论坛"));
        tools.add(new ToolBean(R.mipmap.wend,"问答"));
        tools.add(new ToolBean(R.mipmap.hangq,"行情"));
        tools.add(new ToolBean(R.mipmap.huoy,"活跃度"));
        tools.add(new ToolBean(R.mipmap.zhib,"直播"));
        tools.add(new ToolBean(R.mipmap.shangc,"商城"));
        tools.add(new ToolBean(R.mipmap.ship,"视频"));
        tgList.add("北斗新版咨询类app1.0上线了");
        tgList.add("北斗新版咨询类app1.0上线了,sadnasassosapaosa");
       initBanner();
       initTool();
       initTg();
    }

    private void initTg() {
        vbanner.setAdapter(new SampleAdapter(tgList));
        vbanner.start();
    }

    private void initTool() {
        recycler1.setLayoutManager(new GridLayoutManager(mContext,4));

        adapter1 = new BaseAdapter<>(R.layout.item_gj, tools, new BaseAdapter.ConVert<ToolBean>() {
            @Override
            public void convert(BaseViewHolder helper, ToolBean s) {
                helper.setText(R.id.tv,s.name);
                ImageView view = helper.getView(R.id.iv);
                view.setImageResource(s.id);

            }
        });
        recycler1.setAdapter(adapter1);
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){

                       case 0:
                           Bundle bundle=new Bundle();
                           bundle.putString("url", AppCof.ZIXUN+aCache.getAsString("address"));
                           jumpToActivity(ComWebAct.class,bundle);
                        break;
                        case 1:

                        break;
                        case 2:

                        break;
                        case 3:
                            Bundle bundle1=new Bundle();
                            bundle1.putString("url", AppCof.ZIXUN+aCache.getAsString("address"));
                            jumpToActivity(ComWebAct.class,bundle1);
                        break;
                        case 4:

                        break;
                        case 5:

                        break;
                        case 6:

                        break;
                        case 7:

                        break;
                }
            }
        });
    }
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
    private void initBanner() {
        ArrayList<Integer> images=new ArrayList();
        images.add(R.mipmap.shebanner1);
        images.add(R.mipmap.shebanner2);
        MyImageLoader mMyImageLoader = new MyImageLoader();
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        //banner.setBannerAnimation(Transformer.ZoomOutSlide);
        banner.setImageLoader(mMyImageLoader);

        banner.setDelayTime(2000);//设置轮播时间
        banner.setImages(images);//设置图片源
        banner.start();
    }

    private void initRefreshLayout() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

    }

    private void refresh() {
        mNextRequestPage = 1;
        adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载

        if (true) {
            setData(true, list);
            adapter.setEnableLoadMore(true);
            swipeLayout.setRefreshing(false);
        } else {
            adapter.setEnableLoadMore(true);
            swipeLayout.setRefreshing(false);
        }


    }

    private void loadMore() {
        final List<String> list1 = new ArrayList<>();
        list.add("少奇偶的怕");
        list.add("daasdas");
        list.add("asddssa");
        if (true) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setData(false, list1);
                }
            }, 500);

        } else {
            adapter.loadMoreFail();
        }
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            adapter.setNewData(data);
        } else {
            if (size > 0) {
                adapter.addData(data);
            }
        }
        if (size < 10) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);
            Toast.makeText(mContext, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_advisory, null);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();

    }
   class  ToolBean{
        public Integer id;
        public String name;

       public ToolBean(Integer id, String name) {
           this.id = id;
           this.name = name;
       }
   }
}
