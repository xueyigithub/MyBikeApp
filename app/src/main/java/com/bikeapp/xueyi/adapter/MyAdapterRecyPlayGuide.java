package com.bikeapp.xueyi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bikeapp.xueyi.config.Consts;
import com.bikeapp.xueyi.domain.PlayGuide;
import com.bikeapp.xueyi.mybikeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by XUEYI on 2015/12/10.
 */

public class MyAdapterRecyPlayGuide extends RecyclerView.Adapter<MyAdapterRecyPlayGuide.ViewHolder> {

    /**
     * ItemClick的回调接口
     *
     * @author zhy
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    private List<PlayGuide> mDataset;
    private Context context;

    public MyAdapterRecyPlayGuide(List<PlayGuide> dataset, Context context) {
        this.context = context;
        this.mDataset = dataset;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
//        View view = View.inflate(viewGroup.getContext(), R.layout.news_item, null);
        View view = LayoutInflater.from(context).inflate(R.layout.play_item, viewGroup, false);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        PlayGuide playGuide = mDataset.get(i);
        // 绑定数据到ViewHolder上
        viewHolder.title.setText(playGuide.getTitle());
        viewHolder.context.setText(playGuide.getSummary());
        if (playGuide.getTitleImage() == null) {
            Picasso.with(context)
                    .load(R.drawable.tab_menu_find)
                    .into(viewHolder.icon);
        } else {
            Picasso.with(context)
                    .load(Consts.URL_IMAGE + playGuide.getTitleImage().getDownload())
                    .into(viewHolder.icon);
        }

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_title)
        TextView title;
        @Bind(R.id.txt_context)
        TextView context;
        @Bind(R.id.img_icon)
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
