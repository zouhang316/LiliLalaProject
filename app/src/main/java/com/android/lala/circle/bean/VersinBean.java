package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/10/9.
 * 497239511@qq.com
 */
public class VersinBean implements Parcelable {
    /**
     * datetime : 1
     * version_introduce : 初始版
     * version : 1
     */

    private String datetime;
    private String version_introduce;
    private int version;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getVersion_introduce() {
        return version_introduce;
    }

    public void setVersion_introduce(String version_introduce) {
        this.version_introduce = version_introduce;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "VersinBean{" +
                "datetime='" + datetime + '\'' +
                ", version_introduce='" + version_introduce + '\'' +
                ", version=" + version +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.datetime);
        dest.writeString(this.version_introduce);
        dest.writeInt(this.version);
    }

    public VersinBean() {
    }

    protected VersinBean(Parcel in) {
        this.datetime = in.readString();
        this.version_introduce = in.readString();
        this.version = in.readInt();
    }

    public static final Parcelable.Creator<VersinBean> CREATOR = new Parcelable.Creator<VersinBean>() {
        @Override
        public VersinBean createFromParcel(Parcel source) {
            return new VersinBean(source);
        }

        @Override
        public VersinBean[] newArray(int size) {
            return new VersinBean[size];
        }
    };
}
