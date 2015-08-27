package com.dazaza.api;

import com.dazaza.model.ModelMenu;
import com.dazaza.model.ModelStory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class ApiStory {

    /**
     * get hot story list
     *
     * @param category when 'null' or empty means all
     * @param startId when <= 0 means top
     * @return
     */
    public static List<ModelStory> getStoryList(String category, int startId) {
        if (category == null) {
            category = "";
        }

        List<ModelStory> list = new ArrayList<ModelStory>();

        return list;
    }

    /**
     * 获取下发的菜单
     *
     * @return
     */
    public static List<ModelMenu> getMenuList() {
        List<ModelMenu> list = new ArrayList<ModelMenu>();

        return list;
    }
}
