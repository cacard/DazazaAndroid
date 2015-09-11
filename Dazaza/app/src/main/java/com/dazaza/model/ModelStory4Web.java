package com.dazaza.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cunqingli on 2015/8/27.
 * for app
 */
public class ModelStory4Web implements Serializable, Parcelable {
    private static final long serialVersionUID = -6952822566967592566L;
    protected String id;
    protected String url;
    protected String title;
    protected String name;
    protected String tags;
    protected Date posttime;
    protected String note;
    protected String imageName;
    protected int infoId;
    protected String category;
    protected int isAnchordig;
    protected int isDelete;
    protected String categoryClass;
    protected String ip;
    protected String via;

    public ModelStory4Web() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTags() {
        if (tags == null) {
            return "";
        }
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIsAnchordig() {
        return isAnchordig;
    }

    public void setIsAnchordig(int isAnchordig) {
        this.isAnchordig = isAnchordig;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getCategoryClass() {
        return categoryClass;
    }

    public void setCategoryClass(String categoryClass) {
        this.categoryClass = categoryClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
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
        dest.writeString(this.id);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.name);
        dest.writeString(this.tags);
        dest.writeLong(posttime != null ? posttime.getTime() : -1);
        dest.writeString(this.note);
        dest.writeString(this.imageName);
        dest.writeInt(this.infoId);
        dest.writeString(this.category);
        dest.writeInt(this.isAnchordig);
        dest.writeInt(this.isDelete);
        dest.writeString(this.categoryClass);
        dest.writeString(this.ip);
        dest.writeString(this.via);
    }

    protected ModelStory4Web(Parcel in) {
        this.id = in.readString();
        this.url = in.readString();
        this.title = in.readString();
        this.name = in.readString();
        this.tags = in.readString();
        long tmpPosttime = in.readLong();
        this.posttime = tmpPosttime == -1 ? null : new Date(tmpPosttime);
        this.note = in.readString();
        this.imageName = in.readString();
        this.infoId = in.readInt();
        this.category = in.readString();
        this.isAnchordig = in.readInt();
        this.isDelete = in.readInt();
        this.categoryClass = in.readString();
        this.ip = in.readString();
        this.via = in.readString();
    }

}
