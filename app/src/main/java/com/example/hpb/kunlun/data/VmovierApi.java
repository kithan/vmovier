package com.example.hpb.kunlun.data;

import com.example.hpb.kunlun.home.latest.model.Banner;
import com.example.hpb.kunlun.home.model.Cate;
import com.example.hpb.kunlun.player.model.Comment;
import com.example.hpb.kunlun.player.model.PostDetail;
import com.example.hpb.kunlun.home.latest.model.PostTab;
import com.example.hpb.kunlun.home.model.Series;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hpb on 16/6/8.
 */
public interface VmovierApi {
    @GET("/apiv3/cate/getList")
    Observable<HttpResult<List<Cate>>> getCateList();

    @GET("/apiv3/index/getBanner")
    Observable<HttpResult<List<Banner>>> getBanner();
    @GET("/apiv3/post/getPostByTab")
    Observable<HttpResult<List<PostTab>>> getTabPost(@Query("p") int p);
    @GET("/apiv3/series/getList")
    Observable<HttpResult<List<Series>>> getSeries(@Query("p") int p);
    @GET("/apiv3/post/view")
    Observable<HttpResult<PostDetail>> getPostDetail(@Query("postid") int postId);
    @GET("/apiv3/comment/getLists")
    Observable<HttpResult<List<Comment>>> getComments(@Query("p") int page, @Query("postid") int postId, @Query("type") int type);

    @GET("/apiv3/backstage/getCate")
    Observable<HttpResult<PostDetail>> getBackstageCate();
    @GET("/apiv3/backstage/getPostByCate")
    Observable<HttpResult<PostDetail>> getPostByCate(@Query("p") int page,@Query("cateid") int cateid);





}
