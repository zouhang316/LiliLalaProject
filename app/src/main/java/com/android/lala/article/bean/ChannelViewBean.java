package com.android.lala.article.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ChannelViewBean implements Parcelable {

    /**
     * channels : 哩哩啦啦
     * channel_background : http://lelelala.net/static/upload/20160606445750170104495995a320f9373990f8.png
     * subscription : 16
     * id : 47
     */

    private String channels;
    private String channel_background;
    private String subscription;
    private String id;

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getChannel_background() {
        return channel_background;
    }

    public void setChannel_background(String channel_background) {
        this.channel_background = channel_background;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.channels);
        dest.writeString(this.channel_background);
        dest.writeString(this.subscription);
        dest.writeString(this.id);
    }

    public ChannelViewBean() {
    }

    protected ChannelViewBean(Parcel in) {
        this.channels = in.readString();
        this.channel_background = in.readString();
        this.subscription = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<ChannelViewBean> CREATOR = new Parcelable.Creator<ChannelViewBean>() {
        @Override
        public ChannelViewBean createFromParcel(Parcel source) {
            return new ChannelViewBean(source);
        }

        @Override
        public ChannelViewBean[] newArray(int size) {
            return new ChannelViewBean[size];
        }
    };

    @Override
    public String toString() {
        return "ChannelViewBean{" +
                "channels='" + channels + '\'' +
                ", channel_background='" + channel_background + '\'' +
                ", subscription='" + subscription + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
