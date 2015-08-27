package com.dazaza.api;

import com.dazaza.model.ModelMenu;
import com.dazaza.model.ModelStory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by cunqingli on 2015/8/27.
 */
public class ApiStoryFake {

    /**
     * get hot story list
     *
     * @param category when 'null' or empty means all
     * @param startId  when <= 0 means top
     * @return
     */
    public static List<ModelStory> getStoryList(String category, int startId) {
        if (category == null) {
            category = "";
        }

        List<ModelStory> list = new ArrayList<ModelStory>();
        final int count = 20;
        for (int i = 0; i < count; i++) {
            list.add(generateModelStory());
        }

        return list;
    }

    private static ModelStory generateModelStory() {
        ModelStory model = new ModelStory();
        model.setId("100000");
        model.setTitle("标题，过长的标题会出现什么状况呢？测试一下。测试。测试。测试。测试");
        model.setCategory("design");
        model.setCategoryName("设计");
        model.setImageName("");
        model.setListThumbUrl("http://img.diglog.com/img/2015/8/thumb_03cd82ef-e123-44ea-ba8a-2cde11e46707.jpg");
        model.setNote("这是正文的测试，从目前的信息来看，飞腾仍然是在发挥超算上积累下来的强项：片间互联，因此做了一个64 Cores的芯砖——640平方毫米的Die Size加上封装，真的和一块砖一样。");
        model.setPosttime(new Date());
        model.setTags("Tag1,Tag2,Tag3");

        // random note
        final int len = model.getNote().length();
        model.setNote(model.getNote().substring(0, new Random(System.currentTimeMillis()).nextInt(len - 1)));

        return model;
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
