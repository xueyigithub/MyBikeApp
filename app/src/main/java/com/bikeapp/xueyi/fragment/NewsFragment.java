package com.bikeapp.xueyi.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bikeapp.xueyi.adapter.MyAdapterNewsRecy;
import com.bikeapp.xueyi.domain.CampusNews;
import com.bikeapp.xueyi.domain.FileInfo;
import com.bikeapp.xueyi.dto.Page;
import com.bikeapp.xueyi.enums.NewsTypeEnum;
import com.bikeapp.xueyi.mybikeapp.R;
import com.bikeapp.xueyi.mybikeapp.SingleNewsActivity;
import com.bikeapp.xueyi.service.CallService;
import com.bikeapp.xueyi.util.MapUtils;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class NewsFragment extends Fragment {
    private static final String TAG = "NewsFragment";
    private Context mContext;
    private MyAdapterNewsRecy adapter;
    private List<CampusNews> campusNews = new ArrayList<>();
    private List<FileInfo> imageView=new  ArrayList<>();
    private String newsType;
    private int i = 0;

    @Bind(R.id.recyclerview_vertical)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipyrefreshlayout)
    SwipyRefreshLayout mSwipyRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        initRecyclerView();
        //获取到MyFragment里面的信息
        newsType = getArguments().getString("newsType");

        initNewsGuide();
        initSwipyRefresh();

        return view;
    }

    private void initRecyclerView() {
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initNewsGuide() {
        final CampusNews campusNew = CampusNews.builder().newsType(NewsTypeEnum.valueOf(newsType)).build();
        Call<Page<CampusNews>> call = CallService.service.listCampusNews(i++, MapUtils.getValueMap(campusNew));
        call.enqueue(new Callback<Page<CampusNews>>() {
                         @Override
                         public void onResponse(Response<Page<CampusNews>> response, Retrofit retrofit) {
                             Page<CampusNews> campusNewsPage = response.body();
                             campusNews = campusNewsPage.getContent();
                             campusNews.addAll(campusNews);
                             initAdapter();
                             stopRefresh();
                         }

                         @Override
                         public void onFailure(Throwable t) {
                             Log.d(TAG, "onFailure");
                         }
                     }
        );

    }

    private void initAdapter() {
        adapter = new MyAdapterNewsRecy(campusNews, mContext);
        adapter.setOnItemClickLitener(new MyAdapterNewsRecy.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                String title = campusNews.get(position).getTitle();
                String content=campusNews.get(position).getContent();
                String summary=campusNews.get(position).getSummary();
                imageView=campusNews.get(position).getImages();
                singNewsActivity(title,content,summary,imageView);
            }
        });

        mRecyclerView.setAdapter(adapter);
    }

    private void singNewsActivity(String title,String content,String summary,List<FileInfo> imageView) {

        Intent intent=new Intent(mContext, SingleNewsActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        intent.putExtra("summary",summary);
        intent.putExtra("imageView",(Serializable)imageView);
        startActivity(intent);

    }

    /**
     * 停止刷新
     */
    private void stopRefresh() {
        mSwipyRefreshLayout.setRefreshing(false);
    }

    /**
     * 刷新
     */
    public void initSwipyRefresh() {
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                initNewsGuide();
            }
        });
    }

}
