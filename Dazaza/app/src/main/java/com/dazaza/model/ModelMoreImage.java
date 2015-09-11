package com.dazaza.model;

import java.io.Serializable;

/**
 * Created by cunqingli on 2015/9/11.
 */
public class ModelMoreImage implements Serializable {
    private static final long serialVersionUID = 7870149986186215424L;

    private String imageUrl;
    private String imageNote;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageNote() {
        return imageNote;
    }

    public void setImageNote(String imageNote) {
        this.imageNote = imageNote;
    }
}
