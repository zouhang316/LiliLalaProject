package com.android.lala.login.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author sxshi on 2016/7/27.
 * @email:emotiona_xiaoshi@126.com
 * @describe:Describe the function  of the current class
 */
public class UserBean implements Parcelable {
    private int userId;
    private String password;// '登录密码',
    private String name;//'姓名',
    private String photo;//头像'

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.password);
        dest.writeString(this.name);
        dest.writeString(this.photo);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.userId = in.readInt();
        this.password = in.readString();
        this.name = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
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
