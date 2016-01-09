package com.bikeapp.xueyi.mybikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bikeapp.xueyi.adapter.MyAdapterRecyStudyGuide;
import com.bikeapp.xueyi.domain.StudyGuide;
import com.bikeapp.xueyi.dto.Page;
import com.bikeapp.xueyi.service.CallService;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class StudyGuideActivity extends BaseActivity {
    private static final String TAG = "StudyGuideActivity";
    private MyAdapterRecyStudyGuide adapter;
    private int i = 0;
    private String name;

    private List<StudyGuide> studyGuide = new ArrayList<>();

    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipyrefreshlayout)
    SwipyRefreshLayout mSwipyRefreshLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_study_guide);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        name = intent.getStringExtra("name");

        initRecyclerView();
/**
 * 加载数据内容
 */
        initStudyGuide();
/**
 * 刷新操作
 */
        initSwipyRefresh();
/**
 * 对recycle进行适配
 */
        initAdapter();

        mToolbar.setTitle(name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initAdapter() {
        adapter = new MyAdapterRecyStudyGuide(studyGuide, StudyGuideActivity.this);
        adapter.setOnItemClickLitener(new MyAdapterRecyStudyGuide.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                String title = studyGuide.get(position).getTitle();
                String content = studyGuide.get(position).getContent();
                singStudyActivity(title, content);
            }
        });

        mRecyclerView.setAdapter(adapter);

    }


    private void singStudyActivity(String title, String content) {
        Intent intent = new Intent(this, SingleStudyActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        startActivity(intent);
    }


    private void initRecyclerView() {
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 加载数据
     */
    private void initStudyGuide() {
        Call<Page<StudyGuide>> call = CallService.service.listStudyGuide(i++);
        call.enqueue(new Callback<Page<StudyGuide>>() {
                         @Override
                         public void onResponse(Response<Page<StudyGuide>> response, Retrofit retrofit) {
                             Page<StudyGuide> studyGuidePage = response.body();
                             studyGuide = studyGuidePage.getContent();
                             studyGuide.addAll(studyGuide);
                             initAdapter();
                             adapter.notifyDataSetChanged();
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
                initStudyGuide();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "你点了设置", Toast.LENGTH_LONG).show();
            default:
                break;
        }
        return true;
    }
}
