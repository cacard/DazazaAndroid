package com.dazaza.html;

import com.dazaza.model.ModelInfoImage;
import com.dazaza.model.ModelStory;
import com.dazaza.system.MyApplication;
import com.dazaza.utils.ADUtil;
import com.dazaza.utils.DateUtil;

import java.util.List;

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
        sb.append("<link href=\"./css.css\" type=\"text/css\" rel=\"stylesheet\">"); // using loadDataWithBaseUrl("file:///android_asset/"...)
        sb.append("<script src=\"./js.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
        sb.append("</head>");

        // -------------------
        // body
        sb.append("<body>");
        sb.append("<div>");

        // title
        sb.append("<div class=\"title\">").append(model.getTitle()).append("</div>");

        // date
        sb.append("<div class=\"story_date\">").append(DateUtil.formatDate(model.getPosttime())).append("</div>");

        // image
        String imgMainUrl = model.getListThumbUrl();
        if (imgMainUrl != null && imgMainUrl.length() > 0) {
            imgMainUrl = imgMainUrl.replace("thumb_", "middle_");
            sb.append("<div class=\"img_main_box\">")
                    .append("<img class=\"img_main\" " + getJsAttr4ResizeImg() + " src=\"").append(imgMainUrl).append("\"></img>")
                    .append("</div>");
        }


        // note
        sb.append("<div class=\"note\">").append(formatNote(model.getNote())).append("</div>");

        // more images
        final List<ModelInfoImage> moreImages = model.getMoreImages();
        if (moreImages != null && moreImages.size() > 0) {
            sb.append("<div class=\"more_image\">");
            for (ModelInfoImage moreImage : moreImages) {
                if (moreImage == null) {
                    continue;
                }
                sb.append("<div class=\"more_image_item\">");
                sb.append("<img class=\"img_more\" " + getJsAttr4ResizeImg() + " src=\"").append(moreImage.getImgUrl()).append("\">");
                if (moreImage.getNote() != null && moreImage.getNote().length() > 0) {
                    sb.append("<div class=\"more_image_item_note\">").append(moreImage.getNote()).append("</div>");
                }
                sb.append("</div>");
            }
            sb.append("</div>");
        }

        // relate

        //sb.append(ADUtil.getAD());

        sb.append("</div>");
        sb.append("</body>");
        // body end
        // -------------------


        // app js
        sb.append("<script src=\"http://img.diglog.com/static/app.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");

        sb.append("</html>");

        return sb.toString();
    }

    private static String formatNote(String note) {
        if (note != null && note.length() > 0) {
            note = note.replace("\n", "</p><p>");
        }

        return "<p>" + note + "</p>";
    }

    private static String getJsAttr4ResizeImg() {
        final int diff = 40;

        return "onload=\"javascript:DrawImage(this,'"
                + (MyApplication.screenWidth - diff) + "','"
                + MyApplication.screenHeight + "');\"";
    }

}
