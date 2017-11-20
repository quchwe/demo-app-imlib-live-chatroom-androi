package cn.rongcloud.live.util.NormalUtil;

import java.util.regex.Pattern;

/**
 * Created by quchwe on 2017/5/1 0001.
 */

public class StringUtils {

    public static boolean isEmptyString(String string) {
        return string == null || string.equals("");
    }

    /**
     * 判断字符串是否为6位数字
     * @param str
     * @return
     */
    public static boolean isSixNumber(String str){
        String reg = "^\\d{6}$";

        return Pattern.compile(reg).matcher(str).find();
    }
}
