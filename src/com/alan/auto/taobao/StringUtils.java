package com.alan.auto.taobao;

public class StringUtils {
    public static int indexOf(String str, String content) {
        if (isEmpty(str) || isEmpty(content)) {
            return -1;
        }
        return content.indexOf(str);
    }

    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    public static int lastIndexOf(String str, String content) {
        if (isEmpty(str) || isEmpty(content)) {
            return -1;
        }

        return content.lastIndexOf(str);
    }

    public static String lowerCase(String content) {
        if (isEmpty(content)) {
            return content;
        }
        return content.toLowerCase();
    }
}
