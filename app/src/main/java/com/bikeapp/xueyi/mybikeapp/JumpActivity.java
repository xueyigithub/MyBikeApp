package com.bikeapp.xueyi.mybikeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.activeandroid.ActiveAndroid;
import com.bikeapp.xueyi.dao.Dao;
import com.bikeapp.xueyi.dao.DbUser;

public class JumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_jump);
        ActiveAndroid.initialize(this);
        setLogging();
    }

    public void setLogging(){
        DbUser dbUser = Dao.findOne(DbUser.class, "1");
        if(dbUser!=null&&dbUser.getHasLogin()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        startActivity(new Intent(JumpActivity.this, HomeActivity.class));
                        finish();
                    }catch(Exception e){
                    }
                }
            }).start();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        startActivity(new Intent(JumpActivity.this, MainActivity.class));
                        finish();
                    }catch(Exception e){
                    }
                }
            }).start();
        }
    }
}
