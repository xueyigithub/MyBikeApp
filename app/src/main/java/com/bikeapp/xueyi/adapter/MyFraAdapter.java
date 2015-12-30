package com.bikeapp.xueyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bikeapp.xueyi.domain.Icon;
import com.bikeapp.xueyi.mybikeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by XUEYI on 2015/12/11.
 */
public class MyFraAdapter extends BaseAdapter {
    private Context context;
    private List<Icon> mData;
    public MyFraAdapter(List<Icon> mData, Context context) {
        this.context = context;
        this.mData=mData;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_grid_icon, null);
            viewHolder=new ViewHolder();
            viewHolder.img_icon=(ImageView) view.findViewById(R.id.img_icon);
            viewHolder.txt_icon=(TextView) view.findViewById(R.id.txt_icon);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        Icon icon= mData.get(position);
        Picasso.with(context).load(icon.getId()).into(viewHolder.img_icon);
        viewHolder.txt_icon.setText(icon.getName());
        return view;
    }
    class ViewHolder{
        ImageView img_icon;
        TextView txt_icon;
    }
}


