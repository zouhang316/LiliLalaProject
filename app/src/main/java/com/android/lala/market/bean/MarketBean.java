package com.android.lala.market.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/20.
 * 497239511@qq.com
 */
public class MarketBean implements Parcelable {

    /**
     * id : 6
     * price : 111
     * showcase_img1 : http://lelelala.net/static/upload/20160810bf0c757c1ded4f4d82bd2311b75e419d.png
     * com_name : 是飒飒
     * introduction : 阿的萨达
     */

    private String id;
    private String price;
    private String showcase_img1;
    private String com_name;
    private String introduction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShowcase_img1() {
        return showcase_img1;
    }

    public void setShowcase_img1(String showcase_img1) {
        this.showcase_img1 = showcase_img1;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "MarketBean{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", showcase_img1='" + showcase_img1 + '\'' +
                ", com_name='" + com_name + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.price);
        dest.writeString(this.showcase_img1);
        dest.writeString(this.com_name);
        dest.writeString(this.introduction);
    }

    public MarketBean() {
    }

    protected MarketBean(Parcel in) {
        this.id = in.readString();
        this.price = in.readString();
        this.showcase_img1 = in.readString();
        this.com_name = in.readString();
        this.introduction = in.readString();
    }

    public static final Parcelable.Creator<MarketBean> CREATOR = new Parcelable.Creator<MarketBean>() {
        @Override
        public MarketBean createFromParcel(Parcel source) {
            return new MarketBean(source);
        }

        @Override
        public MarketBean[] newArray(int size) {
            return new MarketBean[size];
        }
    };
}
