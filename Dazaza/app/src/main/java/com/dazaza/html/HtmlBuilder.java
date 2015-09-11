package com.dazaza.html;

import com.dazaza.model.ModelMoreImage;
import com.dazaza.model.ModelStory;

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
        sb.append("</head>");

        // -------------------
        // body
        sb.append("<body>");
        sb.append("<div>");

        // title
        sb.append("<div class=\"title\">").append(model.getTitle()).append("</div>");

        // image
        sb.append("<div class=\"img_main_box\">")
                .append("<img src=\"").append(model.getListThumbUrl()).append("\"></img>")
                .append("</div>");

        // note
        sb.append("<div class=\"note\">").append(formatNote(model.getNote())).append("</div>");

        // more images
        final List<ModelMoreImage> moreImages = model.getMoreImages();
        if (moreImages != null && moreImages.size() > 0 ) {
            sb.append("<div class=\"more_image\">");
            for (ModelMoreImage moreImage : moreImages) {
                if (moreImage == null) {
                    continue;
                }
                sb.append("<div class=\"more_image_item\">");
                sb.append("<img src=\"").append(moreImage.getImageUrl()).append("\">");
                if (moreImage.getImageNote() != null && moreImage.getImageNote().length() > 0) {
                    sb.append("<div class=\"more_image_item_note\">").append(moreImage.getImageNote()).append("</div>");
                }
                sb.append("</div>");
            }
            sb.append("</div>");
        }

        // relate

        sb.append("</div>");
        sb.append("</body>");
        // body end
        // -------------------

        sb.append("</html>");

        return sb.toString();
    }

    private static String formatNote(String note) {
        if (note != null && note.length() > 0) {
            note = note.replace("\n","</p><p>");
        }

        return "<p>"+note+"</p>";
    }

}
