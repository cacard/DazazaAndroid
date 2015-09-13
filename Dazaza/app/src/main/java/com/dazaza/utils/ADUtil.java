package com.dazaza.utils;

/**
 * Created by cacard on 2015/9/13.
 */
public class ADUtil {

    public static String getAD() {
        StringBuilder sb = new StringBuilder();

        sb.append("<script type=\"text/javascript\">");
        sb.append("/*__Mobile_20:3 创建于 2015-09-13*/");
        sb.append("var cpro_id = \"u2309313\";");
        sb.append("</script>");
        sb.append("<script src=\"http://cpro.baidustatic.com/cpro/ui/cm.js\" type=\"text/javascript\"></script>");

        return sb.toString();
    }

}
