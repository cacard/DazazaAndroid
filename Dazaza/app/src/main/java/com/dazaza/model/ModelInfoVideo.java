package com.dazaza.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cacard on 2015/9/12.
 */
public class ModelInfoVideo implements Serializable, Parcelable {
    private static final long serialVersionUID = 7870149986186215425L;
    private int id;
    private String code;
    private Date posttime;
    private String src;
    private String type;
    private int width;
    private int height;
    private int infoid;

    //private int isgame;
    //private int isdelete;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getInfoid() {
        return infoid;
    }

    public void setInfoid(int infoid) {
        this.infoid = infoid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.code);
        dest.writeLong(posttime != null ? posttime.getTime() : -1);
        dest.writeString(this.src);
        dest.writeString(this.type);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.infoid);
    }

    public ModelInfoVideo() {
    }

    protected ModelInfoVideo(Parcel in) {
        this.id = in.readInt();
        this.code = in.readString();
        long tmpPosttime = in.readLong();
        this.posttime = tmpPosttime == -1 ? null : new Date(tmpPosttime);
        this.src = in.readString();
        this.type = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.infoid = in.readInt();
    }

    public static final Creator<ModelInfoVideo> CREATOR = new Creator<ModelInfoVideo>() {
        public ModelInfoVideo createFromParcel(Parcel source) {
            return new ModelInfoVideo(source);
        }

        public ModelInfoVideo[] newArray(int size) {
            return new ModelInfoVideo[size];
        }
    };
}
