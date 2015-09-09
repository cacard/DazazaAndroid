package com.dazaza.html;

import com.dazaza.model.ModelStory;

/**
 * Created by cunqingli on 2015/9/9.
 */
public class HtmlBuilder {

    /**
     * 使用列表页的数据构造本地HTML。
     * 优点：离线数据也可看到HTML，而不用离线HTML本身；
     * 缺点：代码写死；
     */
    public static String buildHtml(ModelStory model) {
        if (model == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(2048);
        sb.append("<!DOCTYPE HTML>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta charset=\"utf-8\">");
        sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        sb.append("<meta content=\"telephone=no\" name=\"format-detection\">");
        sb.append("<meta name=\"format-detection\" content=\"email=no\">");
        sb.append("<title>").append(model.getTitle()).append("</title>");
        sb.append("<link href=\"./css/content.css\" type=\"text/css\" rel=\"stylesheet\">"); // using loadDataWithBaseUrl("file:///android_asset/"...)
        sb.append("</head>");

        // -------------------
        // body
        sb.append("<body>");
        sb.append("</body>");
        // body end
        // -------------------

        sb.append("</html>");

        return sb.toString();
    }

}
