package com.bikeapp.xueyi.mybikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bikeapp.xueyi.adapter.MyAdapterRecyNewsSingle;
import com.bikeapp.xueyi.domain.FileInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleNewsActivity extends BaseActivity {
    private static final String TAG = "SingleNewsActivity";
    private MyAdapterRecyNewsSingle adapter;
    @Bind(R.id.recyclerview_horizontal)
    RecyclerView mRecyclerView;

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

    @Bind(R.id.tv_summary)
    TextView tv_summary;
    @Bind(R.id.tv_conetnt)
    TextView tv_content;
    @Bind(R.id.view)
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        String title = intent.getStringExtra("title");
        String summary = intent.getStringExtra("summary");
        String content = intent.getStringExtra("content");
        List<FileInfo> imageView = (List<FileInfo>) getIntent().getSerializableExtra("imageView");

        initRecyclerView();

        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (summary != null) {
            tv_summary.setText(summary);
        } else {
            tv_summary.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
        tv_content.setText(content);
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());

        if (imageView.size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
        } else {
            adapter = new MyAdapterRecyNewsSingle(imageView, SingleNewsActivity.this);
            adapter.notifyDataSetChanged();
            // 设置Adapter
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void initRecyclerView() {
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
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
