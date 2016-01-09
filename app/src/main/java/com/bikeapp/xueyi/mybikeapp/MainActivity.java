package com.bikeapp.xueyi.mybikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bikeapp.xueyi.domain.User;
import com.bikeapp.xueyi.service.CallService;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private String userName;
    private String password;

    /**
     * toolbar
     */
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    /**
     * FloatingActionButton
     *
     * @param view
     */
    @OnClick(R.id.fab)
    void fab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * 用户的名字
     */
    @Bind(R.id.et_userName)
    TextView et_userName;

    /**
     * 用户的密码
     */
    @Bind(R.id.et_passward)
    TextView et_passward;

    /**
     * 登录失败的toast
     */
    @BindString(R.string.toast_login)
    String toast_login;
    /**
     * 登录
     */
    @BindString(R.string.login)
    String login;

    /**
     * 对登录进行操作
     *
     * @param v
     */
    @OnClick(R.id.bt_login)
    void login(View v) {
        userName = et_userName.getText().toString().trim();
        password = et_passward.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, toast_login, Toast.LENGTH_SHORT).show();
            return;
        }
        Call<User> callUser = CallService.service.login(userName, password);
        callUser.enqueue(new Callback<User>() {
                             @Override
                             public void onResponse(Response<User> response, Retrofit retrofit) {
                                 User userBack = response.body();
                                 if (userBack != null && !TextUtils.isEmpty(userBack.getId())) {
                                     startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                 }
                                 Log.d(TAG, userBack.toString());
                             }

                             @Override
                             public void onFailure(Throwable t) {
                                 Log.d(TAG, "onFailure");
                             }
                         }
        );
    }

    /**
     * 对注册进行操作
     *
     * @param v
     */
    @OnClick(R.id.tv_register)
    void register(View v) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbar.setTitle(login);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
