package com.example.hpb.kunlun.home.model;

import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public class Cate {


    private int cate_type;
    private int orderid;
    private int cateid;
    private String catename;
    private String alias;
    private String icon;

    public int getCate_type() {
        return cate_type;
    }

    public void setCate_type(int cate_type) {
        this.cate_type = cate_type;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
