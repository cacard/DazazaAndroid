package com.dazaza.runnable;

import com.dazaza.cache.StoryListCacheManager;
import com.dazaza.model.ModelStory;

import java.util.List;

/**
 * Created by cunqingli on 2015/9/11.
 */
public class RunnableSaveCache implements Runnable {

    private List<ModelStory> data ;
    private int maxInfoId;

    public RunnableSaveCache(List<ModelStory> data, int maxInfoId) {
        this.data = data;
        this.maxInfoId = maxInfoId;
    }

    @Override
    public void run() {
        StoryListCacheManager.save(data, maxInfoId);
    }
}
