package com.dazaza.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cunqingli on 2015/8/25.
 */
public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");

    public static String formatDate(Date d) {
        return sdf.format(d);
    }
}
