package com.example.hpb.kunlun.player.model;

/**
 * Created by 0000- on 2016/6/12.
 */
public class User {
    private String userid;
    private String username;
    private String avatar;
    private String isadmin;
    private String is_xpc_author;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getIs_xpc_author() {
        return is_xpc_author;
    }

    public void setIs_xpc_author(String is_xpc_author) {
        this.is_xpc_author = is_xpc_author;
    }
}
