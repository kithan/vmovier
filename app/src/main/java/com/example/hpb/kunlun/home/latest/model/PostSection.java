package com.example.hpb.kunlun.home.latest.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by 0000- on 2016/6/9.
 */
public class PostSection extends SectionEntity<PostTab> {
  public PostSection(boolean isHeader, String header) {
    super(isHeader, header);
  }

  public PostSection(PostTab postTab) {
    super(postTab);
  }
}
