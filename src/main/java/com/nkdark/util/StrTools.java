package com.nkdark.util;


public class StrTools {
    private static final String IMAGE_REGEX = ".*[CQ:image,file=([^\"]*)].*";

    public static boolean isImage(String msg){
        return msg != "" && msg.matches(IMAGE_REGEX);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equalsIgnoreCase("null") || str.equals(" ");
    }

    public static int getSubCount(String string, String sub) {
        int count = 0;
        while (string.indexOf(sub) != -1) {
            count++;
            string = string.substring(string.indexOf(sub) + sub.length());
        }
        return count;
    }
}
