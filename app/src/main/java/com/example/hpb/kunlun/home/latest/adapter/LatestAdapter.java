package com.example.hpb.kunlun.home.latest.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hpb.kunlun.R;
import com.example.hpb.kunlun.home.latest.model.PostSection;
import com.example.hpb.kunlun.home.latest.model.PostTab;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 0000- on 2016/6/9.
 */
public class LatestAdapter extends BaseSectionQuickAdapter<PostSection> {
  public LatestAdapter(Context context, List<PostSection> data) {
    super(context, R.layout.item_latest, R.layout.item_latest_section_header, data);
  }

  @Override protected void convertHead(BaseViewHolder helper, PostSection postSection) {
    helper.setText(R.id.month_divider, postSection.header);
  }

  @Override protected void convert(final BaseViewHolder baseViewHolder, PostSection postSection) {
    PostTab postTab = postSection.t;
    baseViewHolder.setText(R.id.tilte, postTab.getTitle());
    String cateNameAndLength = postTab.getCates().get(0).getCatename();
    if (!TextUtils.isEmpty(postTab.getDuration())) {
      int len = Integer.parseInt(postTab.getDuration());
      int minutes = len / 60;
      int secs = len - minutes * 60;
      cateNameAndLength += " / " + minutes + "'" + secs + "\"";
    }
    baseViewHolder.setText(R.id.cate_and_dur, cateNameAndLength);
    Glide.with(mContext)
        .load(postTab.getImage())
        .crossFade()
        .into((ImageView) baseViewHolder.getView(R.id.iv_post));
  }


}
