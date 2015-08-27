package com.dazaza.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cunqingli on 2015/8/27.
 * for app
 */
public class ModelStory4Web implements Serializable {
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
}
