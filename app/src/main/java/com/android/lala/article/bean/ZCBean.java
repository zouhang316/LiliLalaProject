package com.android.lala.article.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/11/24.
 * 497239511@qq.com
 */
public class ZCBean implements Parcelable {
    /**
     * img_url : http://zcr3.ncfstatic.com/attachment/201609/23/12/57e4b2122ec001a_t9_640x480_thumb_534x400.jpg
     * title : yogaplus 会自己卷起来的瑜伽健身垫-众筹网
     * url : http://www.zhongchou.com/deal-show/id-554698
     */

    private String img_url;
    private String title;
    private String url;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ZCBean{" +
                "img_url='" + img_url + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img_url);
        dest.writeString(this.title);
        dest.writeString(this.url);
    }

    public ZCBean() {
    }

    protected ZCBean(Parcel in) {
        this.img_url = in.readString();
        this.title = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<ZCBean> CREATOR = new Parcelable.Creator<ZCBean>() {
        @Override
        public ZCBean createFromParcel(Parcel source) {
            return new ZCBean(source);
        }

        @Override
        public ZCBean[] newArray(int size) {
            return new ZCBean[size];
        }
    };
}
