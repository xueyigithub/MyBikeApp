package com.bikeapp.xueyi.mybikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    @Bind(R.id.toolbar) Toolbar toolbar;
    /**
     * FloatingActionButton
     * @param view
     */
    @OnClick(R.id.fab) void fab(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * 用户的名字
     */
    @Bind(R.id.et_userName)TextView et_userName;
    /**
     * 用户的密码
     */
    @Bind(R.id.et_passward)TextView et_passward;
    /**
     * 登录失败的toast
     */
    @BindString(R.string.toast_login)String toast_login;

    @OnClick(R.id.bt_login)void login(View v){
        userName = et_userName.getText().toString().trim();
        password = et_passward.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(this,toast_login,Toast.LENGTH_SHORT).show();
            return;
        }

        Call<User> callUser = CallService.service.login(userName, password);
        callUser.enqueue(new Callback<User>() {
                          @Override
                          public void onResponse(Response<User> response, Retrofit retrofit) {
                              User userBack = response.body();
                              if(userBack!=null&&!TextUtils.isEmpty(userBack.getId())){
                                  startActivity(new Intent(MainActivity.this,HomeActivity.class));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
