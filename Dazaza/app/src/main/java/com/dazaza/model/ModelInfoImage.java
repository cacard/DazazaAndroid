package com.dazaza.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cunqingli on 2015/9/11.
 */
public class ModelInfoImage implements Serializable, Parcelable {
    private static final long serialVersionUID = 7870149986186215424L;

    private int infoid;
    private Date posttime;
    private String note;
    private String path;
    private int id;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getInfoid() {
        return infoid;
    }

    public void setInfoid(int infoid) {
        this.infoid = infoid;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.infoid);
        dest.writeLong(posttime != null ? posttime.getTime() : -1);
        dest.writeString(this.note);
        dest.writeString(this.path);
        dest.writeInt(this.id);
        dest.writeString(this.imgUrl);
    }

    public ModelInfoImage() {
    }

    protected ModelInfoImage(Parcel in) {
        this.infoid = in.readInt();
        long tmpPosttime = in.readLong();
        this.posttime = tmpPosttime == -1 ? null : new Date(tmpPosttime);
        this.note = in.readString();
        this.path = in.readString();
        this.id = in.readInt();
        this.imgUrl = in.readString();
    }

    public static final Creator<ModelInfoImage> CREATOR = new Creator<ModelInfoImage>() {
        public ModelInfoImage createFromParcel(Parcel source) {
            return new ModelInfoImage(source);
        }

        public ModelInfoImage[] newArray(int size) {
            return new ModelInfoImage[size];
        }
    };
}
