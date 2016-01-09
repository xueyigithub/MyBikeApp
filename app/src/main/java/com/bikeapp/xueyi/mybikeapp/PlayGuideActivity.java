package com.bikeapp.xueyi.mybikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bikeapp.xueyi.adapter.MyAdapterRecyPlayGuide;
import com.bikeapp.xueyi.domain.PlayGuide;
import com.bikeapp.xueyi.dto.Page;
import com.bikeapp.xueyi.enums.CityEnum;
import com.bikeapp.xueyi.service.CallService;
import com.bikeapp.xueyi.util.MapUtils;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PlayGuideActivity extends BaseActivity {
    private static final String TAG = "PlayGuideActivity";
    private int i = 0;
    private String city=CityEnum.Jinhua.toString();
    private List<PlayGuide> playGuides = new ArrayList<>();
    private MyAdapterRecyPlayGuide adapter;
    private String name;


    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipyrefreshlayout)
    SwipyRefreshLayout mSwipyRefreshLayout;
    /**
     * toolbar
     */
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    /**
     * navigation
     *
     * @param view
     */
    @OnClick(R.id.fab)
    void fab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_guide);
        ButterKnife.bind(this);
        initRecyclerView();

        Intent intent = this.getIntent();
        name = intent.getStringExtra("name");
        /**
         * 加载数据内容
         */
        initPlayGuide();

        /**
         * 刷新操作
         */
        initSwipyRefresh();

        mToolbar.setTitle(name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView() {
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initAdapter(){
        adapter = new MyAdapterRecyPlayGuide(playGuides, PlayGuideActivity.this);
        adapter.setOnItemClickLitener(new MyAdapterRecyPlayGuide.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                String title = playGuides.get(position).getTitle();
                String content=playGuides.get(position).getContent();
                singPlayActivity(title,content);
            }
        });

        mRecyclerView.setAdapter(adapter);
    }

    private void singPlayActivity(String title,String content){
        Intent intent=new Intent(PlayGuideActivity.this,SinglePlayActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        startActivity(intent);
    }

    private void initPlayGuide() {
        final PlayGuide playGuide = PlayGuide.builder().city(CityEnum.valueOf(city)).build();
        Call<Page<PlayGuide>> call = CallService.service.listPlayGuide(i++, MapUtils.getValueMap(playGuide));
        call.enqueue(new Callback<Page<PlayGuide>>() {
                         @Override
                         public void onResponse(Response<Page<PlayGuide>> response, Retrofit retrofit) {
                             Page<PlayGuide> playGuidePage = response.body();
                             playGuides = playGuidePage.getContent();
                             playGuides.addAll(playGuides);
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
                initPlayGuide();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

    private boolean changCity(CityEnum myCity){
        city=myCity.toString();
        initPlayGuide();
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_Jinhua:
                changCity(CityEnum.Jinhua);
                break;
            case R.id.action_Hangzhou:
                changCity(CityEnum.Hangzhou);
                break;
            case R.id.action_Ningbo:
                changCity(CityEnum.Ningbo);
                break;
            case R.id.action_Huzhou:
                changCity(CityEnum.Huzhou);
                break;
            case R.id.action_Jiaxing:
                changCity(CityEnum.Jiaxing);
                break;
            case R.id.action_Lishui:
                changCity(CityEnum.Lishui);
                break;
            case R.id.action_Quzhou:
                changCity(CityEnum.Quzhou);
                break;
            case R.id.action_Shaoxing:
                changCity(CityEnum.Shaoxing);
                break;
            case R.id.action_Taizhou:
                changCity(CityEnum.Taizhou);
                break;
            case R.id.action_Wenzhou:
                changCity(CityEnum.Wenzhou);
                break;
            default:
                break;
        }

        return true;
    }
}