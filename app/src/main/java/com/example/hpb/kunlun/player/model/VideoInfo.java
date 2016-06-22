package com.example.hpb.kunlun.player.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 0000- on 2016/6/13.
 */
public class VideoInfo implements Parcelable {

    private String image;
    private String title;
    private String duration;
    private String filesize;
    private String source_link;
    private String qiniu_url;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getSource_link() {
        return source_link;
    }

    public void setSource_link(String source_link) {
        this.source_link = source_link;
    }

    public String getQiniu_url() {
        return qiniu_url;
    }

    public void setQiniu_url(String qiniu_url) {
        this.qiniu_url = qiniu_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.title);
        dest.writeString(this.duration);
        dest.writeString(this.filesize);
        dest.writeString(this.source_link);
        dest.writeString(this.qiniu_url);
    }

    public VideoInfo() {
    }

    protected VideoInfo(Parcel in) {
        this.image = in.readString();
        this.title = in.readString();
        this.duration = in.readString();
        this.filesize = in.readString();
        this.source_link = in.readString();
        this.qiniu_url = in.readString();
    }

    public static final Parcelable.Creator<VideoInfo> CREATOR = new Parcelable.Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel source) {
            return new VideoInfo(source);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };
}
