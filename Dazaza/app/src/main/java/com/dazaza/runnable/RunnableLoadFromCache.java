package com.dazaza.runnable;

import com.dazaza.cache.StoryListCacheManager;
import com.dazaza.model.ModelStory;
import com.dazaza.ui.MainActivity;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by cunqingli on 2015/9/11.
 */
public class RunnableLoadFromCache implements Runnable {

    private WeakReference<MainActivity> ref;

    public RunnableLoadFromCache (MainActivity activity) {
        if (activity != null) {
            ref = new WeakReference<MainActivity>(activity);
        }
    }

    @Override
    public void run() {
        if (ref == null || ref.get() == null) {
            return;
        }

        final List<ModelStory> list = StoryListCacheManager.getStoryListViaCache();
        ref.get().endLoadingDataFromCache(list);

    }
}
