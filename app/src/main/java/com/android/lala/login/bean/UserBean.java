package com.android.lala.login.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author sxshi on 2016/7/27.
 * @email:emotiona_xiaoshi@126.com
 * @describe:Describe the function  of the current class
 */
public class UserBean implements Parcelable {

    /**
     * channels : null
     * username : 15576310006
     * photo :
     * password : E5920BAA1A70598CAB8A81074533F581
     * userId : 111
     * name : 哩哩啦啦22
     */

    private Object channels;
    private String username;
    private String photo;
    private String password;
    private String userId;
    private String name;

    public Object getChannels() {
        return channels;
    }

    public void setChannels(Object channels) {
        this.channels = channels;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "channels=" + channels +
                ", username='" + username + '\'' +
                ", photo='" + photo + '\'' +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.channels, flags);
        dest.writeString(this.username);
        dest.writeString(this.photo);
        dest.writeString(this.password);
        dest.writeString(this.userId);
        dest.writeString(this.name);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.channels = in.readParcelable(Object.class.getClassLoader());
        this.username = in.readString();
        this.photo = in.readString();
        this.password = in.readString();
        this.userId = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
