package com.dazaza.api;

import com.dazaza.model.ModelMenu;
import com.squareup.okhttp.Request;

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
     * @param startId  when <= 0 means top
     * @return
     */
    public static Request getRquest4StoryList(int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        return new Request.Builder().tag(pageIndex).url(ApiSetting.HTTP_API_URL_BASE + "?page=" + pageIndex).build();
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
