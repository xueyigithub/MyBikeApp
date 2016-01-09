package com.bikeapp.xueyi.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.bikeapp.xueyi.adapter.MyFindAdapter;
import com.bikeapp.xueyi.config.Consts;
import com.bikeapp.xueyi.dto.Icon;
import com.bikeapp.xueyi.mybikeapp.CampusNewsActivity;
import com.bikeapp.xueyi.mybikeapp.PlayGuideActivity;
import com.bikeapp.xueyi.mybikeapp.R;
import com.bikeapp.xueyi.mybikeapp.StudyGuideActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class FindFragment extends Fragment {
    private Context mContext;
    private static List<Icon> mData = new ArrayList<>();


    /**
     * 校园指南，生活指南，游玩指南，就业指南，学生课表，失物招领
     */
    @BindString(R.string.grid_campus)
    String campus;
    @BindString(R.string.grid_life)
    String life;
    @BindString(R.string.grid_play)
    String play;
    @BindString(R.string.grid_job)
    String job;
    @BindString(R.string.grid_schedule)
    String schedule;
    @BindString(R.string.grid_lostandfind)
    String lostandfind;

    @Bind(R.id.grid_photo)
    GridView photo;



    /*private static class SetMData{
        private static final Integer i;
        static{
            i = 1;
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(campus).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(life).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(play).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(job).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(schedule).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(lostandfind).build());
        }
    }*/






    private void setMData() {
        if (mData == null || mData.size() < 1) {
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(campus).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(life).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(play).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(job).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(schedule).build());
            mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(lostandfind).build());
        }
    }

    /**
     * 跳转到不同的gridView上的界面
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */

    @OnItemClick(R.id.grid_photo)
    void photo(AdapterView<?> parent, View view, int position, long id) {
        String name = mData.get(position).getName();
        switch (position) {
            case Consts.PAGE_ONE:
                startActivity(new Intent(mContext, CampusNewsActivity.class));
                break;
            case Consts.PAGE_FOUR:
                Intent intent1 = new Intent(mContext, StudyGuideActivity.class);
                intent1.putExtra("name", name);
                startActivity(intent1);
                break;
            case Consts.PAGE_THREE:
                Intent intent2 = new Intent(mContext, PlayGuideActivity.class);
                intent2.putExtra("name", name);
                startActivity(intent2);
                break;
        }
        Toast.makeText(FindFragment.this.getActivity(), "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        /*mData.clear();
        mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(campus).build());
        mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(life).build());
        mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(play).build());
        mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(job).build());
        mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(schedule).build());
        mData.add(Icon.builder().id(R.mipmap.ic_launcher).name(lostandfind).build());*/
        setMData();
       // Integer i=SetMData.i;
        photo.setAdapter(new MyFindAdapter(mData, mContext));
        return view;
    }


}
