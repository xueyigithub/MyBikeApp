package com.bikeapp.xueyi.mybikeapp;

import android.os.Bundle;

import butterknife.ButterKnife;

public class CampusNewsActivity extends BaseActivity {
    private static final String TAG = "CampusNewsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_campus_news);
        ButterKnife.bind(this);

    }


}
