package com.example.hpb.kunlun.data;

import com.example.hpb.kunlun.home.model.Banner;
import com.example.hpb.kunlun.home.model.Cate;
import com.example.hpb.kunlun.home.model.PostDetail;
import com.example.hpb.kunlun.home.model.PostTab;
import com.example.hpb.kunlun.home.model.Series;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hpb on 16/6/8.
 */
public interface VmovierApi {
    @GET("/apiv3/cate/getList")
    Observable<HttpResult<List<Cate>>> getCateList();

    @GET("/apiv3/index/getBanner")
    Observable<HttpResult<List<Banner>>> getBanner();
    @GET("/apiv3/post/getPostByTab?tab=latest")
    Observable<HttpResult<List<PostTab>>> getTabPost(int p);
    @GET("/apiv3/series/getList")
    Observable<HttpResult<List<Series>>> getSeries(int p);
    @GET("/apiv3/post/view")
    Observable<HttpResult<PostDetail>> getPostDetail(int postId);



}
