package com.android.lala.article.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/11/25.
 * 497239511@qq.com
 */
public class BannerImageBean implements Parcelable {
    /**
     * img : http://www.lelelala.net/static/img/zhuyeai_01_12.png
     */

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "BannerImageBean{" +
                "img='" + img + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
    }

    public BannerImageBean() {
    }

    protected BannerImageBean(Parcel in) {
        this.img = in.readString();
    }

    public static final Parcelable.Creator<BannerImageBean> CREATOR = new Parcelable.Creator<BannerImageBean>() {
        @Override
        public BannerImageBean createFromParcel(Parcel source) {
            return new BannerImageBean(source);
        }

        @Override
        public BannerImageBean[] newArray(int size) {
            return new BannerImageBean[size];
        }
    };
}
