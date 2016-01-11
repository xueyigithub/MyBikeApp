package com.bikeapp.xueyi.mybikeapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bikeapp.xueyi.enums.NewsTypeEnum;
import com.bikeapp.xueyi.fragment.NewsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity {
    private static final String TAG = "NewsActivity";
    //校内新闻
    @Bind(R.id.rb_in_news)
    TextView rb_in_news;
    //校外新闻
    @Bind(R.id.rb_out_news)
    TextView rb_out_news;

    @OnClick(R.id.rb_in_news)void setRb_in_news(View v){
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        fg1 = new NewsFragment();
        Bundle args = new Bundle();
        setSelected();
        rb_in_news.setSelected(true);
        args.putString("newsType", NewsTypeEnum.In.toString());
        fg1.setArguments(args);
        fTransaction.add(R.id.ly_content, fg1);
        fTransaction.commit();
    }

    @OnClick(R.id.rb_out_news)void setRb_out_news(View v){
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        fg1 = new NewsFragment();
        Bundle args = new Bundle();
        setSelected();
        rb_out_news.setSelected(true);
        args.putString("newsType", NewsTypeEnum.Out.toString());
        fg1.setArguments(args);
        fTransaction.add(R.id.ly_content, fg1);
        fTransaction.commit();
    }

    @Bind(R.id.ly_content)
    FrameLayout ly_content;

    private NewsFragment fg1;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_campus_news);



        ButterKnife.bind(this);

        fManager = getSupportFragmentManager();

        rb_in_news.performClick();//模拟一次点击，既进去后选择第一项
    }


    //重置所有文本的选中状态
    private void setSelected() {
        rb_in_news.setSelected(false);
        rb_out_news.setSelected(false);

    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) fragmentTransaction.hide(fg1);
    }


   /* public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        fg1 = new NewsFragment();
        Bundle args = new Bundle();
        switch (v.getId()) {
            case R.id.rb_in_news:
                setSelected();
                rb_in_news.setSelected(true);
                args.putString("newsType", NewsTypeEnum.In.toString());
                fg1.setArguments(args);
                fTransaction.add(R.id.ly_content, fg1);
                break;

            case R.id.rb_out_news:
                setSelected();
                rb_out_news.setSelected(true);
                args.putString("newsType", NewsTypeEnum.Out.toString());
                fg1.setArguments(args);
                fTransaction.add(R.id.ly_content, fg1);
                break;

        }
        fTransaction.commit();
    }

*/
}
