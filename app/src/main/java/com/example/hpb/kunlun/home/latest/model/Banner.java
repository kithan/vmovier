package com.example.hpb.kunlun.home.latest.model;

/**
 * Created by hpb on 16/6/8.
 */
public class Banner {

    /**
     * bannerid : 990
     * title :
     * image : http://cs.vmoiver.com/Uploads/Banner/2016/06/06/5755418ba6238.jpg
     * description :
     * addtime : 1465205132
     * extra : {"app_banner_type":"2","app_banner_param":"49180"}
     * end_time : 0
     * extra_data : {"app_banner_type":"2","app_banner_param":"49180","is_album":"0"}
     */

    private String bannerid;
    private String title;
    private String image;
    private String description;
    private String addtime;
    private String extra;
    private String end_time;
    /**
     * app_banner_type : 2
     * app_banner_param : 49180
     * is_album : 0
     */

    private ExtraDataBean extra_data;

    public String getBannerid() {
        return bannerid;
    }

    public void setBannerid(String bannerid) {
        this.bannerid = bannerid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public ExtraDataBean getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(ExtraDataBean extra_data) {
        this.extra_data = extra_data;
    }

    public static class ExtraDataBean {
        private String app_banner_type;
        private String app_banner_param;
        private String is_album;

        public String getApp_banner_type() {
            return app_banner_type;
        }

        public void setApp_banner_type(String app_banner_type) {
            this.app_banner_type = app_banner_type;
        }

        public String getApp_banner_param() {
            return app_banner_param;
        }

        public void setApp_banner_param(String app_banner_param) {
            this.app_banner_param = app_banner_param;
        }

        public String getIs_album() {
            return is_album;
        }

        public void setIs_album(String is_album) {
            this.is_album = is_album;
        }
    }
}
