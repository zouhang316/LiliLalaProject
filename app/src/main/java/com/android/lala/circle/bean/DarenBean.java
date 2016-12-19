package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/12/16.
 * 497239511@qq.com
 */
public class DarenBean implements Parcelable {
    /**
     * background : http://lelelala.net/static/upload/20161215b2b8959bb0344032878f7d5369022129.png
     * author : 夏未初﹌
     * photo : http://lelelala.net/static\ThirdLogin\changed\201611176c10d737cdda4a898b2f37398dba5206.jpg
     * clicknum : 3
     * id : 141
     * sort : 做出花样
     * title : 给你一把铁丝，你会拿它来干啥？
     */

    private String background;
    private String author;
    private String photo;
    private String clicknum;
    private String id;
    private String sort;
    private String title;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getClicknum() {
        return clicknum;
    }

    public void setClicknum(String clicknum) {
        this.clicknum = clicknum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DarenBean{" +
                "background='" + background + '\'' +
                ", author='" + author + '\'' +
                ", photo='" + photo + '\'' +
                ", clicknum='" + clicknum + '\'' +
                ", id='" + id + '\'' +
                ", sort='" + sort + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.background);
        dest.writeString(this.author);
        dest.writeString(this.photo);
        dest.writeString(this.clicknum);
        dest.writeString(this.id);
        dest.writeString(this.sort);
        dest.writeString(this.title);
    }

    public DarenBean() {
    }

    protected DarenBean(Parcel in) {
        this.background = in.readString();
        this.author = in.readString();
        this.photo = in.readString();
        this.clicknum = in.readString();
        this.id = in.readString();
        this.sort = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<DarenBean> CREATOR = new Parcelable.Creator<DarenBean>() {
        @Override
        public DarenBean createFromParcel(Parcel source) {
            return new DarenBean(source);
        }

        @Override
        public DarenBean[] newArray(int size) {
            return new DarenBean[size];
        }
    };
}
