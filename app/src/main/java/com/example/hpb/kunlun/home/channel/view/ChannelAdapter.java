package com.example.hpb.kunlun.home.channel.view;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.home.model.Cate;
import java.util.List;

/**
 * Created by 0000- on 2016/6/9.
 */
public class ChannelAdapter extends BaseQuickAdapter<Cate> {
  public ChannelAdapter(Context context, List<Cate> data) {
    super(context, R.layout.item_channel, data);
  }

  @Override protected void convert(BaseViewHolder baseViewHolder, Cate cate) {
    baseViewHolder.setText(R.id.tv_title, "#"+cate.getCatename()+"#");
    Glide.with(mContext)
        .load(cate.getIcon())
        .crossFade()
        .into((ImageView) baseViewHolder.getView(R.id.iv_channel));
  }
}
