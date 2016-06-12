package com.example.hpb.kunlun.player.view;

import com.example.hpb.kunlun.player.model.Comment;
import com.example.hpb.kunlun.player.model.PostDetail;

import java.util.List;

/**
 * Created by 0000- on 2016/6/12.
 */
public interface IPlayerView {
    public void onGetPostDetail(PostDetail detail);

    public void onGetComments(List<Comment> comments);

    void onPlay(String videoSource);
}
