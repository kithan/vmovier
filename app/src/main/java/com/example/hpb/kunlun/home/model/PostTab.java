package com.example.hpb.kunlun.home.model;

import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public class PostTab {


    /**
     * postid : 49239
     * title : 绝美4k延时摄影风光片《转变》
     * pid : 1
     * app_fu_title :
     * is_xpc : 0
     * is_promote : 0
     * is_xpc_zp : 0
     * is_xpc_cp : 0
     * is_xpc_fx : 0
     * is_album : 0
     * tags :
     * recent_hot : 0
     * discussion : 0
     * image : http://cs.vmoiver.com/Uploads/cover/2016-06-06/575561fa69ef6_cut.jpeg
     * rating : 7.0
     * duration : 152
     * publish_time : 1465344240
     * like_num : 89
     * share_num : 182
     * cates : [{"cateid":"11","catename":"旅行"}]
     * request_url : http://app.vmoiver.com/49239?qingapp=app_new
     */

    private String postid;
    private String title;
    private String pid;
    private String app_fu_title;
    private String is_xpc;
    private String is_promote;
    private String is_xpc_zp;
    private String is_xpc_cp;
    private String is_xpc_fx;
    private String is_album;
    private String tags;
    private String recent_hot;
    private String discussion;
    private String image;
    private String rating;
    private String duration;
    private String publish_time;
    private String like_num;
    private String share_num;
    private String request_url;
    /**
     * cateid : 11
     * catename : 旅行
     */

    private List<Cate> cates;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getApp_fu_title() {
        return app_fu_title;
    }

    public void setApp_fu_title(String app_fu_title) {
        this.app_fu_title = app_fu_title;
    }

    public String getIs_xpc() {
        return is_xpc;
    }

    public void setIs_xpc(String is_xpc) {
        this.is_xpc = is_xpc;
    }

    public String getIs_promote() {
        return is_promote;
    }

    public void setIs_promote(String is_promote) {
        this.is_promote = is_promote;
    }

    public String getIs_xpc_zp() {
        return is_xpc_zp;
    }

    public void setIs_xpc_zp(String is_xpc_zp) {
        this.is_xpc_zp = is_xpc_zp;
    }

    public String getIs_xpc_cp() {
        return is_xpc_cp;
    }

    public void setIs_xpc_cp(String is_xpc_cp) {
        this.is_xpc_cp = is_xpc_cp;
    }

    public String getIs_xpc_fx() {
        return is_xpc_fx;
    }

    public void setIs_xpc_fx(String is_xpc_fx) {
        this.is_xpc_fx = is_xpc_fx;
    }

    public String getIs_album() {
        return is_album;
    }

    public void setIs_album(String is_album) {
        this.is_album = is_album;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRecent_hot() {
        return recent_hot;
    }

    public void setRecent_hot(String recent_hot) {
        this.recent_hot = recent_hot;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getShare_num() {
        return share_num;
    }

    public void setShare_num(String share_num) {
        this.share_num = share_num;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public List<Cate> getCates() {
        return cates;
    }

    public void setCates(List<Cate> cates) {
        this.cates = cates;
    }


}
