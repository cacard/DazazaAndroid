package com.dazaza.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cunqingli on 2015/9/11.
 */
public class ModelStoryListCache implements Serializable {
    private static final long serialVersionUID = -4568365692558967769L;

    private List<ModelStory> storyList;
    private int maxInfoId;

    public List<ModelStory> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<ModelStory> storyList) {
        this.storyList = storyList;
    }

    public int getMaxInfoId() {
        return maxInfoId;
    }

    public void setMaxInfoId(int maxInfoId) {
        this.maxInfoId = maxInfoId;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    private long lastUpdateTime;

}
