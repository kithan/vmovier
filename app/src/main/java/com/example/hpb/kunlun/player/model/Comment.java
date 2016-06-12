package com.example.hpb.kunlun.player.model;

import java.util.List;

/**
 * Created by 0000- on 2016/6/12.
 */
public class Comment {


    /**
     * commentid : 269813
     * isrecommend : 0
     * count_approve : 1
     * has_approve : 0
     * content : 小编影评写的不错啊，赞一个[鼓掌]
     * addtime : 1465698176
     * userinfo : {"userid":"319760","username":"　　　　_3","avatar":"http://cs.vmovier.com/Uploads/avatar/2014/05/29/5386d49832266_80.jpeg","isadmin":"0","is_xpc_author":"0"}
     * reply_username :
     * reply_user_isadmin :
     * reply_userinfo : []
     * subcomment : []
     */

    private String commentid;
    private String isrecommend;
    private String count_approve;
    private String has_approve;
    private String content;
    private String addtime;
    /**
     * userid : 319760
     * username : 　　　　_3
     * avatar : http://cs.vmovier.com/Uploads/avatar/2014/05/29/5386d49832266_80.jpeg
     * isadmin : 0
     * is_xpc_author : 0
     */

    private User userinfo;
    private String reply_username;
    private String reply_user_isadmin;
    private List<User> reply_userinfo;
    private List<Comment> subcomment;

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(String isrecommend) {
        this.isrecommend = isrecommend;
    }

    public String getCount_approve() {
        return count_approve;
    }

    public void setCount_approve(String count_approve) {
        this.count_approve = count_approve;
    }

    public String getHas_approve() {
        return has_approve;
    }

    public void setHas_approve(String has_approve) {
        this.has_approve = has_approve;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public User getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }

    public String getReply_username() {
        return reply_username;
    }

    public void setReply_username(String reply_username) {
        this.reply_username = reply_username;
    }

    public String getReply_user_isadmin() {
        return reply_user_isadmin;
    }

    public void setReply_user_isadmin(String reply_user_isadmin) {
        this.reply_user_isadmin = reply_user_isadmin;
    }

    public List<?> getReply_userinfo() {
        return reply_userinfo;
    }

    public void setReply_userinfo(List<User> reply_userinfo) {
        this.reply_userinfo = reply_userinfo;
    }

    public List<?> getSubcomment() {
        return subcomment;
    }

    public void setSubcomment(List<Comment> subcomment) {
        this.subcomment = subcomment;
    }

}
