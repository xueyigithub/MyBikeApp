package com.bikeapp.xueyi.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bikeapp.xueyi.dao.Dao;
import com.bikeapp.xueyi.dao.DbUser;
import com.bikeapp.xueyi.mybikeapp.MainActivity;
import com.bikeapp.xueyi.mybikeapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MineFragment extends Fragment {
    private Context mContext;


    @OnClick(R.id.bt_exit_login)
    void exit_login(View v) {
        startActivity(new Intent(mContext, MainActivity.class));
        DbUser dbUser = Dao.findOne(DbUser.class, "1");
        dbUser.setHasLogin(false);
        Dao.save(dbUser);
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        mContext = this.getContext();
        return view;
    }
}
