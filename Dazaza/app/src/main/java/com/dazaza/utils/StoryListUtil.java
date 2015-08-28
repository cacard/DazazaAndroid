package com.dazaza.utils;

import com.dazaza.model.ModelStory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cunqingli on 2015/8/28.
 */
public class StoryListUtil {

    /**
     * Merge并去重，不保证排序
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<ModelStory> merge(List<ModelStory> list1, List<ModelStory> list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null || list1.size() == 0) {
            return list2;
        }
        if (list2 == null || list2.size() == 0) {
            return list1;
        }

        List<ModelStory> result = new ArrayList<ModelStory>();

        HashMap<String, ModelStory> hashMap1 = new HashMap<String, ModelStory>();
        if (list1 != null) {
            for (ModelStory m : list1) {
                hashMap1.put(m.getTitle(), m);
            }
        }
        HashMap<String, ModelStory> hashMap2 = new HashMap<String, ModelStory>();
        if (list2 != null) {
            for (ModelStory m : list2) {
                hashMap2.put(m.getTitle(), m);
            }
        }
        hashMap1.putAll(hashMap2);
        result.addAll(hashMap1.values());

        // TODO 按照infoid排序

        return result;
    }

}
