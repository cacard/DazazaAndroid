package com.dazaza.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cunqingli on 2015/8/25.
 * from web
 */
public class ModelStory extends ModelStory4Web implements Serializable, Comparable<ModelStory>, Parcelable {
    private static final long serialVersionUID = -3724249816264412225L;
    private String categoryName; // 分类的中文名字
    private String listThumbUrl; // 下发的缩略图url。通过下发的方式要不在App本地拼接Url更稳妥
    private String webUrl; // 下发的详情页对应的url。

    public ModelStory() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getListThumbUrl() {
        return listThumbUrl;
    }

    public void setListThumbUrl(String listThumbUrl) {
        this.listThumbUrl = listThumbUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public int compareTo(ModelStory another) {
        if (another != null) {
            if (this.getInfoId() == another.getInfoId()) {
                return 0;
            } else if (this.getInfoId() < another.getInfoId()) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.categoryName);
        dest.writeString(this.listThumbUrl);
        dest.writeString(this.webUrl);
    }

    protected ModelStory(Parcel in) {
        super(in);
        this.categoryName = in.readString();
        this.listThumbUrl = in.readString();
        this.webUrl = in.readString();
    }

    public static final Parcelable.Creator<ModelStory> CREATOR = new Parcelable.Creator<ModelStory>() {
        public ModelStory createFromParcel(Parcel source) {
            return new ModelStory(source);
        }

        public ModelStory[] newArray(int size) {
            return new ModelStory[size];
        }
    };
}
