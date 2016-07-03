package com.example.hpb.kunlun.cache;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hpb.kunlun.Henson;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.player.model.VideoInfo;

import java.util.List;

/**
 * Created by 0000- on 2016/6/22.
 */
public class CacheAdapter extends BaseQuickAdapter<VideoInfo> {


    Context mContext;

    public CacheAdapter(Context context, List<VideoInfo> data) {
        super(context, R.layout.item_cache, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final VideoInfo videoInfo) {

        baseViewHolder.setText(R.id.tv_duration, videoInfo.getDuration())
                .setText(R.id.tv_title, videoInfo.getTitle())
                .setText(R.id.tv_size, videoInfo.getFilesize())
                .setImageUrl(R.id.iv_pic, videoInfo.getImage());
        baseViewHolder.getView(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Henson.with(mContext)
                        .gotoPlayerActivity().fromCache(true)
                        .postId(videoInfo.getPostId())
                        .url(videoInfo.getSource_link()).build();
                mContext.startActivity(intent);
            }
        });

    }
}
