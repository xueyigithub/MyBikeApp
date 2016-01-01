package com.bikeapp.xueyi.mybikeapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bikeapp.xueyi.adapter.MyFragmentPagerAdapter;
import com.bikeapp.xueyi.config.Consts;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    @BindString(R.string.app_name)
    String name;
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

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.vpager)
    ViewPager vpager;
    /**
     * 发现，天气，我的按钮
     */
    @Bind(R.id.rb_find)
    RadioButton find;
    @Bind(R.id.rb_weather)
    RadioButton weather;
    @Bind(R.id.rb_mine)
    RadioButton mine;
    /**
     * 实现按钮的转换
     */
    /*@Bind(R.id.rg_tab_bar)
    RadioGroup mGroup;*/
  /*  @OnCheckedChanged(R.id.rg_tab_bar)void tab_bar(View v){
        switch(v.getId()) {
            case R.id.rb_find:
                if (vpager.getCurrentItem() != Consts.PAGE_ONE) {
                    vpager.setCurrentItem(Consts.PAGE_ONE, true);
                }
                break;
            case R.id.rb_weather:
                if (vpager.getCurrentItem() != Consts.PAGE_TWO) {
                    vpager.setCurrentItem(Consts.PAGE_TWO, true);
                }
                break;
            case R.id.rb_mine:
                if (vpager.getCurrentItem() != Consts.PAGE_THREE) {
                    vpager.setCurrentItem(Consts.PAGE_THREE, true);
                }
                break;
            default:
                break;
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mToolbar.setTitle(name);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_menu);
        setSupportActionBar(mToolbar);
        /**
         * 隐藏
         */
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

       // mGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        /**
         * 默认find为第一个界面
         */
        find.setChecked(true);

        vpager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));

        vpager.addOnPageChangeListener(new PageChangeListener());
    }

    /**
     * 实现了对drawlayout里面的点击事件
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_found:
                                if (vpager.getCurrentItem() != Consts.PAGE_ONE) {
                                    vpager.setCurrentItem(Consts.PAGE_TWO, true);
                                }

                                break;

                            case R.id.nav_weather:
                                if (vpager.getCurrentItem() != Consts.PAGE_TWO) {
                                    vpager.setCurrentItem(Consts.PAGE_TWO, true);
                                }

                                break;

                            case R.id.nav_mine:
                                if (vpager.getCurrentItem() != Consts.PAGE_THREE) {
                                    vpager.setCurrentItem(Consts.PAGE_THREE, true);
                                }
                                break;

                        }
                        // menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    /**
     * 实现了对mgroup里面的滑动事件
     */
    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        /*state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕*/
            if (state == 2) {
                switch (vpager.getCurrentItem()) {
                    case Consts.PAGE_ONE:
                        find.setChecked(true);
                        break;
                    case Consts.PAGE_TWO:
                        weather.setChecked(true);
                        break;
                    case Consts.PAGE_THREE:
                        mine.setChecked(true);
                        break;
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
    }

    /**
     * 实现了对按钮的点击事件
     */
   /* private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.d("RadioGroup", "OnCheckedChangeListener!!!");
            switch (checkedId) {
                case R.id.rb_find:
                    if (vpager.getCurrentItem() != Consts.PAGE_ONE) {
                        vpager.setCurrentItem(Consts.PAGE_ONE, true);
                    }
                    break;
                case R.id.rb_weather:
                    if (vpager.getCurrentItem() != Consts.PAGE_TWO) {
                        vpager.setCurrentItem(Consts.PAGE_TWO, true);
                    }
                    break;
                case R.id.rb_mine:
                    if (vpager.getCurrentItem() != Consts.PAGE_THREE) {
                        vpager.setCurrentItem(Consts.PAGE_THREE, true);
                    }
                    break;
                default:
                    break;
            }
        }
    };
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "你点了设置", Toast.LENGTH_LONG).show();
            default:
                break;
        }
        return true;
    }

    /**
     * 确定你要不要退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    Handler handler = new Handler();
    private boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                handler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}
