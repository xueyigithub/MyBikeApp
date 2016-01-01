package com.bikeapp.xueyi.mybikeapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bikeapp.xueyi.domain.User;
import com.bikeapp.xueyi.service.CallService;
import com.bikeapp.xueyi.util.MapUtils;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";

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
     * Toolbar上的名字
     */
    @BindString(R.string.title_activity_register)
    String register;

    /**
     * 用户名，密码，确认密码
     */
    @Bind(R.id.et_userName)
    EditText et_userName;
    @Bind(R.id.et_passward)
    EditText et_passward;
    @Bind(R.id.et_confirm_passward)
    EditText et_confirm_passward;

    /**
     * 对注册进行操作
     *
     * @param v
     */
    @OnClick(R.id.bt_register)
    void setRegister(View v) {
        String userName = et_userName.getText().toString().trim();
        String password = et_passward.getText().toString().trim();
        String confirm_passward = et_confirm_passward.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_passward)) {
            Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();

        } else if (!password.equals(confirm_passward)) {
            Toast.makeText(this, "输入密码不匹配", Toast.LENGTH_SHORT).show();
        }
        /**
         * 写成一个对象，然后
         */
        User userMap = User.builder().userName(userName).password(password).build();
        Call<User> callUser = CallService.service.register(MapUtils.getValueMap(userMap));
        callUser.enqueue(new Callback<User>() {
                             @Override
                             public void onResponse(Response<User> response, Retrofit retrofit) {
                                 User userBack = response.body();
                                 if (userBack != null && !TextUtils.isEmpty(userBack.getId())) {
                                     Toast.makeText(RegisterActivity.this, "注册成功了", Toast.LENGTH_SHORT).show();
                                 } else {
                                     Toast.makeText(RegisterActivity.this, "用户名已经存在，请重新输入", Toast.LENGTH_SHORT).show();
                                 }
                             }

                             @Override
                             public void onFailure(Throwable t) {

                             }
                         }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mToolbar.setTitle(register);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
