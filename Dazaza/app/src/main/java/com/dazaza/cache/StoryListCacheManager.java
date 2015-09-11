package com.dazaza.cache;

import android.util.Log;

import com.dazaza.model.ModelStory;
import com.dazaza.model.ModelStoryListCache;
import com.dazaza.system.MyApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.Date;
import java.util.List;

import cn.trinea.android.common.util.FileUtils;

/**
 * Created by cunqingli on 2015/8/26.
 * 冗余地存储各个列表
 */
public class StoryListCacheManager {

    private static final String TAG = StoryListCacheManager.class.getSimpleName();
    private static ModelStoryListCache cache;
    private static byte[] lock4Cache = new byte[0];
    private static byte[] lock4Dir = new byte[0];

    public static List<ModelStory> getStoryListViaCache() {
        // from cache
        if (cache != null && cache.getStoryList() != null) {
            return cache.getStoryList();
        }

        // from file
        final ModelStoryListCache _cache = getCacheFromFile();
        if (_cache != null && _cache.getStoryList() != null && _cache.getStoryList().size() > 0) {
            cache = _cache;
            return cache.getStoryList();
        }

        return null;
    }

    /**
     * 保存，会自动检测有没有必要更新到文件
     * @param listStory
     * @param maxInfoId
     */
    public static void save(List<ModelStory> listStory, int maxInfoId) {
        if (listStory == null || listStory.size() == 0) {
            return;
        }

        // need update cache?
        final boolean updateCache = isNeedUpdateCache(listStory, maxInfoId);
        if (updateCache) {
            final ModelStoryListCache _cache = new ModelStoryListCache();
            _cache.setLastUpdateTime(new Date().getTime());
            _cache.setMaxInfoId(maxInfoId);
            _cache.setStoryList(listStory);
            cache = _cache;
        }

        // update file
        if (updateCache) {
            save2File(cache);
        }
    }

    public static ModelStoryListCache getCacheFromFile() {
        ModelStoryListCache cacheFromFile = new ModelStoryListCache();

        synchronized (lock4Dir) {
            String json = "";
            try {
                StringBuilder sb = FileUtils.readFile(getFilePath(), "UTF-8");
                if (sb != null) {
                    json = sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable t) {
                t.printStackTrace();
            }

            if (json != null && json.length() > 0) {
                try {
                    cacheFromFile = new Gson().fromJson(json, new TypeToken<ModelStoryListCache>() {
                    }.getType());
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }

        return cacheFromFile;
    }

    public static void save2File(ModelStoryListCache model) {
        if (model == null || model.getStoryList() == null || model.getStoryList().size() == 0) {
            return;
        }

        String json = "";
        try {
            json = new Gson().toJson(cache);
            if (json != null && json.length() > 0) {
                synchronized (lock4Dir) {
                    FileUtils.writeFile(getFilePath(), json, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static boolean isNeedUpdateCache(List<ModelStory> listStory, int maxInfoId) {
        if (cache == null) {
            return true;
        }

        if (cache.getMaxInfoId() != maxInfoId) {
            return true;
        }

        if (listStory.size() != cache.getStoryList().size()) {
            return true;
        }

        return false;
    }


    private static String getFilePath() {
        return MyApplication.storageExternalPath + File.separator + "story_list_cache";
    }

    private void log(String msg) {
        Log.i(TAG, msg);
    }

}
