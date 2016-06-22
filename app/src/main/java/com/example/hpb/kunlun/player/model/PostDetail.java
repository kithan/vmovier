package com.example.hpb.kunlun.player.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public class PostDetail implements Parcelable {


    /**
     * postid : 49216
     * title : 暖心重温成人经典动画《小王子》
     * app_fu_title :
     * intro : 所有大人最初都是孩子，然而，所有的孩子终将长大，时间用最残酷的魔法，将我们变成了当初最不愿成为的那个人。
     * <p/>
     * 本片是由2015年上映的法国大电影《小王子》中截取的片段剪辑而成，去除了小女孩与飞行老爷爷那段现代社会故事线，只留取了与原著本身相关的内容。
     * <p/>
     * 原著《小王子》是法国作家安托万·德·圣·埃克苏佩里，于1942年写成的儿童文学作品，伴随了一代又一代人的成长。作家安托万具有双重身份，他既是一名飞行员，也是一位作家，在他短短44年的人生中，创造了两项别人很难企及的记录，一是创作了这部伟大作品《小王子》，据说迄今为止在全球卖掉了2亿册，二是驾驶着飞机消失在蓝天白云深处，再也没有现身，化作不朽传奇。
     * count_comment : 25
     * is_album : 0
     * is_collect : 0
     * content : {"video":[{"image":"http://cs.vmoiver.com/Uploads/cover/2016-06-03/5750f8150f6e0_cut.jpeg","title":"暖心重温成人经典动画《小王子》","duration":"1004","filesize":"311087171","source_link":"http://v.youku.com/v_show/id_XMTU5MzczNzI3Ng==.html","qiniu_url":"http://bsy.qiniu.vmovier.vmoiver.com/5750fdbae4936.mp4"}]}
     * image : http://cs.vmoiver.com/Uploads/cover/2016-06-03/5750f8150f6e0_cut.jpeg
     * rating : 9.0
     * publish_time : 1464926331
     * count_like : 1231
     * count_share : 2405
     * cate : ["动画"]
     * share_link : {"sweibo":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_sweibo","weixin":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_weixin","qzone":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_qzone","qq":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_qq"}
     * tags :
     */

    private String postid;
    private String title;
    private String app_fu_title;
    private String intro;
    private String count_comment;
    private String is_album;
    private String is_collect;
    private ContentBean content;
    private String image;
    private String rating;
    private String publish_time;
    private String count_like;
    private String count_share;
    /**
     * sweibo : http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_sweibo
     * weixin : http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_weixin
     * qzone : http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_qzone
     * qq : http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_qq
     */

    private ShareLinkBean share_link;
    private String tags;
    private List<String> cate;

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

    public String getApp_fu_title() {
        return app_fu_title;
    }

    public void setApp_fu_title(String app_fu_title) {
        this.app_fu_title = app_fu_title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCount_comment() {
        return count_comment;
    }

    public void setCount_comment(String count_comment) {
        this.count_comment = count_comment;
    }

    public String getIs_album() {
        return is_album;
    }

    public void setIs_album(String is_album) {
        this.is_album = is_album;
    }

    public String getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(String is_collect) {
        this.is_collect = is_collect;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
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

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getCount_like() {
        return count_like;
    }

    public void setCount_like(String count_like) {
        this.count_like = count_like;
    }

    public String getCount_share() {
        return count_share;
    }

    public void setCount_share(String count_share) {
        this.count_share = count_share;
    }

    public ShareLinkBean getShare_link() {
        return share_link;
    }

    public void setShare_link(ShareLinkBean share_link) {
        this.share_link = share_link;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getCate() {
        return cate;
    }

    public void setCate(List<String> cate) {
        this.cate = cate;
    }

    public static class ContentBean implements Parcelable {
        /**
         * image : http://cs.vmoiver.com/Uploads/cover/2016-06-03/5750f8150f6e0_cut.jpeg
         * title : 暖心重温成人经典动画《小王子》
         * duration : 1004
         * filesize : 311087171
         * source_link : http://v.youku.com/v_show/id_XMTU5MzczNzI3Ng==.html
         * qiniu_url : http://bsy.qiniu.vmovier.vmoiver.com/5750fdbae4936.mp4
         */

        private List<VideoInfo> video;

        public List<VideoInfo> getVideo() {
            return video;
        }

        public void setVideo(List<VideoInfo> video) {
            this.video = video;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(this.video);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.video = in.createTypedArrayList(VideoInfo.CREATOR);
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel source) {
                return new ContentBean(source);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };
    }

    public static class ShareLinkBean implements Parcelable {

        private String sweibo;
        private String weixin;
        private String qzone;
        private String qq;

        public String getSweibo() {
            return sweibo;
        }

        public void setSweibo(String sweibo) {
            this.sweibo = sweibo;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getQzone() {
            return qzone;
        }

        public void setQzone(String qzone) {
            this.qzone = qzone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sweibo);
            dest.writeString(this.weixin);
            dest.writeString(this.qzone);
            dest.writeString(this.qq);
        }

        public ShareLinkBean() {
        }

        protected ShareLinkBean(Parcel in) {
            this.sweibo = in.readString();
            this.weixin = in.readString();
            this.qzone = in.readString();
            this.qq = in.readString();
        }

        public static final Creator<ShareLinkBean> CREATOR = new Creator<ShareLinkBean>() {
            @Override
            public ShareLinkBean createFromParcel(Parcel source) {
                return new ShareLinkBean(source);
            }

            @Override
            public ShareLinkBean[] newArray(int size) {
                return new ShareLinkBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.postid);
        dest.writeString(this.title);
        dest.writeString(this.app_fu_title);
        dest.writeString(this.intro);
        dest.writeString(this.count_comment);
        dest.writeString(this.is_album);
        dest.writeString(this.is_collect);
        dest.writeParcelable(this.content, flags);
        dest.writeString(this.image);
        dest.writeString(this.rating);
        dest.writeString(this.publish_time);
        dest.writeString(this.count_like);
        dest.writeString(this.count_share);
        dest.writeParcelable(this.share_link, flags);
        dest.writeString(this.tags);
        dest.writeStringList(this.cate);
    }

    public PostDetail() {
    }

    protected PostDetail(Parcel in) {
        this.postid = in.readString();
        this.title = in.readString();
        this.app_fu_title = in.readString();
        this.intro = in.readString();
        this.count_comment = in.readString();
        this.is_album = in.readString();
        this.is_collect = in.readString();
        this.content = in.readParcelable(ContentBean.class.getClassLoader());
        this.image = in.readString();
        this.rating = in.readString();
        this.publish_time = in.readString();
        this.count_like = in.readString();
        this.count_share = in.readString();
        this.share_link = in.readParcelable(ShareLinkBean.class.getClassLoader());
        this.tags = in.readString();
        this.cate = in.createStringArrayList();
    }

    public static final Parcelable.Creator<PostDetail> CREATOR = new Parcelable.Creator<PostDetail>() {
        @Override
        public PostDetail createFromParcel(Parcel source) {
            return new PostDetail(source);
        }

        @Override
        public PostDetail[] newArray(int size) {
            return new PostDetail[size];
        }
    };
}
