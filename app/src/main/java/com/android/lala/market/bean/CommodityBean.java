package com.android.lala.market.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/23.
 * 497239511@qq.com
 */
public class CommodityBean implements Parcelable {
    /**
     * classify : 3C数码
     * type4 :
     * type3 :
     * type2 :
     * level : 5
     * num : 0
     * type : FLIR
     * type1 :
     * showcase_img3 : http://lelelala.net/static/upload/201609211678974ec19d4566943baa4cac6b884f.webp
     * showcase_img2 : http://lelelala.net/static/upload/20160921a828ca824b944e7b91c653d7cb7eb877.webp
     * showcase_img1 : http://lelelala.net/static/upload/20160921c5f19684c7d546c8836230e391bcf7fc.webp
     * datetime : 2016-09-21 17:58:31
     * price : 5899.00
     * links : https://item.taobao.com/item.htm?spm=a219t.7900221/10.1998910419.d30ccd691.bPnD8Y&id=526536960531
     * id : 45
     * introduction : Scout TK便携式摄像机虽然无法保证让用户看到所有黑暗中的目标，但是可以保证90米以外的热感信号都可以观察，包括人类、动物或其它有热感的无生命物体。Scout TK便携式摄像机的使用很简单，只要将它放到眼睛前方，在640×480分辨率的屏幕上进行取景，然后按下按钮就可以开始使用了（短按为拍照，长按为录像）。
     * com_name :  FLIR Scout TK便携式红外热成像仪热感摄像机袖珍口袋热成像相机
     */

    private String classify;
    private String type4;
    private String type3;
    private String type2;
    private String level;
    private String num;
    private String type;
    private String type1;
    private String showcase_img3;
    private String showcase_img2;
    private String showcase_img1;
    private String datetime;
    private String price;
    private String links;
    private String id;
    private String introduction;
    private String com_name;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getType4() {
        return type4;
    }

    public void setType4(String type4) {
        this.type4 = type4;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getShowcase_img3() {
        return showcase_img3;
    }

    public void setShowcase_img3(String showcase_img3) {
        this.showcase_img3 = showcase_img3;
    }

    public String getShowcase_img2() {
        return showcase_img2;
    }

    public void setShowcase_img2(String showcase_img2) {
        this.showcase_img2 = showcase_img2;
    }

    public String getShowcase_img1() {
        return showcase_img1;
    }

    public void setShowcase_img1(String showcase_img1) {
        this.showcase_img1 = showcase_img1;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    @Override
    public String toString() {
        return "CommodityBean{" +
                "classify='" + classify + '\'' +
                ", type4='" + type4 + '\'' +
                ", type3='" + type3 + '\'' +
                ", type2='" + type2 + '\'' +
                ", level='" + level + '\'' +
                ", num='" + num + '\'' +
                ", type='" + type + '\'' +
                ", type1='" + type1 + '\'' +
                ", showcase_img3='" + showcase_img3 + '\'' +
                ", showcase_img2='" + showcase_img2 + '\'' +
                ", showcase_img1='" + showcase_img1 + '\'' +
                ", datetime='" + datetime + '\'' +
                ", price='" + price + '\'' +
                ", links='" + links + '\'' +
                ", id='" + id + '\'' +
                ", introduction='" + introduction + '\'' +
                ", com_name='" + com_name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.classify);
        dest.writeString(this.type4);
        dest.writeString(this.type3);
        dest.writeString(this.type2);
        dest.writeString(this.level);
        dest.writeString(this.num);
        dest.writeString(this.type);
        dest.writeString(this.type1);
        dest.writeString(this.showcase_img3);
        dest.writeString(this.showcase_img2);
        dest.writeString(this.showcase_img1);
        dest.writeString(this.datetime);
        dest.writeString(this.price);
        dest.writeString(this.links);
        dest.writeString(this.id);
        dest.writeString(this.introduction);
        dest.writeString(this.com_name);
    }

    public CommodityBean() {
    }

    protected CommodityBean(Parcel in) {
        this.classify = in.readString();
        this.type4 = in.readString();
        this.type3 = in.readString();
        this.type2 = in.readString();
        this.level = in.readString();
        this.num = in.readString();
        this.type = in.readString();
        this.type1 = in.readString();
        this.showcase_img3 = in.readString();
        this.showcase_img2 = in.readString();
        this.showcase_img1 = in.readString();
        this.datetime = in.readString();
        this.price = in.readString();
        this.links = in.readString();
        this.id = in.readString();
        this.introduction = in.readString();
        this.com_name = in.readString();
    }

    public static final Parcelable.Creator<CommodityBean> CREATOR = new Parcelable.Creator<CommodityBean>() {
        @Override
        public CommodityBean createFromParcel(Parcel source) {
            return new CommodityBean(source);
        }

        @Override
        public CommodityBean[] newArray(int size) {
            return new CommodityBean[size];
        }
    };
}
